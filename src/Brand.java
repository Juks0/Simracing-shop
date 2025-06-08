import util.ObjectPlus;

import java.util.List;

public class Brand extends ObjectPlus {
    private String name;
    private List<Product> featuredProducts;

    public Brand(String name) {
        setName(name);
    }
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }
    public void setFeaturedProducts(List<Product> featuredProducts) {
        if (featuredProducts == null || featuredProducts.isEmpty()) {
            throw new IllegalArgumentException("Featured products cannot be null or empty");
        }
        if(featuredProducts.size()>3){
            throw new IllegalArgumentException("Featured products cannot exceed 3 items");
        }
        this.featuredProducts = featuredProducts;
    }
    public void addFeaturedProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (featuredProducts.size() >= 3) {
            throw new IllegalArgumentException("Cannot add more than 3 featured products");
        }
        featuredProducts.add(product);
    }
    public void removeFeaturedProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (!featuredProducts.contains(product)) {
            throw new IllegalArgumentException("Product is not in the featured products list");
        }
        featuredProducts.remove(product);
    }
    public List<Product> getFeaturedProducts() {
        return featuredProducts;
    }
    public String getName() {
        return name;
    }


}
