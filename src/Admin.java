import java.util.Date;

public class Admin extends Person{
    private int seniority;
    public Admin(String login, String email, String password, Date dateOfBirth, String address, int seniority) {
        super(login, email, password, dateOfBirth, address);
        this.seniority=seniority;
    }

    public void setSeniority(int seniority) {
        if (seniority < 0) {
            throw new IllegalArgumentException("Seniority cannot be negative");
        }
        this.seniority = seniority;
    }

    public int getSeniority() {
        return seniority;
    }
}
