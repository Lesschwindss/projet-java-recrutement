package view;

import dao.OffreDAO;
import model.Offre;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ModifierOffreForm extends JFrame {
    public ModifierOffreForm() {
        setTitle("Modifier une offre");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        JComboBox<Offre> comboOffres = new JComboBox<>();
        JTextField titreField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField competencesField = new JTextField();
        JComboBox<String> statutBox = new JComboBox<>(new String[]{"Ouvert", "Fermé"});
        JButton btnValider = new JButton("Enregistrer");

        try {
            List<Offre> offres = new OffreDAO().obtenirToutesLesOffres();
            for (Offre offre : offres) comboOffres.addItem(offre);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        comboOffres.addActionListener(e -> {
            Offre selected = (Offre) comboOffres.getSelectedItem();
            if (selected != null) {
                titreField.setText(selected.getTitre());
                descriptionField.setText(selected.getDescription());
                competencesField.setText(selected.getCompetencesRequises());
                statutBox.setSelectedItem(selected.getStatut());
            }
        });

        add(new JLabel("Choisir l'offre :"));
        add(comboOffres);
        add(new JLabel("Titre :"));
        add(titreField);
        add(new JLabel("Description :"));
        add(descriptionField);
        add(new JLabel("Compétences :"));
        add(competencesField);
        add(new JLabel("Statut :"));
        add(statutBox);
        add(new JLabel(""));
        add(btnValider);

        btnValider.addActionListener(e -> {
            Offre selected = (Offre) comboOffres.getSelectedItem();
            if (selected != null) {
                selected.setTitre(titreField.getText());
                selected.setDescription(descriptionField.getText());
                selected.setCompetencesRequises(competencesField.getText());
                selected.setStatut(statutBox.getSelectedItem().toString());

                try {
                    new OffreDAO().modifierOffre(selected);
                    JOptionPane.showMessageDialog(this, "Offre modifiée !");
                    dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Erreur !");
                }
            }
        });

        setVisible(true);
    }
}
