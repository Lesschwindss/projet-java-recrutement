// === view/CandidatView.java ===
package view;
import main.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CandidatView extends JPanel {
    public CandidatView() {
        setLayout(new GridLayout(3,1,10,10));

        JButton btnConsulterOffres = new JButton("Consulter les offres");
        JButton btnConsulterHistorique = new JButton("Consulter l'historique");
        JButton btnDeconnexion = new JButton("Déconnexion");

        btnDeconnexion.addActionListener((ActionEvent e) -> Main.showLoginView());

        add(btnConsulterOffres);
        add(btnConsulterHistorique);
        add(btnDeconnexion);
    }
}
