public class Utilisateur {
    private int id;
    private String email;
    private String motDePasse;
    private String type; // "Candidat" ou "Recruteur"

    // Constructeurs
    public Utilisateur(int id, String email, String motDePasse, String type) {
        this.id = id;
        this.email = email;
        this.motDePasse = motDePasse;
        this.type = type;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
