// Implémentation
package view;

import javax.swing.*;
import java.awt.*;

public class RecruteurView extends JPanel {

    public RecruteurView(JFrame parentFrame) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Espace Recruteur", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JTextArea area = new JTextArea();
        area.setText("Bienvenue dans l’espace recruteur !\n\nFonctionnalités à ajouter :\n- Ajouter / Supprimer / Modifier des offres\n- Voir les candidatures\n- Générer des statistiques");

        add(title, BorderLayout.NORTH);
        add(new JScrollPane(area), BorderLayout.CENTER);
    }
}

