package Models.User;

import Models.Order;
import java.util.List;

public class AdminUser extends AbstractUser {

    public AdminUser(String name) {
        super(name, 0);
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

    @Override
    public String toString() {
        return "AdminUser{" +
                "_id=" + _id +
                ", _name='" + _name + '\'' +
                ", _balance=" + _balance +
                ", _orders=" + _orders +
                '}';
    }
}
