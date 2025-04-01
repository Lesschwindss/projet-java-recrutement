// Implémentation
import javax.swing.*;
import view.LoginView;
import view.RegisterView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Choix de la vue à afficher au démarrage
            JFrame frame = new JFrame("Application Recrutement");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);

            // Afficher soit la LoginView soit la RegisterView (ici Login par défaut)
            frame.setContentPane(new LoginView(frame));
            frame.setVisible(true);
        });
    }
}
