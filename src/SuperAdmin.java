import java.util.Date;

public class SuperAdmin extends User {
    public SuperAdmin(String login, String email, String password, Date dateOfBirth, String address) {
        super(login, email, password, dateOfBirth, address);
    }
    public void removeUserAccount(User user) {
        if(user == null){
            throw new IllegalArgumentException("User cannot be null");
        }
        user.removeFromExtent();
    }
    public void makeNewAdmin(User user, int seniority) {
        new Admin(user.getLogin(),user.getEmail(),user.getPassword(),user.getDateOfBirth(),user.getAddress(),seniority);
    }
}
