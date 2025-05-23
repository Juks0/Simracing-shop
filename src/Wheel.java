import java.util.HashMap;
import java.util.Map;

public class Wheel extends Product{

    private final Map<String, Integer> serialMap = new HashMap<>();
    private Fabric fabric;
    private int size;

    public Wheel(String name, double price, int quantity, String IMG_PATH,Fabric fabric, int size) {
        super(name, price, quantity, IMG_PATH);
        setFabric(fabric);
        setSize(size);
    }
    public void setFabric(Fabric fabric) {
        this.fabric = fabric;
    }
    public void setSize(int size) {
        if(size < 1 || size > 3){
            throw new IllegalArgumentException("Size must be between 5 and 100");
        }
        this.size = size;
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
}
