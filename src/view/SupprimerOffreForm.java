package view;

import dao.OffreDAO;
import model.Offre;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class SupprimerOffreForm extends JFrame {
    public SupprimerOffreForm() {
        setTitle("Supprimer une offre");
        setSize(400, 150);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));

        JComboBox<Offre> comboOffres = new JComboBox<>();
        JButton btnSupprimer = new JButton("Supprimer");

        try {
            List<Offre> offres = new OffreDAO().obtenirToutesLesOffres();
            for (Offre offre : offres) comboOffres.addItem(offre);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        add(new JLabel("Sélectionner une offre à supprimer :"));
        add(comboOffres);
        add(btnSupprimer);

        btnSupprimer.addActionListener(e -> {
            Offre selected = (Offre) comboOffres.getSelectedItem();
            if (selected != null) {
                try {
                    new OffreDAO().supprimerOffre(selected.getId());
                    JOptionPane.showMessageDialog(this, "Offre supprimée !");
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
