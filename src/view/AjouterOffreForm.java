package view;

import dao.OffreDAO;
import model.Offre;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AjouterOffreForm extends JFrame {

    public AjouterOffreForm(int recruteurId) {
        setTitle("Ajouter une offre");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 10, 10));

        JTextField titreField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField competencesField = new JTextField();
        JComboBox<String> statutBox = new JComboBox<>(new String[]{"Ouvert", "Fermé"});
        JComboBox<String> categorieBox = new JComboBox<>(new String[]{
                "Direction", "Ingénierie", "Marketing", "Finance", "RH"
        });

        JButton btnValider = new JButton("Valider");

        add(new JLabel("Titre :"));
        add(titreField);
        add(new JLabel("Description :"));
        add(descriptionField);
        add(new JLabel("Compétences requises :"));
        add(competencesField);
        add(new JLabel("Statut :"));
        add(statutBox);
        add(new JLabel("Catégorie :"));
        add(categorieBox);
        add(new JLabel(""));
        add(btnValider);

        btnValider.addActionListener(e -> {
            Offre offre = new Offre(
                    0,
                    titreField.getText(),
                    descriptionField.getText(),
                    competencesField.getText(),
                    statutBox.getSelectedItem().toString(),
                    recruteurId,
                    categorieBox.getSelectedItem().toString()
            );


            try {
                new OffreDAO().ajouterOffre(offre);
                JOptionPane.showMessageDialog(this, "Offre ajoutée avec succès !");
                dispose();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout.");
            }
        });

        setVisible(true);
    }
}
