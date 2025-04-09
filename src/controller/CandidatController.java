package controller;

import main.Main;
import dao.OffreDAO;
import dao.CandidatureDAO;
import model.Offre;
import model.Candidature;
import java.sql.SQLException;
import java.util.List;

/**
 * Gère les actions du candidat
 */
public class CandidatController {

    /**
     * Redirige vers l'historique
     */
    public static void afficherHistoriqueCandidatures(int idCandidat) {
        CandidatureDAO candidatureDAO = new CandidatureDAO();
        List<Candidature> candidatures = candidatureDAO.consulterHistorique(idCandidat);
        for (Candidature c : candidatures) {
            System.out.println("Offre ID: " + c.getIdOffre() + ", Statut: " + c.getStatut());
        }
    }

    /**
     * Redirige vers la liste des offres
     */
    public static void afficherToutesLesOffres() {
        OffreDAO offreDAO = new OffreDAO();
        try {
            List<Offre> offres = offreDAO.obtenirToutesLesOffres();
            for (Offre offre : offres) {
                System.out.println("ID: " + offre.getId());
                System.out.println("Titre: " + offre.getTitre());
                System.out.println("Description: " + offre.getDescription());
                System.out.println("Compétences requises: " + offre.getCompetencesRequises());
                System.out.println("Statut: " + offre.getStatut());
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void postulerAOffre(int idCandidat, int idOffre) {
        CandidatureDAO candidatureDAO = new CandidatureDAO();
        candidatureDAO.postuler(idCandidat, idOffre);
        System.out.println("Candidature envoyée avec succès !");
    }

    public static List<Offre> getToutesLesOffres() {
        OffreDAO offreDAO = new OffreDAO();
        try {
            return offreDAO.obtenirToutesLesOffres();
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of(); // retourne une liste vide si erreur
        }
    }

    public static List<Candidature> getHistoriqueCandidatures(int idCandidat) {
        CandidatureDAO dao = new CandidatureDAO();
        return dao.consulterHistorique(idCandidat);
    }
    /**
     * Déconnecte l'utilisateur
     */
    public static void logout() {
        Main.showLoginView();
    }
}
