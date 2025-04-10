package dao;

import model.Candidat;
import utils.JDBCConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CandidatDAO {

    /**
     * Ajoute un nouveau candidat à la base de données
     * @param candidat L'objet Candidat à ajouter
     * @return true si l'insertion a réussi, false sinon
     */
    public static boolean ajouterCandidat(Candidat candidat) {
        String query = "INSERT INTO Candidat (nom, email, motDePasse, competences, experience, sexe, region, trancheAge) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
     * Récupère tous les candidats de la base de données
     * @return Une liste de candidats
     * @throws SQLException
     */
    public List<Candidat> obtenirTousLesCandidats() throws SQLException {
        List<Candidat> candidats = new ArrayList<>();
        String query = "SELECT * FROM Candidat";

        try (Connection connection = JDBCConnection.connect();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Candidat candidat = new Candidat(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("email"),
                        resultSet.getString("motDePasse"),
                        resultSet.getString("competences"),
                        resultSet.getString("experience"),
                        resultSet.getBoolean("sexe"),
                        resultSet.getString("region"),
                        resultSet.getString("trancheAge")
                );
                candidats.add(candidat);
            }
        }
        return candidats;
    }

    /**
     * Authentifie un candidat
     * @param email L'email du candidat
     * @param password Le mot de passe du candidat
     * @return Un objet Candidat si l'authentification réussit, null sinon
     */
    public static Candidat auth(String email, String password) {
        try (Connection conn = JDBCConnection.connect()) {
            String sql = "SELECT * FROM Candidat WHERE email = ? AND motDePasse = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, email);
                stmt.setString(2, password);

                try (ResultSet rs = stmt.executeQuery()) {
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
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur dans CandidatDAO.auth : " + e.getMessage());
        }
        return null;
    }
}
