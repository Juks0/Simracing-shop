import javax.swing.*;

public class Login {
    private JButton loginAsAdminButton;
    private JTextField mailTextField;
    private JTextField passwordTextField;
    private JPanel panel1;
    private JFrame frame;

    Login(){
        frame = new JFrame("Admin UI");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        loginAsAdminButton.addActionListener(e -> {
            String login = mailTextField.getText();
            String password = passwordTextField.getText();
            if (Person.verifyCredentialsTF(login, password)) {
                Main.setPerson(Person.verifyCredentials(login, password));
                SwingUtilities.getWindowAncestor(loginAsAdminButton).dispose();
                CategoriesUI categoriesUI = new CategoriesUI();
                categoriesUI.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "Invalid login or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

}
