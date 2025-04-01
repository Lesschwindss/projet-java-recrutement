package controller;

import main.Main;
import model.Utilisateur;

/**
 * Gère l'authentification et l'enregistrement des utilisateurs
 */
public class AuthController {

    /**
     * Authentifie un utilisateur
     */
    public static void login(String email, String password) {
        // Simulation provisoire
        if(email.contains("recruteur")) {
            Main.goToDashboard("Recruteur");
        } else if (email.contains("candidat")) {
            Main.goToDashboard("Candidat");
        } else {
            System.out.println("Utilisateur inconnu !");
            Main.showLoginView();
        }
    }

    /**
     * Enregistre un nouvel utilisateur
     */
    public static void register(Utilisateur user) {
        // Simulation provisoire
        System.out.println("Inscription réussie de : " + user.getEmail());
        Main.showLoginView();
    }
}
