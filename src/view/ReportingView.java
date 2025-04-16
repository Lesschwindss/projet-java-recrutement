package view;

import dao.CandidatDAO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ReportingView extends JPanel {

    public ReportingView() {
        setLayout(new BorderLayout());

        JPanel graphPanel = new JPanel(new GridLayout(3, 1));

        CandidatDAO dao = new CandidatDAO();

        // Graphique 1 : Genre
        Map<String, Integer> statsGenre = dao.getNombreCandidatsParGenre();
        graphPanel.add(creerPanelGraphiqueCirculaire(statsGenre, "Répartition par Genre"));

        // Graphique 2 : Tranche d’âge
        Map<String, Integer> statsAge = dao.getNombreCandidatsParTrancheAge();
        graphPanel.add(creerPanelGraphiqueCirculaire(statsAge, "Répartition par Tranche d'Âge"));

        // Graphique 3 : Région
        Map<String, Integer> statsRegion = dao.getNombreCandidatsParRegion();
        graphPanel.add(creerPanelGraphiqueCirculaire(statsRegion, "Répartition par Région"));
        add(graphPanel, BorderLayout.CENTER);

        // Bouton Retour
        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.setContentPane(new RecruteurView());  // Reviens à la vue classique sans parent
            frame.revalidate();
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnRetour);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private ChartPanel creerPanelGraphiqueCirculaire(Map<String, Integer> donnees, String titre) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        for (Map.Entry<String, Integer> entry : donnees.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            if (key != null && value != null) {
                dataset.setValue(key, value);
            } else {
                // Remplacer null par "Inconnu" ou ignorer selon tes besoins
                dataset.setValue(key == null ? "Inconnu" : key, value != null ? value : 0);
            }
        }

        JFreeChart chart = ChartFactory.createPieChart(titre, dataset, true, true, false);
        return new ChartPanel(chart);
    }
}
