
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
  public Stock(int quantite) {
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
   * 
   * @param newQte the new value of quantite
   */
  public void setQuantite(int newQte) {
    this.quantite = newQte;
  }

  /**
   * Get the value of quantite
   * 
   * @return the value of quantite
   */
  public int getQuantite() {
    return this.quantite;
  }

  //
  // Other methods
  //

}
