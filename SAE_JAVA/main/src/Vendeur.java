
import java.util.*;


/**
 * Class Vendeur
 */
public class Vendeur {

  //
  // Fields
  //
  private Magasin magasin;
  private int idvendeur;
  private String nom;
  private String prenom;
  private String email;
  private String MDP;
  
  //
  // Constructors
  //
  public Vendeur (int idvendeur,
   String nom,
   String prenom,
   String email,
   String MDP,
   Magasin magasin) {
    this.idvendeur = idvendeur;
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
    this.MDP = MDP;
    this.magasin=magasin;
    };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of idvendeur
   * @param newId the new value of idvendeur
   */
  public void setIdvendeur (int newId) {
    this.idvendeur =  newId;
  }

  /**
   * Get the value of idvendeur
   * @return the value of idvendeur
   */
  public int getIdvendeur () {
    return this.idvendeur;
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
   * Set the value of prenom
   * @param newPrenom the new value of prenom
   */
  public void setPrenom (String newPrenom) {
    this.prenom = newPrenom;
  }

  /**
   * Get the value of prenom
   * @return the value of prenom
   */
  public String getPrenom () {
    return this.prenom;
  }

  /**
   * Set the value of email
   * @param newEmail the new value of email
   */
  public void setEmail (String newVar) {
    this.email = newVar;
  }

  /**
   * Get the value of email
   * @return the value of email
   */
  public String getEmail () {
    return this.email;
  }

  /**
   * Set the value of MDP
   * @param newMdp the new value of MDP
   */
  public void setMDP (String newMdp) {
    this.MDP = newMdp;
  }

  /**
   * Get the value of MDP
   * @return the value of MDP
   */
  public String getMDP () {
    return this.MDP;
  }

  //
  // Other methods
  //

  /**
   * @return       Commande
   */
  public Commande passerCommandeClient(){
    return null;
  }


  /**
   */
  public void ajouterLivreStock(Livre l,Stock i)  {
    this.magasin.ajouteStock(l, i);
  }


  /**
   */
  public void mettreAJourStock(HashMap<Livre,Stock> dicoMAJ) {
    for(Livre l:dicoMAJ.keySet()){
      for(Livre l2:this.magasin.getStock().keySet()){
        if (l.equals(l2)) {
          if (verifierDisponibilite(l, dicoMAJ.get(l))) {
            this.magasin.getStock().put(l,new Stock(this.magasin.getStock().get(l2).getQuantite()+dicoMAJ.get(l).getQuantite()));
          }
        }
      }
    }
  }


  /**
   * @return       boolean
   */
  public boolean verifierDisponibilite(Livre l, Stock i){
    if (!this.magasin.getStock().containsKey(l)){
      return false;
    }
    else if (this.magasin.getStock().get(l).getQuantite()+i.getQuantite()>0) {
      return true;
    }
    return false;
  }


  /**
   * @return       boolean
   */
  public void transfereLivre(Set<Magasin> s,Livre l)throws LivreDansAucunMagasinException{
      for (Magasin m : s) {
        if (m.getStock().containsKey(l)) {
          
        }
      }
    
      throw new LivreDansAucunMagasinException(); 
      }


}
