package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

    // Paramètres de connexion pour MAMP sur Mac
    private static final String URL = "jdbc:mysql://localhost:8889/RecrutementDB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    /**
     * Méthode pour établir une connexion à la base de données
     * @return Une instance de Connection
     * @throws SQLException si une erreur survient lors de la connexion
     */
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        // Test de la connexion
        try (Connection connection = connect()) {
            if (connection != null) {
                System.out.println("Connexion à la base de données réussie!");
            }
        } catch (SQLException e) {
            System.err.println("Erreur de connexion à la base de données: " + e.getMessage());
        }
    }
}
