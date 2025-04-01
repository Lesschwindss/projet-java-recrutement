public class Offre {
    private int id;
    private String titre;
    private String description;
    private String competencesRequises;
    private String statut;

    // Constructeurs
    public Offre(int id, String titre, String description, String competencesRequises, String statut) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.competencesRequises = competencesRequises;
        this.statut = statut;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompetencesRequises() {
        return competencesRequises;
    }

    public void setCompetencesRequises(String competencesRequises) {
        this.competencesRequises = competencesRequises;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
