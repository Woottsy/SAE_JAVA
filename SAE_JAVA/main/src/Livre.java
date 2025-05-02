
import java.util.*;


/**
 * Class Livre
 */
public class Livre {

  //
  // Fields
  //

  private String isbn;
  private String titre;
  private int nbpages;
  private String datedepublication;
  private double prix;
  
  //
  // Constructors
  //
  public Livre (String isbn,String titre,int nbpages,String datedepublication,double prix) {
    this.isbn = isbn;
    this.titre = titre;
    this.nbpages = nbpages;
    this.datedepublication = datedepublication;
    this.prix = prix;
    };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of isbn
   * @param newVar the new value of isbn
   */
  public void setIsbn (String newVar) {
    isbn = newVar;
  }

  /**
   * Get the value of isbn
   * @return the value of isbn
   */
  public String getIsbn () {
    return isbn;
  }

  /**
   * Set the value of titre
   * @param newVar the new value of titre
   */
  public void setTitre (String newVar) {
    titre = newVar;
  }

  /**
   * Get the value of titre
   * @return the value of titre
   */
  public String getTitre () {
    return titre;
  }

  /**
   * Set the value of nbpages
   * @param newVar the new value of nbpages
   */
  public void setNbpages (int newVar) {
    nbpages = newVar;
  }

  /**
   * Get the value of nbpages
   * @return the value of nbpages
   */
  public int getNbpages () {
    return nbpages;
  }

  /**
   * Set the value of datedepublication
   * @param newVar the new value of datedepublication
   */
  public void setDatedepublication (String newVar) {
    datedepublication = newVar;
  }

  /**
   * Get the value of datedepublication
   * @return the value of datedepublication
   */
  public String getDatedepublication () {
    return datedepublication;
  }

  /**
   * Set the value of prix
   * @param newVar the new value of prix
   */
  public void setPrix (double newVar) {
    prix = newVar;
  }

  /**
   * Get the value of prix
   * @return the value of prix
   */
  public double getPrix () {
    return prix;
  }

  //
  // Other methods
  //

  /**
   */
  public void onVousRecommande()
  {
  }


}
