package Models.User;

import Models.Order;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractUser {
    protected int _id;
    protected String _name;
    protected double _balance;
    protected List<Order> _orders;

    public AbstractUser(String name, double balance) {
        this._name = name;
        this._balance = balance;
        this._orders = new ArrayList<>();
    }

    public AbstractUser() {
        this._orders = new ArrayList<>();
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public String getName() {
        return _name;
    }

    public double getBalance() {
        return _balance;
    }
}
