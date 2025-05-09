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
            Main.goToDashboard("Candidat", candidat); // ✔️ on passe bien le candidat maintenant
            return;
        }

        // Vérification côté Recruteur
        Recruteur recruteur = RecruteurDAO.auth(email, password);
        if (recruteur != null) {
            System.out.println("Recruteur connecté : " + recruteur.getNom());
            Main.goToDashboard("Recruteur", recruteur); // <- on passe le recruteur à Main
            return;
        }

        // Aucun trouvé
        System.out.println("Identifiants incorrects !");
        Main.showLoginView();
    }

    public static void register(Utilisateur utilisateur, Boolean sexe, String region, String trancheAge) {
        boolean success = false;

        if (utilisateur.getType().equalsIgnoreCase("Candidat")) {
            Candidat c = new Candidat(
                    0,
                    utilisateur.getNom(),
                    utilisateur.getEmail(),
                    utilisateur.getMotDePasse(),
                    "", // competences
                    "", // experience
                    sexe,
                    region,
                    trancheAge
            );
            success = CandidatDAO.ajouterCandidat(c);

        } else if (utilisateur.getType().equalsIgnoreCase("Recruteur")) {
            Recruteur r = new Recruteur(0, utilisateur.getNom(), utilisateur.getEmail(), utilisateur.getMotDePasse());
            success = RecruteurDAO.ajouterRecruteur(r);
        }

        if (success) {
            System.out.println("Utilisateur inscrit !");
        } else {
            System.out.println("Échec de l'inscription.");
        }

        Main.showLoginView();
    }
}
