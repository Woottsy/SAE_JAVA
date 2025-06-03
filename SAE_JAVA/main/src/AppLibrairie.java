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
	    System.out.println("a: pour vous connecter en tant qu'administrateur");
	    System.out.println("c: pour vous connecter en tant que client");
	    System.out.println("v: pour vous connecter en tant que vendeur");
	    System.out.println("Q: quitter");
	    String commande_brute = System.console().readLine();
	    String commande = commande_brute.strip().toLowerCase();
	    if(commande.equals("q")) {
		quitter = true;
		commande_faite = true;
	    } 
		if(commande.equals("a")) {
		menuAdmin();
		commande_faite = true;
	    }
		if(commande.equals("c")) {
		menuAcheteur();
		commande_faite = true;
	    }
		if(commande.equals("v")) {
		menuVendeur();
		commande_faite = true;
	    }else {
		System.out.println("Commande '" + commande_brute + "' invalide.");
	    }
    }
    }
	public void menuAdmin() {
	boolean commande_faite = false;
	while(!commande_faite) {
	    System.out.println("Que voulez vous faire?");
	    System.out.println("g: gérer les rôles");
	    System.out.println("il: insérer un nouveau livre");
	    System.out.println("ir: insérer un nouveau role");
	    System.out.println("s: obtenir les infos souhaiter");
	    System.out.println("Q: quitter");
	    String commande_brute = System.console().readLine();
	    String commande = commande_brute.strip().toLowerCase();
	    if(commande.equals("q")) {
		quitter = true;
		commande_faite = true;
	    }
		if(commande.equals("g")) {
		commande_faite = true;
		
	    }
		if(commande.equals("il")) {
		commande_faite = true;

	    }
		if(commande.equals("ir")) {
		commande_faite = true;
		
	    }
		if(commande.equals("s")) {
		commande_faite = true;
		
	    } 
		else {
		System.out.println("Commande '" + commande_brute + "' invalide.");
	    }
    }
    }
	public void menuVendeur() {
	boolean commande_faite = false;
	while(!commande_faite) {
	    System.out.println("Que voulez vous faire?");
	    System.out.println("il: insérer un nouveau livre");
	    System.out.println("m: modifier les infos ");
	    System.out.println("s: obtenir les infos souhaiter");
	    System.out.println("Q: quitter");
	    String commande_brute = System.console().readLine();
	    String commande = commande_brute.strip().toLowerCase();
	    if(commande.equals("q")) {
		quitter = true;
		commande_faite = true;
	    } 
		if(commande.equals("il")) {
		commande_faite = true;

	    }
		if(commande.equals("m")) {
		commande_faite = true;
		
	    }
		if(commande.equals("s")) {
		commande_faite = true;
		
	    } else {
		System.out.println("Commande '" + commande_brute + "' invalide.");
	    }
	}
	}
	public void menuAcheteur() {
	boolean commande_faite = false;
	while(!commande_faite) {
	    System.out.println("Que voulez vous faire?");
	    System.out.println("s: obtenir les infos souhaiter");
	    System.out.println("Q: quitter");
	    String commande_brute = System.console().readLine();
	    String commande = commande_brute.strip().toLowerCase();
	    if(commande.equals("q")) {
		quitter = true;
		commande_faite = true;
	    } 
		if(commande.equals("s")) {
		commande_faite = true;
		
	    }else {
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
	System.out.println("│ Bienvenue!Sur cette application tu pourra voir tous les livres dont tu peux réver. │");
	System.out.println("╰────────────────────────────────────────────────────────────────────────────────────╯");
    }
    public void au_revoir() {
	System.out.println("╭────────────╮");
	System.out.println("│ Au revoir .│");
	System.out.println("╰────────────╯");
    }
}
