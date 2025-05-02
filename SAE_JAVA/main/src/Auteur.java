
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
  public Auteur () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of idauteur
   * @param newVar the new value of idauteur
   */
  public void setIdauteur (int newVar) {
    idauteur = newVar;
  }

  /**
   * Get the value of idauteur
   * @return the value of idauteur
   */
  public int getIdauteur () {
    return idauteur;
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
   * Set the value of annee
   * @param newVar the new value of annee
   */
  public void setAnnee (String newVar) {
    annee = newVar;
  }

  /**
   * Get the value of annee
   * @return the value of annee
   */
  public String getAnnee () {
    return annee;
  }

  //
  // Other methods
  //

}
