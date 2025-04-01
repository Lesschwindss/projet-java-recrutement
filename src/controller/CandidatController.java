package controller;

import main.Main;

/**
 * Gère les actions du candidat
 */
public class CandidatController {

    /**
     * Redirige vers l'historique
     */
    public static void showHistorique() {
        // Placeholder
        System.out.println("Affichage de l'historique du candidat (à implémenter)");
    }

    /**
     * Redirige vers la liste des offres
     */
    public static void showOffres() {
        // Placeholder
        System.out.println("Affichage des offres disponibles (à implémenter)");
    }

    /**
     * Déconnecte l'utilisateur
     */
    public static void logout() {
        Main.showLoginView();
    }
}
