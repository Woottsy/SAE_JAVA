
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
  /**
   * Une des fonction nécessaire pour onVousRecommande
   * @param c1 un client
   * @param c2 un client différent de c1
   * @return int
   */
  private int livreEncommuns(Client c1,Client c2){
    int nbLivreEnCommuns = 0;
    for (int i = 0; i < c1.getNbLivres(); i++) {
      for (int j = 0; j < c2.getNbLivres(); j++) {
        if (c1.getLivre(i).getIdclass().equals(c2.getLivre(j).getIdclass())) {
          nbLivreEnCommuns++;
        }
      }
    }
    return nbLivreEnCommuns;
  }
  private boolean compatible(Client c1,Client c2){
    if (livreEncommuns(c1, c2)>=5){
      return true;
    }
    else {
      return false;
    }
  }
  private Set<Livre> lesLivresRecommandable(Client c1,Client c2){
    Set<Livre> reco = new HashSet<Livre>();
    if (compatible(c1, c2)){
      
      for(Livre l : c1.getLivre()){
        if (!c2.getLivre().contains(l)){
          reco.add(l);
        }
      }
      for(Livre l : c2.getLivre()){
        if (!c1.getLivre().contains(l)){
          reco.add(l);
        }
      }
    }
    return reco; 
  }
}