
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.List;
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
		ResultSet resultat=st.executeQuery("select ifnull(max(keyVendeur), 0) maxn from VENDEUR");
		if(resultat.next()){

			maxNum=resultat.getInt("maxn");
		}
		return maxNum + 1 ;
	}
	public boolean seConnecter() {
        try {
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
        } catch (SQLException e) {
            System.out.println("Votre Identifiant ou mot de passe est incorrecte");
        }
        return false;
    }
    public void insererLivre(Livre l){
        try {
            ResultSet resultat=st.executeQuery("select idmag from  AFFLIATION where idVendeur="+vendeur);
            String magasin=resultat.getString("idmag");
            ResultSet verif=st.executeQuery("select isbn,qte from  AFFLIATION where idmag="+magasin);
            if(!verif.isBeforeFirst()){
                PreparedStatement ps= this.laConnexion.prepareStatement("insert into POSSEDER (idmag,isbn,qte) values("+magasin+","+l.getIsbn()+","+1+")");
                ps.executeQuery();
            }

        } catch (SQLException e) {
            System.out.println("livre déjà éxistant");
        }

    }
    public void majQTELivre(Livre l ,int qte){
        try {
            ResultSet resultat=st.executeQuery("select idmag from  AFFLIATION where idVendeur="+vendeur);
            String magasin=resultat.getString("idmag");
            ResultSet verif=st.executeQuery("select isbn,qte from  AFFLIATION where idmag="+magasin);
            if(verif.isBeforeFirst()){
                PreparedStatement ps= this.laConnexion.prepareStatement("update qte="+qte+" where isbn="+l.getIsbn()+" and idmag="+magasin);
                ps.executeQuery();
            }
        } catch (SQLException e) {
            System.out.println("ne peut pas mettre à jour");
        }


    



    }
    public ArrayList<Livre> selectionLivreMagasin(){
        ArrayList<Livre> res= new  ArrayList<>();
        try {
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
        } catch (SQLException e) {
            System.out.println("aucun livre");
        }

		return res;
        
    }


}
