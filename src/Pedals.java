import java.util.HashMap;
import java.util.Map;

public class Pedals extends Product {
    private final Map<String, Integer> serialMap = new HashMap<>();
    private boolean clutch;
    public Pedals(String name, double price, int quantity, String IMG_PATH, boolean clutch) {
        super(name, price, quantity, IMG_PATH);
        setClutch(clutch);
    }

    public void setClutch(boolean clutch) {
        this.clutch = clutch;
    }
    public boolean hasClutch() {
        return clutch;
    }
    @Override
    protected String getSerialPrefix() {
        return "BB";
    }
}
