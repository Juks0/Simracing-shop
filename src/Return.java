import java.util.Date;
import java.util.HashMap;

public class Return {
    private Date returnDate;
    private double refundAmount;
    private String reason;
    private Order order;

    public Return(Order order, String reason) {
        setOrder(order);
        setReason(reason);
        setReturnDate();
        setRefundAmount();
        order.setStatus("RETURNED");
        addBackToStock(order.getOrder());
    }
    public void addBackToStock(HashMap<Product,Integer> items) {
        for(int i = 0; i < items.size(); i++) {
            Product product = (Product) items.keySet().toArray()[i];
            int quantity = items.get(product);
            product.setQuantity(product.getQuantity() + quantity);
        }
    }
    public void setReturnDate() {
        returnDate = new Date();
    }
    public void setRefundAmount() {
        double refundAmount = order.getPrice();
        if(refundAmount < 0){
            throw new IllegalArgumentException("Refund amount cannot be negative");
        }
        this.refundAmount = refundAmount;
    }
    public void setReason(String reason) {
        if (reason == null || reason.isEmpty()) {
            throw new IllegalArgumentException("Reason cannot be null or empty");
        }
        this.reason = reason;
    }
    public void setOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        this.order = order;
    }
    public Date getReturnDate() {
        return returnDate;
    }
    public double getRefundAmount() {
        return refundAmount;
    }
    public String getReason() {
        return reason;
    }
    public Order getOrder() {
        return order;
    }
}
