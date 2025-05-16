

/**
 * Class TypeDeLivraison
 */
public class TypeDeLivraison {

  private int idlivraison;
  private String type;

  
  //
  // Constructors
  //
  public TypeDeLivraison (int idlivraison,
   String type) {
    this.idlivraison = idlivraison;
    this.type = type;
    };
  
  //
  // Methods
  //
  public int getIdlivraison() {
    return idlivraison;
  }
  public void setIdlivraison(int idlivraison) {
      this.idlivraison = idlivraison;
  }
  public String getType() {
      return type;
  }
  public void setType(String type) {
      this.type = type;
  }

  public enum TypeLivraison {
    MAGASIN, DOMICILE;
  }

  //
  // Accessor methods
  //

  //
  // Other methods
  //

}
