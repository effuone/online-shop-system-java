package Models;
public class Order {
    private int _userId;
    private String _productName;
    private int _quantity;
    private double _totalSum;

    public Order(int userId, String productName, int quantity, double totalSum) {
        this._userId = userId;
        this._productName = productName;
        this._quantity = quantity;
        this._totalSum = totalSum;
    }

    public int getUserId() {
        return _userId;
    }

    public String getProductName() {
        return _productName;
    }

    public int getQuantity() {
        return _quantity;
    }

    public double getTotalSum() {
        return _totalSum;
    }

    public void setUserId(int userId) {
        this._userId = userId;
    }

    public void setProductName(String productName) {
        this._productName = productName;
    }

    public void setQuantity(int quantity) {
        if(quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        this._quantity = quantity;
    }

    public void setTotalSum(double totalSum) {
        if(totalSum < 0) {
            throw new IllegalArgumentException("Total sum cannot be negative.");
        }
        this._totalSum = totalSum;
    }

    public void updateTotalSum(double pricePerUnit) {
        this._totalSum = this._quantity * pricePerUnit;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId=" + _userId +
                ", productName='" + _productName + '\'' +
                ", quantity=" + _quantity +
                ", totalSum=" + _totalSum +
                '}';
    }
}
