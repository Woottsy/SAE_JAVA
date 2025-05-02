
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
   * @param newVar the new value of idclient
   */
  public void setIdclient (int newVar) {
    idclient = newVar;
  }

  /**
   * Get the value of idclient
   * @return the value of idclient
   */
  public int getIdclient () {
    return idclient;
  }

  /**
   * Set the value of nom
   * @param newVar the new value of nom
   */
  public void setNom (String newVar) {
    nom = newVar;
  }

  /**
   * Get the value of nom
   * @return the value of nom
   */
  public String getNom () {
    return nom;
  }

  /**
   * Set the value of prenom
   * @param newVar the new value of prenom
   */
  public void setPrenom (String newVar) {
    prenom = newVar;
  }

  /**
   * Get the value of prenom
   * @return the value of prenom
   */
  public String getPrenom () {
    return prenom;
  }

  /**
   * Set the value of adresse
   * @param newVar the new value of adresse
   */
  public void setAdresse (String newVar) {
    adresse = newVar;
  }

  /**
   * Get the value of adresse
   * @return the value of adresse
   */
  public String getAdresse () {
    return adresse;
  }

  /**
   * Set the value of ville
   * @param newVar the new value of ville
   */
  public void setVille (String newVar) {
    ville = newVar;
  }

  /**
   * Get the value of ville
   * @return the value of ville
   */
  public String getVille () {
    return ville;
  }

  /**
   * Set the value of codepostal
   * @param newVar the new value of codepostal
   */
  public void setCodepostal (String newVar) {
    codepostal = newVar;
  }

  /**
   * Get the value of codepostal
   * @return the value of codepostal
   */
  public String getCodepostal () {
    return codepostal;
  }

  /**
   * Set the value of email
   * @param newVar the new value of email
   */
  public void setEmail (String newVar) {
    email = newVar;
  }

  /**
   * Get the value of email
   * @return the value of email
   */
  public String getEmail () {
    return email;
  }

  /**
   * Set the value of mdp
   * @param newVar the new value of mdp
   */
  public void setMdp (String newVar) {
    mdp = newVar;
  }

  /**
   * Get the value of mdp
   * @return the value of mdp
   */
  public String getMdp () {
    return mdp;
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


}
