package Models.User;

import Models.Order;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractUser {
    private static int lastId = 0;
    protected int _id;
    protected String _name;
    protected double _balance;
    protected List<Order> _orders;
    public AbstractUser(String name, double balance) {
        this._id = ++lastId;
        this._name = name;
        this._balance = balance;
        this._orders = new ArrayList<>();
    }
    public AbstractUser(){
        this._id = ++lastId;
        this._orders = new ArrayList<>();
    }
    public abstract int getId();
}
