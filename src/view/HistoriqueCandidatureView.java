package view;

import controller.CandidatController;
import model.Candidature;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HistoriqueCandidatureView extends JPanel {

    public HistoriqueCandidatureView(int candidatId) {
        setLayout(new BorderLayout());

        JLabel titre = new JLabel("Historique de vos candidatures");
        titre.setHorizontalAlignment(SwingConstants.CENTER);
        add(titre, BorderLayout.NORTH);

        String[] colonnes = {"ID Offre", "Statut"};
        DefaultTableModel model = new DefaultTableModel(colonnes, 0);
        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Récupération et affichage des candidatures
        List<Candidature> candidatures = CandidatController.getHistoriqueCandidatures(candidatId);
        for (Candidature c : candidatures) {
            model.addRow(new Object[]{c.getIdOffre(), c.getStatut()});
        }

        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.setContentPane(new CandidatView(candidatId));
            topFrame.revalidate();
        });

        add(btnRetour, BorderLayout.SOUTH);
    }
}
