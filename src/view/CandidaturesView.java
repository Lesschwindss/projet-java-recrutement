package view;

import dao.CandidatureDAO;
import model.Candidature;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;

public class CandidaturesView extends JPanel {

    private JTable table;
    private CandidatureTableModel tableModel;

    public CandidaturesView() {
        setLayout(new BorderLayout());

        JLabel titre = new JLabel("Candidatures reçues", JLabel.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 18));
        add(titre, BorderLayout.NORTH);

        // Charger les candidatures depuis la BDD
        List<Candidature> candidatures = CandidatureDAO.getToutesLesCandidatures();
        tableModel = new CandidatureTableModel(candidatures);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panneau bas avec boutons
        JPanel bottomPanel = new JPanel();

        JButton btnAccepter = new JButton("Accepter");
        btnAccepter.addActionListener(e -> accepterCandidature());

        JButton btnRefuser = new JButton("Refusé");
        btnRefuser.addActionListener(e -> refuserCandidature());

        JButton btnVoirProfil = new JButton("Voir Profil");
        btnVoirProfil.addActionListener(e -> voirProfil());

        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.setContentPane(new RecruteurView());
            frame.revalidate();
        });

        bottomPanel.add(btnAccepter);
        bottomPanel.add(btnRefuser);
        bottomPanel.add(btnVoirProfil);
        bottomPanel.add(btnRetour);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void accepterCandidature() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            Candidature candidature = tableModel.getCandidatureAt(selectedRow);
            candidature.setStatut("Acceptée");
            CandidatureDAO.updateStatut(candidature.getId(), "Acceptée");
            JOptionPane.showMessageDialog(this, "Candidature acceptée !");
            tableModel.fireTableRowsUpdated(selectedRow, selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une candidature.");
        }
    }

    private void refuserCandidature() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            Candidature candidature = tableModel.getCandidatureAt(selectedRow);
            candidature.setStatut("Refusée");
            CandidatureDAO.updateStatut(candidature.getId(), "Refusée");
            JOptionPane.showMessageDialog(this, "Candidature refusée !");
            tableModel.fireTableRowsUpdated(selectedRow, selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une candidature.");
        }
    }

    private void voirProfil() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            Candidature candidature = tableModel.getCandidatureAt(selectedRow);
            int idCandidat = candidature.getIdCandidat();
            // Afficher une nouvelle fenêtre ou panneau avec les infos du profil candidat
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.setContentPane(new ProfilCandidatView(idCandidat));
            frame.revalidate();
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une candidature.");
        }
    }

    // === TableModel interne ===
    static class CandidatureTableModel extends AbstractTableModel {

        private final String[] colonnes = {"ID", "Candidat", "Offre", "Statut"};
        private final List<Candidature> candidatures;

        public CandidatureTableModel(List<Candidature> candidatures) {
            this.candidatures = candidatures;
        }

        @Override
        public int getRowCount() {
            return candidatures.size();
        }

        @Override
        public int getColumnCount() {
            return colonnes.length;
        }

        @Override
        public String getColumnName(int column) {
            return colonnes[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Candidature c = candidatures.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> c.getId();
                case 1 -> CandidatureDAO.getNomCandidat(c.getIdCandidat());
                case 2 -> CandidatureDAO.getTitreOffre(c.getIdOffre());
                case 3 -> c.getStatut();
                default -> null;
            };
        }

        public Candidature getCandidatureAt(int row) {
            return candidatures.get(row);
        }
    }
}
