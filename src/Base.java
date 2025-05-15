import java.util.HashMap;
import java.util.Map;

public class Base extends Product {
    private final Map<String, Integer> serialMap = new HashMap<>();
    private int torque;
    private Mra mra;
    private int height;
    private int width;
    private int length;

    public Base(String name, double price, int quantity,String IMG_PATH, int torque, Mra mra, int height, int width, int length) {
        super(name,price,quantity,IMG_PATH);
        setTorque(torque);
        setMra(mra);
        setHeight(height);
        setWidth(width);
        setLength(length);
    }
    public synchronized String getNextSerial() {
        String prefix = "BB";
        int serial = serialMap.getOrDefault(prefix, 0) + 1;
        serialMap.put(prefix, serial);
        return prefix + "-" + String.format("%02d", serial);
    }
    public void setTorque(int torque) {
        if(torque < 1500 || torque > 20000){
            throw new IllegalArgumentException("Torque must be between 1500 and 20000");
        }
        this.torque = torque;
    }

    public void setMra(Mra mra) {
        if(mra.getValue() == 5 || mra.getValue() == 9 || mra.getValue() == 12 || mra.getValue() == 16 || mra.getValue() == 21)
            this.mra = mra;
        else
            throw new IllegalArgumentException("Mra must be between 5 and 9 and 12 and 16 and 21");
    }

    public void setHeight(int height) {
        if(height<5||height>100){
            throw new IllegalArgumentException("Height must be between 5 and 100");
        }
        this.height = height;
    }

    public void setWidth(int width) {
        if(width<5||width>100){
            throw new IllegalArgumentException("Width must be between 5 and 100");
        }
        this.width = width;
    }

    public void setLength(int length) {
        if(length<5||length>100){
            throw new IllegalArgumentException("Length must be between 5 and 100");
        }
        this.length = length;
    }

    public int getTorque() {
        return torque;
    }

    public Mra getMra() {
        return mra;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }
}
