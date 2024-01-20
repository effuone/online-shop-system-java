import Models.OnlineShop;
import Models.Product;
import Models.User;

import java.util.Scanner;

public class Main {
    private static OnlineShop _onlineShop = new OnlineShop();
    private static Scanner _scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nHello! You have the following available functions:");
            System.out.println("1) To show products list;");
            System.out.println("2) To add a product;");
            System.out.println("3) To add a new user;");
            System.out.println("4) To buy product;");
            System.out.println("5) To return a product;");
            System.out.println("6) To show all users;");
            System.out.println("7) To show the certain user’s orders;");
            System.out.println("8) Exit.");

            System.out.print("Please select an option: ");
            int choice = _scanner.nextInt();
            _scanner.nextLine();

            switch (choice) {
                case 1:
                    _onlineShop.displayProducts();
                    break;
                case 2:
                    addProductToShop();
                    break;
                case 3:
                    addUserToShop();
                    break;
                case 4:
                    purchaseProduct();
                    break;
                case 5:
                    returnProduct();
                    break;
                case 6:
                    _onlineShop.displayUsers();
                    break;
                case 7:
                    showUserOrders();
                    break;
                case 8:
                    System.out.println("Ulken rakhmet for using the Online Shopping system. Dauay!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        _scanner.close();
    }

    private static void addProductToShop() {
        System.out.println("Enter product name:");
        String name = _scanner.nextLine();
        System.out.println("Enter product price:");
        double price = _scanner.nextDouble();
        System.out.println("Enter product quantity:");
        int quantity = _scanner.nextInt();
        _scanner.nextLine();
        System.out.println("Enter product description:");
        String description = _scanner.nextLine();

        Product product = new Product(name, price, quantity, description);
        _onlineShop.addProduct(product);
        System.out.println("Product added successfully!");
    }

    private static void addUserToShop() {
        System.out.println("Enter user ID:");
        int id = _scanner.nextInt();
        _scanner.nextLine();
        System.out.println("Enter user name:");
        String name = _scanner.nextLine();
        System.out.println("Enter user balance:");
        double balance = _scanner.nextDouble();
        _scanner.nextLine();

        User user = new User(id, name, balance);
        _onlineShop.addUser(user);
        System.out.println("User added successfully!");
    }

    private static void purchaseProduct() {
        System.out.println("Enter user ID:");
        int userId = _scanner.nextInt();
        _scanner.nextLine();
        System.out.println("Enter product name:");
        String productName = _scanner.nextLine();
        System.out.println("Enter quantity:");
        int quantity = _scanner.nextInt();

        _onlineShop.buyProduct(userId, productName, quantity);
    }

    private static void returnProduct() {
        System.out.println("Enter user ID for the return:");
        int userId = _scanner.nextInt();
        _scanner.nextLine();
        System.out.println("Enter product name to return:");
        String productName = _scanner.nextLine();
        System.out.println("Enter the quantity to return:");
        int quantity = _scanner.nextInt();

        boolean success = _onlineShop.returnProduct(userId, productName, quantity);
        if (success) {
            System.out.println("Product returned successfully!");
        } else {
            System.out.println("Product return failed. Please check the details and try again.");
        }
    }


    private static void showUserOrders() {
        System.out.println("Enter user ID:");
        int userId = _scanner.nextInt();

        _onlineShop.displayUserOrders(userId);
    }
}
