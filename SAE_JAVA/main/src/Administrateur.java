public class Administrateur {
    private final String identifiant;
    private final String motDePasse;
    private final String email;
    // attributs à rajouter peut-être

    public Administrateur(String id, String mdp, String em) {
        this.identifiant = id;
        this.motDePasse = mdp;
        this.email = em;
    }

    public String getId() {
        return this.identifiant;
    }

    public String getMotDePasse() {
        return this.motDePasse;
    }

    public String getEmail() {
        return this.email;
    }
}