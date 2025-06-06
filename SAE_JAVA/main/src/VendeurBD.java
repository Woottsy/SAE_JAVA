
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
}
