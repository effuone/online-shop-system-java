package Models.User;

import Models.Order;
import java.util.List;

public class AdminUser extends AbstractUser {

    public AdminUser(String name) {
        this._name = name;
    }

    @Override
    public int getId() {
        return this._id;
    }

    public void displayOrderHistory(List<Order> orderHistory) {
        if (orderHistory.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (Order order : orderHistory) {
                System.out.println(order);
            }
        }
    }
}
