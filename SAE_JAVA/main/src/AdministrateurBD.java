/**
 * Classe permettant de gérer les opérations administrateur sur la base de données.
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdministrateurBD {

    ConnexionMySQL laConnexion;
    Statement st;

    /**
     * Constructeur de la classe AdministrateurBD.
     * @param co la connexion MySQL à utiliser
     */
    public AdministrateurBD(ConnexionMySQL co) {
        this.laConnexion = co;
    }

    /**
     * Ajoute une nouvelle librairie (magasin) dans la base de données.
     * Demande les informations à l'utilisateur puis insère le magasin.
     * @param idMag l'identifiant du magasin
     * @param NomMag le nom du magasin
     * @param VilleMag la ville du magasin
     */
    public void ajouterLibrairie(String idMag, String NomMag, String VilleMag) {
        try {
            System.out.println("Pour ajouter une Librairie, écrivez les informations de celle-ci au format (idMag, NomMag, VilleMag)"); // mettre une requete sql pour l'id 
            String input = System.console().readLine();
            String[] parts = input.split(",");
            if (parts.length == 3) {
                idMag = parts[0].strip();
                NomMag = parts[1].strip();
                VilleMag = parts[2].strip();
                System.out.println("Librairie ajoutée : ID= " + idMag + ", Nom= " + NomMag + ", Ville= " + VilleMag);
            }
        } catch (NumberFormatException e) {
            System.out.println("Format invalide. Veuillez entrer les informations au format (idMag, NomMag, VilleMag).");
        }

        try {
            Statement st = this.laConnexion.createStatement();
            PreparedStatement resultat = this.laConnexion.prepareStatement("INSERT INTO MAGASIN(idmag, nommag, villemag) values (?, ?, ?)");
            resultat.setString(1, idMag);
            resultat.setString(2, NomMag);
            resultat.setString(3, VilleMag);
            resultat.executeUpdate();

        } catch (java.sql.SQLException e) {
            System.out.println("Error creating statement: " + e.getMessage());
        }
    }

    /**
     * Supprime une librairie (magasin) de la base de données selon son identifiant.
     * Affiche les informations du magasin supprimé.
     * @param idMag l'identifiant du magasin à supprimer
     */
    public void supprimerLibrairie(String idMag) {
        try {
            Statement st = this.laConnexion.createStatement();
            Statement af = this.laConnexion.createStatement();
            String magasinSelect = "Vous avez supprimer le magasin qui a pour identifiant : ";
            //Pour afficher le magasin supprimée
            ResultSet magasin = af.executeQuery("select * from MAGASIN where idmag = " + "'" + idMag + "'");
            if (magasin.next()) {
                magasinSelect += magasin.getString("idmag") + ", ";
                magasinSelect += " le nom du magasin est " + magasin.getString("nommag") + ", ";
                magasinSelect += "situé dans la ville : " + magasin.getString("villemag");
            }

            System.out.println(magasinSelect);
            // Pour supprimer
            PreparedStatement resultat = this.laConnexion.prepareStatement("DELETE from MAGASIN where idmag = " + "'" + idMag + "'");
            resultat.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error creating statement: " + e.getMessage());
        }
    }

    /**
     * Permet à un administrateur de se connecter en vérifiant l'identifiant et le mot de passe.
     * @return true si la connexion est réussie, false sinon
     */
    public boolean seConnecter() {
        try {
            // Initialisation et récupération de l'id et du mdp
            Statement st = this.laConnexion.createStatement();
            System.out.println("Entrez votre identifiant");
            String id = System.console().readLine();
            System.out.println("Entrez votre mot de passe");
            String mdp = System.console().readLine();
            //Pour afficher le magasin supprimée
            ResultSet admins = st.executeQuery("select * from ADMINISTRATEUR");
            if (admins.next()) {
                if (id.equals(admins.getString("identAdmin"))) {
                    if (mdp.equals(admins.getString("motdepasseAdmin"))) {
                        System.out.println("Connexion réussie !");
                        System.out.println("Bienvenue " + admins.getString("identAdmin"));
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

    /**
     * Affiche la liste de tous les magasins présents dans la base de données.
     */
    public void listeMagasins() {
        try {
            Statement st = this.laConnexion.createStatement();
            List<Magasin> lmag = new ArrayList<>();
            String res = "";
            ResultSet magasin = st.executeQuery("select * from MAGASIN");
            while (magasin.next()) {
                lmag.add(new Magasin(magasin.getString("idmag"), magasin.getString("nommag"), magasin.getString("villemag")));
            }
            for (Magasin m : lmag) {
                res += m + "\n";
            }
            System.out.println(res);
        } catch (SQLException e) {
            System.out.println("Error retrieving magasins: " + e.getMessage());
        }
    }

    /**
     * Crée un nouveau vendeur dans la base de données.
     * Demande les informations à l'utilisateur puis insère le vendeur.
     */
    public void creerVendeur() {
        try {
            VendeurBD vendeur = new VendeurBD(this.laConnexion);
            System.out.println("Quel est l'identifiant du vendeur que vous voulez créer?");
            String id = System.console().readLine();
            System.out.println("Quel est son mot de passe ?");
            String mdp = System.console().readLine();
            System.out.println("Quelle est son adresse email ?");
            String email = System.console().readLine();
            PreparedStatement ps = this.laConnexion.prepareStatement(
                    "INSERT INTO VENDEUR(keyVendeur, identVendeur, motdepasseVendeur, email) VALUES (?, ?, ?, ?)"
            );
            ps.setInt(1, vendeur.maxnumVendeur());
            ps.setString(2, id);
            ps.setString(3, mdp);
            ps.setString(4, email);
            ps.executeUpdate();
            System.out.println("Vous avez créer le compte vendeur " + id);

        } catch (SQLException e) {
            System.out.println("Erreur lors de la création du vendeur : " + e.getMessage());
        }
    }

    /**
     * Affiche la liste de tous les vendeurs présents dans la base de données.
     */
    public void listeVendeurs() {
        try {
            Statement st = this.laConnexion.createStatement();
            List<Vendeur> lvendeur = new ArrayList<>();
            String res = "";
            ResultSet vendeur = st.executeQuery("select * from VENDEUR");
            while (vendeur.next()) {
                lvendeur.add(new Vendeur(vendeur.getInt("keyVendeur"),
                        vendeur.getString("identVendeur"),
                        vendeur.getString("motdepasseVendeur"),
                        vendeur.getString("email")));
            }
            for (Vendeur v : lvendeur) {
                res += v + "\n";
            }
            System.out.println(res);
        } catch (SQLException e) {
            System.out.println("Erreur pour retrouver les vendeurs: " + e.getMessage());
        }
    }

    /**
     * Supprime un vendeur de la base de données selon sa clé unique.
     * Demande la clé à l'utilisateur.
     */
    public void supprimerVendeur() {
        try {
            Statement st = this.laConnexion.createStatement();
            System.out.println("Pour supprimer un vendeur, entrez sa clé unique vendeur : ");
            String key = System.console().readLine();
            PreparedStatement vendeurAff = this.laConnexion.prepareStatement("DELETE IGNORE from AFFILIATION where keyVendeur = ?");
            PreparedStatement resultat = this.laConnexion.prepareStatement("DELETE IGNORE from VENDEUR where keyVendeur = ?");
            resultat.setString(1, key);
            vendeurAff.setString(1, key);
            vendeurAff.executeUpdate();
            resultat.executeUpdate();
            System.out.println("Le vendeur avec l'identifiant " + key + " a été supprimé.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du vendeur: " + e.getMessage());
        }
    }

    /**
     * Affilie un vendeur à un magasin dans la base de données.
     * Demande les identifiants à l'utilisateur.
     */
    public void affilierVendeur() {
        try {
            Statement st = this.laConnexion.createStatement();
            System.out.println("Pour affilier un vendeur, entrez sa clé vendeur : ");
            String keyVendeur = System.console().readLine();
            System.out.println("Pour affilier un magasin, entrez son identifiant : ");
            String idmag = System.console().readLine();
            PreparedStatement resultat = this.laConnexion.prepareStatement("INSERT INTO AFFILIATION(keyVendeur, idmag) VALUES (?, ?)");
            resultat.setString(1, keyVendeur);
            resultat.setString(2, idmag);
            resultat.executeUpdate();
            System.out.println("Le vendeur " + keyVendeur + " a été affilié au magasin " + idmag + ".");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affiliation du vendeur: " + e.getMessage());
        }
    }

    /**
     * Affiche le montant total des ventes pour une année donnée.
     * Demande l'année à l'utilisateur.
     */
    public void ventesGlobales() {
        try {
            Statement st = this.laConnexion.createStatement();
            System.out.println("De quelle année voulez-vous voir les ventes globales ?");
            String annee = System.console().readLine();
            ResultSet ps = st.executeQuery("SELECT IFNULL(SUM(prixvente), 0) AS ventes FROM DETAILCOMMANDE NATURAL JOIN COMMANDE WHERE YEAR(datecom) = " + annee);
            if (ps.next()) {
                int res = ps.getInt("ventes");
                System.out.println("Les ventes globales de " + annee + " s'élèvent à " + res + '€');
            } else {
                System.out.println("Aucune vente trouvée pour l'année " + annee);
            }
        } catch (SQLException e) {
            System.out.println("AIE");
        }
    }

    /**
     * Affiche le livre le plus vendu pour une année donnée.
     * Demande l'année à l'utilisateur.
     */
    public void livreLePlusVendu() {
        try {
            Statement st = this.laConnexion.createStatement();
            System.out.println("De quelle année voulez-vous voir le livre le plus vendu ?");
            String annee = System.console().readLine();
            ResultSet rs = st.executeQuery(
                "SELECT isbn, titre, SUM(qte) AS totalVentes " +
                "FROM DETAILCOMMANDE NATURAL JOIN LIVRE NATURAL JOIN COMMANDE " +
                "WHERE YEAR(datecom) = " + annee + " " +
                "GROUP BY isbn, titre " +
                "ORDER BY totalVentes DESC " +
                "LIMIT 1"
            );
            if (rs.next()) {
                String idLivre = rs.getString("isbn");
                String titre = rs.getString("titre");
                int totalVentes = rs.getInt("totalVentes");
                System.out.println("Le livre le plus vendu en " + annee + " est : " + titre + " (ID: " + idLivre + ") avec " + totalVentes + " ventes.");
            } else {
                System.out.println("Aucun livre vendu pour l'année " + annee);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du livre le plus vendu : " + e.getMessage());
        }
    }

}
