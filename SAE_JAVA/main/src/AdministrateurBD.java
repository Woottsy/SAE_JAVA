
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdministrateurBD {

    ConnexionMySQL laConnexion;
    Statement st;

    public AdministrateurBD(ConnexionMySQL co) {
        this.laConnexion = co;
    }

    public void ajouterLibrairie(String idMag, String NomMag, String VilleMag) {
        System.out.println("Pour ajouter une Librairie, écrivez les informations de celle-ci au format (idMag, NomMag, VilleMag)"); // mettre une requete sql pour l'id 
        try {
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

    public void supprimerLibrairie(String idMag) {
        try {
            Statement st = this.laConnexion.createStatement();
            Statement af = this.laConnexion.createStatement();
            String magasinSelect = "Vous avez supprimer le magasin qui a pour identifiant : ";
            //Pour afficher le magasin supprimée
            ResultSet magasin = af.executeQuery("select * from MAGASIN where idmag = " + idMag);
            if (magasin.next()) {
                magasinSelect += magasin.getString("idmag") + ", ";
                magasinSelect += " le nom du magasin est " + magasin.getString("nommag") + ", ";
                magasinSelect += "situé dans la ville : " + magasin.getString("villemag");
            }

            System.out.println(magasinSelect);
            // Pour supprimer
            PreparedStatement resultat = this.laConnexion.prepareStatement("DELETE from MAGASIN where idmag = ?");
            resultat.setString(1, idMag);
            resultat.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error creating statement: " + e.getMessage());
        }
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
            ResultSet magasin = st.executeQuery("select * from ADMINISTRATEUR");
            if (magasin.next()) {
                if (id.equals(magasin.getString("identAdmin"))) {
                    if (mdp.equals(magasin.getString("motdepasseAdmin"))) {
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



}
