package Models.User;

import Models.Order;
import Models.SubscriptionType;

import java.util.List;

public class RegularUser extends AbstractUser {
    private SubscriptionType _subType;

    public RegularUser(String name, double balance) {
        super(name, balance);
        this._subType = SubscriptionType.ORDINARY;
    }

    public void setNewSubscriptionType(SubscriptionType subType) {
        this._subType = subType;
    }

    @Override
    public int getId() {
        return this._id;
    }

    public double getBalance() {
        return _balance;
    }

    public SubscriptionType getSubscriptionType() {
        return _subType;
    }

    public List<Order> getOrders() {
        return _orders;
    }

    public void setName(String name) {
        this._name = name;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        this._balance = balance;
    }

    public void addOrder(Order order) {
        _orders.add(order);
    }

    public void updateBalance(double amount) {
        double newBalance = this._balance + amount;
        if (newBalance < 0) {
            throw new IllegalArgumentException("Insufficient balance for this operation.");
        }
        this._balance = newBalance;
    }

    public void applyDiscount(double discountPercentage) {
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100.");
        }
        double discountAmount = this._balance * (discountPercentage / 100.0);
        this._balance += discountAmount;
        System.out.println("A discount of " + discountPercentage + "% has been applied. New balance: " + this._balance);
    }

    @Override
    public String toString() {
        return "RegularUser{" +
                "id=" + _id +
                ", name='" + _name + '\'' +
                ", balance=" + _balance +
                ", orders=" + _orders +
                '}';
    }
}
