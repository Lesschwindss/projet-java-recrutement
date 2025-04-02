package dao;
import model.Recruteur;
import utils.JDBCConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecruteurDAO {

    /**
     * Ajoute un nouveau recruteur à la base de données
     * @param recruteur L'objet Recruteur à ajouter
     * @throws SQLException
     */
    public static boolean ajouterRecruteur(Recruteur recruteur) {
        String query = "INSERT INTO Recruteur (nom, email, motDePasse) VALUES (?, ?, ?)";

        try (Connection connection = JDBCConnection.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, recruteur.getNom());
            statement.setString(2, recruteur.getEmail());
            statement.setString(3, recruteur.getMotDePasse());

            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("Erreur dans ajouterRecruteur : " + e.getMessage());
            return false;
        }
    }


    /**
     * Récupère tous les recruteurs de la base de données
     * @return Une liste de recruteurs
     * @throws SQLException
     */
    public List<Recruteur> obtenirTousLesRecruteurs() throws SQLException {
        List<Recruteur> recruteurs = new ArrayList<>();
        String query = "SELECT * FROM Recruteur";

        try (Connection connection = JDBCConnection.connect();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Recruteur recruteur = new Recruteur(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("email"),
                        resultSet.getString("motDePasse")
                );
                recruteurs.add(recruteur);
            }
        }
        return recruteurs;
    }
public static Recruteur auth(String email, String password) {
            try (Connection conn = JDBCConnection.connect()) {
                String sql = "SELECT * FROM Recruteur WHERE email = ? AND motDePasse = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, email);
                    stmt.setString(2, password);

                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            return new Recruteur(
                                    rs.getInt("id"),
                                    rs.getString("nom"),
                                    rs.getString("email"),
                                    rs.getString("motDePasse")
                            );
                        }
                    }
                }
            } catch (SQLException e) {
                System.err.println("Erreur dans RecruteurDAO.auth : " + e.getMessage());
            }
            return null;
        }
    }
