
import java.util.*;


/**
 * Class Classification
 */
public class Classification {

  //
  // Fields
  //

  private int idclass;
  private String nomclasse;
  
  //
  // Constructors
  //
  public Classification (int idclass,String nomclasse) {
    this.idclass = idclass;
    this.nomclasse = nomclasse;
   };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of idclass
   * @param newId the new value of idclass
   */
  public void setIdclass (int newId) {
    this.idclass = newId;
  }

  /**
   * Get the value of idclass
   * @return the value of idclass
   */
  public int getIdclass () {
    return this.idclass;
  }

  /**
   * Set the value of nomclasse
   * @param newClasse the new value of nomclasse
   */
  public void setNomclasse (String newClasse) {
    this.nomclasse = newClasse;
  }

  /**
   * Get the value of nomclasse
   * @return the value of nomclasse
   */
  public String getNomclasse () {
    return this.nomclasse;
  }

  //
  // Other methods
  //

}
