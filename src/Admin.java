import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Admin extends Person implements IAdmin {
    private int seniority;
    private List<OrderToShop> orders = new ArrayList<>();

    public Admin(String login, String email, String password, Date dateOfBirth, String address, int seniority) {
        super(login, email, password, dateOfBirth, address);
        setSeniority(seniority);
    }

    public Admin(User user, int seniority) {
        super(user.getLogin(), user.getEmail(), user.getPassword(), user.getDateOfBirth(), user.getAddress());
        setSeniority(seniority);
    }

    public void setSeniority(int seniority) {
        if (seniority < 0) {
            throw new IllegalArgumentException("Seniority cannot be negative");
        }
        this.seniority = seniority;
    }

    @Override
    public void setDiscount(Product product, Discount discount) {
        if (product == null || discount == null) {
            throw new IllegalArgumentException("Product and discount cannot be null");
        }
        product.setDiscount(discount);
    }

    @Override
    public void OrderProducts() {
        HashMap<Product, Integer> cartCopy = new HashMap<>(getCart());
        OrderToShop orderToShop = new OrderToShop(cartCopy, this);
        addOrder(orderToShop);
        clearCart();
    }

    public void addOrder(OrderToShop order) {
        if (order != null && !orders.contains(order)) {
            orders.add(order);
        }
    }

    public void removeOrder(OrderToShop order) {
        if (orders.remove(order)) {
            order.removeFromExtent();
        }
    }

    public int getSeniority() {
        return seniority;
    }

    public List<OrderToShop> getOrders() {
        return orders;
    }

    @Override
    public void removeFromExtent() {
        for (int i = orders.size() - 1; i >= 0; i--) {
            OrderToShop order = orders.get(i);
            order.removeFromExtent();
        }
        super.removeFromExtent();
    }

    public HashMap<Product, Integer> getCart() {
        return cart;
    }

}
