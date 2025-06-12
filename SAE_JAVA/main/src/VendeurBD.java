
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.util.List;
import java.util.ArrayList;
import java.sql.Date;
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

    public void insererLivre(Livre l)throws SQLException{

            ResultSet resultat=st.executeQuery("select idmag from  AFFLIATION where idVendeur="+vendeur);
            String magasin=resultat.getString("idmag");
            ResultSet verif=st.executeQuery("select isbn,qte from  AFFLIATION where idmag="+magasin);
            if(!verif.isBeforeFirst()){
                PreparedStatement ps= this.laConnexion.prepareStatement("insert into POSSEDER (idmag,isbn,qte) values("+magasin+","+l.getIsbn()+","+1+")");
                ps.executeQuery();
               }

            }

    
    public void majQTELivre(Livre l ,int qte)throws SQLException{

            ResultSet resultat=st.executeQuery("select idmag from  AFFLIATION where idVendeur="+vendeur);
            String magasin=resultat.getString("idmag");
            ResultSet verif=st.executeQuery("select isbn,qte from  AFFLIATION where idmag="+magasin);
            if(verif.isBeforeFirst()){
                PreparedStatement ps= this.laConnexion.prepareStatement("update qte="+qte+" where isbn="+l.getIsbn()+" and idmag="+magasin);
                ps.executeQuery();
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
public void nouvelleCommande(Commande c,Client client) throws SQLException {

    int numcom = this.maxnumCommande();
    char enligne = c.getEnligne() ? 'O' : 'N';
    char livraison = (c.getTypelivraison() != null && !c.getTypelivraison().isEmpty()) ? 'O' : 'N';
    int idcli = client.getIdclient(); 
    String idmag = this.getidMagasin();

    PreparedStatement ps = this.laConnexion.prepareStatement(
        "INSERT INTO COMMANDE (numcom, datecom, enligne, livraison, idcli, idmag) VALUES (?, ?, ?, ?, ?, ?)"
    );
    ps.setInt(1, numcom);
    ps.setDate(2, new java.sql.Date(c.getDatecommande().getTime()));
    ps.setString(3, String.valueOf(enligne));
    ps.setString(4, String.valueOf(livraison));
    ps.setInt(5, idcli);
    ps.setString(6, idmag);

    ps.executeUpdate();
    ps.close();
}



}
