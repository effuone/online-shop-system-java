package Models;


public class Product {
    private String _name;
    private double _price;
    private int _quantity;
    private String _description;

    public Product(String name, double price, int quantity, String description) {
        this._name = name;
        this._price = price;
        this._quantity = quantity;
        this._description = description;
    }

    public String getName() {
        return _name;
    }

    public double getPrice() {
        return _price;
    }

    public int getQuantity() {
        return _quantity;
    }

    public String getDescription() {
        return _description;
    }

    public void setName(String name) {
        this._name = name;
    }

    public void setPrice(double price) {
        if(price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this._price = price;
    }

    public void setQuantity(int quantity) {
        if(quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        this._quantity = quantity;
    }

    public void setDescription(String description) {
        this._description = description;
    }

    public void adjustQuantity(int amount) {
        int newQuantity = this._quantity + amount;
        if(newQuantity < 0) {
            throw new IllegalArgumentException("Not enough stock for this product.");
        }
        this._quantity = newQuantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + _name + '\'' +
                ", price=" + _price +
                ", quantity=" + _quantity +
                ", description='" + _description + '\'' +
                '}';
    }
}

