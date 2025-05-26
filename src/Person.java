
import util.ObjectPlus;

import java.util.*;

public abstract class Person  {

    private String login;
    private String email;
    private String password;
    private Date dateOfBirth;
    private int age;
    private String address;
    protected HashMap<Product,Integer> cart = new HashMap<>();


    public Person(String login, String email, String password, Date dateOfBirth, String address) {
       setLogin(login);
       setEmail(email);
       setPassword(password);
       setDateOfBirth(dateOfBirth);
       setAge(calculateAge(dateOfBirth, new Date()));
       setAddress(address);
    }

    private int calculateAge(Date birthDate, Date currentDate) {
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthDate);
        Calendar current = Calendar.getInstance();
        current.setTime(currentDate);

        int yearDiff = current.get(Calendar.YEAR) - birth.get(Calendar.YEAR);

        if (current.get(Calendar.MONTH) < birth.get(Calendar.MONTH) ||
                (current.get(Calendar.MONTH) == birth.get(Calendar.MONTH) &&
                        current.get(Calendar.DAY_OF_MONTH) < birth.get(Calendar.DAY_OF_MONTH))) {
            yearDiff--;
        }
        return yearDiff;
    }

    public void changeMail(String newMail) {
        setEmail(newMail);
    }
    public void addToCart(Product product, int quantity) {
        if(cart.containsKey(product)) {
            cart.put(product, cart.get(product) + quantity);
        } else {
            cart.put(product, quantity);
        }
    }
    public void setCart(HashMap<Product, Integer> cart) {
        this.cart = cart;
    }

    public static Person verifyCredentials(String login, String password) {
        return ObjectPlus.getExtentFromClass(Person.class).stream()
                .filter(person -> person.getLogin().equals(login) && person.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
    public static boolean verifyCredentialsTF(String login, String password) {
        return ObjectPlus.getExtentFromClass(Person.class).stream()
                .anyMatch(person -> person.getLogin().equals(login) && person.getPassword().equals(password));
    }


    public void removeFromCart(Product product) {
        if(cart.containsKey(product)) {
            cart.remove(product);
        } else {
            throw new IllegalArgumentException("Product not in cart");
        }
    }
    public void clearCart() {
        cart.clear();
    }
    public void showCart() {
        if(cart.isEmpty()){
            System.out.println("Cart is empty");
        } else {
            System.out.println("Cart:");
            for (Product product : cart.keySet()) {
                System.out.println(product.getName() + " - " + cart.get(product));

            }
        }
    }
    public void setLogin(String login) {
        if (login == null || login.isEmpty()) {
            throw new IllegalArgumentException("Login cannot be null or empty");
        }
        this.login = login;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        this.email = email;
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        this.password = password;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Date of birth cannot be null");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        this.age = age;
    }

    public void setAddress(String address) {
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty");
        }
        this.address = address;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String showPersonalInfo() {
        return "Login: " + login + "\n" +
                "Email: " + email + "\n" +
                "Password: " + password + "\n" +
                "Date of Birth: " + dateOfBirth + "\n" +
                "Age: " + age + "\n" +
                "Address: " + address;
    }
}
