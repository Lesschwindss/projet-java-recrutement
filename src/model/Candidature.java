public class Candidature {
    private int id;
    private int idCandidat;
    private int idOffre;
    private String statut;

    // Constructeurs
    public Candidature(int id, int idCandidat, int idOffre, String statut) {
        this.id = id;
        this.idCandidat = idCandidat;
        this.idOffre = idOffre;
        this.statut = statut;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCandidat() {
        return idCandidat;
    }

    public void setIdCandidat(int idCandidat) {
        this.idCandidat = idCandidat;
    }

    public int getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
