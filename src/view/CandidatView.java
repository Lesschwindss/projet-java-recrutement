// Implémentation
package view;

import javax.swing.*;
import java.awt.*;

public class CandidatView extends JPanel {

    public CandidatView(JFrame parentFrame) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Espace Candidat", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JTextArea area = new JTextArea();
        area.setText("Bienvenue dans l’espace candidat !\n\nFonctionnalités à ajouter :\n- Parcourir les offres\n- Postuler\n- Historique");

        add(title, BorderLayout.NORTH);
        add(new JScrollPane(area), BorderLayout.CENTER);
    }
}
