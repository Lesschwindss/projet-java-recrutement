package dao;

import model.Candidature;
import utils.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidatureDAO {

    // Méthode pour postuler à une offre
    public void postuler(int candidatId, int offreId) {
        String query = "INSERT INTO candidature (idCandidat, idOffre, statut) VALUES (?, ?, ?)";
        try (Connection conn = JDBCConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, candidatId);
            stmt.setInt(2, offreId);
            stmt.setString(3, "En attente");
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour consulter l'historique des candidatures d'un candidat
    public List<Candidature> consulterHistorique(int candidatId) {
        List<Candidature> candidatures = new ArrayList<>();
        String query = "SELECT * FROM candidature WHERE idCandidat = ?";
        try (Connection conn = JDBCConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, candidatId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                candidatures.add(new Candidature(
                        rs.getInt("id"),
                        rs.getInt("idCandidat"),
                        rs.getInt("idOffre"),
                        rs.getString("statut")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candidatures;
    }

    // Méthode pour mettre à jour le statut de la candidature
    public void mettreAJourStatut(int candidatureId, String statut) {
        String query = "UPDATE candidature SET statut = ? WHERE id = ?";
        try (Connection conn = JDBCConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, statut);
            stmt.setInt(2, candidatureId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
