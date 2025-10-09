package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class Client
 */
public class Client {

  //
  // Fields
  //

  private int idclient;
  private String nom;
  private String prenom;
  private String adresse;
  private String ville;
  private String codepostal;

  private final List<Livre> livres;
  private final List<Commande> commandes;

  //
  // Constructors
  //
  public Client(int idclient, String nom, String prenom, String adresse, String codepostal, String ville) {
    this.idclient = idclient;
    this.nom = nom;
    this.prenom = prenom;
    this.adresse = adresse;
    this.codepostal = codepostal;
    this.ville = ville;
    this.livres = new ArrayList<>();
    this.commandes = new ArrayList<>();
  };

  //
  // Methods
  //

  //
  // Accessor methods
  //

  /**
   * Set the value of idclient
   * 
   * @param newId the new value of idclient
   */
  public void setIdclient(int newId) {
    this.idclient = newId;
  }

  /**
   * Get the value of idclient
   * 
   * @return the value of idclient
   */
  public int getIdclient() {
    return this.idclient;
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

  /**
   * Set the value of prenom
   * 
   * @param newPrenom the new value of prenom
   */
  public void setPrenom(String newPrenom) {
    this.prenom = newPrenom;
  }

  /**
   * Get the value of prenom
   * 
   * @return the value of prenom
   */
  public String getPrenom() {
    return this.prenom;
  }

  /**
   * Set the value of adresse
   * 
   * @param newAdresse the new value of adresse
   */
  public void setAdresse(String newAdresse) {
    adresse = newAdresse;
  }

  /**
   * Get the value of adresse
   * 
   * @return the value of adresse
   */
  public String getAdresse() {
    return this.adresse;
  }

  /**
   * Set the value of ville
   * 
   * @param newVille the new value of ville
   */
  public void setVille(String newVille) {
    this.ville = newVille;
  }

  /**
   * Get the value of ville
   * 
   * @return the value of ville
   */
  public String getVille() {
    return this.ville;
  }

  /**
   * Set the value of codepostal
   * 
   * @param newCodePostal the new value of codepostal
   */
  public void setCodepostal(String newCodePostal) {
    this.codepostal = newCodePostal;
  }

  /**
   * Get the value of codepostal
   * 
   * @return the value of codepostal
   */
  public String getCodepostal() {
    return this.codepostal;
  }

  public List<Livre> getLivre() {
    return this.livres;
  }

  public void ajouteCommande(Commande commande) {
    this.commandes.add(commande);
  }

  public List<Commande> getCommandes() {
    return this.commandes;
  }

  //
  // Other methods
  //

  /**
   */
  public void consulterCatalogue() {
  }

  /**
   * @return Commande
   */
  public Commande passerCommande() {
    return null;
  }

  /**
   * Permet au client de choisir un type de livraison.
   * 
   * @return TypeDeLivraison
   */
  public TypeDeLivraison.TypeLivraison choisirLivraison() {
      TypeDeLivraison.TypeLivraison typeLivraison;
      try (Scanner scanner = new Scanner(System.in)) {
          int choix = 0;
          typeLivraison = null;
          while (choix != 1 && choix != 2) {
              System.out.println("Choisissez votre mode de livraison :");
              System.out.println("1 - Retrait en magasin");
              System.out.println("2 - Livraison à domicile");
              System.out.print("Votre choix : ");
              
              try {
                  choix = scanner.nextInt();
                  switch (choix) {
                      case 1 -> typeLivraison = TypeDeLivraison.TypeLivraison.MAGASIN;
                      case 2 -> typeLivraison = TypeDeLivraison.TypeLivraison.DOMICILE;
                      default -> System.out.println("Veuillez entrer 1 ou 2.");
                  }
              } catch (Exception e) {
                  System.out.println("Erreur de saisie. Veuillez entrer un nombre.");
                  scanner.nextLine();
              }
          } }

    return typeLivraison;
  }

  /**
   * @return String
   */
  public String editerFacture() {
    return null;
  }

  /**
   * @return String
   */
  public String consulterHistorique() {
    return null;
  }

  // private int getNbLivresCommander() {
  // return this.livres.size();
  // }

  // private Livre getLivre(int i) {
  // return this.livres.get(i);
  // }

  // les méthoes suivantes sont commentées car elles ne servent que d'aide pour
  // ClientBD

  // public void onVousRecommande(){
  // Set<Livre> livresReco = new HashSet<Livre>();
  // // for (Client c : ) {
  // // if (c.getIdclient() != this.idclient) {
  // // livresReco.addAll(lesLivresRecommandable(this, c));
  // // }
  // // }

  // if (livresReco.isEmpty()) {
  // System.out.println("Aucune recommandation disponible pour le moment.");
  // } else {
  // System.out.println("Livres recommandés pour vous :");
  // for (Livre l : livresReco) {
  // System.out.println("- " + l.getTitre());
  // }
  // }
  // }

  // //les methode en dessous seront utile pour la fonction onVousRecommande

  // /**
  // * Une des fonction nécessaire pour onVousRecommande
  // * @param c1 un client
  // * @param c2 un client différent de c1
  // * @return int
  // */
  // private int livreEncommuns(Client c1,Client c2){
  // int nbLivreEnCommuns = 0;
  // for (int i = 0; i < c1.getNbLivresCommander(); i++) {
  // for (int j = 0; j < c2.getNbLivresCommander(); j++) {
  // if
  // (c1.getLivre(i).getClassification().getIdclass()==(c2.getLivre(j).getClassification().getIdclass()))
  // {
  // nbLivreEnCommuns++;
  // }
  // }
  // }
  // return nbLivreEnCommuns;
  // }
  // private boolean compatible(Client c1,Client c2){
  // if (livreEncommuns(c1, c2)>=5){
  // return true;
  // }
  // else {
  // return false;
  // }
  // }
  // private Set<Livre> lesLivresRecommandable(Client c1,Client c2){
  // Set<Livre> reco = new HashSet<Livre>();
  // if (compatible(c1, c2)){

  // for(Livre l : c1.livres){
  // if (!c2.livres.contains(l)){
  // reco.add(l);
  // }
  // }

  // }
  // return reco;
  // }

  // /**
  // * @return String
  // */
  // public String modifierProfil(){
  // return null;
  // }

  // /**
  // * @return String
  // */
  // public String seDeconnecter(){
  // return null;
  // }

  @Override
  public String toString() {
    return "Le Client " + nom + " " + prenom + " (" + idclient + ") vit à l'adresse " + adresse + " dans la ville "
        + ville + " (" + codepostal + ")";
  }
}
