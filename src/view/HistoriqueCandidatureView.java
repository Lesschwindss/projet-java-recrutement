package view;

import controller.CandidatController;
import model.Candidature;
import dao.CandidatureDAO;
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

        JButton supprimerBtn = new JButton("Supprimer la candidature");
        supprimerBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String statut = table.getValueAt(selectedRow, 1).toString();
                int idOffre = (int) table.getValueAt(selectedRow, 0);

                if (!statut.equalsIgnoreCase("En attente")) {
                    JOptionPane.showMessageDialog(this, "Seules les candidatures 'En attente' peuvent être supprimées.");
                    return;
                }

                CandidatureDAO dao = new CandidatureDAO();
                List<Candidature> candidatures2 = CandidatController.getHistoriqueCandidatures(candidatId);
                for (Candidature c : candidatures2) {
                    if (c.getIdOffre() == idOffre && c.getStatut().equalsIgnoreCase("En attente")) {
                        dao.supprimerCandidature(c.getId());
                        JOptionPane.showMessageDialog(this, "Candidature supprimée.");
                        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                        frame.setContentPane(new HistoriqueCandidatureView(candidatId));
                        frame.revalidate();
                        break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Sélectionnez une ligne.");
            }
        });

        JPanel bas = new JPanel(new FlowLayout());
        bas.add(supprimerBtn);
        bas.add(btnRetour);
        add(bas, BorderLayout.SOUTH);

    }
}