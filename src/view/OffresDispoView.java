package view;

import controller.CandidatController;
import model.Offre;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class OffresDispoView extends JPanel {

    public OffresDispoView(int candidatId) {
        setLayout(new BorderLayout());

        JLabel titre = new JLabel("Liste des offres disponibles");
        titre.setHorizontalAlignment(SwingConstants.CENTER);
        add(titre, BorderLayout.NORTH);

        // --- Barre de filtre par catégorie ---
        JComboBox<String> filtreCategorie = new JComboBox<>(new String[]{
                "Toutes", "Direction", "Ingénierie", "Marketing", "Finance", "RH"
        });
        JPanel filtrePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filtrePanel.add(new JLabel("Filtrer par catégorie :"));
        filtrePanel.add(filtreCategorie);
        add(filtrePanel, BorderLayout.BEFORE_FIRST_LINE);

        // --- Table des offres ---
        String[] colonnes = {"ID", "Titre", "Description", "Compétences", "Statut", "Catégorie"};
        DefaultTableModel tableModel = new DefaultTableModel(colonnes, 0);
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        List<Offre> toutesLesOffres = CandidatController.getToutesLesOffres();

        Runnable rafraichirTable = () -> {
            tableModel.setRowCount(0);
            String selectedCategorie = (String) filtreCategorie.getSelectedItem();
            for (Offre o : toutesLesOffres) {
                if (selectedCategorie.equals("Toutes") ||
                        (o.getCategorie() != null && o.getCategorie().equalsIgnoreCase(selectedCategorie))) {
                    tableModel.addRow(new Object[]{
                            o.getId(), o.getTitre(), o.getDescription(),
                            o.getCompetencesRequises(), o.getStatut(), o.getCategorie()
                    });
                }
            }
        };

        rafraichirTable.run(); // initial load
        filtreCategorie.addActionListener(e -> rafraichirTable.run());

        // --- Boutons Postuler et Retour ---
        JButton btnPostuler = new JButton("Postuler à l'offre sélectionnée");
        btnPostuler.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int idOffre = (int) tableModel.getValueAt(row, 0);

                JTextArea lettreArea = new JTextArea(10, 30);
                JScrollPane scrollPane = new JScrollPane(lettreArea);
                int result = JOptionPane.showConfirmDialog(this, scrollPane, "Votre lettre de motivation",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    String lettre = lettreArea.getText();
                    if (lettre.isBlank()) {
                        JOptionPane.showMessageDialog(this, "Veuillez écrire une lettre.");
                    } else {
                        CandidatController.postulerAOffre(candidatId, idOffre, lettre);
                        JOptionPane.showMessageDialog(this, "Candidature envoyée !");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une offre.");
            }
        });

        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.setContentPane(new CandidatView(candidatId));
            topFrame.revalidate();
        });

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(btnPostuler);
        bottomPanel.add(btnRetour);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}