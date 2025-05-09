
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
  private Stock leStock;
  
  //
  // Constructors
  //
  public Magasin (int idmagasin,String nom,String ville,String adresse,Stock leStock) {
    this.idmagasin = idmagasin;
    this.nom = nom;
    this.ville = ville;
    this.adresse = adresse;
    this.leStock = leStock;
   };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of idmagasin
   * @param newId the new value of idmagasin
   */
  public void setIdmagasin (int newId) {
    this.idmagasin = newId;
  }

  /**
   * Get the value of idmagasin
   * @return the value of idmagasin
   */
  public int getIdmagasin () {
    return this.idmagasin;
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
   * Set the value of adresse
   * @param newAdresse the new value of adresse
   */
  public void setAdresse (String newAdresse) {
    this.adresse = newAdresse;
  }

  /**
   * Get the value of adresse
   * @return the value of adresse
   */
  public String getAdresse () {
    return this.adresse;
  }

  /**
   * Set the value of nouvel_attribut
   * @param newStock the new value of nouvel_attribut
   */
  public void setStock (Stock newStock) {
    this.leStock =  newStock;
  }

  /**
   * Get the value of nouvel_attribut
   * @return the value of nouvel_attribut
   */
  public Stock getStock () {
    return this.leStock;
  }

  //
  // Other methods
  //

}
