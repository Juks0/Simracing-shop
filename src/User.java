import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class User extends Person {
    private double Balance;
    protected List<Order> orders = new ArrayList<>();
    protected List<Returned_Order> returnedOrders = new ArrayList<>();

    public User(String login, String email, String password, Date dateOfBirth, String address) {
        super(login, email, password, dateOfBirth, address);
        setBalance(0);
    }
    public void placeOrder() {
        if (getCart().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }
        HashMap<Product, Integer> cart = getCart();
        double totalPrice = 0;
        for (Product product : cart.keySet()) {
            if (product.getDiscount() != null) {
                totalPrice -= (product.getPrice() * (100-product.getDiscount().getPercentage()))/100;
            } else {
                totalPrice -= product.getPrice();
            }
            int quantity = cart.get(product);
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity cannot be zero or negative");
            }
            totalPrice += product.getPrice() * quantity;
        }
        if (getBalance() < totalPrice) {
            throw new IllegalArgumentException("Insufficient balance to place order. Needed: " + totalPrice + ", available: " + getBalance());
        }
        subtractBalance(totalPrice);
        Order order = new Order(cart,"ORDERED",this);
        orders.add(order);
        clearCart();
    }

    public void setBalance(int balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.Balance = balance;
    }
    public void addBalance(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.Balance += amount;
    }
    public void subtractBalance(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (this.Balance - amount < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        this.Balance -= amount;
    }
    public double getBalance() {
        return Balance;
    }
    public HashMap<Product, Integer> getCart() {
        return cart;
    }

    public List<Order> getOrders() {
        return orders;
    }
    @Override
    public void removeFromExtent()  {
        if(orders != null) {
            for (Order order : new ArrayList<>(orders)) {
                order.removeFromExtent();
            }
            orders.clear();
        }
        super.removeFromExtent();
    }
}
