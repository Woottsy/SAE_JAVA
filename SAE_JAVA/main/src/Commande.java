
import java.util.*;


/**
 * Class Commande
 */
public class Commande {

  //
  // Fields
  //

  private int idcommande;
  private String datecommande;
  private boolean enligne;
  private String typelivraison;
  private boolean estpayee;
  private DetailCommande leDetail;
  
  //
  // Constructors
  //
  public Commande (int idcommande,String datecommande,boolean enligne,String typelivraison,boolean estpayee,DetailCommande leDetail) {
    this.idcommande = idcommande;
    this.datecommande = datecommande;
    this.enligne = enligne;
    this.typelivraison = typelivraison;
    this.estpayee = estpayee;
    this.leDetail = leDetail;
    };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of idcommande
   * @param newId the new value of idcommande
   */
  public void setIdcommande (int newId) {
    this.idcommande = newId;
  }

  /**
   * Get the value of idcommande
   * @return the value of idcommande
   */
  public int getIdcommande () {
    return this.idcommande;
  }

  /**
   * Set the value of datecommande
   * @param newDate the new value of datecommande
   */
  public void setDatecommande (String newDate) {
    this.datecommande = newDate;
  }

  /**
   * Get the value of datecommande
   * @return the value of datecommande
   */
  public String getDatecommande () {
    return this.datecommande;
  }

  /**
   * Set the value of enligne
   * @param newEnligne the new value of enligne
   */
  public void setEnligne (boolean newEnligne) {
    this.enligne = newEnligne;
  }

  /**
   * Get the value of enligne
   * @return the value of enligne
   */
  public boolean getEnligne () {
    return this.enligne;
  }

  /**
   * Set the value of typelivraison
   * @param newLivraison the new value of typelivraison
   */
  public void setTypelivraison (String newLivraison) {
    this.typelivraison = newLivraison;
  }

  /**
   * Get the value of typelivraison
   * @return the value of typelivraison
   */
  public String getTypelivraison () {
    return this.typelivraison;
  }

  /**
   * Set the value of estpayee
   * @param newEstpayee the new value of estpayee
   */
  public void setEstpayee (boolean newEstpayee) {
    this.estpayee = newEstpayee;
  }

  /**
   * Get the value of estpayee
   * @return the value of estpayee
   */
  public boolean getEstpayee () {
    return this.estpayee;
  }

  /**
   * Set the value of nouvel_attribut
   * @param newDetail the new value of nouvel_attribut
   */
  public void setNouveauDetail (DetailCommande newDetail) {
    this.leDetail = newDetail;
  }

  /**
   * Get the value of nouvel_attribut
   * @return the value of nouvel_attribut
   */
  public DetailCommande getDetailCommande () {
    return this.leDetail;
  }

  //
  // Other methods
  //

  /**
   * @return       String
   */
  public String editerFacture(){
    return null;
  }


  /**
   * @return       double
   */
  public double calculerTotal(){
    return 0.0;
  }


}
