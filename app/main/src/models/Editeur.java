
package models;

/**
 * Class Editeur
 */
public class Editeur {

  //
  // Fields
  //

  private int idediteur;
  private String nom;

  //
  // Constructors
  //
  public Editeur(int idediteur, String nom) {
    this.idediteur = idediteur;
    this.nom = nom;
  };

  //
  // Methods
  //

  //
  // Accessor methods
  //

  /**
   * Set the value of idediteur
   * 
   * @param newVar the new value of idediteur
   */
  public void setIdediteur(int newVar) {
    idediteur = newVar;
  }

  /**
   * Get the value of idediteur
   * 
   * @return the value of idediteur
   */
  public int getIdediteur() {
    return idediteur;
  }

  /**
   * Set the value of nom
   * 
   * @param newVar the new value of nom
   */
  public void setNom(String newVar) {
    nom = newVar;
  }

  /**
   * Get the value of nom
   * 
   * @return the value of nom
   */
  public String getNom() {
    return nom;
  }

  //
  // Other methods
  //

}
