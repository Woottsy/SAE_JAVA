
import java.sql.SQLException;

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
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        }

    }

    public AppLibrairie() throws SQLException, ClassNotFoundException {
        this.connexionMySQL = new ConnexionMySQL();
        try {
            connexionMySQL.connecter("servinfo-maria", "DBlacroix", "lacroix", "lacroix");
        } catch (SQLException e) {
        }
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
        while (!quitter) {
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
                while (!quitter_admin) {
                    connexion_admin();
                }
                commande_faite = true;
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

    public void connexion_admin() {
        boolean commande_faite = false;
        AdministrateurBD administrateurBD = new AdministrateurBD(this.connexionMySQL);
        while (!commande_faite) {
            System.out.println("╭──────────────────────────────────────────╮");
            System.out.println("|            Menu Administrateur           |");
            System.out.println("|──────────────────────────────────────────|");
            System.out.println("| S : Se connecter                         |");
            System.out.println("| Q : Appuyer pour revenir en arrière      |");
            System.out.println("╰──────────────────────────────────────────╯" + '\n');
            String commande_brute = System.console().readLine();
            String commande = commande_brute.strip().toLowerCase();
            if (commande.equals("q")) {
                quitter_admin = true;
                commande_faite = true;
            } else if (commande.equals("s")) {
                if (administrateurBD.seConnecter()) {
                    menu_Admin();
                    commande_faite = true;
                }
            }
        }
    }

    public void menu_Admin() {
        boolean commande_faite = false;
        String idMag = "";
        String nomMag = "";
        String villeMag = "";
        AdministrateurBD administrateurBD = new AdministrateurBD(this.connexionMySQL);
        while (!commande_faite) {
            System.out.println("╭──────────────────────────────────────────╮");
            System.out.println("|         Menu Administrateur              |");
            System.out.println("|──────────────────────────────────────────|");
            System.out.println("| A : Ajouter un magasin                   |");
            System.out.println("| S : Supprimer un magasin                 |"); // ajouter une partie gerer les stock (lire le sujet)
            System.out.println("| L : Liste des magasins                   |");
            System.out.println("| M : Gérer les stocks                     |");
            System.out.println("| G : Affilier un vendeur                  |");
            System.out.println("| V : Liste des vendeurs                   |");
            System.out.println("| C : Créer un vendeur                     |");
            System.out.println("| D : Supprimer un vendeur                 |");
            System.out.println("| Q : Se déconnecter                       |");
            System.out.println("╰──────────────────────────────────────────╯" + '\n');
            String commande_brute = System.console().readLine();
            String commande = commande_brute.strip().toLowerCase();
            if (commande.equals("q")) {
                quitter_admin = true;
                commande_faite = true;
            } else if (commande.equals("a")) {
                administrateurBD.ajouterLibrairie(idMag, nomMag, villeMag);
            } else if (commande.equals("s")) {
                System.out.println("Pour supprimer une Librairie, entrez son Identifiant : ");
                try {
                    String input = System.console().readLine();
                    idMag = input;

                } catch (NumberFormatException e) {
                    System.out.println("Format invalide. Veuillez entrer les informations au format (idMag, NomMag, VilleMag).");
                }
                administrateurBD.supprimerLibrairie(idMag);

            } else if (commande.equals("l")) {
                administrateurBD.listeMagasins();
            } else if (commande.equals("c")) {
                administrateurBD.creerVendeur();
            } else if (commande.equals("v")) {
                administrateurBD.listeVendeurs();
            } else if(commande.equals("d")){
                administrateurBD.supprimerVendeur();
            } else if (commande.equals("g"){
                administrateurBD.affilierVendeur();
            } else {
                System.out.println("Commande '" + commande_brute + "' invalide.");
            }
        }

    }
    // En attente il faut faire des INSERT ET POUR SE CONNECTER IL FAUT JUSTE FAIRE UN SELECT TO AVEC UN WHERE ET LE MDP ET ID = A CELUI ENTREE

    public void menu_Client() {
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
            } else if (commande.equals("s")) {
                System.out.println("Veuillez entrer votre Identifiant :");
                while (!id) {
                    String input = System.console().readLine();
                    if (!input.isEmpty()) {
                        id = true;
                        identifiant = input;
                        commande_faite = true;
                    }
                }
                System.out.println("Veuillez entrer votre mot de passe :");
                while (!mdp) {
                    String input = System.console().readLine();
                    if (!input.isEmpty()) {
                        mdp = true;
                        motDePasse = input;
                        commande_faite = true;
                    }
                }
            }

        } // mettre une requete sql pour l'id
    }

    //à completer
    public void menu_vendeur() {

    }

    public void quit() {
        System.out.println("╭────────────╮");
        System.out.println("│ Au revoir !│");
        System.out.println("╰────────────╯");
    }
}
