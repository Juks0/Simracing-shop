import java.util.HashMap;
import java.util.Map;

public class Base extends Product   {
    private int torque;
    private Mra mra;
    private boolean boostMode;

    public Base(String name, double price, int quantity,String IMG_PATH,Brand brand ,int torque, Mra mra) {
        super(name,price,quantity,IMG_PATH,brand);
        setTorque(torque);
        setMra(mra);
    }
    public Base(String name, double price, int quantity,Brand brand,String IMG_PATH, int torque) {
        super(name,price,quantity,IMG_PATH,brand);
        setTorque(torque);
    }
    public void setTorque(int torque) {
        if(torque < 1500 || torque > 20000){
            throw new IllegalArgumentException("Torque must be between 1500 and 20000");
        }
        this.torque = torque;
    }

    public void setMra(Mra mra) {
        if(mra == null) {
            throw new IllegalArgumentException("MRA cannot be null");
        }
        this.mra = mra;
    }

    public boolean isBoostMode() {
        return boostMode;
    }

    public void setBoostMode(boolean boostMode) {
        this.boostMode = boostMode;
    }

    public int getTorque() {
        return torque;
    }

    public Mra getMra() {
        return mra;
    }
    @Override
    protected String getSerialPrefix() {
        return "BB";
    }
    @Override
    public String toString() {
        return getName() + " - " + getPrice() + " $"+ " "+ getQuantity();
    }

}
