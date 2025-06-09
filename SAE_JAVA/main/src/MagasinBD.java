import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MagasinBD {
    private ConnexionMySQL laConnexion;
    private List<Magasin> magasins;

    public MagasinBD(ConnexionMySQL connexion) {
        this.laConnexion = connexion;
        this.magasins = new ArrayList<>();
    }

    public List<Magasin> getAllMagasins() throws SQLException {
        Statement st = this.laConnexion.createStatement();
        ResultSet res = st.executeQuery("SELECT * FROM MAGASIN");
        while (res.next()) {
            magasins.add(new Magasin(res.getString("idMag"), res.getString("nommag"), res.getString("villemag")));
        }
        return magasins;
    }
}