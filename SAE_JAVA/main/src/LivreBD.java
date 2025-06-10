
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LivreBD {

    ConnexionMySQL laConnexion;
    Statement st;

    public LivreBD(ConnexionMySQL co) {
        this.laConnexion = co;
    }

    public Integer maxnumLivre() {
        try{
        Integer maxNum = 0;
        this.st = this.laConnexion.createStatement();
        ResultSet resultat = st.executeQuery("select ifnull(max(isbn), 0) maxn from LIVRE");
        if (resultat.next()) {

            maxNum = resultat.getInt("maxn");
        }
        return maxNum + 1;
    } catch (SQLException e){}
    return 0;
    }
}

