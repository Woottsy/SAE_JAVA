public class AppLibrairie {
    private Librairie librairie;
    private boolean quitter;
    public AppLibrairie(Librairie l){
        this.librairie=l;
        this.quitter=false;
    }
    public void menu() {
	boolean commande_faite = false;
	while(!commande_faite) {
	    System.out.println("Que voulez vous faire?");
	    System.out.println("Q: quitter");
	    String commande_brute = System.console().readLine();
	    String commande = commande_brute.strip().toLowerCase();
	    if(commande.equals("q")) {
		quitter = true;
		commande_faite = true;
	    } else {
		System.out.println("Commande '" + commande_brute + "' invalide.");
	    }
    }
    }
    public void run() {
	bienvenue();
	boolean continuer = true;
	while(!quitter) {
	    menu();
	}
	au_revoir();
    }
    public void bienvenue() {
	System.out.println("╭────────────────────────────────────────────────────────────────────────────────────╮");
	System.out.println("│ Bienvenue!Sur cette application tu pourra voir tous les livres dont ut peux réver. │");
	System.out.println("╰────────────────────────────────────────────────────────────────────────────────────╯");
    }
    public void au_revoir() {
	System.out.println("╭────────────╮");
	System.out.println("│ Au revoir  │");
	System.out.println("╰────────────╯");
    }
}
