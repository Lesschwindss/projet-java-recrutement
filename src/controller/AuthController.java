package controller;

import dao.CandidatDAO;
import dao.RecruteurDAO;
import main.Main;
import model.Candidat;
import model.Recruteur;
import model.Utilisateur;


public class AuthController {

    public static void login(String email, String password) {
        // Vérification côté Candidat
        Candidat candidat = CandidatDAO.auth(email, password);
        if (candidat != null) {
            System.out.println("Candidat connecté : " + candidat.getNom());
            Main.goToDashboard("Candidat");
            return;
        }

        // Vérification côté Recruteur
        Recruteur recruteur = RecruteurDAO.auth(email, password);
        if (recruteur != null) {
            System.out.println("Recruteur connecté : " + recruteur.getNom());
            Main.goToDashboard("Recruteur");
            return;
        }

        // Aucun trouvé
        System.out.println("Identifiants incorrects !");
        Main.showLoginView();
    }

    public static void register(Utilisateur Utilisateur) {
        boolean success = false;

        if (Utilisateur.getType().equalsIgnoreCase("Candidat")) {
            Candidat c = new Candidat(0, Utilisateur.getNom(), Utilisateur.getEmail(), Utilisateur.getMotDePasse(), "", "");
            success = dao.CandidatDAO.ajouterCandidat(c);

        } else if (Utilisateur.getType().equalsIgnoreCase("Recruteur")) {
            Recruteur r = new Recruteur(0, Utilisateur.getNom(), Utilisateur.getEmail(), Utilisateur.getMotDePasse());
            success = dao.RecruteurDAO.ajouterRecruteur(r);
        }

        if (success) {
            System.out.println("Utilisateur inscrit !");
        } else {
            System.out.println("Échec de l'inscription.");
        }

        Main.showLoginView();
    }

}
