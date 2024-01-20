package Models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int _id;
    private String _name;
    private double _balance;
    private List<Order> _orders;

    public User(int id, String name, double balance) {
        this._id = id;
        this._name = name;
        this._balance = balance;
        this._orders = new ArrayList<>();
    }

    public int getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public double getBalance() {
        return _balance;
    }

    public List<Order> getOrders() {
        return _orders;
    }

    public void setId(int id) {
        this._id = id;
    }

    public void setName(String name) {
        this._name = name;
    }

    public void setBalance(double balance) {
        if(balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        this._balance = balance;
    }

    public void addOrder(Order order) {
        _orders.add(order);
    }

    public void removeOrder(Order order) {
        _orders.remove(order);
    }

    public void updateBalance(double amount) {
        double newBalance = this._balance + amount;
        if(newBalance < 0) {
            throw new IllegalArgumentException("Insufficient balance for this operation.");
        }
        this._balance = newBalance;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + _id +
                ", name='" + _name + '\'' +
                ", balance=" + _balance +
                ", orders=" + _orders +
                '}';
    }
}

