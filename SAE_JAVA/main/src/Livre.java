
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
   * @param newIsbn the new value of isbn
   */
  public void setIsbn (String newIsbn) {
    this.isbn = newIsbn;
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
   * @param newTitre the new value of titre
   */
  public void setTitre (String newTitre) {
    this.titre = newTitre;
  }

  /**
   * Get the value of titre
   * @return the value of titre
   */
  public String getTitre () {
    return this.titre;
  }

  /**
   * Set the value of nbpages
   * @param newNbpages the new value of nbpages
   */
  public void setNbpages (int newNbpages) {
    this.nbpages = newNbpages;
  }

  /**
   * Get the value of nbpages
   * @return the value of nbpages
   */
  public int getNbpages () {
    return this.nbpages;
  }

  /**
   * Set the value of datedepublication
   * @param newDate the new value of datedepublication
   */
  public void setDatedepublication (String newDate) {
    this.datedepublication = newDate;
  }

  /**
   * Get the value of datedepublication
   * @return the value of datedepublication
   */
  public String getDatedepublication () {
    return this.datedepublication;
  }

  /**
   * Set the value of prix
   * @param newPrix the new value of prix
   */
  public void setPrix (double newPrix) {
    this.prix = newPrix;
  }

  /**
   * Get the value of prix
   * @return the value of prix
   */
  public double getPrix () {
    return this.prix;
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
