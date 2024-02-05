package Repositories;

import Models.User.AbstractUser;
import Models.User.AdminUser;
import Models.User.RegularUser;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IRepository<AbstractUser> {

    private Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int add(AbstractUser entity) {
        String sql = "INSERT INTO users (name, balance, type, subscription_type) VALUES (?, ?, ?, ?)";
        int generatedId = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setDouble(2, entity.getBalance());
            preparedStatement.setString(3, entity instanceof AdminUser ? "admin" : "regular");
            preparedStatement.setString(4, entity instanceof RegularUser ? ((RegularUser) entity).getSubscriptionType().toString() : null);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return generatedId;
    }

    @Override
    public List<AbstractUser> getAll() {
        List<AbstractUser> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                AbstractUser user = createUserFromResultSet(resultSet);
                if (user != null) {
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return users;
    }

    @Override
    public AbstractUser getById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        AbstractUser user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = createUserFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return user;
    }

    @Override
    public void update(AbstractUser entity) {
        String sql = "UPDATE users SET name = ?, balance = ?, type = ?, subscription_type = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setDouble(2, entity.getBalance());
            preparedStatement.setString(3, entity instanceof AdminUser ? "admin" : "regular");
            preparedStatement.setString(4, entity instanceof RegularUser ? ((RegularUser) entity).getSubscriptionType().toString() : null);
            preparedStatement.setInt(5, entity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    private AbstractUser createUserFromResultSet(ResultSet resultSet) throws SQLException {
        String type = resultSet.getString("type");
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        double balance = resultSet.getDouble("balance");
        if ("admin".equals(type)) {
            AdminUser user = new AdminUser(name);
            user.setId(id);
            return user;
        } else if ("regular".equals(type)) {
            RegularUser user = new RegularUser(name, balance);
            user.setId(id);
            return user;
        }
        return null;
    }
}
