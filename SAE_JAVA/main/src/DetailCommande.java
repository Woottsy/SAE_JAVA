
import java.util.*;


/**
 * Class DetailCommande
 */
public class DetailCommande {

  //
  // Fields
  //

  private int quantite;
  private int prixunitaire;
  
  //
  // Constructors
  //
  public DetailCommande () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of quantite
   * @param newVar the new value of quantite
   */
  public void setQuantite (int newVar) {
    quantite = newVar;
  }

  /**
   * Get the value of quantite
   * @return the value of quantite
   */
  public int getQuantite () {
    return quantite;
  }

  /**
   * Set the value of prixunitaire
   * @param newVar the new value of prixunitaire
   */
  public void setPrixunitaire (int newVar) {
    prixunitaire = newVar;
  }

  /**
   * Get the value of prixunitaire
   * @return the value of prixunitaire
   */
  public int getPrixunitaire () {
    return prixunitaire;
  }

  //
  // Other methods
  //

}
