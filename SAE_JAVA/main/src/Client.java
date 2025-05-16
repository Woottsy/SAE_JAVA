
import java.util.*;


/**
 * Class Client
 */
public class Client {

  //
  // Fields
  //

  private int idclient;
  private String nom;
  private String prenom;
  private String adresse;
  private String ville;
  private String codepostal;
  private String email;
  private String mdp;
  
  //
  // Constructors
  //
  public Client (int idclient,String nom,String prenom,String adresse,String ville,String codepostal,String email,String mdp) {
    this.idclient = idclient;
    this.nom = nom;
    this.prenom = prenom;
    this.adresse = adresse;
    this.ville = ville;
    this.codepostal = codepostal;
    this.email = email;
    this.mdp = mdp;
   };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of idclient
   * @param newId the new value of idclient
   */
  public void setIdclient (int newId) {
    idclient = newId;
  }

  /**
   * Get the value of idclient
   * @return the value of idclient
   */
  public int getIdclient () {
    return this.idclient;
  }

  /**
   * Set the value of nom
   * @param newNom the new value of nom
   */
  public void setNom (String newNom) {
    this.nom = newNom;
  }

  /**
   * Get the value of nom
   * @return the value of nom
   */
  public String getNom () {
    return this.nom;
  }

  /**
   * Set the value of prenom
   * @param newPrenom the new value of prenom
   */
  public void setPrenom (String newPrenom) {
    this.prenom = newPrenom;
  }

  /**
   * Get the value of prenom
   * @return the value of prenom
   */
  public String getPrenom () {
    return this.prenom;
  }

  /**
   * Set the value of adresse
   * @param newAdresse the new value of adresse
   */
  public void setAdresse (String newAdresse) {
    adresse = newAdresse;
  }

  /**
   * Get the value of adresse
   * @return the value of adresse
   */
  public String getAdresse () {
    return this.adresse;
  }

  /**
   * Set the value of ville
   * @param newVille the new value of ville
   */
  public void setVille (String newVille) {
    this.ville = newVille;
  }

  /**
   * Get the value of ville
   * @return the value of ville
   */
  public String getVille () {
    return this.ville;
  }

  /**
   * Set the value of codepostal
   * @param newCodePostal the new value of codepostal
   */
  public void setCodepostal (String newCodePostal) {
    this.codepostal = newCodePostal;
  }

  /**
   * Get the value of codepostal
   * @return the value of codepostal
   */
  public String getCodepostal () {
    return this.codepostal;
  }

  /**
   * Set the value of email
   * @param newEmail the new value of email
   */
  public void setEmail (String newEmail) {
    this.email = newEmail;
  }

  /**
   * Get the value of email
   * @return the value of email
   */
  public String getEmail () {
    return this.email;
  }

  /**
   * Set the value of mdp
   * @param newMdp the new value of mdp
   */
  public void setMdp (String newMdp) {
    this.mdp = newMdp;
  }

  /**
   * Get the value of mdp
   * @return the value of mdp
   */
  public String getMdp () {
    return this.mdp;
  }

  //
  // Other methods
  //

  /**
   */
  public void consulterCatalogue()
  {
  }


  /**
   * @return       Commande
   */
  public Commande passerCommande(){
    return null;
  }


  /**
   * @return       TypeDeLivraison
   */
  public TypeDeLivraison choisirLivraison(){
    return null;
  }
   public void onVousRecommande()
  {
  }

  //les methode en dessous seront utile pour la fonction onVousRecommande

  /**
   * Une des fonction nécessaire pour onVousRecommande
   * @param c1 un client
   * @param c2 un client différent de c1
   * @return int
   */
  private int livreEncommuns(Client c1,Client c2){
    int nbLivreEnCommuns = 0;
    for (int i = 0; i < c1.getNbLivres(); i++) {
      for (int j = 0; j < c2.getNbLivres(); j++) {
        if (c1.getLivre(i).getIdclass().equals(c2.getLivre(j).getIdclass())) {
          nbLivreEnCommuns++;
        }
      }
    }
    return nbLivreEnCommuns;
  }
  private boolean compatible(Client c1,Client c2){
    if (livreEncommuns(c1, c2)>=5){
      return true;
    }
    else {
      return false;
    }
  }
  private Set<Livre> lesLivresRecommandable(Client c1,Client c2){
    Set<Livre> reco = new HashSet<Livre>();
    if (compatible(c1, c2)){
      
      for(Livre l : c1.getLivre()){
        if (!c2.getLivre().contains(l)){
          reco.add(l);
        }
      }
      for(Livre l : c2.getLivre()){
        if (!c1.getLivre().contains(l)){
          reco.add(l);
        }
      }
    }
    return reco; 
  }


}
