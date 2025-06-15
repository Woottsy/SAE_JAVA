
import java.sql.SQLException;
import java.util.List;

public class AppLibrairie {

    private boolean quitter_stat;
    private boolean quitter_vendeur;
    private boolean quitter_Client;
    private boolean quitter_admin;
    private boolean quitter;
    private Librairie librairie;
    private ConnexionMySQL connexionMySQL;
    private Magasin magChoisi;

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

    public void start()throws SQLException {
        while (!quitter) {
            menu();
        }
    }

    public void menu() throws SQLException{
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
                connexion_vendeur();
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
            System.out.println("| Z : Accéder au menu statistique          |");
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
            } else if (commande.equals("d")) {
                administrateurBD.supprimerVendeur();
            } else if (commande.equals("g")) {
                administrateurBD.affilierVendeur();
            } else if (commande.equals("m")) {
                menu_choisirMagasin();
            } else if (commande.equals("z")) {
                while(!quitter_stat){
                    menu_stat();
                }
                
            } else {
                System.out.println("Commande '" + commande_brute + "' invalide.");
            }
        }

    }

    public void menu_stat() {
        boolean commande_faite = false;
        AdministrateurBD adminBD = new AdministrateurBD(this.connexionMySQL);
        while (!commande_faite) {
            System.out.println("╭──────────────────────────────────────────╮");
            System.out.println("|             Menu Statistique             |");
            System.out.println("|──────────────────────────────────────────|");
            System.out.println("| V : Voir les ventes globales             |");
            System.out.println("| C : Voir le CA par Clients               |");
            System.out.println("| L : Voir le livre le plus vendu          |");
            System.out.println("| Q : Se déconnecter                       |");
            System.out.println("╰──────────────────────────────────────────╯" + '\n');
            String commande_brute = System.console().readLine();
            String commande = commande_brute.strip().toLowerCase();
            if (commande.equals("q")) {
                quitter_stat = true;
                commande_faite = true;
            } else if (commande.equals("v")){
                adminBD.ventesGlobales();
            } else if (commande.equals("c")){
                //à implementer
            } else if (commande.equals("l")){
                adminBD.livreLePlusVendu();
            }
        }
    }

    public void menu_choisirMagasin() {
        boolean commande_faite = false;
        try {
            MagasinBD magasinBD = new MagasinBD(this.connexionMySQL);
            List<Magasin> magasins = magasinBD.getAllMagasins();
            if (magasins.isEmpty()) {
                System.out.println("Aucun magasin existant.");
            }
            StringBuilder res = new StringBuilder();
            res.append("╭──────────────────────────────────────────╮\n");
            res.append("|         Choisissez un magasin            |\n");
            res.append("|──────────────────────────────────────────|\n");
            for (int i = 0; i < magasins.size(); i++) {
                int longeurNom = magasins.get(i).getNom().length();
                int espaces = 37 - longeurNom; // Calculer le nombre d'espaces à ajouter
                res.append("| ").append(i + 1).append(" : ").append(magasins.get(i).getNom());
                for (int j = 0; j < espaces; j++) {
                    res.append(" "); // Ajouter les espaces
                }
                res.append("|\n");
            }
            res.append("| Q : Retour                               |\n");
            res.append("╰──────────────────────────────────────────╯\n");
            System.out.println(res.toString());
            String commande_brute = System.console().readLine();
            String commande = commande_brute.strip().toLowerCase();
            if (commande.equals("q")) {
                commande_faite = true;
            } else {
                try {
                    int choix = Integer.parseInt(commande);
                    if (choix > 0 && choix <= magasins.size()) {
                        Magasin magasinChoisi = magasins.get(choix - 1);
                        this.magChoisi = magasinChoisi;
                        System.out.println("Vous avez choisi le magasin : " + magasinChoisi.getNom() + " (" + magasinChoisi.getVille() + ")");
                        menu_gererStock(); // Appeler le menu de gestion des stocks pour ce magasin
                    } else {
                        System.out.println("Choix invalide. Veuillez réessayer.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Format invalide. Veuillez entrer un numéro valide.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des magasins.");
        }
    }

    public void menu_gererStock() {
        boolean commande_faite = false;
        MagasinBD magBD = new MagasinBD(this.connexionMySQL);
        while (!commande_faite) {
            System.out.println("╭──────────────────────────────────────────╮");
            System.out.println("|         Menu Gérer les stocks            |");
            System.out.println("|──────────────────────────────────────────|");
            System.out.println("| A : Ajouter un livre                     |");
            System.out.println("| S : Supprimer un livre                   |");
            System.out.println("| L : Liste des livres                     |");
            System.out.println("| M : Modifier la quantité d'un livre      |");
            System.out.println("| Q : Revenir au menu précédent            |");
            System.out.println("╰──────────────────────────────────────────╯" + '\n');
            String commande_brute = System.console().readLine();
            String commande = commande_brute.strip().toLowerCase();
            if (commande.equals("q")) {
                commande_faite = true;
            } else if (commande.equals("a")) {
                magBD.ajouterLivre(this.magChoisi);
            } else if (commande.equals("s")) {
                magBD.supprimerLivre(this.magChoisi);
            } else if (commande.equals("l")) {
                magBD.listeLivres(this.magChoisi);
            } else if (commande.equals("m")) {
                magBD.modifierQuantiteLivre(this.magChoisi);
            } else {
                System.out.println("Commande '" + commande_brute + "' invalide.");
            }
        }
    }

    public void menu_Client() {
        String motDePasse = "";
        String identifiant = "";
        boolean id = false;
        boolean mdp = false;
        boolean commande_faite = false;
        ClientBD clientBD = new ClientBD(this.connexionMySQL);
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
                if (clientBD.seConnecter()){
                    System.out.println("YES");
                }
            }

        } // mettre une requete sql pour l'id
    }

    public void connexion_vendeur()throws SQLException {
        VendeurBD vendeurBD = new VendeurBD(this.connexionMySQL);
        boolean commande_faite = false;
        System.out.println("╭──────────────────────────────────────────╮");
        System.out.println("|               Menu Vendeur               |");
        System.out.println("|──────────────────────────────────────────|");
        System.out.println("| S : Se connecter                         |");
        System.out.println("| Q : Revenir au menu précédent            |");
        System.out.println("╰──────────────────────────────────────────╯" + '\n');
        String commande_brute = System.console().readLine();
        String commande = commande_brute.strip().toLowerCase();
        if (commande.equals("q")) {
            quitter_vendeur = true;
            commande_faite = true;
        } else if (commande.equals("s")) {
            if(vendeurBD.seConnecter()){
                menu_vendeur();
            }
        }
    }

    public void menu_vendeur()throws SQLException {
        VendeurBD vendeurBD = new VendeurBD(this.connexionMySQL);
        boolean commande_faite = false;
        System.out.println("╭──────────────────────────────────────────╮");
        System.out.println("|               Menu Vendeur               |");
        System.out.println("|──────────────────────────────────────────|");
        System.out.println("| A : Ajouter un livre au stock            |");
        System.out.println("| M : Mettre à jour le stock               |");
        System.out.println("| V : Vérifier la disponibilité            |");
        System.out.println("| P : Passer une commande pour un client   |");
        System.out.println("| Q : Revenir au menu précédent            |");
        System.out.println("╰──────────────────────────────────────────╯" + '\n');
        String commande_brute = System.console().readLine();
        String commande = commande_brute.strip().toLowerCase();
        if (commande.equals("q")) {
            quitter_vendeur = true;
            commande_faite = true;
        } else if (commande.equals("a")) {

            vendeurBD.insererLivre();
        }else if (commande.equals("m")) {
            vendeurBD.majQTELivre();
        }else if (commande.equals("v")) {
            vendeurBD.verifierDispo();
        }else if (commande.equals("p")) {
            vendeurBD.nouvelleCommande();

        }


        
    }

    public void quit() {
        System.out.println("╭────────────╮");
        System.out.println("│ Au revoir !│");
        System.out.println("╰────────────╯");
    }
}
