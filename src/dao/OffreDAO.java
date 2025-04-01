package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OffreDAO {

    /**
     * Ajoute une nouvelle offre d'emploi à la base de données
     * @param offre L'objet OffreEmploi à ajouter
     * @throws SQLException
     */
    public void ajouterOffre(Offre offre) throws SQLException {
        String query = "INSERT INTO OffreEmploi (titre, description, competencesRequises, statut, recruteur_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = JDBCConnection.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, offre.getTitre());
            statement.setString(2, offre.getDescription());
            statement.setString(3, offre.getCompetencesRequises());
            statement.setString(4, offre.getStatut());
            statement.setInt(5, offre.getRecruteurId());
            statement.executeUpdate();
        }
    }

    /**
     * Récupère toutes les offres d'emploi de la base de données
     * @return Une liste d'offres d'emploi
     * @throws SQLException
     */
    public List<Offre> obtenirToutesLesOffres() throws SQLException {
        List<Offre> offres = new ArrayList<>();
        String query = "SELECT * FROM OffreEmploi";

        try (Connection connection = JDBCConnection.connect();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Offre offre = new Offre(
                        resultSet.getInt("id"),
                        resultSet.getString("titre"),
                        resultSet.getString("description"),
                        resultSet.getString("competencesRequises"),
                        resultSet.getString("statut"),
                        resultSet.getInt("recruteur_id")
                );
                offres.add(offre);
            }
        }
        return offres;
    }
}
