// === view/LoginView.java ===
package view;

import controller.AuthController;
import main.Main;
import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel {
    public LoginView() {
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Mot de passe:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Se connecter");
        JButton goToRegister = new JButton("Créer un compte");

        add(emailLabel); add(emailField);
        add(passwordLabel); add(passwordField);
        add(loginButton); add(goToRegister);

        loginButton.addActionListener(e -> AuthController.login(emailField.getText(), new String(passwordField.getPassword())));
        goToRegister.addActionListener(e -> Main.showRegisterView());
    }
}
