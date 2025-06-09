import util.ObjectPlus;
import java.util.Date;
import java.util.HashMap;

/**
 * Klasa reprezentuje zamówienie do sklepu, które może być złożone przez administratora.
 * Zamówienie może zawierać jeden lub wiele produktów wraz z ich ilościami.
 * Każde zamówienie jest powiązane z konkretną datą oraz administratorem, który je złożył.
 * Dziedziczy po klasie ObjectPlus, co pozwala na zarządzanie ekstensją obiektów.
 */
public class OrderToShop extends ObjectPlus {
    /** Data złożenia zamówienia */
    private Date date;

    /** Mapa przechowująca produkty oraz ich ilości w zamówieniu */
    private HashMap<Product, Integer> orders = new HashMap<>();

    /** Administrator składający zamówienie */
    private Admin admin;

    /**
     * Konstruktor tworzący zamówienie na podstawie mapy produktów oraz administratora.
     * @param products Mapa produktów oraz ich ilości.
     * @param admin Administrator składający zamówienie.
     */
    public OrderToShop(HashMap<Product,Integer> products, Admin admin) {
        try {
            setOrders(products);
            setAdmin(admin);
            placeOrder_shop();
            admin.addOrder(this);
        }
        catch (IllegalArgumentException e) {
            removeFromExtent();
        }
    }

    /**
     * Konstruktor tworzący zamówienie na pojedynczy produkt.
     * @param product Produkt do zamówienia.
     * @param quantity Ilość produktu.
     * @param admin Administrator składający zamówienie.
     */
    public OrderToShop(Product product , int quantity, Admin admin) {
        try {
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
        catch (IllegalArgumentException e) {
            removeFromExtent();
        }
    }

    /**
     * Składa zamówienie do sklepu.
     * Ustawia datę zamówienia oraz zwiększa ilość każdego produktu w magazynie.
     */
    public void placeOrder_shop() {
        setDate();
        for (Product product : orders.keySet()) {
            int quantity = orders.get(product);
            product.addQuantity(quantity);
        }
    }

    /**
     * Ustawia aktualną datę jako datę zamówienia.
     */
    public void setDate() {
        date = new Date();
    }

    /**
     * Ustawia produkty i ich ilości w zamówieniu.
     * @param cart Mapa produktów i ilości.
     * @throws IllegalArgumentException jeśli koszyk jest pusty lub null.
     */
    public void setOrders(HashMap<Product, Integer> cart) {
        if (cart == null || cart.isEmpty()) {
            throw new IllegalArgumentException("cart cannot be null or empty");
        }
        orders = cart;
    }

    /**
     * Przypisuje administratora do zamówienia.
     * @param admin Administrator.
     * @throws IllegalArgumentException jeśli admin jest null.
     */
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

    /**
     * Zwraca datę złożenia zamówienia.
     * @return Data zamówienia.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Usuwa zamówienie z ekstensji oraz z listy zamówień administratora.
     */
    @Override
    public void removeFromExtent() {
        if (admin != null) {
            Admin temp = admin;
            admin = null;
            temp.removeOrder(this);
        }
        super.removeFromExtent();
    }

    /**
     * Zwraca administratora powiązanego z zamówieniem.
     * @return Administrator.
     */
    public Admin getAdmin() {
        return admin;
    }

    /**
     * Zwraca mapę produktów i ich ilości w zamówieniu.
     * @return Mapa produktów i ilości.
     */
    public HashMap<Product, Integer> getOrders() {
        return orders;
    }

    /**
     * Zwraca tekstową reprezentację zamówienia.
     * @return String z informacjami o zamówieniu.
     */
    @Override
    public String toString() {
        return "OrderToShop{" +
                "date=" + date +
                ", Order=" + orders +
                ", admin=" + (admin != null ? admin.getLogin() : "null") +
                '}';
    }
}
