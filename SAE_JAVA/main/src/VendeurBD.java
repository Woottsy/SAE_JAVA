
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VendeurBD {

    ConnexionMySQL laConnexion;
    Statement st;

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
            ResultSet admins = st.executeQuery("select * from VENDEUR");
            if (admins.next()) {
                if (id.equals(admins.getString("idVendeur"))) {
                    if (mdp.equals(admins.getString("motdepasseVendeur"))) {
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
    public void insererLivre(Livre l,Vendeur v){
        try {
            ResultSet resultat=st.executeQuery("select idmag from  AFFLIATION where idVendeur="+v);
            String magasin=resultat.getString("idmag");
            PreparedStatement ps= this.laConnexion.prepareStatement("insert into POSSEDER "+l +"where idmag="+magasin);
        } catch (SQLException e) {
            System.out.println("ne travaille pas en tant que vendeur ou magasins inexistant");
        }

    }


}
