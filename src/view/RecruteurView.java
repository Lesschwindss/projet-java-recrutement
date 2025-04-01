// Implémentation
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RecruteurView extends JPanel {

    public RecruteurView(JFrame parentFrame) {
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Espace Recruteur", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        // Zone centrale : gestion des offres
        JPanel centerPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        JButton btnAjouterOffre = new JButton("Ajouter une offre");
        JButton btnModifierOffre = new JButton("Modifier une offre");
        JButton btnSupprimerOffre = new JButton("Supprimer une offre");
        JButton btnConsulterCandidatures = new JButton("Consulter les candidatures");
        JButton btnVoirStats = new JButton("Voir les statistiques");

        centerPanel.add(btnAjouterOffre);
        centerPanel.add(btnModifierOffre);
        centerPanel.add(btnSupprimerOffre);
        centerPanel.add(btnConsulterCandidatures);
        centerPanel.add(btnVoirStats);

        // Zone bas : bouton de déconnexion
        JPanel bottomPanel = new JPanel();
        JButton btnDeconnexion = new JButton("Déconnexion");

        btnDeconnexion.addActionListener((ActionEvent e) -> {
            parentFrame.setContentPane(new LoginView(parentFrame));
            parentFrame.revalidate();
        });

        bottomPanel.add(btnDeconnexion);

        add(title, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
