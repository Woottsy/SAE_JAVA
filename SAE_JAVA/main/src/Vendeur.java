
import java.util.*;


/**
 * Class Vendeur
 */
public class Vendeur {

  //
  // Fields
  //

  private int idvendeur;
  private String nom;
  private String prenom;
  private String email;
  private String MDP;
  
  //
  // Constructors
  //
  public Vendeur (int idvendeur,
   String nom,
   String prenom,
   String email,
   String MDP) {
    this.idvendeur = idvendeur;
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
    this.MDP = MDP;
    };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of idvendeur
   * @param newVar the new value of idvendeur
   */
  public void setIdvendeur (int newVar) {
    idvendeur = newVar;
  }

  /**
   * Get the value of idvendeur
   * @return the value of idvendeur
   */
  public int getIdvendeur () {
    return idvendeur;
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
   * Set the value of MDP
   * @param newVar the new value of MDP
   */
  public void setMDP (String newVar) {
    MDP = newVar;
  }

  /**
   * Get the value of MDP
   * @return the value of MDP
   */
  public String getMDP () {
    return MDP;
  }

  //
  // Other methods
  //

  /**
   * @return       Commande
   */
  public Commande passerCommandeClient(){
    return null;
  }


  /**
   */
  public void ajouterLivreStock()
  {
  }


  /**
   */
  public void mettreAJourStock()
  {
  }


  /**
   * @return       boolean
   */
  public boolean verifierDisponibilite(){
    return true;
  }


  /**
   * @return       boolean
   */
  public boolean transfererLivre(){
    return true;
  }


}
