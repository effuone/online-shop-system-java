import Controllers.UserController;
import Controllers.ProductController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static Scanner _scanner = new Scanner(System.in);

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/kaskr-db";
    private static final String USERNAME = "test";
    private static final String PASSWORD = "test";

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection connection = null;
        boolean isRunning = true;

        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            while (isRunning) {
                System.out.println("\nMain Menu:");
                System.out.println("1) Enter user management system");
                System.out.println("2) Enter product management system");
                System.out.println("3) Exit");

                System.out.print("Please select an option: ");
                int choice = _scanner.nextInt();
                _scanner.nextLine();

                switch (choice) {
                    case 1:
                        UserController userController = new UserController(connection);
                        userController.run();
                        break;
                    case 2:
                        ProductController productController = new ProductController(connection);
                        productController.run();
                        break;
                    case 3:
                        isRunning = false;
                        System.out.println("Exiting the application...");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace(System.out);
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close the database connection.");
                e.printStackTrace(System.out);
            }
        }

        _scanner.close();
    }
}
