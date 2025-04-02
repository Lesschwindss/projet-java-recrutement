package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

    /**
     * Méthode pour établir une connexion à la base de données
     * avec détection automatique de l'OS (macOS ou Windows uniquement)
     */
    public static Connection connect() throws SQLException {
        String os = System.getProperty("os.name").toLowerCase();
        String url;
        String user;
        String password;

        if (os.contains("win")) {
            // 🪟 Configuration pour WAMP sous Windows
            System.out.println("Système détecté : Windows (WAMP)");
            url = "jdbc:mysql://localhost:3306/Recrutement?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            user = "root";
            password = "";
        } else if (os.contains("mac")) {
            // Configuration pour MAMP sous macOS
            System.out.println("Système détecté : macOS (MAMP)");
            url = "jdbc:mysql://localhost:8889/Recrutement?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            user = "root";
            password = "root";
        } else {
            throw new SQLException("OS non pris en charge pour la connexion JDBC : " + os);
        }

        return DriverManager.getConnection(url, user, password);
    }

    public static void main(String[] args) {
        try (Connection connection = connect()) {
            if (connection != null) {
                System.out.println("Connexion à la base de données réussie !");
            }
        } catch (SQLException e) {
            System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
        }
    }
}
