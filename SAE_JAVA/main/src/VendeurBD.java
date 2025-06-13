
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class VendeurBD {

    ConnexionMySQL laConnexion;
    Statement st;
    int vendeur;

    public VendeurBD(ConnexionMySQL co) {
        this.laConnexion = co;
    }

    public int maxnumVendeur() throws SQLException{
		int maxNum=0;
		this.st=this.laConnexion.createStatement();
		ResultSet resultat=st.executeQuery("select ifnull(max(numcom), 0) maxn from COMMANDE");
		if(resultat.next()){

			maxNum=resultat.getInt("maxn");
		}
		return maxNum + 1 ;
	}
    public int maxnumCommande() throws SQLException{
		int maxNum=0;
		this.st=this.laConnexion.createStatement();
		ResultSet resultat=st.executeQuery("select ifnull(max(keyVendeur), 0) maxn from VENDEUR");
		if(resultat.next()){

			maxNum=resultat.getInt("maxn");
		}
		return maxNum + 1 ;
	}
    public int maxnumDetailCommande() throws SQLException{
		int maxNum=0;
		this.st=this.laConnexion.createStatement();
		ResultSet resultat=st.executeQuery("select ifnull(max(numlig), 0) maxn from DETAILCOMMANDE");
		if(resultat.next()){

			maxNum=resultat.getInt("maxn");
		}
		return maxNum + 1 ;
	}
	public boolean seConnecter()throws SQLException {
  
            // Initialisation et récupération de l'id et du mdp
            Statement st = this.laConnexion.createStatement();
            System.out.println("Entrez votre identifiant");
            String id = System.console().readLine();
            System.out.println("Entrez votre mot de passe");
            String mdp = System.console().readLine();
            //Pour afficher le magasin supprimée
            ResultSet vendeurs = st.executeQuery("select * from VENDEUR");
            if (vendeurs.next()) {
                if (id.equals(vendeurs.getString("identVendeur"))) {
                    vendeur=vendeurs.getInt("keyvendeur");
                    if (mdp.equals(vendeurs.getString("motdepasseVendeur"))) {
                        return true;
                    }
                } else {
                    throw new SQLException();
                }
            }
        return false;
    }
    public String getidMagasin()throws SQLException{
        String idmagasin="";

            ResultSet resultatset=st.executeQuery("select * from MAGASIN natural join AFFILIATION natural join VENDEUR where keyVendeur="+this.vendeur);
            idmagasin= resultatset.getString("idmag");
        return idmagasin;

    }

    public void insererLivre()throws SQLException{
            Statement localSt = this.laConnexion.createStatement();
            System.out.println("Pour inserer un livre, entrez son isbn : ");
            String isbn = System.console().readLine();
            ResultSet resultat=localSt.executeQuery("select idmag from  AFFLIATION where idVendeur="+vendeur);
            String magasin=resultat.getString("idmag");
            ResultSet verif=localSt.executeQuery("select isbn,qte from  POSSEDER where idmag="+magasin);
            if(!verif.isBeforeFirst()){
                PreparedStatement ps= this.laConnexion.prepareStatement("insert into POSSEDER (idmag,isbn,qte) values("+magasin+","+isbn+","+1+")");
                ps.executeUpdate();
               }

            }
    public String verifierDispo(){
            System.out.println("Pour inserer un livre, entrez son isbn : ");
            String isbn = System.console().readLine();
            try {
                ResultSet resultat=st.executeQuery("select idmag from  AFFLIATION where idVendeur="+vendeur);
                String magasin=resultat.getString("idmag");
                ResultSet verif=st.executeQuery("select isbn,qte from  POSSEDER where idmag="+magasin);
            } catch (SQLException e) {
                System.out.println("le livre "+isbn+"n'est pas diponible");
            }
            return "le livre "+isbn+"est disponible";

    }

    
    public void majQTELivre()throws SQLException{

            System.out.println("Pour mettre a jour un livre, entrez son isbn : ");
            String isbn = System.console().readLine();

            System.out.println("Pour mettre a jour sa quantité, entrez sa quantité : ");
            String qtestring = System.console().readLine();
            int qte=Integer.parseInt(qtestring);

            ResultSet resultat=st.executeQuery("select idmag from  AFFLIATION where idVendeur="+vendeur);
            String magasin=resultat.getString("idmag");
            ResultSet verif=st.executeQuery("select isbn,qte from  POSSEDER where idmag="+magasin);
            if(verif.isBeforeFirst()){
                PreparedStatement ps= this.laConnexion.prepareStatement("update qte="+qte+" where isbn="+isbn+" and idmag="+magasin);
                ps.executeUpdate();
            }

        
    }
    public ArrayList<Livre> selectionLivreMagasin()throws SQLException{
        ArrayList<Livre> res= new  ArrayList<>();
            this.st=laConnexion.createStatement();
            ResultSet resultat = st.executeQuery("Select isbn,titre,nbpages,datepubli,prix,iddewey,nomclass from Livre natural join POSSEDER natural join MAGASIN natural join AFFILIATION natural join THEMES natural join CLASSIFICATION where keyVendeur="+this.vendeur);
            while (resultat.next()) {
                String id=resultat.getString("isbn");
                String titre=resultat.getString("titre");
                int nbpages=resultat.getInt("nbpages");
                String datepubli=""+resultat.getInt("datepubli");
                int iddewey=resultat.getInt("iddewey");
                String theme=resultat.getString("nomclass");
                double prix=resultat.getDouble("prix");

                Livre ltmp=new Livre(id, titre, nbpages, datepubli,prix, new Classification(iddewey, theme));
                res.add(ltmp);
            }
		return res;
        
    }
    public void nouvelleCommande() throws SQLException {
        int numcom = this.maxnumCommande();
            System.out.println("Pour commencer la commande, entrez l'id du client : ");
            String idstrng = System.console().readLine();
            int idclient=Integer.parseInt(idstrng);

            java.util.Date date= new java.util.Date();

            System.out.println("Commande en ligne ? (O/N) :");
            String enligneStr = System.console().readLine();
            boolean enligne = enligneStr.equalsIgnoreCase("O");

            System.out.println("Type de livraison (M pour en magasin et C pour commander :");
            String typelivraison = System.console().readLine();

            System.out.println("Pour mettre a jour sa quantité, entrez sa quantité : ");
            String qtestring = System.console().readLine();
            int qte=Integer.parseInt(qtestring);

            System.out.println("Pour bien commander, entrez l'isbn du livre : ");
            String isbn = System.console().readLine();


            Statement st = this.laConnexion.createStatement();
            ResultSet res=st.executeQuery("select prix from LIVRE where isbn="+isbn);
            Double prix= res.getDouble("prix")*qte;

            PreparedStatement ps = this.laConnexion.prepareStatement(
                "INSERT INTO COMMANDE (numcom, datecom, enligne, livraison, idcli, idmag) VALUES (?, ?, ?, ?, ?, ?)"
            );
            ps.setInt(1, numcom);
            ps.setDate(2, new java.sql.Date(date.getTime()));
            ps.setString(3, String.valueOf(enligne));
            ps.setString(4, String.valueOf(typelivraison));
            ps.setInt(5, idclient);
            ps.setString(6, this.getidMagasin());

            ps.executeUpdate();
            ps.close();
            PreparedStatement ps2 = this.laConnexion.prepareStatement(
                "INSERT INTO DETAILCOMMANDE (numcom, numlig, qte, prixvente, isbn) VALUES (?, ?, ?, ?, ?)"
            ); 
            ps2.setInt(1, numcom);
            ps2.setInt(2, maxnumDetailCommande());
            ps2.setInt(3, qte);
            ps2.setDouble(4, prix);
            ps2.setString(5, isbn);
            ps2.executeUpdate();
            ps2.close();
    }



}
