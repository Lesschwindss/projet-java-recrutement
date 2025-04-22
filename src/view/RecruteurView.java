package view;

import controller.RecruteurController;
import javax.swing.*;
import java.awt.*;

public class RecruteurView extends JPanel {

    public RecruteurView() {
        setLayout(new GridLayout(6, 1, 10, 10));

        JButton btnAjouterOffre = new JButton("Ajouter une offre");
        JButton btnModifierOffre = new JButton("Modifier une offre");
        JButton btnSupprimerOffre = new JButton("Supprimer une offre");
        JButton btnVoirStats = new JButton("Voir les statistiques");
        JButton btnVoirCandidatures = new JButton("Voir les candidatures");
        JButton btnDeconnexion = new JButton("Déconnexion");

        btnAjouterOffre.addActionListener(e -> RecruteurController.ajouterOffre());
        btnModifierOffre.addActionListener(e -> RecruteurController.modifierOffre());
        btnSupprimerOffre.addActionListener(e -> RecruteurController.supprimerOffre());
        btnVoirStats.addActionListener(e -> RecruteurController.voirStatistiques());


        btnVoirCandidatures.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.setContentPane(new CandidaturesView());
            frame.revalidate();
        });

        btnDeconnexion.addActionListener(e -> RecruteurController.logout());

        add(btnAjouterOffre);
        add(btnModifierOffre);
        add(btnSupprimerOffre);
        add(btnVoirStats);
        add(btnVoirCandidatures); 
        add(btnDeconnexion);
    }
}
