// === view/RegisterView.java ===
package view;

import main.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegisterView extends JPanel {
    public RegisterView() {
        setLayout(new GridLayout(6,2,10,10));

        add(new JLabel("Nom:")); add(new JTextField());
        add(new JLabel("Prénom:")); add(new JTextField());
        add(new JLabel("Email:")); add(new JTextField());
        add(new JLabel("Mot de passe:")); add(new JPasswordField());
        add(new JLabel("Type:")); add(new JComboBox<>(new String[]{"Candidat", "Recruteur"}));

        JButton registerButton = new JButton("S'inscrire");
        JButton backToLogin = new JButton("Retour");

        add(registerButton); add(backToLogin);

        backToLogin.addActionListener((ActionEvent e) -> Main.showLoginView());
    }
}

