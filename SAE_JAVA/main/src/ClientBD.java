import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ClientBD{
    private ConnexionMySQL co;
    private Statement st;
    public Client clientConnecte;



    public ClientBD(ConnexionMySQL co) {
        this.co = co;
        this.clientConnecte = null;
    }

    public boolean seConnecter() {
        System.out.println("Entrez votre identifiant");
        String ident = System.console().readLine();
        int id = Integer.parseInt(ident);
        System.out.println("Entrez votre nom");
        String nom = System.console().readLine();
        System.out.println("Entrez votre prenom");
        String prenom = System.console().readLine();
        System.out.println("Entrez votre adresse");
        String adresse = System.console().readLine();
        System.out.println("Entrez votre ville");
        String ville = System.console().readLine();
        System.out.println("Entrez votre code postal");
        String codeP = System.console().readLine();
        this.clientConnecte = new Client(id, nom, prenom, adresse, codeP, ville);
        try {
            
            Statement st = this.co.createStatement();

            PreparedStatement ps = this.co.prepareStatement("SELECT * FROM CLIENT WHERE idCli = ? AND nomcli = ? AND prenomcli = ? AND villecli = ? AND codepostal = ?");
            ps.setInt(1, id);
            ps.setString(2, nom);
            ps.setString(3, prenom);
            ps.setString(4, ville);
            ps.setString(5, codeP);
            ResultSet resultat = ps.executeQuery();
            if (resultat.next()) {
                System.out.println("Connexion réussie !");
                return true;
            } else {
                System.out.println("Identifiant ou mot de passe incorrect.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion : " + e.getMessage());
            return false;
        }
    }

    public void creerCompte(int id, String nom, String prenom, String adresse, String codePostal, String ville) {
        try {
            PreparedStatement ps = this.co.prepareStatement("INSERT INTO CLIENT (idCli, nomcli, prenomcli, adressecli, codepostal, villecli) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, id);
            ps.setString(2, nom);
            ps.setString(3, prenom);
            ps.setString(4, adresse);
            ps.setString(5, codePostal);
            ps.setString(6, ville);
            ps.executeUpdate();
            System.out.println("Compte créé avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création du compte : " + e.getMessage());
        }
    }




    public void onVousRecommande(Client c){
        try {
            Statement st = this.co.createStatement();
            ResultSet rs = st.executeQuery("select isbn from DETAILCOMMANDE natural join COMMANDE where idCli in (select idcli from CLIENT) limit 10");
            ResultSet rs2= st.executeQuery("select isbn from DETAILCOMMANDE natural join COMMANDE where idCli ="+ c.getIdclient());
            Set<String> LivrePasLu = new HashSet<>();
            while (rs.next()) {
                String isbn = rs.getString("isbn");
                rs2.beforeFirst();
                while (rs2.next()) {
                    if (!rs2.getString("isbn").equals(isbn)) {
                       LivrePasLu.add(isbn);
                        
                        
                    }
                }
                
            }
            if (LivrePasLu.isEmpty()) {
                System.out.println("Aucune recommandation disponible pour le moment.");
            } else {
                System.out.println("Livres recommandés : " + LivrePasLu);
            }
    }
    catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des recommandations : " + e.getMessage());
        }

    }
    private int RandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }


    public void passerCommande() {
        try {
            Statement st = this.co.createStatement();
    
            System.out.println("Entrez votre identifiant client :");
            String idcli = System.console().readLine();
    
            System.out.println("Dans quel magasin souhaitez-vous passer la commande ?");
            String idMagasin = System.console().readLine();
    
            System.out.println("Entrez l'identifiant du livre que vous souhaitez commander :");
            String idLivre = System.console().readLine();
    
            System.out.println("Entrez la quantité souhaitée :");
            int quantite = Integer.parseInt(System.console().readLine());
    
            System.out.println("Souhaitez-vous une livraison à domicile ? C (oui) ou M (non)");
            String typeLivraison = System.console().readLine();
    
            // Récupérer le max numcom
            ResultSet rs2 = st.executeQuery("SELECT MAX(numcom) AS maxNumCom FROM COMMANDE");
            int idCommande = 1;
            if (rs2.next()) {
                idCommande = rs2.getInt("maxNumCom") + 1;
            }
    
            // Récupérer le max numlig
            ResultSet rs3 = st.executeQuery("SELECT MAX(numlig) AS maxNumLig FROM DETAILCOMMANDE");
            int idDetailCom = 1;
            if (rs3.next()) {
                idDetailCom = rs3.getInt("maxNumLig") + 1;
            }
    
            // Récupérer le prix du livre
            ResultSet rs = st.executeQuery("SELECT prix FROM LIVRE WHERE isbn = '" + idLivre + "'");
            double prix;
            if (rs.next()) {
                prix = rs.getDouble("prix");
            } else {
                System.out.println("Livre introuvable !");
                return;
            }
    
            Date dateCommande = new Date(System.currentTimeMillis());
    
            String enli = "O";
            if (typeLivraison.equalsIgnoreCase("M")) {
                enli = "N";
            }
    
            PreparedStatement ps = this.co.prepareStatement(
                "INSERT INTO COMMANDE (numcom, datecom, enligne, livraison, idcli, idmag) VALUES (?, ?, ?, ?, ?, ?)"
            );
            ps.setInt(1, idCommande);
            ps.setDate(2, dateCommande);
            ps.setString(3, enli);
            ps.setString(4, typeLivraison);
            ps.setString(5, idcli);
            ps.setString(6, idMagasin);
            ps.executeUpdate();
    
            PreparedStatement ps2 = this.co.prepareStatement(
                "INSERT INTO DETAILCOMMANDE (numcom, numlig, isbn, qte, prixvente) VALUES (?, ?, ?, ?, ?)"
            );
            ps2.setInt(1, idCommande);
            ps2.setInt(2, idDetailCom);
            ps2.setString(3, idLivre);
            ps2.setInt(4, quantite);
            ps2.setDouble(5, prix);
            ps2.executeUpdate();
    
            System.out.println("Commande passée avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la passation de la commande : " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Quantité invalide. Veuillez saisir un nombre entier.");
        }
    }
    
    public void VoirlesStock(){
       try {
            Statement st = this.co.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM POSSEDER");
            while (rs.next()) {
                System.out.println("Livre ID: " + rs.getString("isbn") + ", Quantité: " + rs.getInt("qte")+ ", Magasin ID: " + rs.getString("idmag"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des stocks : " + e.getMessage());
        }

    }
}
















    

