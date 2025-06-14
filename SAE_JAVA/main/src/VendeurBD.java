
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class VendeurBD {

    ConnexionMySQL laConnexion;
    Statement st;
    int vendeur;

    public VendeurBD(ConnexionMySQL co) {
        this.laConnexion = co;
    }

    public int maxnumCommande() throws SQLException{
		int maxNum=0;
		this.st=this.laConnexion.createStatement();
		ResultSet resultat=st.executeQuery("select ifnull(max(numcom), 0) maxn from COMMANDE");
		if(resultat.next()){

			maxNum=resultat.getInt("maxn");
		}
		return maxNum + 1 ;
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
    public int maxnumDetailCommande() throws SQLException{
		int maxNum=0;
		this.st=this.laConnexion.createStatement();
		ResultSet resultat=st.executeQuery("select ifnull(max(numlig), 0) maxn from DETAILCOMMANDE");
		if(resultat.next()){

			maxNum=resultat.getInt("maxn");
		}
		return maxNum + 1 ;
	}
	public boolean seConnecter(){
        try{
            Statement st = this.laConnexion.createStatement();
            System.out.println("Entrez votre identifiant");
            String id = System.console().readLine();
            System.out.println("Entrez votre mot de passe");
            String mdp = System.console().readLine();
            PreparedStatement ps = this.laConnexion.prepareStatement("SELECT * FROM VENDEUR WHERE identVendeur = ? AND motdepasseVendeur = ?");
            ps.setString(1, id);
            ps.setString(2, mdp);
            ResultSet resultat = ps.executeQuery();
            if (resultat.next()) {
                this.vendeur = resultat.getInt("keyVendeur");
                System.out.println("Connexion réussie en tant que vendeur avec l'id : " + this.vendeur);
                return true;
            } else {
                System.out.println("Identifiant ou mot de passe incorrect.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
            
            }
        return false;
    }
    public String getidMagasin()throws SQLException{
        String idmagasin="";

            ResultSet resultatset=st.executeQuery("select * from MAGASIN natural join AFFILIATION natural join VENDEUR where keyVendeur="+this.vendeur);
            idmagasin= resultatset.getString("idmag");
        return idmagasin;

    }

    public void insererLivre() throws SQLException {
        try {
            System.out.println("Pour insérer un livre, entrez son ISBN : ");
            String isbn = System.console().readLine();
            if (isbn == null || isbn.trim().isEmpty()) {
                System.out.println("ISBN invalide.");
                return;
            }
                PreparedStatement psVerifLivre = this.laConnexion.prepareStatement(
                "SELECT isbn FROM LIVRE WHERE isbn = ?"
            );
            psVerifLivre.setString(1, isbn);
            ResultSet rsLivre = psVerifLivre.executeQuery();
            if (!rsLivre.next()) {
                System.out.println("L'ISBN fourni n'existe pas dans la table LIVRE.");
                rsLivre.close();
                psVerifLivre.close();
                return;
            }
            rsLivre.close();
            psVerifLivre.close();
    
            PreparedStatement psMagasin = this.laConnexion.prepareStatement(
                "SELECT idmag FROM AFFILIATION WHERE keyVendeur = ?"
            );
            psMagasin.setInt(1, this.vendeur);
            ResultSet rsMagasin = psMagasin.executeQuery();
    
            if (!rsMagasin.next()) {
                System.out.println("Aucun magasin trouvé pour ce vendeur.");
                rsMagasin.close();
                psMagasin.close();
                return;
            }
    
            String idMagasin = rsMagasin.getString("idmag");
            rsMagasin.close();
            psMagasin.close();
    
            PreparedStatement psVerifPosseder = this.laConnexion.prepareStatement(
                "SELECT isbn, qte FROM POSSEDER WHERE idmag = ? AND isbn = ?"
            );
            psVerifPosseder.setString(1, idMagasin);
            psVerifPosseder.setString(2, isbn);
            ResultSet rsPosseder = psVerifPosseder.executeQuery();
    
            if (!rsPosseder.next()) {
                PreparedStatement psInsert = this.laConnexion.prepareStatement(
                    "INSERT INTO POSSEDER (idmag, isbn, qte) VALUES (?, ?, ?)"
                );
                psInsert.setString(1, idMagasin);
                psInsert.setString(2, isbn);
                psInsert.setInt(3, 1); 
                psInsert.executeUpdate();
                psInsert.close();
                System.out.println("Le livre a été inséré avec succès.");
            } else {
                System.out.println("Le livre existe déjà dans ce magasin.");
            }
    
            rsPosseder.close();
            psVerifPosseder.close();
        } catch (SQLException e) {
            System.out.println("Une erreur SQL s'est produite : " + e.getMessage());
            e.printStackTrace();
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

    
    public void majQTELivre() throws SQLException {
        try {

            System.out.println("Pour mettre à jour un livre, entrez son ISBN : ");
            String isbn = System.console().readLine();
            if (isbn == null || isbn.trim().isEmpty()) {
                System.out.println("ISBN invalide.");
                return;
            }
    
            System.out.println("Pour mettre à jour sa quantité, entrez la quantité : ");
            String qteString = System.console().readLine();
            if (qteString == null || qteString.trim().isEmpty()) {
                System.out.println("Quantité invalide.");
                return;
            }
    
            int qte;
            try {
                qte = Integer.parseInt(qteString);
            } catch (NumberFormatException e) {
                System.out.println("La quantité doit être un nombre entier.");
                return;
            }
    
            
            PreparedStatement psMagasin = laConnexion.prepareStatement(
                "SELECT idmag FROM AFFILIATION WHERE keyVendeur = ?"
            );
            psMagasin.setInt(1, vendeur); 
            ResultSet resultat = psMagasin.executeQuery();
    
            if (!resultat.next()) {
                System.out.println("Aucun magasin trouvé pour ce vendeur.");
                return;
            }
    
            String magasin = resultat.getString("idmag");
    

            PreparedStatement psVerif = laConnexion.prepareStatement(
                "SELECT qte FROM POSSEDER WHERE idmag = ? AND isbn = ?"
            );
            psVerif.setString(1, magasin);
            psVerif.setString(2, isbn);
            ResultSet verif = psVerif.executeQuery();
    
            if (!verif.next()) {
                System.out.println("Le livre avec cet ISBN n'existe pas dans ce magasin.");
                return;
            }

            PreparedStatement psUpdate = laConnexion.prepareStatement(
                "UPDATE POSSEDER SET qte = ? WHERE idmag = ? AND isbn = ?"
            );
            psUpdate.setInt(1, qte);
            psUpdate.setString(2, magasin);
            psUpdate.setString(3, isbn);
    
            int rowsUpdated = psUpdate.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La quantité du livre a été mise à jour avec succès.");
            } else {
                System.out.println("Échec de la mise à jour de la quantité.");
            }
    
            verif.close();
            psVerif.close();
            psUpdate.close();
            resultat.close();
            psMagasin.close();
        } catch (SQLException e) {
            System.out.println("Une erreur SQL s'est produite : " + e.getMessage());
            e.printStackTrace();
        }
    }
public List<Livre> selectionLivreMagasin(String idMagasin) throws SQLException {
    List<Livre> res = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet resultat = null;

    try {
        if (idMagasin == null || idMagasin.trim().isEmpty()) {
            System.out.println("L'ID du magasin est invalide.");
            return res;
        }

        String query = """
            SELECT L.isbn, L.titre, L.nbpages, L.datepubli, L.prix, C.iddewey, C.nomclass
            FROM POSSEDER P
            JOIN LIVRE L ON P.isbn = L.isbn
            LEFT JOIN THEMES T ON L.isbn = T.isbn
            LEFT JOIN CLASSIFICATION C ON T.iddewey = C.iddewey
            WHERE P.idmag = ?
        """;

        ps = this.laConnexion.prepareStatement(query);
        ps.setString(1, idMagasin);

        resultat = ps.executeQuery();

        while (resultat.next()) {
            String id = resultat.getString("isbn");
            String titre = resultat.getString("titre");
            int nbpages = resultat.getInt("nbpages");
            String datepubli = String.valueOf(resultat.getInt("datepubli"));
            double prix = resultat.getDouble("prix");
            int iddewey = resultat.getInt("iddewey");
            String theme = resultat.getString("nomclass");

            Livre ltmp = new Livre(id, titre, nbpages, datepubli, prix, new Classification(iddewey, theme));
            res.add(ltmp);
        }
    } catch (SQLException e) {
        System.out.println("Une erreur SQL s'est produite : " + e.getMessage());
        e.printStackTrace();
    } finally {
        if (resultat != null) {
            try {
                resultat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
