import util.ObjectPlus;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Main {
    static Person person;

    public static void main(String[] args){
        if (extentFileExists()) {
            try {
                ObjectPlus.loadExtent();
                System.out.println("Data loaded from file.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error reading data: " + e.getMessage());
                return;
            }
        } else {
            generateSampleData();
            try {
                ObjectPlus.saveExtent();
                System.out.println("Data loaded to file.");
            } catch (Exception e) {
                System.out.println("Error saving data: " + e.getMessage());
            }
        }
        ObjectPlus.getExtentFromClass(Wheel.class).forEach(System.out::println);
        ObjectPlus.getExtentFromClass(Pedals.class).forEach(System.out::println);
        ObjectPlus.getExtentFromClass(Base.class).forEach(System.out::println);

        new Login();
    }

    public static boolean extentFileExists() {
        File file = new File(ObjectPlus.NAZWA_PLIKU);
        return file.exists();
    }

    public static void generateSampleData() {
        Brand brand1 = new Brand("Moza Racing");
        Brand brand2 = new Brand("Fanatec");
        Brand brand3 = new Brand("Logitech");

        Product base1 = new Base("Moza r9", 1000, 10 ,"src/img/Moza-Racing-R9.png", brand1,1500,Mra.NINE);
        Product base2 = new Base("Moza r12", 1200, 8, "src/img/Moza-Racing-R12.jpg", brand1,1700,Mra.TWELVE);
        Product base3 = new Base("Moza r16", 1500, 5, "src/img/Moza-Racing-R16.jpg", brand1,2000,Mra.SIXTEEN);

        Product wheel1 = new Wheel("Moza cv2",2000,5,"src/img/Moza-cs-v2.jpg",brand1, Fabric.Alcantara, 2);
        Product wheel2 = new Wheel("Moza fsr2",3000,10,"src/img/Moza-fsr2.jpg",brand1, Fabric.Leather, 1);
        Product wheel3 = new Wheel("Moza Vision",3000,10,"src/img/Moza-Vision.jpg",brand1, Fabric.Leather, 1);


        Product pedal1 = new Pedals("Moza SRP", 2000, 10, "src/img/Moza-SR-P.jpg", brand1,false);
        Product pedal2 = new Pedals("Moza SRP clutch", 2200, 8, "src/img/Moza-SRP-P.jpg", brand1,true);

        Discount discount1 = new Discount(base1, 10);
        Discount discount2 = new Discount(base3, 15);

        LocalDate date1 = LocalDate.of(2004, 8, 5);
        LocalDate date2 = LocalDate.of(1999, 3, 15);
        LocalDate date3 = LocalDate.of(1990, 12, 1);

        User user1 = new User("juks", "kacper@iqp.pl", "1234", Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()), "Koszykowa 86");
        User user2 = new User("marta", "marta@xyz.pl", "abcd", Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()), "Piękna 10");
        User user3 = new User("john", "john@abc.com", "pass", Date.from(date3.atStartOfDay(ZoneId.systemDefault()).toInstant()), "Main Street 123");

        Admin admin1 = new Admin(user1,5);
        Admin admin2 = new Admin(user2,3);

        admin1.addToCart(base1,3);
        admin1.addToCart(base2,2);
        admin1.orderProducts();

        admin2.addToCart(wheel2, 1);
        admin2.addToCart(pedal2, 2);
        admin2.orderProducts();

        admin1.addToCart(base1,3);
        admin2.addToCart(wheel2, 1);
        admin2.addToCart(wheel1, 1);
        admin1.addToCart(base2,2);
        admin1.orderProducts();

        admin2.addToCart(wheel2, 8);
        admin2.addToCart(wheel2, 4);
        admin2.addToCart(pedal2, 3);
        admin2.orderProducts();

        admin1.addToCart(base1,3);
        admin1.addToCart(base2,2);
        admin1.addToCart(base3,6);
        admin1.orderProducts();

        admin2.addToCart(wheel1, 6);
        admin2.addToCart(wheel2, 1);
        admin2.addToCart(pedal2, 2);
        admin2.addToCart(pedal1, 2);
        admin2.orderProducts();

        user1.addBalance(15000);
        user1.addToCart(base1, 2);
        user1.addToCart(base2, 1);
        user1.addToCart(wheel1, 1);
        user1.addToCart(pedal2, 3);
        user1.placeOrder();

        user2.addBalance(7000);
        user2.addToCart(base3, 1);
        user2.addToCart(wheel1, 1);
        user2.placeOrder();

        user3.addBalance(3000);
        user3.addToCart(base2, 1);
        user3.addToCart(wheel2, 1);
        user3.addToCart(pedal2, 1);
        user3.placeOrder();
    }

    public static void setPerson(Person person) {
        Main.person = person;
    }

}