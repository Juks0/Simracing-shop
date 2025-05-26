import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JButton loginAsAdminButton;
    private JTextField mailTextField;
    private JTextField passwordTextField;


    Login(){
        loginAsAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = mailTextField.getText();
                String password = passwordTextField.getText();

                if (Person.verifyCredentialsTF(login, password)) {
                    // Close current login window
                    SwingUtilities.getWindowAncestor(loginAsAdminButton).dispose();

                    // Open new window
                    AdminUI adminUI = new AdminUI();
                    adminUI.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid login or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

}
