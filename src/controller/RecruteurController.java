package controller;

import main.Main;

/**
 * Gère les actions du recruteur
 */
public class RecruteurController {

    public static void ajouterOffre() {
        System.out.println("Ajout d'une offre (à implémenter)");
    }

    public static void modifierOffre() {
        System.out.println("Modification d'une offre (à implémenter)");
    }

    public static void supprimerOffre() {
        System.out.println("Suppression d'une offre (à implémenter)");
    }

    public static void voirStatistiques() {
        Main.showReportingView();
    }

    public static void logout() {
        Main.showLoginView();
    }
}
