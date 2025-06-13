import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.sql.Date;
import java.util.Random;

public class ClientBD{
    ConnexionMySQL co;
    Statement st;



    public ClientBD(ConnexionMySQL co) {
        this.co = co;
    }

    public boolean seConnecter() {
        try {
            
            Statement st = this.co.createStatement();
            System.out.println("Entrez votre identifiant");
            String id = System.console().readLine();
            System.out.println("Entrez votre nom");
            String nom = System.console().readLine();
            System.out.println("Entrez votre prenom");
            String prenom = System.console().readLine();
            System.out.println("Entrez votre ville");
            String ville = System.console().readLine();
            System.out.println("Entrez votre code postal");
            String codeP = System.console().readLine();
            
            ResultSet admins = st.executeQuery("select * from Client");
            if (admins.next()) {
                if (id.equals(admins.getString("idCli"))) {
                    if (nom.equals(admins.getString("nomcli"))) {
                        if (prenom.equals(admins.getString("prenomcli"))){
                            if (ville.equals(admins.getString("villecli"))){
                                if (codeP.equals(admins.getString("codepostal"))){
                                    return true;
                                }
                            }
                        }
                    }
                } else {
                    throw new SQLException();
                }
            }
        } catch (SQLException e) {
            System.out.println("Votre Identifiant ou mot de passe est incorrecte");
        }
        return false;
    }
    public void creerCompte(String id, String mdp, String nom, String prenom, String adresse, String email) {
        try {
            PreparedStatement ps = this.co.prepareStatement("INSERT INTO Client (idCli, motdepasse, nom, prenom, adresse, email) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, id);
            ps.setString(2, mdp);
            ps.setString(3, nom);
            ps.setString(4, prenom);
            ps.setString(5, adresse);
            ps.setString(6, email);
            ps.executeUpdate();
            System.out.println("Compte créé avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création du compte : " + e.getMessage());
        }
    }




    public void onVousRecommande(Client C){
        try {
            Statement st = this.co.createStatement();
            ResultSet rs = st.executeQuery("select isbn fron DetailCommande natural join Commande where idCli in (select idcli from Client limit 10)");
            ResultSet rs2= st.executeQuery("select isbn fron DetailCommande natural join Commande where idCli ="+ C.getIdclient());
            Set<Integer> LivrePasLu = new HashSet<>();
            while (rs.next()) {
                String isbn = rs.getString("isbn");
                rs2.beforeFirst();
                while (rs2.next()) {
                    if (!rs2.getString("isbn").equals(isbn)) {
                       LivrePasLu.add(Integer.parseInt(isbn));
                        
                        
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
       try{
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
            ResultSet rs2 = st.executeQuery("SELECT MAX(numcom) AS maxNumCom FROM Commande");
            ResultSet rs3 = st.executeQuery("SELECT MAX(numlig) AS maxNumLig FROM DETAILCOMMANDE");
            int  idCommande =  rs2.getInt("maxNumCom") + 1;
            int idDetailCom = rs3.getInt("maxNumLig") + 1;
            ResultSet rs = st.executeQuery("SELECT prix FROM Livre WHERE isbn = '" + idLivre + "'");
            double prix = rs.getDouble("prix");
            Date dateCommande = new Date(System.currentTimeMillis());
            String enli = "O";
            if (typeLivraison.equalsIgnoreCase("M")) {
                enli = "N";
            }

            PreparedStatement ps = this.co.prepareStatement("INSERT INTO Commande (numcom, datecom, enligne, livraison, idcli, idmag) VALUES (?, ?, ?, ?, ?,?)");
            ps.setInt(1, idCommande);
            ps.setDate(2,dateCommande);
            ps.setString(3, enli);
            ps.setString(4, typeLivraison);
            ps.setString(5, idcli);
            ps.setString(6, idMagasin);
            ps.executeUpdate();

            PreparedStatement ps2 = this.co.prepareStatement("INSERT INTO DETAILCOMMANDE(numcom, numlig, isbn, qte, prixvente) values (?, ?, ?, ?, ?)");
            ps2.setInt(1, idCommande);
            ps2.setInt(2, idDetailCom);
            ps2.setString(3, idLivre);
            ps2.setInt(4, quantite);
            ps2.setDouble(5, prix );
            ps2.executeUpdate();


        }catch (SQLException e) {
                System.out.println("Erreur lors de la passation de la commande : " + e.getMessage());
        } 
    }
    public void VoirlesStock(){
       try {
            Statement st = this.co.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Posseder");
            while (rs.next()) {
                System.out.println("Livre ID: " + rs.getString("isbn") + ", Quantité: " + rs.getInt("quantite")+ ", Magasin ID: " + rs.getString("idmag"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des stocks : " + e.getMessage());
        }

    }

}















    

