
import java.util.Date;
import java.util.HashMap;

public class OrderToShop {
    private Date date;
    private HashMap<Product,Integer> Order = new HashMap<>();
    private Admin admin;

    public OrderToShop(HashMap<Product,Integer> products, Admin admin) {
        setOrder(products);
        setAdmin(admin);
        placeOrder_shop();
    }
    public void placeOrder_shop() {
        setDate();
        for(int i =0; i<Order.size(); i++){
            Product product = (Product) Order.keySet().toArray()[i];
            int quantity = Order.get(product);
            product.addQuantity(quantity);
        }
    }
    public void setDate() {
        date = new Date();
    }

    public void setOrder(HashMap<Product, Integer> cart) {
        if (cart == null || cart.isEmpty()) {
            throw new IllegalArgumentException("cart cannot be null or empty");
        }
        Order = cart;
    }

    public void setAdmin(Admin admin) {
        if (admin == null) {
            throw new IllegalArgumentException("Admin cannot be null");
        }
        this.admin = admin;
    }
    public Date getDate() {
        return date;
    }

    public HashMap<Product, Integer> getOrder() {
        return Order;
    }
}
