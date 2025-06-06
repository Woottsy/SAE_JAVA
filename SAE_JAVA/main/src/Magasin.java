
import java.util.*;


/**
 * Class Magasin
 */
public class Magasin {

  //
  // Fields
  //

  private String idmagasin;
  private String nom;
  private String ville;
  private Map<Livre,Stock> stockMagasin;;
  
  //
  // Constructors
  //
  public Magasin (String idmagasin,String nom,String ville) {
    this.idmagasin = idmagasin;
    this.nom = nom;
    this.ville = ville;

    this.stockMagasin = new HashMap<>();
   };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of idmagasin
   * @param newId the new value of idmagasin
   */
  public void setIdmagasin (String newId) {
    this.idmagasin = newId;
  }

  /**
   * Get the value of idmagasin
   * @return the value of idmagasin
   */
  public String getIdmagasin () {
    return this.idmagasin;
  }

  /**
   * Set the value of nom
   * @param newNom the new value of nom
   */
  public void setNom (String newNom) {
    this.nom = newNom;
  }

  /**
   * Get the value of nom
   * @return the value of nom
   */
  public String getNom () {
    return this.nom;
  }

  /**
   * Set the value of ville
   * @param newVille the new value of ville
   */
  public void setVille (String newVille) {
    this.ville = newVille;
  }

  /**
   * Get the value of ville
   * @return the value of ville
   */
  public String getVille () {
    return this.ville;
  }






  /**
   * Set the value of nouvel_attribut
   * @param newStock the new value of nouvel_attribut
   */
   public void ajouteStock (Livre newLivre,Stock newStock) {
     this.stockMagasin.put(newLivre,newStock); ;
   }

  /**
   * Get the value of nouvel_attribut
   * @return the value of nouvel_attribut
   */
  public Map<Livre,Stock> getStock () {
     return this.stockMagasin;
  }

  @Override
  public String toString() {
    return "Magasin{" +
            "idmagasin=" + this.idmagasin +
            ", nom='" + this.nom + '\'' +
            ", ville='" + this.ville + '\'' +
            ", stockMagasin=" + this.stockMagasin +
            '}';
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Magasin)) return false;
    Magasin magasin = (Magasin) o;
    return this.idmagasin == magasin.idmagasin && Objects.equals(this.nom, magasin.nom) && Objects.equals(this.ville, magasin.ville);
  }
  @Override
  public int hashCode() {
    return Objects.hash(this.idmagasin, this.nom, this.ville);
  }
}
