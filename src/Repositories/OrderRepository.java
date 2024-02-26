package Repositories;

import Models.Order;
import Models.OrderItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements IRepository<Order> {

    private final Connection connection;

    public OrderRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int add(Order entity) {
        String orderSql = "INSERT INTO orders (user_id, order_date, status) VALUES (?, ?, ?)";
        int orderId = 0;
        try (PreparedStatement orderStatement = connection.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)) {
            orderStatement.setInt(1, entity.getUserId());
            orderStatement.setTimestamp(2, Timestamp.valueOf(entity.getOrderDate()));
            orderStatement.setString(3, entity.getStatus());

            int affectedRows = orderStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = orderStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        orderId = generatedKeys.getInt(1);
                    }
                }

                if (!entity.getOrderItems().isEmpty()) {
                    String itemSql = "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)";
                    for (OrderItem item : entity.getOrderItems()) {
                        try (PreparedStatement itemStatement = connection.prepareStatement(itemSql)) {
                            itemStatement.setInt(1, orderId);
                            itemStatement.setInt(2, item.getProductId());
                            itemStatement.setInt(3, item.getQuantity());
                            itemStatement.executeUpdate();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return orderId;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Order order = createOrderFromResultSet(resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return orders;
    }

    @Override
    public Order getById(int id) {
        Order order = null;
        String sql = "SELECT * FROM orders WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    order = createOrderFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return order;
    }

    @Override
    public void update(Order entity) {
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM orders WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    private Order createOrderFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("user_id");
        Timestamp orderDate = resultSet.getTimestamp("order_date");
        String status = resultSet.getString("status");

        Order order = new Order(userId, orderDate.toLocalDateTime(), status);
        order.setId(id);

        return order;
    }
}
