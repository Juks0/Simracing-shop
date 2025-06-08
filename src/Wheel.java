import java.util.HashMap;
import java.util.Map;

public class Wheel extends Product {

    private Fabric fabric;
    private int size;
    private int rotationAngle;

    public Wheel(String name, double price, int quantity, String IMG_PATH,Brand brand,Fabric fabric, int size) {
        super(name, price, quantity, IMG_PATH,brand);
        setFabric(fabric);
        setSize(size);
    }
    public void setFabric(Fabric fabric) {
        if (fabric == null) {
            throw new IllegalArgumentException("Fabric cannot be null");
        }
        this.fabric = fabric;
    }
    public void setSize(int size) {
        if(size < 1 || size > 3){
            throw new IllegalArgumentException("Size must be between 5 and 100");
        }
        this.size = size;
    }
    public void changeRotationAngle(int angle) {
        if (angle < 0 || angle > 1800) {
            throw new IllegalArgumentException("Rotation angle must be between 0 and 360 degrees");
        }
        this.rotationAngle=angle;
    }
    public Fabric getFabric() {
        return fabric;
    }

    public int getSize() {
        return size;
    }
    @Override
    protected String getSerialPrefix() {
        return "WH";
    }


    public int getRotationAngle() {
        return rotationAngle;
    }
    @Override
    public String toString() {
        return getName() + " - " + getPrice() + " $"+ " "+ getQuantity();
    }
}
