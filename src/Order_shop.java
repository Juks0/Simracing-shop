import util.ObjectPlus;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Order_shop extends ObjectPlus {
    private Date date;
    private HashMap<Product,Integer> Order = new HashMap<>();


    public Order_shop(HashMap<Product,Integer> products) {
        setOrder(products);
        placeOrder_shop();
    }
    public void placeOrder_shop() {
        setDate();
        for(int i =0; i<Order.size(); i++){
            Product product = (Product) Order.keySet().toArray()[i];
            int quantity = Order.get(product);
            if (product.getQuantity() < quantity) {
                throw new IllegalArgumentException("Not enough stock for product: " + product.getName());
            }
            product.setQuantity(product.getQuantity() - quantity);
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

    public Date getDate() {
        return date;
    }

    public HashMap<Product, Integer> getOrder() {
        return Order;
    }
}
