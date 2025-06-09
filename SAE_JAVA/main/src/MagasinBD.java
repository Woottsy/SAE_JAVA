import java.util.*;
import java.sql.*;

public class MagasinBD {
    private ConnexionMySQL connexion;
    private List<Magasin> magasins;

    public MagasinBD(ConnexionMySQL connexion) {
        this.connexion = connexion;
        this.magasins = new ArrayList<>();
    }

    public List<Magasin> getAllMagasins() throws SQLException {
        Statement st = connexion.getConnexion().createStatement();
        ResultSet res = st.executeQuery("SELECT * FROM Magasin");
        while (res.next()) {
            magasins.add(new Magasin(res.getString("idMag"), res.getString("nommag"), res.getString("villemag")));
        }
        return magasins;
    }
}