

public class AppLibrairie {

    public static void main(String[] args) {
        AppLibrairie app = new AppLibrairie();
        app.start();
    }



	private boolean quitter_Client;
	private boolean quitter_admin;
    private boolean quitter;
    private Librairie librairie;
    private ConnexionMySQL connexionMySQL;

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
            System.out.println("|              Menu princpal               |");
            System.out.println("|──────────────────────────────────────────|");
            System.out.println("| A: Menu Administrateur                   |");
            System.out.println("| P: Menu Clients                          |");
            System.out.println("| T: Menu Vendeur                          |");
            System.out.println("| Q: Quitter                               |");
			System.out.println("╰──────────────────────────────────────────╯" + '\n');
            String commande_brute = System.console().readLine();
            String commande = commande_brute.strip().toLowerCase();
            if (commande.equals("q")) {
                this.quitter = true;
                commande_faite = true;
            } else if (commande.equals("p")) {
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
            } else if (commande.equals("t")) {
                System.out.println("\n");
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
        while (!commande_faite) {
            System.out.println("╭──────────────────────────────────────────╮");
            System.out.println("|         Menu Administrateur              |");
            System.out.println("|──────────────────────────────────────────|");
			System.out.println("| A : Ajouter un magasin                   |");
            System.out.println("| S : Supprimer une librairie              |");
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
						String idMag = parts[0].strip();
                        Integer.parseInt(idMag);
						String nomMag = parts[1].strip();
						String villeMag = parts[2].strip();
						System.out.println("Librairie ajoutée : ID=" + idMag + ", Nom=" + nomMag + ", Ville=" + villeMag);
						// Vous pouvez ajouter ici une requête SQL pour insérer ces informations dans la base de données
					}
				} catch(NumberFormatException e) {System.out.println("Format invalide. Veuillez entrer les informations au format (idMag, NomMag, VilleMag).");}
				
				commande_faite = true;
            }
            else if (commande.equals("s")){
              //a implémenter

            }
            else {
                System.out.println("Commande '" + commande_brute + "' invalide.");
            }
        }

    }

    public void menu_Client(){
        boolean commande_faite = false;
        while (!commande_faite) {
            System.out.println("╭──────────────────────────────────────────╮");
            System.out.println("|               Menu Client                |");
            System.out.println("|──────────────────────────────────────────|");
			System.out.println("| S : Se connecter                         |");
            System.out.println("| C : Créer un compte                      |");
            System.out.println("| Q : Retour en arrière                    |");
            System.out.println("╰──────────────────────────────────────────╯" + '\n');
            String commande_brute = System.console().readLine();
            String commande = commande_brute.strip().toLowerCase();
            if (commande.equals("q")) {
                quitter_Client = true;
                commande_faite = true;
            }
            else if (commande.equals("a")){
                System.out.println("Pour ajouter une Librairie, écrivez les informations de celle-ci au format (idMag, NomMag, VilleMag)");
             }
        } // mettre une requete sql pour l'id
    }

    public void quit() {
        System.out.println("╭────────────╮");
        System.out.println("│ Au revoir !│");
        System.out.println("╰────────────╯");
    }
}
