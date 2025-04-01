// Implémentation
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginView extends JPanel {
    public LoginView(JFrame parentFrame) {
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Mot de passe:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Se connecter");
        JButton goToRegister = new JButton("Créer un compte");

        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(goToRegister);

        goToRegister.addActionListener((ActionEvent e) -> {
            parentFrame.setContentPane(new RegisterView(parentFrame));
            parentFrame.revalidate();
        });
    }
}
