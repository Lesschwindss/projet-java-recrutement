// === view/RecruteurView.java ===
package view;
import main.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RecruteurView extends JPanel {
    public RecruteurView() {
        setLayout(new GridLayout(5,1,10,10));

        JButton btnAjouterOffre = new JButton("Ajouter une offre");
        JButton btnModifierOffre = new JButton("Modifier une offre");
        JButton btnSupprimerOffre = new JButton("Supprimer une offre");
        JButton btnVoirStats = new JButton("Voir les statistiques");
        JButton btnDeconnexion = new JButton("Déconnexion");

        btnDeconnexion.addActionListener((ActionEvent e) -> Main.showLoginView());
        btnVoirStats.addActionListener((ActionEvent e) -> Main.showReportingView());

        add(btnAjouterOffre);
        add(btnModifierOffre);
        add(btnSupprimerOffre);
        add(btnVoirStats);
        add(btnDeconnexion);
    }
}
