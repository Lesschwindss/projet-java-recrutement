package dao;

import model.Offre;
import utils.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OffreDAO {

    /**
     * Ajoute une nouvelle offre d'emploi à la base de données
     * @param offre L'objet Offre à ajouter
     * @throws SQLException
     */
    public void ajouterOffre(Offre offre) throws SQLException {
        String query = "INSERT INTO offre (titre, description, competencesRequises, statut, recruteurId, categorie) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = JDBCConnection.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, offre.getTitre());
            statement.setString(2, offre.getDescription());
            statement.setString(3, offre.getCompetencesRequises());
            statement.setString(4, offre.getStatut());
            statement.setInt(5, offre.getRecruteurId());
            statement.setString(6, offre.getCategorie());
            statement.executeUpdate();
        }
    }

    /**
     * Supprime une offre d'emploi par son ID
     * @param id L'identifiant de l'offre à supprimer
     * @throws SQLException
     */
    public void supprimerOffre(int id) throws SQLException {
        String query = "DELETE FROM offre WHERE id = ?";
        try (Connection connection = JDBCConnection.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    /**
     * Met à jour une offre d'emploi existante
     * @param offre L'objet Offre contenant les nouvelles informations
     * @throws SQLException
     */
    public void modifierOffre(Offre offre) throws SQLException {
        String query = "UPDATE offre SET titre = ?, description = ?, competencesRequises = ?, statut = ?, recruteurId = ? WHERE id = ?";
        try (Connection connection = JDBCConnection.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, offre.getTitre());
            statement.setString(2, offre.getDescription());
            statement.setString(3, offre.getCompetencesRequises());
            statement.setString(4, offre.getStatut());
            statement.setInt(5, offre.getRecruteurId());
            statement.setInt(6, offre.getId());
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
        String query = "SELECT * FROM offre";

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
                        resultSet.getInt("recruteurId"),
                        resultSet.getString("categorie")
                );
                offres.add(offre);
            }
        }
        return offres;
    }

    /**
     * Recherche des offres d'emploi contenant un mot-clé dans le titre ou la description
     * @param motCle Le mot-clé à rechercher
     * @return Une liste d'offres correspondant au mot-clé
     * @throws SQLException
     */
    public List<Offre> rechercherOffres(String motCle) throws SQLException {
        List<Offre> offres = new ArrayList<>();
        String query = "SELECT * FROM offre WHERE titre LIKE ? OR description LIKE ?";

        try (Connection connection = JDBCConnection.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            String motCleLike = "%" + motCle + "%";
            statement.setString(1, motCleLike);
            statement.setString(2, motCleLike);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Offre offre = new Offre(
                            resultSet.getInt("id"),
                            resultSet.getString("titre"),
                            resultSet.getString("description"),
                            resultSet.getString("competencesRequises"),
                            resultSet.getString("statut"),
                            resultSet.getInt("recruteurId"),
                            resultSet.getString("categorie")
                    );
                    offres.add(offre);
                }
            }
        }

        return offres;
    }

    public List<Offre> obtenirOffresAvecNbCandidatures() throws SQLException {
        List<Offre> offres = new ArrayList<>();
        String query = """
            SELECT o.*, COUNT(c.id) AS nbCandidatures
            FROM offre o
            LEFT JOIN Candidature c ON o.id = c.idOffre
            GROUP BY o.id
            """;

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
                        resultSet.getInt("recruteurId"),
                        resultSet.getString("categorie")
                );
                offre.setNbCandidatures(resultSet.getInt("nbCandidatures"));
                offres.add(offre);
            }
        }
        return offres;
    }

}
