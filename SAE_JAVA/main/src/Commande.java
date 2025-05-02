
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
  private DetailCommande nouvel_attribut;
  
  //
  // Constructors
  //
  public Commande () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of idcommande
   * @param newVar the new value of idcommande
   */
  public void setIdcommande (int newVar) {
    idcommande = newVar;
  }

  /**
   * Get the value of idcommande
   * @return the value of idcommande
   */
  public int getIdcommande () {
    return idcommande;
  }

  /**
   * Set the value of datecommande
   * @param newVar the new value of datecommande
   */
  public void setDatecommande (String newVar) {
    datecommande = newVar;
  }

  /**
   * Get the value of datecommande
   * @return the value of datecommande
   */
  public String getDatecommande () {
    return datecommande;
  }

  /**
   * Set the value of enligne
   * @param newVar the new value of enligne
   */
  public void setEnligne (boolean newVar) {
    enligne = newVar;
  }

  /**
   * Get the value of enligne
   * @return the value of enligne
   */
  public boolean getEnligne () {
    return enligne;
  }

  /**
   * Set the value of typelivraison
   * @param newVar the new value of typelivraison
   */
  public void setTypelivraison (String newVar) {
    typelivraison = newVar;
  }

  /**
   * Get the value of typelivraison
   * @return the value of typelivraison
   */
  public String getTypelivraison () {
    return typelivraison;
  }

  /**
   * Set the value of estpayee
   * @param newVar the new value of estpayee
   */
  public void setEstpayee (boolean newVar) {
    estpayee = newVar;
  }

  /**
   * Get the value of estpayee
   * @return the value of estpayee
   */
  public boolean getEstpayee () {
    return estpayee;
  }

  /**
   * Set the value of nouvel_attribut
   * @param newVar the new value of nouvel_attribut
   */
  public void setNouvel_attribut (DetailCommande newVar) {
    nouvel_attribut = newVar;
  }

  /**
   * Get the value of nouvel_attribut
   * @return the value of nouvel_attribut
   */
  public DetailCommande getNouvel_attribut () {
    return nouvel_attribut;
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
