package dao;

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
    public void ajouterRecruteur(Recruteur recruteur) throws SQLException {
        String query = "INSERT INTO Recruteur (nom, email, motDePasse) VALUES (?, ?, ?)";
        try (Connection connection = JDBCConnection.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, recruteur.getNom());
            statement.setString(2, recruteur.getEmail());
            statement.setString(3, recruteur.getMotDePasse());
            statement.executeUpdate();
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
}
