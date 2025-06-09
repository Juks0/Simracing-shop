import util.ObjectPlus;

public class Discount extends ObjectPlus {
    private int percentage;
    private Product product;
    private double oldPrice;

    public Discount(Product product, int percentage) {
        try {
            setProduct(product);
            setPercentage(percentage);
            product.setDiscount(this);
            setOldPrice(product.getPrice());
            product.setPrice(product.getPrice() * (1 - percentage / 100.0));
        }catch (IllegalArgumentException e) {
            removeFromExtent();
        }
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public void setPercentage(int percentage) {
        if (percentage < 1 || percentage > 100) {
            throw new IllegalArgumentException("Percentage must be between 1 and 100");
        }
        this.percentage = percentage;
    }
    public void setOldPrice(double oldPrice) {
        if (oldPrice < 0) {
            throw new IllegalArgumentException("Old price cannot be negative");
        }
        this.oldPrice = oldPrice;
    }

    public Product getProduct() {
        return product;
    }
    public int getPercentage() {
        return percentage;
    }
    public void removeDiscount() {
        if (product != null) {
            product.setPrice(oldPrice);
            product.setDiscount(null);
            this.removeFromExtent();
        }
    }

    @Override
    public String toString() {
        return "Discount{" +
                "percentage=" + percentage +
                ", product=" + product +
                '}';
    }

}
