// === view/RegisterView.java ===
package view;

import controller.AuthController;
import main.Main;
import model.Utilisateur;
import javax.swing.*;
import java.awt.*;

public class RegisterView extends JPanel {
    public RegisterView() {
        setLayout(new GridLayout(6,2,10,10));

        JTextField nomField = new JTextField();
        JTextField prenomField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField mdpField = new JPasswordField();
        JComboBox<String> typeBox = new JComboBox<>(new String[]{"Candidat", "Recruteur"});

        JButton registerButton = new JButton("S'inscrire");
        JButton backToLogin = new JButton("Retour");

        add(new JLabel("Nom:")); add(nomField);
        add(new JLabel("Prénom:")); add(prenomField);
        add(new JLabel("Email:")); add(emailField);
        add(new JLabel("Mot de passe:")); add(mdpField);
        add(new JLabel("Type:")); add(typeBox);
        add(registerButton); add(backToLogin);

        registerButton.addActionListener(e -> {
            Utilisateur u = new Utilisateur(nomField.getText(), prenomField.getText(), emailField.getText(), new String(mdpField.getPassword()), (String) typeBox.getSelectedItem());
            AuthController.register(u);
        });

        backToLogin.addActionListener(e -> Main.showLoginView());
    }
}

