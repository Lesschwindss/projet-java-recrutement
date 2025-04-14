package dao;

import model.Candidature;
import utils.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidatureDAO {

    // Retourne les candidatures avec leurs ID (modèle original)
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

    // Accepter une candidature = mise à jour du statut
    public static void updateStatut(int idCandidature, String nouveauStatut) {
        try (Connection conn = JDBCConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE Candidature SET statut = ? WHERE id = ?")) {

            stmt.setString(1, nouveauStatut);
            stmt.setInt(2, idCandidature);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Pour afficher un nom candidat depuis son ID
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

    // Pour afficher un titre d’offre depuis son ID
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
    public static List<Candidature> consulterHistorique(int idCandidat) {
        List<Candidature> candidatures = new ArrayList<>();

        try (Connection conn = JDBCConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM Candidature WHERE idCandidat = ?")) {

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
    public static boolean postuler(int idCandidat, int idOffre) {
        String sql = "INSERT INTO Candidature (idCandidat, idOffre, statut) VALUES (?, ?, 'en attente')";

        try (Connection conn = JDBCConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCandidat);
            stmt.setInt(2, idOffre);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}

