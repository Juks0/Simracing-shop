import util.ObjectPlus;

import java.util.Date;
import java.util.HashMap;

public class Admin extends Person{
    private int seniority;
    private HashMap<Product,Integer> cart = new HashMap<>();
    public Admin(String login, String email, String password, Date dateOfBirth, String address, int seniority) {
        super(login, email, password, dateOfBirth, address);
        this.seniority=seniority;
    }

    public void setSeniority(int seniority) {
        if (seniority < 0) {
            throw new IllegalArgumentException("Seniority cannot be negative");
        }
        this.seniority = seniority;
    }

    public int getSeniority() {
        return seniority;
    }

    public void makeNewAdmin(User user, int seniority) {
        Admin admin = new Admin(user.getLogin(),user.getEmail(),user.getPassword(),user.getDateOfBirth(),user.getAddress(),seniority);
    }
    public void makeNewShopOrder() {
       for(Product product : cart.keySet()) {

        }
    }

    public HashMap<Product, Integer> getCart() {
        return cart;
    }

    public void setCart(HashMap<Product, Integer> cart) {
        this.cart = cart;
    }
    public void addToCart(Product product, int quantity) {
        if(cart.containsKey(product)) {
            cart.put(product, cart.get(product) + quantity);
        } else {
            cart.put(product, quantity);
        }
    }
}
