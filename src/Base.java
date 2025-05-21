import java.util.HashMap;
import java.util.Map;

public class Base extends Product {
    private final Map<String, Integer> serialMap = new HashMap<>();
    private int torque;
    private Mra mra;

    public Base(String name, double price, int quantity,String IMG_PATH, int torque, Mra mra) {
        super(name,price,quantity,IMG_PATH);
        setTorque(torque);
        setMra(mra);
    }

    public void setTorque(int torque) {
        if(torque < 1500 || torque > 20000){
            throw new IllegalArgumentException("Torque must be between 1500 and 20000");
        }
        this.torque = torque;
    }

    public void setMra(Mra mra) {
        this.mra = mra;
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
}
