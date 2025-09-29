
/**
 * Class Theme
 */
public class Theme {

  //
  // Fields
  //

  private int idtheme;
  private String nom;

  //
  // Constructors
  //
  public Theme(int idtheme,
      String nom) {
    this.idtheme = idtheme;
    this.nom = nom;
  };

  //
  // Methods
  //

  //
  // Accessor methods
  //

  /**
   * Set the value of idtheme
   * 
   * @param newId the new value of idtheme
   */
  public void setIdtheme(int newId) {
    this.idtheme = newId;
  }

  /**
   * Get the value of idtheme
   * 
   * @return the value of idtheme
   */
  public int getIdtheme() {
    return this.idtheme;
  }

  /**
   * Set the value of nom
   * 
   * @param newNom the new value of nom
   */
  public void setNom(String newNom) {
    this.nom = newNom;
  }

  /**
   * Get the value of nom
   * 
   * @return the value of nom
   */
  public String getNom() {
    return this.nom;
  }

  //
  // Other methods
  //

}
