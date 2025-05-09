
import java.util.*;


/**
 * Class Auteur
 */
public class Auteur {

  //
  // Fields
  //

  private int idauteur;
  private String nom;
  private String prenom;
  private String annee;
  
  //
  // Constructors
  //
  public Auteur (int idauteur,String nom,String prenom,String annee) {
    this.idauteur = idauteur;
    this.nom = nom;
    this.prenom = prenom;
    this.annee = annee;
   };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of idauteur
   * @param newId the new value of idauteur
   */
  public void setIdauteur (int newId) {
    this.idauteur = newId;
  }

  /**
   * Get the value of idauteur
   * @return the value of idauteur
   */
  public int getIdauteur () {
    return this.idauteur;
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
   * Set the value of annee
   * @param newAnnee the new value of annee
   */
  public void setAnnee (String newAnnee) {
    this.annee = newAnnee;
  }

  /**
   * Get the value of annee
   * @return the value of annee
   */
  public String getAnnee () {
    return this.annee;
  }

  //
  // Other methods
  //

}
