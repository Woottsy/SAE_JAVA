
import java.util.*;


/**
 * Class Magasin
 */
public class Magasin {

  //
  // Fields
  //

  private int idmagasin;
  private String nom;
  private String ville;
  private String adresse;
  private Stock nouvel_attribut;
  
  //
  // Constructors
  //
  public Magasin () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of idmagasin
   * @param newVar the new value of idmagasin
   */
  public void setIdmagasin (int newVar) {
    idmagasin = newVar;
  }

  /**
   * Get the value of idmagasin
   * @return the value of idmagasin
   */
  public int getIdmagasin () {
    return idmagasin;
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
   * Set the value of nouvel_attribut
   * @param newVar the new value of nouvel_attribut
   */
  public void setNouvel_attribut (Stock newVar) {
    nouvel_attribut = newVar;
  }

  /**
   * Get the value of nouvel_attribut
   * @return the value of nouvel_attribut
   */
  public Stock getNouvel_attribut () {
    return nouvel_attribut;
  }

  //
  // Other methods
  //

}
