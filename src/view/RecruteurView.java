// === view/RecruteurView.java ===
package view;

import controller.RecruteurController;
import javax.swing.*;
import java.awt.*;

public class RecruteurView extends JPanel {
    public RecruteurView() {
        setLayout(new GridLayout(5,1,10,10));

        JButton btnAjouterOffre = new JButton("Ajouter une offre");
        JButton btnModifierOffre = new JButton("Modifier une offre");
        JButton btnSupprimerOffre = new JButton("Supprimer une offre");
        JButton btnVoirStats = new JButton("Voir les statistiques");
        JButton btnDeconnexion = new JButton("Déconnexion");

        btnAjouterOffre.addActionListener(e -> RecruteurController.ajouterOffre());
        btnModifierOffre.addActionListener(e -> RecruteurController.modifierOffre());
        btnSupprimerOffre.addActionListener(e -> RecruteurController.supprimerOffre());
        btnVoirStats.addActionListener(e -> RecruteurController.voirStatistiques());
        btnDeconnexion.addActionListener(e -> RecruteurController.logout());

        add(btnAjouterOffre);
        add(btnModifierOffre);
        add(btnSupprimerOffre);
        add(btnVoirStats);
        add(btnDeconnexion);
    }
}
