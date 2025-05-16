
import java.util.*;


/**
 * Class DetailCommande
 */
public class DetailCommande{

  //
  // Fields
  //

  private int quantite;
  private int prixunitaire;
  
  //
  // Constructors
  //
  public DetailCommande (int quantite , int prixunitaire) {
    this.quantite = quantite;
    this.prixunitaire = prixunitaire;
    };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of quantite
   * @param newQte the new value of quantite
   */
  public void setQuantite (int newQte) {
    this.quantite = newQte;
  }

  /**
   * Get the value of quantite
   * @return the value of quantite
   */
  public int getQuantite () {
    return this.quantite;
  }

  /**
   * Set the value of prixunitaire
   * @param newPrix the new value of prixunitaire
   */
  public void setPrixunitaire (int newPrix) {
    this.prixunitaire = newPrix;
  }

  /**
   * Get the value of prixunitaire
   * @return the value of prixunitaire
   */
  public int getPrixunitaire () {
    return this.prixunitaire;
  }

  //
  // Other methods
  //

}
