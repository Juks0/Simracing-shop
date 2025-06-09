import util.ObjectPlus;

import java.util.HashMap;
import java.util.Map;

public abstract class Product extends ObjectPlus {
    private String serial;
    private String name;
    private double price;
    private int quantity;
    private String IMG_PATH;
    private static final Map<String, Integer> serialCounters = new HashMap<>();
    private Discount discount;
    private Brand brand;

    protected abstract String getSerialPrefix();

    public Product(String name, double price, int quantity, String IMG_PATH, Brand brand) {
        try{
        generateNextSerial();
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        setIMG_PATH(IMG_PATH);
        setBrand(brand);
    }catch (IllegalArgumentException e) {
            super.removeFromExtent();
        }
    }


    public Product getProductBySerial(String serial){
        return ObjectPlus.getExtentFromClass(this.getClass()).stream()
                .filter(product -> product.getSerial().equals(serial))
                .findFirst().orElse(null);
    }
    private synchronized void generateNextSerial() {
        String prefix = getSerialPrefix();
        int serial = serialCounters.getOrDefault(prefix, 0) + 1;
        serialCounters.put(prefix, serial);
        String newSerial = prefix + String.format("%03d", serial);
        setSerial(newSerial);
    }

    public void setSerial(String serial) {
        if(serial == null || serial.isEmpty()){
            throw new IllegalArgumentException("Serial cannot be null or empty");
        }
        this.serial = serial;
    }
    public void setBrand(Brand brand) {
        if(brand == null){
            throw new IllegalArgumentException("Brand cannot be null");
        }
        this.brand = brand;
    }
    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
    public void setName(String name) {
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public void setPrice(double price) {
        if(price < 0){
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public void setQuantity(int quantity) {
        if(quantity < 0){
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }
    public void addQuantity(int quantity) {
        if(quantity < 0){
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity += quantity;
    }

    public void setIMG_PATH(String IMG_PATH) {
        if(IMG_PATH == null || IMG_PATH.isEmpty()){
            throw new IllegalArgumentException("Image path cannot be null or empty");
        }
        this.IMG_PATH = IMG_PATH;
    }


    public void removeDiscount() {
        if (discount != null) {
            discount.removeDiscount();
            this.discount = null;
        }
    }
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSerial() {
        return serial;
    }
    public Discount getDiscount() {
        return discount;
    }
    public String getIMG_PATH() {
        return IMG_PATH;
    }

}
