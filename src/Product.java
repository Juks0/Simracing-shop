public class Product {
    private String name;
    private double price;
    private int quantity;
    private String IMG_PATH;

    public Product(String name, double price, int quantity, String IMG_PATH) {
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        setIMG_PATH(IMG_PATH);
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

    public void setIMG_PATH(String IMG_PATH) {
        if(IMG_PATH == null || IMG_PATH.isEmpty()){
            throw new IllegalArgumentException("Image path cannot be null or empty");
        }
        this.IMG_PATH = IMG_PATH;
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

    public String getIMG_PATH() {
        return IMG_PATH;
    }
}
