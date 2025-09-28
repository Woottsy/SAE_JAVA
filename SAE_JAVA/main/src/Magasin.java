import java.util.*;

/**
 * Classe représentant un magasin avec son identifiant, son nom, sa ville et son
 * stock de livres.
 */
public class Magasin {

  private String idmagasin;
  private String nom;
  private String ville;
  private Map<Livre, Stock> stockMagasin;

  /**
   * Constructeur de la classe Magasin.
   * 
   * @param idmagasin l'identifiant unique du magasin
   * @param nom       le nom du magasin
   * @param ville     la ville où se situe le magasin
   */
  public Magasin(String idmagasin, String nom, String ville) {
    this.idmagasin = idmagasin;
    this.nom = nom;
    this.ville = ville;
    this.stockMagasin = new HashMap<>();
  }

  /**
   * Modifie l'identifiant du magasin.
   * 
   * @param newId le nouvel identifiant du magasin
   */
  public void setIdmagasin(String newId) {
    this.idmagasin = newId;
  }

  /**
   * Retourne l'identifiant du magasin.
   * 
   * @return l'identifiant du magasin
   */
  public String getIdmagasin() {
    return this.idmagasin;
  }

  /**
   * Modifie le nom du magasin.
   * 
   * @param newNom le nouveau nom du magasin
   */
  public void setNom(String newNom) {
    this.nom = newNom;
  }

  /**
   * Retourne le nom du magasin.
   * 
   * @return le nom du magasin
   */
  public String getNom() {
    return this.nom;
  }

  /**
   * Modifie la ville du magasin.
   * 
   * @param newVille la nouvelle ville du magasin
   */
  public void setVille(String newVille) {
    this.ville = newVille;
  }

  /**
   * Retourne la ville du magasin.
   * 
   * @return la ville du magasin
   */
  public String getVille() {
    return this.ville;
  }

  /**
   * Ajoute ou met à jour le stock d'un livre dans le magasin.
   * 
   * @param newLivre le livre à ajouter ou mettre à jour
   * @param newStock le stock associé à ce livre
   */
  public void ajouteStock(Livre newLivre, Stock newStock) {
    this.stockMagasin.put(newLivre, newStock);
  }

  /**
   * Retourne la map des livres et leur stock dans le magasin.
   * 
   * @return la map Livre/Stock du magasin
   */
  public Map<Livre, Stock> getStock() {
    return this.stockMagasin;
  }

  /**
   * Retourne une représentation textuelle du magasin.
   * 
   * @return une chaîne décrivant le magasin
   */
  @Override
  public String toString() {
    return "Le magasin " + this.nom + " qui a pour identifiant : " + this.idmagasin + " est situé dans la ville : "
        + this.ville;
  }

  /**
   * Vérifie l'égalité entre deux objets Magasin.
   * 
   * @param o l'objet à comparer
   * @return true si les magasins sont identiques, false sinon
   */
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Magasin))
      return false;
    Magasin magasin = (Magasin) o;
    return this.idmagasin == magasin.idmagasin && Objects.equals(this.nom, magasin.nom)
        && Objects.equals(this.ville, magasin.ville);
  }

  /**
   * Calcule le hashcode du magasin.
   * 
   * @return le hashcode basé sur l'identifiant, le nom et la ville
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.idmagasin, this.nom, this.ville);
  }
}
