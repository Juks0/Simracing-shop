import util.ObjectPlus;
import java.util.*;

/**
 * Klasa abstrakcyjna reprezentująca osobę w systemie sklepu.
 * Przechowuje podstawowe dane osobowe oraz obsługuje operacje na koszyku produktów.
 * Dziedziczy po klasie ObjectPlus, umożliwiając zarządzanie ekstensją obiektów.
 */
public abstract class Person extends ObjectPlus  {

    /** Login użytkownika */
    private String login;

    /** Adres e-mail użytkownika */
    private String email;

    /** Hasło użytkownika */
    private String password;

    /** Data urodzenia użytkownika */
    private Date dateOfBirth;

    /** Wiek użytkownika (wyliczany na podstawie daty urodzenia) */
    private int age;

    /** Adres użytkownika */
    private String address;

    /** Koszyk użytkownika - mapa produktów oraz ich ilości */
    protected HashMap<Product,Integer> cart = new HashMap<>();

    /**
     * Konstruktor tworzący nową osobę na podstawie przekazanych danych.
     * @param login Login użytkownika.
     * @param email Adres e-mail.
     * @param password Hasło.
     * @param dateOfBirth Data urodzenia.
     * @param address Adres zamieszkania.
     * @throws IllegalArgumentException jeśli którykolwiek z parametrów jest nieprawidłowy.
     */
    public Person(String login, String email, String password, Date dateOfBirth, String address) {
        try {
            setLogin(login);
            setEmail(email);
            setPassword(password);
            setDateOfBirth(dateOfBirth);
            setAge(calculateAge(dateOfBirth, new Date()));
            setAddress(address);
        } catch (IllegalArgumentException e) {
            removeFromExtent();
        }
    }

    /**
     * Prywatna metoda obliczająca wiek na podstawie daty urodzenia i bieżącej daty.
     * @param birthDate Data urodzenia.
     * @param currentDate Bieżąca data.
     * @return Wiek w latach.
     */
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

    /**
     * Zmienia adres e-mail użytkownika.
     * @param newMail Nowy adres e-mail.
     */
    public void changeMail(String newMail) {
        setEmail(newMail);
    }

    /**
     * Dodaje produkt do koszyka lub zwiększa ilość, jeśli już istnieje.
     * @param product Produkt do dodania.
     * @param quantity Ilość produktu.
     */
    public void addToCart(Product product, int quantity) {
        if(cart.containsKey(product)) {
            cart.put(product, cart.get(product) + quantity);
        } else {
            cart.put(product, quantity);
        }
    }

    /**
     * Ustawia koszyk użytkownika.
     * @param cart Mapa produktów i ich ilości.
     */
    public void setCart(HashMap<Product, Integer> cart) {
        this.cart = cart;
    }

    /**
     * Weryfikuje dane logowania i zwraca osobę, jeśli dane są poprawne.
     * Przeszukuje ekstensję klasy Admin.
     * @param login Login użytkownika.
     * @param password Hasło użytkownika.
     * @return Obiekt Person jeśli dane są poprawne, w przeciwnym razie null.
     */
    public static Person verifyCredentials(String login, String password) {
        return ObjectPlus.getExtentFromClass(Admin.class).stream()
                .filter(person -> person.getLogin().equals(login) && person.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    /**
     * Weryfikuje dane logowania i zwraca true/false.
     * Przeszukuje ekstensję klasy Admin.
     * @param login Login użytkownika.
     * @param password Hasło użytkownika.
     * @return true jeśli dane są poprawne, false w przeciwnym razie.
     */
    public static boolean verifyCredentialsTF(String login, String password) {
        return ObjectPlus.getExtentFromClass(Admin.class).stream()
                .anyMatch(person -> person.getLogin().equals(login) && person.getPassword().equals(password));
    }

    /**
     * Usuwa produkt z koszyka.
     * @param product Produkt do usunięcia.
     * @throws IllegalArgumentException jeśli produktu nie ma w koszyku.
     */
    public void removeFromCart(Product product) {
        if(cart.containsKey(product)) {
            cart.remove(product);
        } else {
            throw new IllegalArgumentException("Product not in cart");
        }
    }

    /**
     * Czyści cały koszyk użytkownika.
     */
    public void clearCart() {
        cart.clear();
    }

    /**
     * Wyświetla zawartość koszyka na konsoli.
     */
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

    /**
     * Ustawia login użytkownika.
     * @param login Login.
     * @throws IllegalArgumentException jeśli login jest pusty lub null.
     */
    public void setLogin(String login) {
        if (login == null || login.isEmpty()) {
            throw new IllegalArgumentException("Login cannot be null or empty");
        }
        this.login = login;
    }

    /**
     * Ustawia adres e-mail użytkownika.
     * @param email Adres e-mail.
     * @throws IllegalArgumentException jeśli email jest pusty lub null.
     */
    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        this.email = email;
    }

    /**
     * Ustawia hasło użytkownika.
     * @param password Hasło.
     * @throws IllegalArgumentException jeśli hasło jest puste lub null.
     */
    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        this.password = password;
    }

    /**
     * Ustawia datę urodzenia użytkownika.
     * @param dateOfBirth Data urodzenia.
     * @throws IllegalArgumentException jeśli data jest null.
     */
    public void setDateOfBirth(Date dateOfBirth) {
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Date of birth cannot be null");
        }
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Ustawia wiek użytkownika.
     * @param age Wiek.
     * @throws IllegalArgumentException jeśli wiek jest ujemny.
     */
    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        this.age = age;
    }

    /**
     * Ustawia adres użytkownika.
     * @param address Adres.
     * @throws IllegalArgumentException jeśli adres jest pusty lub null.
     */
    public void setAddress(String address) {
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty");
        }
        this.address = address;
    }

    /**
     * Zwraca login użytkownika.
     * @return Login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Zwraca adres e-mail użytkownika.
     * @return Adres e-mail.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Zwraca hasło użytkownika.
     * @return Hasło.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Zwraca datę urodzenia użytkownika.
     * @return Data urodzenia.
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Zwraca wiek użytkownika.
     * @return Wiek.
     */
    public int getAge() {
        return age;
    }

    /**
     * Zwraca adres użytkownika.
     * @return Adres.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Zwraca informacje osobiste użytkownika w postaci tekstowej.
     * @return Informacje osobiste jako String.
     */
    public String showPersonalInfo() {
        return "Login: " + login + "\n" +
                "Email: " + email + "\n" +
                "Password: " + password + "\n" +
                "Date of Birth: " + dateOfBirth + "\n" +
                "Age: " + age + "\n" +
                "Address: " + address;
    }
}
