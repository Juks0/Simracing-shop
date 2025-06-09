import util.ObjectPlus;

import java.util.Date;
import java.util.HashMap;

public class Order extends ObjectPlus {
    private User user;
    private Date placeDate;
    private double price;
    private int totalItems;
    private HashMap<Product,Integer> items = new HashMap<>();
    private String status;

    public Order(HashMap<Product,Integer> items, String status, User user) {
        try{
        setPrice(items);
        setTotalItems(items);
        setItems(items);
        setPlaceDate();
        setStatus(status);
        setUser(user);
        placeOrder();
        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid order details: " + e.getMessage());
        }

    }
   public Order(Product product, int quantity,String status) {
        try {
            if (product == null) {
                throw new IllegalArgumentException("Product name cannot be null or empty");
            }
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than zero");
            }
            HashMap<Product, Integer> order = new HashMap<>();
            order.put(product, quantity);
            setPrice(order);
            setTotalItems(order);
            setItems(order);
            setPlaceDate();
            setStatus(status);
            placeOrder();
        }catch (IllegalArgumentException e) {
            removeFromExtent();
        }
   }
   public void placeOrder(){
         for(int i = 0; i< items.size(); i++){
              Product product = (Product) items.keySet().toArray()[i];
              int quantity = items.get(product);
              product.addQuantity(quantity);
         }
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
        if (status.equals("ORDERED") || status.equals("RETURNED")) {
           this.status=status;
        }else
            throw new IllegalArgumentException("Status must be either ORDERED or RETURNED");
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
    public void setItems(HashMap<Product, Integer> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order cannot be null or empty");
        }
        this.items = items;
    }
    public void setUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        this.user = user;
    }

    public Returned_Order returnOrder(){
        if(!user.orders.contains(this)){
            throw new IllegalArgumentException("Order does not belong to the user");
        }
        if (!status.equals("ORDERED")) {
            throw new IllegalArgumentException("Order cannot be returned unless it is in ORDERED status");
        }
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Order has no items to return");
        }
        Returned_Order returnedOrder = new Returned_Order(this, "No reason provided");
        user.returnedOrders.add(returnedOrder);
        this.removeFromExtent();
        return returnedOrder;
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

    public HashMap<Product, Integer> getItems() {
        return items;
    }
    public String getStatus() {
        return status;
    }
    public User getUser() {
        return user;
    }

    @Override
    public  void removeFromExtent() {
        if (user != null) {
            user.removeFromExtent();
        }
        super.removeFromExtent();
    }
}
