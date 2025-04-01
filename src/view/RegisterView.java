// Implémentation
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegisterView extends JPanel {
    public RegisterView(JFrame parentFrame) {
        setLayout(new GridLayout(6, 2, 10, 10));

        JTextField nomField = new JTextField();
        JTextField prenomField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField mdpField = new JPasswordField();

        String[] types = {"Candidat", "Recruteur"};
        JComboBox<String> typeBox = new JComboBox<>(types);

        JButton registerButton = new JButton("S'inscrire");
        JButton backToLogin = new JButton("Retour");

        add(new JLabel("Nom:"));
        add(nomField);
        add(new JLabel("Prénom:"));
        add(prenomField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Mot de passe:"));
        add(mdpField);
        add(new JLabel("Type:"));
        add(typeBox);
        add(registerButton);
        add(backToLogin);

        backToLogin.addActionListener((ActionEvent e) -> {
            parentFrame.setContentPane(new LoginView(parentFrame));
            parentFrame.revalidate();
        });
    }
}
