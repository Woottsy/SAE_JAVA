import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AppLibrairie {	



    private boolean quitter_Client;
	private boolean quitter_admin;
    private boolean quitter;
    private Librairie librairie;
    private ConnexionMySQL connexionMySQL;
    

// séparer en Personne/PersonneBD

    public static void main(String[] args) {
        try {
        AppLibrairie app = new AppLibrairie();
        app.start();
        } catch (ClassNotFoundException e){}
        catch (SQLException e) {} 
        
    }





    public AppLibrairie() throws SQLException, ClassNotFoundException{
        this.connexionMySQL = new ConnexionMySQL();
        try {
            connexionMySQL.connecter("servinfo-maria", "DBlacroix", "lacroix", "lacroix");
        } catch (SQLException e) {}
    }

    public void init() {
        try {
            this.connexionMySQL = new ConnexionMySQL();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver MySQL non trouvé!!!");
            System.exit(1);
        }

    }

    public void start() {
        while(!quitter){
			menu();
		}
    }

    public void menu() {
        boolean commande_faite = false;
        while (!commande_faite) {
            System.out.println("╭──────────────────────────────────────────╮");
            System.out.println("|              Menu principal              |");
            System.out.println("|──────────────────────────────────────────|");
            System.out.println("| A: Menu Administrateur                   |");
            System.out.println("| C: Menu Clients                          |");
            System.out.println("| V: Menu Vendeur                          |");
            System.out.println("| Q: Quitter                               |");
			System.out.println("╰──────────────────────────────────────────╯" + '\n');
            String commande_brute = System.console().readLine();
            String commande = commande_brute.strip().toLowerCase();
            if (commande.equals("q")) {
                this.quitter = true;
                commande_faite = true;
            } else if (commande.equals("c")) {
                while (!quitter_Client) {
                    menu_Client();
                }
                quitter_Client = false;
                commande_faite = true;
            } else if (commande.equals("a")) {
				commande_faite = true;
                while(!quitter_admin){
					menu_Admin();
				}
                quitter_admin = false;
            } else if (commande.equals("v")) {
                menu_vendeur();
                commande_faite = true;
            } else if (commande.equals("m")) {
                System.out.println("\n");
                commande_faite = true;

            } else {
                System.out.println("Commande '" + commande_brute + "' invalide.");
            }

        }
    }

	public void menu_Admin() {
        boolean commande_faite = false;
        String idMag = "";
        String nomMag = "";
        String villeMag = "";
        while (!commande_faite) {
            System.out.println("╭──────────────────────────────────────────╮");
            System.out.println("|         Menu Administrateur              |");
            System.out.println("|──────────────────────────────────────────|");
			System.out.println("| A : Ajouter un magasin                   |");
            System.out.println("| S : Supprimer une librairie              |"); // ajouter une partie gerer les stock (lire le sujet)
            System.out.println("| L : Liste des librairies                 |");
            System.out.println("| V : Liste des vendeurs                   |");
            System.out.println("| C : Créer un vendeur                     |");
            System.out.println("| D : Supprimer un vendeur                 |");
            System.out.println("| Q : retour en arrière                    |");
            System.out.println("╰──────────────────────────────────────────╯" + '\n');
            String commande_brute = System.console().readLine();
            String commande = commande_brute.strip().toLowerCase();
            if (commande.equals("q")) {
                quitter_admin = true;
                commande_faite = true;
            }
            else if (commande.equals("a")){
                System.out.println("Pour ajouter une Librairie, écrivez les informations de celle-ci au format (idMag, NomMag, VilleMag)"); // mettre une requete sql pour l'id 
				try {
					String input = System.console().readLine();
					String[] parts = input.split(",");
					if (parts.length == 3) {
						 idMag = parts[0].strip();
                        Integer.parseInt(idMag);
						 nomMag = parts[1].strip();
						 villeMag = parts[2].strip();
						System.out.println("Librairie ajoutée : ID=" + idMag + ", Nom=" + nomMag + ", Ville=" + villeMag);
						// Ajouter ici une requête SQL
					}
				} catch(NumberFormatException e) {System.out.println("Format invalide. Veuillez entrer les informations au format (idMag, NomMag, VilleMag).");}
				
				commande_faite = true;
                try {
                    Statement st = this.connexionMySQL.createStatement();
                    PreparedStatement resultat=this.connexionMySQL.prepareStatement("INSERT INTO MAGASIN(idmag, nommag, villemag) values (?, ?, ?)");
                    resultat.setString(1, idMag);
                    resultat.setString(2, nomMag);
                    resultat.setString(3, villeMag);
                    resultat.executeUpdate();

                } catch (java.sql.SQLException e) {
                    System.out.println("Error creating statement: " + e.getMessage());
                }
            }
            else if (commande.equals("s")){
              //a implémenter

            }
            else {
                System.out.println("Commande '" + commande_brute + "' invalide.");
            }
        }

    }


    // En attente il faut faire des INSERT ET POUR SE CONNECTER IL FAUT JUSTE FAIRE UN SELECT TO AVEC UN WHERE ET LE MDP ET ID = A CELUI ENTREE
    public void menu_Client(){
        String motDePasse = "";
        String identifiant = "";
        boolean id = false;
        boolean mdp = false;
        boolean commande_faite = false;
        while (!commande_faite) {
            System.out.println("╭──────────────────────────────────────────╮");
            System.out.println("|               Menu Client                |");
            System.out.println("|──────────────────────────────────────────|");
			System.out.println("| S : Se connecter                         |");
            System.out.println("| C : Créer un compte                      |");
            System.out.println("| Q : Appuyer pour revenir en arrière      |");
            System.out.println("╰──────────────────────────────────────────╯" + '\n');
            String commande_brute = System.console().readLine();
            String commande = commande_brute.strip().toLowerCase();
            if (commande.equals("q")) {
                quitter_Client = true;
                commande_faite = true;
            }
            else if (commande.equals("s")){
                System.out.println("Veuillez entrer votre Identifiant :");
                while (!id) { 
                    String input = System.console().readLine();
                    if(!input.isEmpty()){
                        id = true;
                        identifiant = input;
                        commande_faite = true;
                    }
                }
                System.out.println("Veuillez entrer votre mot de passe :");
                while (!mdp){
                    String input = System.console().readLine();
                    if(!input.isEmpty()){
                        mdp = true;
                        motDePasse = input;
                        commande_faite = true;
                    }
                }
             }

        } // mettre une requete sql pour l'id
    }

    //à completer
    public void menu_vendeur(){

    }

    public void quit() {
        System.out.println("╭────────────╮");
        System.out.println("│ Au revoir !│");
        System.out.println("╰────────────╯");
    }
}
