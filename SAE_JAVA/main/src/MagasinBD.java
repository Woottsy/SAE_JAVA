
import java.sql.PreparedStatement;
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

    public void ajouterLivre(Magasin magChoisi) {
        try {
            LivreBD livreBD = new LivreBD(this.laConnexion);
            Statement st = this.laConnexion.createStatement();
            System.out.println("Entrez le titre du livre");
            String titre = System.console().readLine();
            System.out.println("Entrez son nombre de pages");
            String nbpages = System.console().readLine();
            System.out.println("Entrez l'année de publication du livre");
            String datepubli = System.console().readLine();
            System.out.println("Entrez la quantité du livre dans le magasin");
            String quant = System.console().readLine();
            System.out.println("Entrez son prix");
            String prix = System.console().readLine();
            PreparedStatement res = this.laConnexion.prepareStatement("INSERT IGNORE INTO LIVRE(isbn, titre, nbpages,datepubli, prix) values(?,?,?,?,?)");
            PreparedStatement poss = this.laConnexion.prepareStatement("INSERT IGNORE INTO POSSEDER(idmag, isbn, qte) values(?,?,?)");
            String maxnum = livreBD.maxnumLivre().toString();
            res.setString(1, maxnum);
            res.setString(2, titre);
            res.setInt(3, Integer.parseInt(nbpages));
            res.setInt(4, Integer.parseInt(datepubli));
            res.setDouble(5, Integer.parseInt(prix));
            poss.setString(1, magChoisi.getIdmagasin());
            poss.setString(2, maxnum);
            poss.setInt(3, Integer.parseInt(quant));
            res.executeUpdate();
            poss.executeUpdate();
            System.out.println("Vous avez ajouter le livre("+maxnum+") : " + titre + " qui fait " + nbpages + " pages et publié en " + datepubli + " au prix de " + prix + "en " + quant + " exemplaires");
            
        } catch (SQLException e) {

        }
    }

    //À implémenter : Supprimer Livre ListeLivres et Modifier la quantité d'un livre
}
