import java.util.HashMap;
import java.util.Map;

public class Pedals extends Product  {
    private boolean clutch;
    private String springs;
    public Pedals(String name, double price, int quantity, String IMG_PATH, Brand brand,boolean clutch) {
        super(name, price, quantity, IMG_PATH,brand);
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

    @Override
    public String toString() {
        return getName() + " - " + getPrice() + " $"+ " "+ getQuantity();
    }
}
