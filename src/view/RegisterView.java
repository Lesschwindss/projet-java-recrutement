package view;

import controller.AuthController;
import main.Main;
import model.Utilisateur;
import javax.swing.*;
import java.awt.*;

public class RegisterView extends JPanel {
    public RegisterView() {
        setLayout(new GridLayout(9, 2, 10, 10));

        JTextField nomField = new JTextField();
        JTextField prenomField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField mdpField = new JPasswordField();
        JComboBox<String> typeBox = new JComboBox<>(new String[]{"Candidat", "Recruteur"});

        // Nouveaux champs pour le sexe, la région et la tranche d'âge
        JComboBox<String> sexeBox = new JComboBox<>(new String[]{"Masculin", "Féminin"});
        JComboBox<String> regionBox = new JComboBox<>(new String[]{"Nord", "Sud", "Est", "Ouest"});
        JComboBox<String> trancheAgeBox = new JComboBox<>(new String[]{"Moins de 18", "18-30", "30-50", "50+"});

        JButton registerButton = new JButton("S'inscrire");
        JButton backToLogin = new JButton("Retour");

        add(new JLabel("Nom:")); add(nomField);
        add(new JLabel("Prénom:")); add(prenomField);
        add(new JLabel("Email:")); add(emailField);
        add(new JLabel("Mot de passe:")); add(mdpField);
        add(new JLabel("Type:")); add(typeBox);
        add(new JLabel("Sexe:")); add(sexeBox);
        add(new JLabel("Région:")); add(regionBox);
        add(new JLabel("Tranche d'âge:")); add(trancheAgeBox);
        add(registerButton); add(backToLogin);

        registerButton.addActionListener(e -> {
            // Récupérer les valeurs des champs
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            String email = emailField.getText();
            String motDePasse = new String(mdpField.getPassword());
            String type = (String) typeBox.getSelectedItem();
            Boolean sexe = sexeBox.getSelectedIndex() == 0; // 0 pour Masculin, 1 pour Féminin
            String region = (String) regionBox.getSelectedItem();
            String trancheAge = (String) trancheAgeBox.getSelectedItem();

            // Créer un objet Utilisateur sans les informations spécifiques au candidat
            Utilisateur u = new Utilisateur(nom, prenom, email, motDePasse, type);

            // Passer les informations supplémentaires à AuthController
            AuthController.register(u, sexe, region, trancheAge);
        });

        backToLogin.addActionListener(e -> Main.showLoginView());
    }
}
