package view;

import controller.CandidatController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import model.Offre;

public class OffresDispoView extends JPanel {

    public OffresDispoView(int candidatId) {
        setLayout(new BorderLayout());

        JLabel titre = new JLabel("Liste des offres disponibles");
        titre.setHorizontalAlignment(SwingConstants.CENTER);
        add(titre, BorderLayout.NORTH);

        String[] colonnes = {"ID", "Titre", "Description", "Compétences", "Statut"};
        DefaultTableModel tableModel = new DefaultTableModel(colonnes, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Remplir la table
        List<Offre> offres = controller.CandidatController.getToutesLesOffres();
        for (Offre o : offres) {
            tableModel.addRow(new Object[]{
                    o.getId(),
                    o.getTitre(),
                    o.getDescription(),
                    o.getCompetencesRequises(),
                    o.getStatut()
            });
        }

        JButton btnPostuler = new JButton("Postuler à l'offre sélectionnée");
        btnPostuler.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int idOffre = (int) tableModel.getValueAt(row, 0);
                CandidatController.postulerAOffre(candidatId, idOffre);
                JOptionPane.showMessageDialog(this, "Candidature envoyée !");
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une offre.");
            }
        });

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(btnPostuler);

        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.setContentPane(new CandidatView(candidatId));
            topFrame.revalidate();
        });
        bottomPanel.add(btnRetour);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}

