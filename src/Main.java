import util.ObjectPlus;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Main {
    static Person person;
    public static void main(String[] args){
        Brand brand1 = new Brand("Moza Racing");
        Product base1 = new Base("Moza r9", 1000, 10 ,"img/Moza-Racing-R9.png", brand1,1500,Mra.NINE);
        Product base2 = new Base("Moza r12", 1000, 10, "img/Moza-Racing-R12.jpg", brand1,1500,Mra.TWELVE);
        Product base3 = new Base("Moza r16", 1000, 10, "img/Moza-Racing-R16.jpg", brand1,1500,Mra.SIXTEEN);
        Product wheel1 = new Wheel("Moza cv2",2000,5,"img/Moza-cs-v2.webp",brand1, Fabric.Alcantara, 2);
        Product wheel2 = new Wheel("Moza fsr2",3000,10,"img/Moza-fsr2.jpg",brand1, Fabric.Leather, 1);
        Product wheel3 = new Wheel("Moza vision",2000,10,"img/Moza-Vision.webp",brand1, Fabric.Leather, 3);
        Product pedal1 = new Pedals("Moza SRP", 2000, 10, "img/Moza-SR-P.jpg", brand1,false);
        Product pedal2 = new Pedals("Moza SRP cutch", 2000, 10, "img/Moza-SRP-P.webp", brand1,true);
        Product pedal3 = new Pedals("Moza SRP cutch lite", 2000, 10, "img/Moza-SRP-P-LITE.webp",brand1, true);
//        Bundle bundle1 = new Bundle("Moza bundle", 5000, 10, "img/Moza-Bundle.webp", "PC");
        Discount discount1 = new Discount(base1, 10);
        LocalDate localDate = LocalDate.of(2004, 8, 5);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        User user1 = new User(
                "juks",
                "kacper@iqp.pl",
                "1234",
                date,
                "Koszykowa 86"
        );
        Admin admin1 = new Admin(user1,5);
        admin1.addToCart(base1,3);
        admin1.addToCart(base2,2);
        admin1.showCart();
        admin1.OrderProducts();
        System.out.println(base1.getQuantity());
        System.out.println("---------");
        for(OrderToShop product : ObjectPlus.getExtentFromClass(OrderToShop.class)) {
            System.out.println(product.toString());
        }
        System.out.println("---------");

//        admin1.makeNewOrderToShop();
//        System.out.println(base1.getQuantity());

        System.out.println(base1.getSerial());
        System.out.println(base2.getSerial());
        System.out.println(wheel1.getSerial());
        System.out.println(wheel2.getSerial());
//        System.out.println(bundle1.getSerial());

        user1.addBalance(15000);
        user1.addToCart(base1, 2);
        user1.addToCart(base2, 1);
        user1.addToCart(wheel1, 1);
        user1.addToCart(pedal2, 3);
        user1.showCart();
        user1.placeOrder();
        System.out.println(user1.getBalance());
        user1.showCart();
        System.out.println(user1.getAge());
        System.out.println(user1.getOrders());
        System.out.println(user1.getOrders().getFirst().getStatus());
//        Returned_Order returnedOrder = new Returned_Order(user1.getOrders().getFirst(), "Damaged product");
        System.out.println(user1.getOrders().getFirst().getStatus());
        try{
            ObjectPlus.saveExtent();

        }catch(Exception e){
            System.out.println("Error saving extent: " + e.getMessage());
        }
//        try{
//            ObjectPlus.loadExtent();
//        } catch (IOException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("------------------------");
//        System.out.println(ObjectPlus.getExtentFromClass(Admin.class));
//        System.out.println(ObjectPlus.getExtentFromClass(User.class));
//        System.out.println(ObjectPlus.getExtentFromClass(Product.class));
//        System.out.println(ObjectPlus.getExtentFromClass(Order.class));
//        System.out.println(ObjectPlus.getExtentFromClass(Returned_Order.class));
//        System.out.println(ObjectPlus.getExtentFromClass(Discount.class));
//        System.out.println(ObjectPlus.getExtentFromClass(Person.class));
//        System.out.println(ObjectPlus.getExtentFromClass(Base.class));
//        System.out.println(Person.verifyCredentials("juks","1234"));
//        System.out.println(Person.verifyCredentialsTF("juks","1234"));
//        System.out.println(person);
        new Login();
//        new OrderToShopBrowser();
    }
    public static void setPerson(Person person) {
        Main.person = person;
    }
}