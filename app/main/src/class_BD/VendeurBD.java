package class_BD;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Classification;
import models.ConnexionMySQL;
import models.Livre;

public class VendeurBD {

    private final ConnexionMySQL laConnexion;
    private Statement st;
    public int vendeur;

    public VendeurBD(ConnexionMySQL co) {
        this.laConnexion = co;
        this.vendeur = 0;
    }

    public int maxnumCommande() throws SQLException {
        int maxNum = 0;
        this.st = this.laConnexion.createStatement();
        ResultSet resultat = st.executeQuery("select ifnull(max(numcom), 0) maxn from COMMANDE");
        if (resultat.next()) {

            maxNum = resultat.getInt("maxn");
        }
        return maxNum + 1;
    }

    public int maxnumVendeur() throws SQLException {
        int maxNum = 0;
        this.st = this.laConnexion.createStatement();
        ResultSet resultat = st.executeQuery("select ifnull(max(keyVendeur), 0) maxn from VENDEUR");
        if (resultat.next()) {

            maxNum = resultat.getInt("maxn");
        }
        return maxNum + 1;
    }

    public int maxnumDetailCommande() throws SQLException {
        int maxNum = 0;
        this.st = this.laConnexion.createStatement();
        ResultSet resultat = st.executeQuery("select ifnull(max(numlig), 0) maxn from DETAILCOMMANDE");
        if (resultat.next()) {

            maxNum = resultat.getInt("maxn");
        }
        return maxNum + 1;
    }

    public boolean seConnecter() {
        try {
            System.out.println("Entrez votre identifiant");
            String id = System.console().readLine();
            System.out.println("Entrez votre mot de passe");
            String mdp = System.console().readLine();
            PreparedStatement ps = this.laConnexion
                    .prepareStatement("SELECT * FROM VENDEUR WHERE identVendeur = ? AND motdepasseVendeur = ?");
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

    public String getidMagasin(int vendeurKey) throws SQLException {
        String idMagasin = null;
        try (PreparedStatement ps = this.laConnexion.prepareStatement(
                "SELECT idmag FROM AFFILIATION WHERE keyVendeur = ?")) {
            ps.setInt(1, vendeurKey);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    idMagasin = rs.getString("idmag");
                }
            }
        }
        return idMagasin;
    }

    public void insererLivre(int vendeurKey) throws SQLException {
        try {
            System.out.println("Pour insérer un livre, entrez son ISBN : ");
            String isbn = System.console().readLine();
            if (isbn == null || isbn.trim().isEmpty()) {
                System.out.println("ISBN invalide.");
                return;
            }
            try (PreparedStatement psVerifLivre = this.laConnexion.prepareStatement(
                    "SELECT isbn FROM LIVRE WHERE isbn = ?")) {
                psVerifLivre.setString(1, isbn);
                try (ResultSet rsLivre = psVerifLivre.executeQuery()) {
                    if (!rsLivre.next()) {
                        System.out.println("L'ISBN fourni n'existe pas dans la table LIVRE.");
                        rsLivre.close();
                        psVerifLivre.close();
                        return;
                    }
                }
            }
            String idMagasin;
            try (PreparedStatement psMagasin = this.laConnexion.prepareStatement(
                    "SELECT idmag FROM AFFILIATION WHERE keyVendeur = ?")) {
                psMagasin.setInt(1, vendeurKey);
                try (ResultSet rsMagasin = psMagasin.executeQuery()) {
                    if (!(rsMagasin.next())) {
                        System.out.println("Aucun magasin trouvé pour ce vendeur.");
                        rsMagasin.close();
                        psMagasin.close();
                        return;
                    }
                    idMagasin = rsMagasin.getString("idmag");
                }
            }

            try (PreparedStatement psVerifPosseder = this.laConnexion.prepareStatement(
                    "SELECT isbn, qte FROM POSSEDER WHERE idmag = ? AND isbn = ?")) {
                psVerifPosseder.setString(1, idMagasin);
                psVerifPosseder.setString(2, isbn);
                try (ResultSet rsPosseder = psVerifPosseder.executeQuery()) {
                    if (!rsPosseder.next()) {
                        try (PreparedStatement psInsert = this.laConnexion.prepareStatement(
                                "INSERT INTO POSSEDER (idmag, isbn, qte) VALUES (?, ?, ?)")) {
                            psInsert.setString(1, idMagasin);
                            psInsert.setString(2, isbn);
                            psInsert.setInt(3, 1);
                            psInsert.executeUpdate();
                        }
                        System.out.println("Le livre a été inséré avec succès.");
                    } else {
                        System.out.println("Le livre existe déjà dans ce magasin.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Une erreur SQL s'est produite : " + e.getMessage());
        }
    }

    public String verifierDispo(int keyVendeur, String isbn) throws SQLException {
        try {
            try (PreparedStatement psAffiliation = this.laConnexion.prepareStatement(
                    "SELECT idmag FROM AFFILIATION WHERE keyVendeur = ?")) {
                psAffiliation.setInt(1, keyVendeur);
                try (ResultSet resultat = psAffiliation.executeQuery()) {
                    if (resultat.next()) {
                        String magasin = resultat.getString("idmag");

                        try (PreparedStatement psPosseder = this.laConnexion.prepareStatement(
                                "SELECT isbn, qte FROM POSSEDER WHERE idmag = ? AND isbn = ?")) {
                            psPosseder.setString(1, magasin);
                            psPosseder.setString(2, isbn);
                            try (ResultSet verif = psPosseder.executeQuery()) {
                                if (verif.next()) {
                                    System.out.println("Le livre : " + isbn + " est disponible (stock : " + verif.getInt("qte") + ")");
                                    return "Le livre : " + isbn + " est disponible";
                                } else {
                                    System.out.println("Le livre n'est pas disponible");
                                    return "Le livre n'est pas disponible";
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return "Le livre n'est pas disponible";
    }

    public void majQTELivre(int vendeurKey) throws SQLException {
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

            try (PreparedStatement psMagasin = laConnexion.prepareStatement(
                    "SELECT idmag FROM AFFILIATION WHERE keyVendeur = ?")) {
                psMagasin.setInt(1, vendeurKey);
                try (ResultSet resultat = psMagasin.executeQuery()) {
                    if (!resultat.next()) {
                        System.out.println("Aucun magasin trouvé pour ce vendeur.");
                        return;
                    }

                    String magasin = resultat.getString("idmag");

                    PreparedStatement psUpdate;
                    try (PreparedStatement psVerif = laConnexion.prepareStatement(
                            "SELECT qte FROM POSSEDER WHERE idmag = ? AND isbn = ?")) {
                        psVerif.setString(1, magasin);
                        psVerif.setString(2, isbn);
                        try (ResultSet verif = psVerif.executeQuery()) {
                            if (!verif.next()) {
                                System.out.println("Le livre avec cet ISBN n'existe pas dans ce magasin.");
                                return;
                            }
                            psUpdate = laConnexion.prepareStatement(
                                    "UPDATE POSSEDER SET qte = ? WHERE idmag = ? AND isbn = ?");
                            psUpdate.setInt(1, qte);
                            psUpdate.setString(2, magasin);
                            psUpdate.setString(3, isbn);
                            int rowsUpdated = psUpdate.executeUpdate();
                            if (rowsUpdated > 0) {
                                System.out.println("La quantité du livre a été mise à jour avec succès.");
                            } else {
                                System.out.println("Échec de la mise à jour de la quantité.");
                            }
                        }
                    }
                    psUpdate.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Une erreur SQL s'est produite : " + e.getMessage());
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
        } finally {
            if (resultat != null) {
                try {
                    resultat.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
        }

        return res;
    }

    public void passerCommande(int vendeurKey) {
        try {
            this.st = this.laConnexion.createStatement();

            System.out.println("Entrez l'identifiant client :");
            String idcli = System.console().readLine();

            System.out.println("Entrez l'isbn du livre que vous souhaitez commander :");
            String idLivre = System.console().readLine();
            String verif = verifierDispo(vendeurKey, idLivre);
            if (verif.equals("Le livre n'est pas disponible")) {
                System.out.println("Le livre n'est pas disponible recommencer la commande");
            }

            System.out.println("Entrez la quantité souhaitée :");
            int quantite = Integer.parseInt(System.console().readLine());

            System.out.println("livraison à domicile ? C (oui) ou M (non)");
            String typeLivraison = System.console().readLine();

            ResultSet rs2 = st.executeQuery("SELECT MAX(numcom) AS maxNumCom FROM COMMANDE");
            int idCommande = 1;
            if (rs2.next()) {
                idCommande = rs2.getInt("maxNumCom") + 1;
            }

            ResultSet rs3 = st.executeQuery("SELECT MAX(numlig) AS maxNumLig FROM DETAILCOMMANDE");
            int idDetailCom = 1;
            if (rs3.next()) {
                idDetailCom = rs3.getInt("maxNumLig") + 1;
            }

            ResultSet rs = st.executeQuery("SELECT prix FROM LIVRE WHERE isbn = '" + idLivre + "'");
            double prix;
            if (rs.next()) {
                prix = rs.getDouble("prix");
            } else {
                System.out.println("Livre introuvable !");
                return;
            }

            Date dateCommande = new Date(System.currentTimeMillis());

            PreparedStatement ps = this.laConnexion.prepareStatement(
                    "INSERT INTO COMMANDE (numcom, datecom, enligne, livraison, idcli, idmag) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, idCommande);
            ps.setDate(2, dateCommande);
            ps.setString(3, "N");
            ps.setString(4, typeLivraison);
            ps.setString(5, idcli);
            ps.setString(6, this.getidMagasin(vendeurKey));
            ps.executeUpdate();

            PreparedStatement ps2 = this.laConnexion.prepareStatement(
                    "INSERT INTO DETAILCOMMANDE (numcom, numlig, isbn, qte, prixvente) VALUES (?, ?, ?, ?, ?)");
            ps2.setInt(1, idCommande);
            ps2.setInt(2, idDetailCom);
            ps2.setString(3, idLivre);
            ps2.setInt(4, quantite);
            ps2.setDouble(5, prix);
            ps2.executeUpdate();

            System.out.println("Commande passée avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la passation de la commande : " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Quantité invalide. Veuillez saisir un nombre entier.");
        }
    }
}
