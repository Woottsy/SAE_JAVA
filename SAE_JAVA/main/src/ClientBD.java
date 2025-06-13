import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

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


    public void passerCommande() {
       try{
            Statement st = this.co.createStatement();
            System.out.println("Dans quel magasin souhaitez-vous passer la commande ?");
            String idMagasin = System.console().readLine();
            System.out.println("Entrez l'identifiant du livre que vous souhaitez commander :");
            String idLivre = System.console().readLine();
            System.out.println("Entrez la quantité souhaitée :");
            int quantite = Integer.parseInt(System.console().readLine());
            


        
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
















    

