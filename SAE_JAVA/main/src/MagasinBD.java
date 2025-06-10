
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
            System.out.println("Veuillez entrer l'isbn du livre");
            String isbn = System.console().readLine();
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
            String maxnum = isbn;
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


    public void supprimerLivre(Magasin magChoisi) {
        try {
            LivreBD livreBD = new LivreBD(this.laConnexion);
            Statement st = this.laConnexion.createStatement();
            System.out.println("Entrez l'ISBN du livre à supprimer");
            String isbn = System.console().readLine();
            PreparedStatement res = this.laConnexion.prepareStatement("DELETE FROM POSSEDER WHERE idmag = ? AND isbn = ?");
            res.setString(1, magChoisi.getIdmagasin());
            res.setString(2, isbn);
            res.executeUpdate();
            System.out.println("Vous avez supprimé le livre avec l'ISBN : " + isbn + " du magasin " + magChoisi.getNom());
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du livre : " + e.getMessage());
        }
    }

    public void listeLivres(Magasin magChoisi) {
        try {
            Statement st = this.laConnexion.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM LIVRE INNER JOIN POSSEDER ON LIVRE.isbn = POSSEDER.isbn WHERE POSSEDER.idmag = '" + magChoisi.getIdmagasin() + "'");
            System.out.println("Liste des livres dans le magasin " + magChoisi.getNom() + ":");
            while (res.next()) {
                System.out.println("ISBN: " + res.getString("isbn") + ", Titre: " + res.getString("titre") + ", Quantité: " + res.getInt("qte"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des livres : " + e.getMessage());
        }
    }

    public void modifierQuantiteLivre(Magasin magChoisi) {
        try {
            Statement st = this.laConnexion.createStatement();
            System.out.println("Entrez l'ISBN du livre dont vous voulez modifier la quantité");
            String isbn = System.console().readLine();
            System.out.println("Entrez la nouvelle quantité");
            String quantite = System.console().readLine();
            PreparedStatement res = this.laConnexion.prepareStatement("UPDATE POSSEDER SET qte = ? WHERE idmag = ? AND isbn = ?");
            res.setInt(1, Integer.parseInt(quantite));
            res.setString(2, magChoisi.getIdmagasin());
            res.setString(3, isbn);
            res.executeUpdate();
            System.out.println("La quantité du livre avec l'ISBN " + isbn + " a été mise à jour à " + quantite + " dans le magasin " + magChoisi.getNom());
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification de la quantité : " + e.getMessage());
        }
    }


    //À implémenter : ListeLivres et Modifier la quantité d'un livre
}
