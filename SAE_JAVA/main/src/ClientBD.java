import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientBD{
    ConnexionMySQL co;
    Statement st;



    public ClientBD(ConnexionMySQL co) {
        this.co = co;
    }

    public boolean seConnecter() {
        try {
            
            Statement st = this.co.createStatement();
            System.out.println("Entrez votre identifiant");
            String id = System.console().readLine();
            System.out.println("Entrez votre mot de passe");
            String mdp = System.console().readLine();
            
            ResultSet admins = st.executeQuery("select * from Client");
            if (admins.next()) {
                if (id.equals(admins.getString("idCli"))) {
                    if (mdp.equals(admins.getString("motdepasse"))) {
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