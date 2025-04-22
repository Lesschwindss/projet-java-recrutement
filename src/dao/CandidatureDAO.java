package dao;

import model.Candidature;
import utils.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidatureDAO {

    /**
     * Récupère toutes les candidatures de la base.
     */
    public static List<Candidature> getToutesLesCandidatures() {
        List<Candidature> candidatures = new ArrayList<>();

        try (Connection conn = JDBCConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Candidature");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int idCandidat = rs.getInt("idCandidat");
                int idOffre = rs.getInt("idOffre");
                String statut = rs.getString("statut");

                candidatures.add(new Candidature(id, idCandidat, idOffre, statut));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return candidatures;
    }

    /**
     * Met à jour le statut d'une candidature.
     */
    public static void updateStatut(int idCandidature, String nouveauStatut) {
        try (Connection conn = JDBCConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("UPDATE Candidature SET statut = ? WHERE id = ?")) {

            stmt.setString(1, nouveauStatut);
            stmt.setInt(2, idCandidature);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Récupère le nom du candidat à partir de son ID.
     */
    public static String getNomCandidat(int idCandidat) {
        try (Connection conn = JDBCConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT nom FROM Candidat WHERE id = ?")) {

            stmt.setInt(1, idCandidat);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) return rs.getString("nom");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Inconnu";
    }

    /**
     * Récupère le titre de l'offre à partir de son ID.
     */
    public static String getTitreOffre(int idOffre) {
        try (Connection conn = JDBCConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT titre FROM Offre WHERE id = ?")) {

            stmt.setInt(1, idOffre);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) return rs.getString("titre");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Inconnue";
    }

    /**
     * Retourne l'historique des candidatures d'un candidat.
     */
    public static List<Candidature> consulterHistorique(int idCandidat) {
        List<Candidature> candidatures = new ArrayList<>();

        try (Connection conn = JDBCConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Candidature WHERE idCandidat = ?")) {

            stmt.setInt(1, idCandidat);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int idOffre = rs.getInt("idOffre");
                String statut = rs.getString("statut");

                candidatures.add(new Candidature(id, idCandidat, idOffre, statut));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return candidatures;
    }

    /**
     * Insère une nouvelle candidature avec lettre de motivation.
     */
    public void postuler(int candidatId, int offreId, String lettreMotivation) {
        String query = "INSERT INTO Candidature (idCandidat, idOffre, statut, lettreMotivation) VALUES (?, ?, ?, ?)";

        try (Connection conn = JDBCConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, candidatId);
            stmt.setInt(2, offreId);
            stmt.setString(3, "En attente");
            stmt.setString(4, lettreMotivation);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Supprime une candidature par son ID.
     */
    public void supprimerCandidature(int id) {
        String query = "DELETE FROM Candidature WHERE id = ?";

        try (Connection conn = JDBCConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Récupère la lettre de motivation d'un candidat (équivalent du "CV").
     */
    public static String getLettreMotivationParCandidatId(int idCandidat) {
        String lettre = "";
        String query = "SELECT lettreMotivation FROM Candidature WHERE idCandidat = ? LIMIT 1";

        try (Connection conn = JDBCConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idCandidat);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                lettre = rs.getString("lettreMotivation");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lettre;
    }
}
