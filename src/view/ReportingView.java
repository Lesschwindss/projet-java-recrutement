// === view/ReportingView.java ===
package view;

import javax.swing.*;
import java.awt.*;
import main.Main;


public class ReportingView extends JPanel {
    public ReportingView() {
        setLayout(new BorderLayout());
        add(new JLabel("[Graphiques générés ici avec JFreeChart]", JLabel.CENTER), BorderLayout.CENTER);
        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(e -> Main.showLoginView());
        add(btnRetour, BorderLayout.SOUTH);
    }
}
