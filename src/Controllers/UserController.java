package Controllers;

import Models.User.AbstractUser;
import Models.User.AdminUser;
import Models.User.RegularUser;
import Repositories.UserRepository;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class UserController {
    private final UserRepository userRepository;
    private final Scanner scanner;

    public UserController(Connection connection) {
        this.userRepository = new UserRepository(connection);
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("\nUser Management System");
            System.out.println("1. Add User");
            System.out.println("2. Get User by ID");
            System.out.println("3. Get all Users");
            System.out.println("4. Update User");
            System.out.println("5. Delete User");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    getUserById();
                    break;
                case 3:
                    getAllUsers();
                    break;
                case 4:
                    updateUser();
                    break;
                case 5:
                    deleteUser();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting User Management System...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addUser() {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();

        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Is this an admin user? (yes/no): ");
        String isAdmin = scanner.nextLine();

        AbstractUser user;
        if ("yes".equalsIgnoreCase(isAdmin)) {
            user = new AdminUser(name);
        } else {
            user = new RegularUser(name, balance);
        }

        int userId = userRepository.add(user);
        System.out.println("User added with ID: " + userId);
    }

    private void getUserById() {
        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        AbstractUser user = userRepository.getById(id);
        if (user != null) {
            System.out.println("User Found: " + user);
        } else {
            System.out.println("User not found.");
        }
    }

    private void getAllUsers() {
        List<AbstractUser> users = userRepository.getAll();
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            for (AbstractUser user : users) {
                System.out.println(user);
            }
        }
    }
    private void updateUser() {
        System.out.println("Update user functionality is not implemented yet.");
    }

    private void deleteUser() {
        System.out.print("Enter user ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        userRepository.delete(id);
        System.out.println("User deleted successfully.");
    }
}
