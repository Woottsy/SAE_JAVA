

public class AppLibrairie {

    public static void main(String[] args) {
        AppLibrairie app = new AppLibrairie();
        app.start();
    }

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
            System.out.println("| Q: quitter                               |");
            System.out.println("| D: Menu Administrateur                   |");
            System.out.println("| P: Menu Clients                          |");
            System.out.println("| T: Menu Vendeur                          |");
            System.out.println("|                                          |");
            System.out.println("╰──────────────────────────────────────────╯" + '\n');
            String commande_brute = System.console().readLine();
            String commande = commande_brute.strip().toLowerCase();
            if (commande.equals("q")) {
                this.quitter = true;
                commande_faite = true;
            } else if (commande.equals("p")) {
                while (!quitter) {
                    menu_Admin();
                }
                commande_faite = true;
            } else if (commande.equals("d")) {
				commande_faite = true;
                System.out.println("uu");
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
            System.out.println("|              Menu amis                   |");
            System.out.println("|──────────────────────────────────────────|");
			System.out.println("| L : liste                                |");
            System.out.println("| S : selection                            |");
			System.out.println("| Q : quitter                              |");
            System.out.println("╰──────────────────────────────────────────╯" + '\n');
            String commande_brute = System.console().readLine();
            String commande = commande_brute.strip().toLowerCase();
            if (commande.equals("q")) {
                quitter = true;
                commande_faite = true;
            }
            else if (commande.equals("l")){
                System.out.println("\n");
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

    public void quit() {
        System.out.println("╭────────────╮");
        System.out.println("│ Au revoir !│");
        System.out.println("╰────────────╯");
    }
}
