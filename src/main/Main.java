package main;

import view.*;
import model.Recruteur;

import javax.swing.*;

public class Main {

    public static JFrame frame;
    public static Recruteur recruteurConnecte;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Projet Recrutement");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 400);
            frame.setLocationRelativeTo(null);
            showLoginView();
        });
    }

    // Affiche la vue de connexion
    public static void showLoginView() {
        frame.setContentPane(new LoginView());
        frame.revalidate();
        frame.setVisible(true);
    }

    // Affiche la vue d'inscription
    public static void showRegisterView() {
        frame.setContentPane(new RegisterView());
        frame.revalidate();
    }

    /**
     * Redirection vers le dashboard selon le type utilisateur
     * et stockage du recruteur si applicable
     */
    public static void goToDashboard(String userType, Recruteur recruteur) {
        if (userType.equalsIgnoreCase("Candidat")) {
            frame.setContentPane(new CandidatView());
        } else if (userType.equalsIgnoreCase("Recruteur")) {
            recruteurConnecte = recruteur; // ✅ stocke le recruteur connecté
            frame.setContentPane(new RecruteurView());
        }
        frame.revalidate();
    }

    // Affiche la vue de statistiques
    public static void showReportingView() {
        frame.setContentPane(new ReportingView());
        frame.revalidate();
    }
}
