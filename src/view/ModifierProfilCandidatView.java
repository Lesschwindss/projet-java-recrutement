package view;

import dao.CandidatDAO;
import model.Candidat;

import javax.swing.*;
import java.awt.*;

public class ModifierProfilCandidatView extends JPanel {

    public ModifierProfilCandidatView(Candidat candidat) {
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(9, 2, 10, 10));

        JTextField nomField = new JTextField(candidat.getNom());
        JTextField emailField = new JTextField(candidat.getEmail());
        emailField.setEnabled(false);

        JPasswordField mdpField = new JPasswordField(candidat.getMotDePasse());

        JTextArea compArea = new JTextArea(candidat.getCompetences());
        JTextArea expArea = new JTextArea(candidat.getExperience());

        JComboBox<String> sexeBox = new JComboBox<>(new String[]{"Femme", "Homme"});
        sexeBox.setSelectedIndex(candidat.getSexe() ? 1 : 0);

        JComboBox<String> regionBox = new JComboBox<>(new String[]{"Nord", "Sud", "Est", "Ouest"});
        regionBox.setSelectedItem(candidat.getRegion());

        JComboBox<String> trancheBox = new JComboBox<>(new String[]{"Moins de 18", "18-30", "30-50", "50+"});
        trancheBox.setSelectedItem(candidat.getTrancheAge());

        // Champs
        formPanel.add(new JLabel("Nom")); formPanel.add(nomField);
        formPanel.add(new JLabel("Email")); formPanel.add(emailField);
        formPanel.add(new JLabel("Mot de passe")); formPanel.add(mdpField);
        formPanel.add(new JLabel("Compétences")); formPanel.add(new JScrollPane(compArea));
        formPanel.add(new JLabel("Expérience")); formPanel.add(new JScrollPane(expArea));
        formPanel.add(new JLabel("Sexe")); formPanel.add(sexeBox);
        formPanel.add(new JLabel("Région")); formPanel.add(regionBox);
        formPanel.add(new JLabel("Tranche d'âge")); formPanel.add(trancheBox);

        // Boutons
        JPanel boutonPanel = new JPanel(new FlowLayout());

        JButton enregistrer = new JButton("Enregistrer");
        enregistrer.addActionListener(e -> {
            candidat.setNom(nomField.getText());
            candidat.setMotDePasse(new String(mdpField.getPassword()));
            candidat.setCompetences(compArea.getText());
            candidat.setExperience(expArea.getText());
            candidat.setSexe(sexeBox.getSelectedIndex() == 1);
            candidat.setRegion((String) regionBox.getSelectedItem());
            candidat.setTrancheAge((String) trancheBox.getSelectedItem());

            CandidatDAO dao = new CandidatDAO();
            dao.modifierCandidat(candidat);

            JOptionPane.showMessageDialog(this, "Profil mis à jour !");
        });

        JButton retour = new JButton("Retour");
        retour.addActionListener(e -> {
            JFrame top = (JFrame) SwingUtilities.getWindowAncestor(this);
            top.setContentPane(new CandidatView(candidat.getId()));
            top.revalidate();
        });

        boutonPanel.add(enregistrer);
        boutonPanel.add(retour);

        add(formPanel, BorderLayout.CENTER);
        add(boutonPanel, BorderLayout.SOUTH);
    }
}

