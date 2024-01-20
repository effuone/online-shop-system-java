package Models;

import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import java.util.Map;

public class OnlineShop {
    private Map<Integer, User> _users;
    private Map<String, Product> _products;
    private List<Order> _orderHistory;

    public OnlineShop() {
        _users = new HashMap<>();
        _products = new HashMap<>();
        _orderHistory = new ArrayList<>();
    }

    public void addUser(User user) {
        _users.put(user.getId(), user);
    }

    public void addProduct(Product product) {
        _products.put(product.getName(), product);
    }

    public void displayProducts() {
        for (Product product : _products.values()) {
            System.out.println(product);
        }
    }

    public void displayUsers() {
        for (User user : _users.values()) {
            System.out.println(user);
        }
    }

    public void buyProduct(int userId, String productName, int quantity) {
        User user = _users.get(userId);
        Product product = _products.get(productName);

        if (user != null && product != null && product.getQuantity() >= quantity) {
            double totalCost = quantity * product.getPrice();
            if (user.getBalance() >= totalCost) {
                user.updateBalance(-totalCost);
                product.adjustQuantity(-quantity);
                Order order = new Order(userId, productName, quantity, totalCost);
                user.addOrder(order);
                _orderHistory.add(order);
                System.out.println("Purchase successful!");
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Product not available or quantity too high.");
        }
    }

    public boolean returnProduct(int userId, String productName, int quantity) {
        User user = _users.get(userId);
        if (user == null) {
            System.out.println("User not found.");
            return false;
        }

        Order orderToReturn = null;
        for (Order order : user.getOrders()) {
            if (order.getProductName().equals(productName) && order.getQuantity() >= quantity) {
                orderToReturn = order;
                break;
            }
        }

        if (orderToReturn == null) {
            System.out.println("Order not found or quantity higher than ordered.");
            return false;
        }

        Product product = _products.get(productName);
        if (product == null) {
            System.out.println("Product not found.");
            return false;
        }

        user.updateBalance(orderToReturn.getTotalSum());
        product.adjustQuantity(quantity);

        if (orderToReturn.getQuantity() == quantity) {
            user.getOrders().remove(orderToReturn);
        } else {
            orderToReturn.setQuantity(orderToReturn.getQuantity() - quantity);
            orderToReturn.updateTotalSum(product.getPrice());
        }
        return true;
    }


    public void displayUserOrders(int userId) {
        User user = _users.get(userId);
        if (user != null) {
            List<Order> orders = user.getOrders();
            if (orders.isEmpty()) {
                System.out.println("User has no orders.");
            } else {
                for (Order order : orders) {
                    System.out.println(order);
                }
            }
        } else {
            System.out.println("User not found.");
        }
    }

    public void updateProductDetails(String productName, Double price, Integer quantity, String description) {
        Product product = _products.get(productName);
        if (product != null) {
            if (price != null) {
                product.setPrice(price);
            }
            if (quantity != null) {
                product.setQuantity(quantity);
            }
            if (description != null && !description.isEmpty()) {
                product.setDescription(description);
            }
            System.out.println("Product details updated successfully!");
        } else {
            System.out.println("Product not found.");
        }
    }

    public void updateUserDetails(int userId, String name, Double balance) {
        User user = _users.get(userId);
        if (user != null) {
            if (name != null && !name.isEmpty()) {
                user.setName(name);
            }
            if (balance != null) {
                user.setBalance(balance);
            }
            System.out.println("User details updated successfully!");
        } else {
            System.out.println("User not found.");
        }
    }

    public Product getProductByName(String productName) {
        return _products.get(productName);
    }

    public User getUserById(int userId) {
        return _users.get(userId);
    }

}

