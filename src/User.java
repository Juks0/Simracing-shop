import java.util.Date;

public class User extends Person {
    private int Balance;

    public User(String login, String email, String password, Date dateOfBirth, String address) {
        super(login, email, password, dateOfBirth, address);
        setBalance(0);
    }
    public void setBalance(int balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.Balance = balance;
    }
    public void addBalance(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.Balance += amount;
    }
    public void subtractBalance(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (this.Balance - amount < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        this.Balance -= amount;
    }
    public int getBalance() {
        return Balance;
    }


}
