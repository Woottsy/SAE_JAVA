
package models;

/**
 * Class Vendeur
 */
public class Vendeur {

    //
    // Fields
    //
    private int keyVendeur;
    private String idvendeur;
    private String email;
    private String MDP;

    //
    // Constructors
    //
    public Vendeur(int keyVendeur, String idvendeur, String MDP, String email) {
        this.keyVendeur = keyVendeur;
        this.idvendeur = idvendeur;
        this.MDP = MDP;
        this.email = email;

    }

    //
    // Accessor methods
    //
    public int getKeyVendeur() {
        return keyVendeur;
    }

    public void setKeyVendeur(int keyVendeur) {
        this.keyVendeur = keyVendeur;
    }

    public String getIdvendeur() {
        return idvendeur;
    }

    public void setIdvendeur(String idvendeur) {
        this.idvendeur = idvendeur;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMDP() {
        return MDP;
    }

    public void setMDP(String MDP) {
        this.MDP = MDP;
    }

    @Override
    public String toString() {
        return "Le vendeur " + this.idvendeur + " a pour clé unique : " + this.keyVendeur + " et a pour email "
                + this.email + " et son mot de passe est " + this.MDP + ".";
    }
}
