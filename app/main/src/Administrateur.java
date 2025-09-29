public class Administrateur {
    private final int KeyAdmin;
    private final String identifiant;
    private final String motDePasse;
    private final String email;

    public Administrateur(String id, String mdp, String em, int keyAdmin) {
        this.KeyAdmin = keyAdmin;
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

    public int getKeyAdmin() {
        return this.KeyAdmin;
    }
}