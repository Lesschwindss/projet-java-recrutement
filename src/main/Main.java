package main;

import view.*;
import javax.swing.*;

public class Main {

    public static JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Projet Recrutement");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 400);
            frame.setLocationRelativeTo(null);
            showLoginView();
        });
    }

    // Affichage de LoginView
    public static void showLoginView() {
        frame.setContentPane(new LoginView());
        frame.revalidate();
        frame.setVisible(true);
    }

    // Affichage de RegisterView
    public static void showRegisterView() {
        frame.setContentPane(new RegisterView());
        frame.revalidate();
    }

    // Redirection selon le type utilisateur simulé
    public static void goToDashboard(String userType) {
        if(userType.equalsIgnoreCase("Candidat")) {
            frame.setContentPane(new CandidatView());
        } else if(userType.equalsIgnoreCase("Recruteur")) {
            frame.setContentPane(new RecruteurView());
        }
        frame.revalidate();
    }

    // Redirection vers ReportingView
    public static void showReportingView() {
        frame.setContentPane(new ReportingView());
        frame.revalidate();
    }
}
