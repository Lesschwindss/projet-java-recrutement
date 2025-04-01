// === view/ReportingView.java ===
package view;

import main.Main;
import javax.swing.*;
import java.awt.*;

public class ReportingView extends JPanel {
    public ReportingView() {
        setLayout(new BorderLayout());

        add(new JLabel("[Graphiques générés ici avec JFreeChart]", JLabel.CENTER), BorderLayout.CENTER);
        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(e -> Main.goToDashboard("Recruteur"));
        add(btnRetour, BorderLayout.SOUTH);
    }
}
