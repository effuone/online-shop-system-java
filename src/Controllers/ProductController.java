package Controllers;

import Models.Product;
import Repositories.ProductRepository;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class ProductController {
    private ProductRepository productRepository;
    private Scanner scanner;

    public ProductController(Connection connection) {
        this.productRepository = new ProductRepository(connection);
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("\nProduct Management System");
            System.out.println("1. Add Product");
            System.out.println("2. List All Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    listAllProducts();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting Product Management System...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter product quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter product description: ");
        String description = scanner.nextLine();

        Product product = new Product(name, price, quantity, description);
        int productId = productRepository.add(product);
        System.out.println("Product added with ID: " + productId);
    }

    private void listAllProducts() {
        List<Product> products = productRepository.getAll();
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    private void updateProduct() {
        System.out.println("Update product functionality is not implemented yet.");
    }

    private void deleteProduct() {
        System.out.print("Enter product ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        productRepository.delete(id);
        System.out.println("Product deleted successfully.");
    }
}
