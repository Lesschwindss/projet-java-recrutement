package controller;

import main.Main;
import view.AjouterOffreForm;
import view.ModifierOffreForm;
import view.SupprimerOffreForm;

/**
 * Gère les actions du recruteur
 */
public class RecruteurController {

    /**
     * Ouvre la fenêtre pour ajouter une offre
     */
    public static void ajouterOffre() {
        if (Main.recruteurConnecte != null) {
            int idRecruteur = Main.recruteurConnecte.getId(); // Récupère l'ID du recruteur connecté
            new AjouterOffreForm(idRecruteur); // Ouvre la fenêtre
        } else {
            System.err.println("Erreur : Aucun recruteur connecté.");
        }
    }

    /**
     * Ouvre la fenêtre pour modifier une offre
     */
    public static void modifierOffre() {
        new ModifierOffreForm();
    }

    /**
     * Ouvre la fenêtre pour supprimer une offre
     */
    public static void supprimerOffre() {
        new SupprimerOffreForm();
    }

    /**
     * Affiche les statistiques via la méthode de la classe Main
     */
    public static void voirStatistiques() {
        Main.showReportingView();
    }

    /**
     * Déconnecte l'utilisateur et retourne à l'écran de login
     */
    public static void logout() {
        Main.showLoginView();
    }
}
