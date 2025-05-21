import java.util.Date;
import java.util.HashMap;

public class User extends Person {
    private double Balance;

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
        new Order(cart);
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

}
