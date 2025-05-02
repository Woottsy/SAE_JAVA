
import java.util.*;


/**
 * Class Stock
 */
public class Stock {

  //
  // Fields
  //

  private int quantite;
  
  //
  // Constructors
  //
  public Stock (int quantite) {
    this.quantite = quantite;
   };
  
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

  //
  // Other methods
  //

}
