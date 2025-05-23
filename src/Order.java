import java.util.Date;
import java.util.HashMap;

public class Order {
    private Date placeDate;
    private double price;
    private int totalItems;
    private HashMap<Product,Integer> Order = new HashMap<>();

    private String status;

    public Order(HashMap<Product,Integer> order, String status) {
        setPrice(order);
        setTotalItems(order);
        setOrder(order);
        setPlaceDate();
        setStatus(status);
    }
    public void setPrice(HashMap<Product,Integer> order) {
        double price = 0;
        for (int i = 0; i < order.size(); i++) {
            Product product = (Product) order.keySet().toArray()[i];
            int quantity = order.get(product);
            price += product.getPrice() * quantity;
        }
        if(price < 0){
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }
    public void setStatus(String status) {
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
        this.status = status;
    }
    public void setPlaceDate() {
        placeDate = new Date();
    }
    public void setTotalItems(HashMap<Product,Integer> order) {
        int totalItems = 0;
        for (int i = 0; i < order.size(); i++) {
            Product product = (Product) order.keySet().toArray()[i];
            int quantity = order.get(product);
            totalItems += quantity;
        }
        if(totalItems < 0){
            throw new IllegalArgumentException("Total items cannot be negative");
        }
        this.totalItems = totalItems;
    }
    public void setOrder(HashMap<Product, Integer> order) {
        if (order == null || order.isEmpty()) {
            throw new IllegalArgumentException("Order cannot be null or empty");
        }
        Order = order;
    }

    public Date getPlaceDate() {
        return placeDate;
    }

    public double getPrice() {
        return price;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public HashMap<Product, Integer> getOrder() {
        return Order;
    }
    public String getStatus() {
        return status;
    }
}
