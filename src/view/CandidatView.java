// === view/CandidatView.java ===
package view;

import controller.CandidatController;
import javax.swing.*;
import java.awt.*;

public class CandidatView extends JPanel {
    public CandidatView(int candidatId) {
        setLayout(new GridLayout(3,1,10,10));

        JButton btnConsulterOffres = new JButton("Consulter les offres");
        JButton btnConsulterHistorique = new JButton("Consulter l'historique");
        JButton btnDeconnexion = new JButton("Déconnexion");

        btnConsulterOffres.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.setContentPane(new OffresDispoView(candidatId));
            topFrame.revalidate();
        });
        btnConsulterHistorique.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.setContentPane(new HistoriqueCandidatureView(candidatId));
            topFrame.revalidate();
        });
        btnDeconnexion.addActionListener(e -> CandidatController.logout());

        add(btnConsulterOffres);
        add(btnConsulterHistorique);
        add(btnDeconnexion);
    }
}
