package controller;

import dao.CandidatureDAO;
import model.Candidature;

import java.util.List;

/**
 * Contrôleur métier pour gérer les candidatures.
 */
public class CandidatureController {

    private final CandidatureDAO candidatureDAO;

    public CandidatureController() {
        this.candidatureDAO = new CandidatureDAO();
    }

    /**
     * Permet à un candidat de postuler à une offre.
     * @param idCandidat L'identifiant du candidat
     * @param idOffre L'identifiant de l'offre
     * @param lettre Lettre de motivation
     */
    public void postuler(int idCandidat, int idOffre, String lettre) {
        candidatureDAO.postuler(idCandidat, idOffre, lettre);
        System.out.println("Candidature enregistrée (vérifiez la BDD pour confirmer).");
    }

    /**
     * Retourne l'historique des candidatures d'un candidat.
     * @param idCandidat L'identifiant du candidat
     * @return Liste des candidatures
     */
    public List<Candidature> getHistoriqueCandidat(int idCandidat) {
        return candidatureDAO.consulterHistorique(idCandidat);
    }

    /**
     * Affiche l'historique des candidatures d'un candidat dans la console.
     * @param idCandidat L'identifiant du candidat
     */
    public void afficherHistorique(int idCandidat) {
        List<Candidature> candidatures = candidatureDAO.consulterHistorique(idCandidat);
        if (candidatures.isEmpty()) {
            System.out.println("ℹ️ Aucun historique de candidatures.");
        } else {
            System.out.println("📄 Historique des candidatures :");
            for (Candidature c : candidatures) {
                System.out.println("- Offre ID : " + c.getIdOffre() +
                        " | Statut : " + c.getStatut());
            }
        }
    }

    /**
     * Met à jour le statut d'une candidature.
     * @param idCandidature ID de la candidature
     * @param nouveauStatut Nouveau statut (ex: "Acceptée", "Refusée")
     */
    public void mettreAJourStatut(int idCandidature, String nouveauStatut) {
        boolean success = candidatureDAO.mettreAJourStatut(idCandidature, nouveauStatut);
        if (success) {
            System.out.println("Statut mis à jour avec succès.");
        } else {
            System.out.println("Échec de la mise à jour du statut.");
        }
    }
}
