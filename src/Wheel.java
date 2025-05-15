import java.util.HashMap;
import java.util.Map;

public class Wheel extends Product{

    private final Map<String, Integer> serialMap = new HashMap<>();
    private String fabric;
    private int size;

    public Wheel(String name, double price, int quantity, String IMG_PATH,String fabric, int size) {
        super(name, price, quantity, IMG_PATH);
        setFabric(fabric);
        setSize(size);
    }

    public synchronized String getNextSerial() {
        String prefix = "WH";
        int serial = serialMap.getOrDefault(prefix, 0) + 1;
        serialMap.put(prefix, serial);
        return prefix + "-" + String.format("%02d", serial);
    }
    public void setFabric(String fabric) {
        if(fabric == null || fabric.isEmpty()){
            throw new IllegalArgumentException("Fabric cannot be null or empty");
        }
        this.fabric = fabric;
    }
    public void setSize(int size) {
        if(size < 1 || size > 3){
            throw new IllegalArgumentException("Size must be between 5 and 100");
        }
        this.size = size;
    }

    public String getFabric() {
        return fabric;
    }

    public int getSize() {
        return size;
    }
}
