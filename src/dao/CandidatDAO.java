package dao;

import model.Candidat;
import utils.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CandidatDAO {

    /**
     * Ajoute un nouveau candidat à la base de données.
     */
    public static boolean ajouterCandidat(Candidat candidat) {
        String query = "INSERT INTO Candidat (nom, email, motDePasse, competences, experience, sexe, region, trancheAge) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = JDBCConnection.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, candidat.getNom());
            statement.setString(2, candidat.getEmail());
            statement.setString(3, candidat.getMotDePasse());
            statement.setString(4, candidat.getCompetences());
            statement.setString(5, candidat.getExperience());
            statement.setBoolean(6, candidat.getSexe());
            statement.setString(7, candidat.getRegion());
            statement.setString(8, candidat.getTrancheAge());

            int rows = statement.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Erreur dans ajouterCandidat : " + e.getMessage());
            return false;
        }
    }

    /**
     * Récupère tous les candidats depuis la base.
     */
    public List<Candidat> obtenirTousLesCandidats() throws SQLException {
        List<Candidat> candidats = new ArrayList<>();
        String query = "SELECT * FROM Candidat";

        try (Connection connection = JDBCConnection.connect();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Candidat candidat = new Candidat(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("motDePasse"),
                        rs.getString("competences"),
                        rs.getString("experience"),
                        rs.getBoolean("sexe"),
                        rs.getString("region"),
                        rs.getString("trancheAge")
                );
                candidats.add(candidat);
            }
        }

        return candidats;
    }

    /**
     * Authentifie un candidat via email + mot de passe.
     */
    public static Candidat auth(String email, String password) {
        String sql = "SELECT * FROM Candidat WHERE email = ? AND motDePasse = ?";

        try (Connection conn = JDBCConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Candidat(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("motDePasse"),
                        rs.getString("competences"),
                        rs.getString("experience"),
                        rs.getBoolean("sexe"),
                        rs.getString("region"),
                        rs.getString("trancheAge")
                );
            }

        } catch (SQLException e) {
            System.err.println("Erreur dans CandidatDAO.auth : " + e.getMessage());
        }

        return null;
    }

    /**
     * Récupère un candidat par ID.
     */
    public static Candidat getById(int id) {
        String sql = "SELECT * FROM Candidat WHERE id = ?";

        try (Connection conn = JDBCConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Candidat(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("motDePasse"),
                        rs.getString("competences"),
                        rs.getString("experience"),
                        rs.getBoolean("sexe"),
                        rs.getString("region"),
                        rs.getString("trancheAge")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Met à jour les infos d’un candidat.
     */
    public void modifierCandidat(Candidat candidat) {
        String query = "UPDATE Candidat SET nom = ?, motDePasse = ?, competences = ?, experience = ?, sexe = ?, region = ?, trancheAge = ? WHERE id = ?";

        try (Connection conn = JDBCConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, candidat.getNom());
            stmt.setString(2, candidat.getMotDePasse());
            stmt.setString(3, candidat.getCompetences());
            stmt.setString(4, candidat.getExperience());
            stmt.setBoolean(5, candidat.getSexe());
            stmt.setString(6, candidat.getRegion());
            stmt.setString(7, candidat.getTrancheAge());
            stmt.setInt(8, candidat.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Statistique : nombre de candidats par genre.
     */
    public static Map<String, Integer> getNombreCandidatsParGenre() {
        Map<String, Integer> stats = new HashMap<>();
        String query = "SELECT sexe, COUNT(*) AS total FROM Candidat GROUP BY sexe";

        try (Connection connection = JDBCConnection.connect();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                String genre = rs.getBoolean("sexe") ? "Homme" : "Femme";
                stats.put(genre, rs.getInt("total"));
            }

        } catch (SQLException e) {
            System.err.println("Erreur statistiques genre : " + e.getMessage());
        }

        return stats;
    }

    /**
     * Statistique : nombre de candidats par tranche d’âge.
     */
    public static Map<String, Integer> getNombreCandidatsParTrancheAge() {
        Map<String, Integer> stats = new HashMap<>();
        String query = "SELECT trancheAge, COUNT(*) AS total FROM Candidat GROUP BY trancheAge";

        try (Connection connection = JDBCConnection.connect();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                stats.put(rs.getString("trancheAge"), rs.getInt("total"));
            }

        } catch (SQLException e) {
            System.err.println("Erreur statistiques tranche d'âge : " + e.getMessage());
        }

        return stats;
    }

    /**
     * Statistique : nombre de candidats par région.
     */
    public static Map<String, Integer> getNombreCandidatsParRegion() {
        Map<String, Integer> stats = new HashMap<>();
        String query = "SELECT region, COUNT(*) AS total FROM Candidat GROUP BY region";

        try (Connection connection = JDBCConnection.connect();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                stats.put(rs.getString("region"), rs.getInt("total"));
            }

        } catch (SQLException e) {
            System.err.println("Erreur statistiques région : " + e.getMessage());
        }

        return stats;
    }
}
