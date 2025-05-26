import util.ObjectPlus;

import javax.swing.*;

public class Discount extends ObjectPlus {
    private int percentage;
    private Product product;

    public Discount(Product product, int percentage) {
        setProduct(product);
        setPercentage(percentage);
        product.setDiscount(this);
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
    public Product getProduct() {
        return product;
    }
    public int getPercentage() {
        return percentage;
    }
}
