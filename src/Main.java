import Models.OnlineShop;
import Models.Product;
import Models.User.AdminUser;
import Models.User.RegularUser;

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
            System.out.println("8) To apply discount to user;");
            System.out.println("9) Exit.");

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
                    displayUsers();
                    break;
                case 7:
                    showUserOrders();
                    break;
                case 8:
                    applyDiscountForUser();
                    break;
                case 9:
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
        System.out.print("Enter product name: ");
        String name = _scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = _scanner.nextDouble();
        System.out.print("Enter product quantity: ");
        int quantity = _scanner.nextInt();
        System.out.print("Enter product description: ");
        String description = _scanner.nextLine();
        _scanner.nextLine();
        Product product = new Product(name, price, quantity, description);
        _onlineShop.addProduct(product);
        System.out.println("Product added successfully.");
    }

    private static void addUserToShop() {
        System.out.print("Enter user name: ");
        String name = _scanner.nextLine();

        System.out.print("Is the user an admin? (yes/no): ");
        String isAdmin = _scanner.nextLine();

        if ("yes".equalsIgnoreCase(isAdmin)) {
            var passId = _onlineShop.addAdminUser(name);
            System.out.printf("Your secret id is %d. Please remember it", passId);


        } else {
            System.out.print("Enter user balance: ");
            double balance = _scanner.nextDouble();
            _scanner.nextLine();
            var passId = _onlineShop.addRegularUser(name, balance);
            System.out.printf("Your secret id is %d. Please remember it", passId);

        }

        System.out.println("User added successfully.");
    }

    private static void purchaseProduct() {
        System.out.print("Enter user ID: ");
        int userId = _scanner.nextInt();
        _scanner.nextLine();
        System.out.print("Enter product name: ");
        String productName = _scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = _scanner.nextInt();
        _scanner.nextLine();

        _onlineShop.buyProduct(userId, productName, quantity);
    }

    private static void returnProduct() {
        System.out.print("Enter user ID: ");
        int userId = _scanner.nextInt();
        _scanner.nextLine();
        System.out.print("Enter product name: ");
        String productName = _scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = _scanner.nextInt();
        _scanner.nextLine();

        boolean success = _onlineShop.returnProduct(userId, productName, quantity);
        if (success) {
            System.out.println("Product returned successfully.");
        } else {
            System.out.println("Failed to return product.");
        }
    }

    private static void displayUsers() {
        System.out.print("Enter admin user ID to display users: ");
        int adminUserId = _scanner.nextInt();
        _scanner.nextLine();

        _onlineShop.displayUsers(adminUserId);
    }

    private static void showUserOrders() {
        System.out.print("Enter user ID to display orders: ");
        int userId = _scanner.nextInt();
        _scanner.nextLine();

        _onlineShop.displayUserOrders(userId);
    }

    private static void applyDiscountForUser() {
        System.out.print("Enter admin user ID to apply discount for someone: ");
        int adminUserId = _scanner.nextInt();
        var g = _onlineShop.getUserById(adminUserId);
        if(g instanceof AdminUser) {
            System.out.print("Enter user ID to apply discount for: ");
            int regularUserId = _scanner.nextInt();
            if(_onlineShop.getUserById(regularUserId) instanceof RegularUser)
            {
                System.out.print("Enter discount percentage: ");
                int discountPercentage = _scanner.nextInt();
                _onlineShop.applyDiscount(adminUserId, regularUserId, discountPercentage);
            }
            else {
                System.out.print("User not found, try again: ");
            };
        } else {
            System.out.print("You have no permission to apply discount as an user: ");
        }
    }
}
