import java.util.Date;
import java.util.Objects;

public class SuperAdmin extends User {
    private Admin admin;

    public SuperAdmin(String login, String email, String password, Date dateOfBirth, String address, int seniority) {
        super(login, email, password, dateOfBirth, address);
        try {
            this.admin = new Admin(login, email, password, dateOfBirth, address, seniority);
        }
        catch (IllegalArgumentException e) {
            super.removeFromExtent();
        }
    }

    public SuperAdmin(Admin admin) {
        super(admin.getLogin(), admin.getEmail(), admin.getPassword(), admin.getDateOfBirth(), admin.getAddress());
        if (admin == null) {
            throw new IllegalArgumentException("Admin cannot be null");
        }
        this.admin = admin;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setDiscount(Product product, Discount discount) {
        admin.setDiscount(product, discount);
    }

    @Override
    public void removeFromExtent() {
        if (admin != null) {
            admin.removeFromExtent();
        }
        super.removeFromExtent();
    }

    public void removeUserAccount(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        user.removeFromExtent();
    }

    public void makeNewAdmin(User user, int seniority) {
        new Admin(user.getLogin(), user.getEmail(), user.getPassword(), user.getDateOfBirth(), user.getAddress(), seniority);
    }
    public void makeNewAdmin(String login, String email,String password, Date dateOfBirth, String adress , int seniority) {
        new Admin(login, email, password, dateOfBirth, adress, seniority);
    }
}
