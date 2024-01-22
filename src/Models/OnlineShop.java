package Models;

import Models.User.AbstractUser;
import Models.User.AdminUser;
import Models.User.RegularUser;

import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import java.util.Map;

public class OnlineShop {
    private Map<Integer, AbstractUser> _users;
    private Map<String, Product> _products;
    private List<Order> _orderHistory;

    public OnlineShop() {
        _users = new HashMap<>();
        _products = new HashMap<>();
        _orderHistory = new ArrayList<>();
    }

    public void addProduct(Product product) {
        _products.put(product.getName(), product);
    }

    public void displayProducts() {
        for (Product product : _products.values()) {
            System.out.println(product);
        }
    }

    public AbstractUser getUserById(int userId) {
        return _users.get(userId);
    }

    public void displayUsers(int userId) {
        AbstractUser requestingUser = _users.get(userId);
        if (!(requestingUser instanceof AdminUser)) {
            System.out.println("Only admin users can view the list of users.");
            return;
        }

        for (AbstractUser user : _users.values()) {
            System.out.println(user);
        }
    }


    public void buyProduct(int userId, String productName, int quantity) {
        AbstractUser user = _users.get(userId);
        if (!(user instanceof RegularUser)) {
            System.out.println("Only regular users can buy products.");
            return;
        }

        RegularUser regularUser = (RegularUser) user;
        Product product = _products.get(productName);

        if (product != null && product.getQuantity() >= quantity) {
            double totalCost = quantity * product.getPrice();

            if (regularUser.getSubscriptionType() == SubscriptionType.PREMIUM) {
                totalCost *= 0.9;
            }

            if (regularUser.getBalance() >= totalCost) {
                regularUser.updateBalance(-totalCost);
                product.adjustQuantity(-quantity);
                Order order = new Order(userId, productName, quantity, totalCost);
                regularUser.addOrder(order);
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
        AbstractUser user = _users.get(userId);
        if (!(user instanceof RegularUser)) {
            System.out.println("Only regular users can return products.");
            return false;
        }

        RegularUser regularUser = (RegularUser) user;
        if (regularUser == null) {
            System.out.println("User not found.");
            return false;
        }

        Order orderToReturn = null;
        for (Order order : regularUser.getOrders()) {
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

        regularUser.updateBalance(orderToReturn.getTotalSum());
        product.adjustQuantity(quantity);

        if (orderToReturn.getQuantity() == quantity) {
            regularUser.getOrders().remove(orderToReturn);
        } else {
            orderToReturn.setQuantity(orderToReturn.getQuantity() - quantity);
            orderToReturn.updateTotalSum(product.getPrice());
        }
        return true;
    }


    public void displayUserOrders(int userId) {
        AbstractUser user = _users.get(userId);
        if (!(user instanceof AdminUser)) {
            System.out.println("Only admin users can view order history.");
            return;
        }

        AdminUser adminUser = (AdminUser) user;
        adminUser.displayOrderHistory(_orderHistory);
    }

    public int addRegularUser(String name, double balance) {
        RegularUser user = new RegularUser(name, balance);
        var id = user.getId();
        _users.put(user.getId(), user);
        return id;
    }

    public int addAdminUser(String name) {
        AdminUser user = new AdminUser(name);
        var id = user.getId();
        _users.put(id, user);
        return id;
    }

    public void applyDiscount(int adminUserId, int regularUserId, double discountPercentage) {
        AbstractUser adminUser = _users.get(adminUserId);
        if (!(adminUser instanceof AdminUser)) {
            System.out.println("Only admin users can apply discounts.");
            return;
        }

        AbstractUser regularUser = _users.get(regularUserId);
        if (!(regularUser instanceof RegularUser)) {
            System.out.println("Discount can only be applied to regular users.");
            return;
        }

        RegularUser targetUser = (RegularUser) regularUser;
        targetUser.applyDiscount(discountPercentage);
    }
}

