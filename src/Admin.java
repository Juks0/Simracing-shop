import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Admin extends Person{
    private int seniority;
//    private HashMap<Product,Integer> cart = new HashMap<>();
    private List<OrderToShop> orders = new ArrayList<>();
    public Admin(String login, String email, String password, Date dateOfBirth, String address, int seniority) {
        super(login, email, password, dateOfBirth, address);
        this.seniority=seniority;
    }
    public Admin(User user, int seniority) {
        super(user.getLogin(), user.getEmail(), user.getPassword(), user.getDateOfBirth(), user.getAddress());
        this.seniority = seniority;
    }

    public void setSeniority(int seniority) {
        if (seniority < 0) {
            throw new IllegalArgumentException("Seniority cannot be negative");
        }
        this.seniority = seniority;
    }

    public void makeNewAdmin(User user, int seniority) {
        new Admin(user.getLogin(),user.getEmail(),user.getPassword(),user.getDateOfBirth(),user.getAddress(),seniority);
    }


//    public void addToCart(Product product, int quantity) {
//        if(cart.containsKey(product)) {
//            cart.put(product, cart.get(product) + quantity);
//        } else {
//            cart.put(product, quantity);
//        }
//    }
//    public void setCart(HashMap<Product, Integer> cart) {
//        this.cart = cart;
//    }
//
//    public void removeFromCart(Product product) {
//        if(cart.containsKey(product)) {
//            cart.remove(product);
//        } else {
//            throw new IllegalArgumentException("Product not in cart");
//        }
//    }
//    public void clearCart() {
//        cart.clear();
//    }
//    public void showCart() {
//        if(cart.isEmpty()){
//            System.out.println("Cart is empty");
//        } else {
//            System.out.println("Cart:");
//            for (Product product : cart.keySet()) {
//                System.out.println(product.getName() + " - " + cart.get(product));
//
//            }
//        }
//    }
    public void makeNewOrderToShop(){
        OrderToShop orderToShop = new OrderToShop(getCart(), this);
        orders.add(orderToShop);
        clearCart();
    }



    public void showOrders() {
        if(orders.isEmpty()){
            System.out.println("No orders");
        } else {
            System.out.println("Orders:");
            for (OrderToShop order : orders) {
                System.out.println(order.getDate() + " - " + order.getOrder());

            }
        }
    }

    public void changeUserEmail(User user, String newEmail) {
        if(user.getEmail() == null || user.getEmail().isEmpty()){
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        user.setEmail(newEmail);
    }
    public int getSeniority() {
        return seniority;
    }
    public HashMap<Product, Integer> getCart() {
        return cart;
    }
}
