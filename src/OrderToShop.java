import util.ObjectPlus;

import java.util.Date;
import java.util.HashMap;

public class OrderToShop extends ObjectPlus {
    private Date date;
    private HashMap<Product,Integer> orders = new HashMap<>();
    private Admin admin;

    public OrderToShop(HashMap<Product,Integer> products, Admin admin) {
        setOrders(products);
        setAdmin(admin);
        placeOrder_shop();
        admin.addOrder(this);
    }

    public OrderToShop(Product product , int quantity, Admin admin) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException("Product cannot be null and quantity must be greater than 0");
        }
        HashMap<Product, Integer> cart = new HashMap<>();
        cart.put(product, quantity);
        setOrders(cart);
        setAdmin(admin);
        placeOrder_shop();
        admin.addOrder(this);
    }

    public void placeOrder_shop() {
        setDate();
        for (Product product : orders.keySet()) {
            int quantity = orders.get(product);
            product.addQuantity(quantity);
        }
    }

    public void setDate() {
        date = new Date();
    }

    public void setOrders(HashMap<Product, Integer> cart) {
        if (cart == null || cart.isEmpty()) {
            throw new IllegalArgumentException("cart cannot be null or empty");
        }
        orders = cart;
    }

    public void setAdmin(Admin admin) {
        if (admin == null) {
            throw new IllegalArgumentException("Admin cannot be null");
        }
        if (this.admin != null) {
            this.admin.removeOrder(this);
        }
        this.admin = admin;
        admin.addOrder(this);
    }

    public Date getDate() {
        return date;
    }

    @Override
    public void removeFromExtent() {
        if (admin != null) {
            Admin temp = admin;
            admin = null;
            temp.removeOrder(this);
        }
        super.removeFromExtent();
    }

    public Admin getAdmin() {
        return admin;
    }

    public HashMap<Product, Integer> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "OrderToShop{" +
                "date=" + date +
                ", Order=" + orders +
                ", admin=" + (admin != null ? admin.getLogin() : "null") +
                '}';
    }
}
