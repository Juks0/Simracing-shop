import javax.swing.*;

public class MyFrame extends JFrame {
    private JPanel mainPanel;
    private JButton button1;

     MyFrame() {
         button1 = new JButton("Click me");
        button1.setBounds(100, 100, 200, 50);
        button1.addActionListener(e -> {
            Product base1 = new Base("Moza r9", 1000, 10, "img/Moza-Racing-R9.png", 1500,Mra.NINE);
        });
        this.setTitle("Simpshop");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 600);
        this.setVisible(true);
        this.add(button1);
        this.setLayout(null);
    }


}
