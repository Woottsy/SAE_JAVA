import java.sql.*;

public class Connexion {
    private Connection connexion = null;
    private boolean connecte = false;

    public Connexion() throws ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
    }

    public void connecter(String nomServeur, String nomBase, String nomLogin, String motDePasse) throws SQLException {
        this.connexion = DriverManager.getConnection(
            "jdbc:mysql://" + nomServeur + ":3306/" + nomBase,
            nomLogin, motDePasse
        );
        this.connecte = this.connexion != null;
    }

    public void close() throws SQLException {
        if (this.connexion != null && !this.connexion.isClosed()) {
            this.connexion.close();
            this.connecte = false;
        }
    }

    public boolean isConnecte() {
        return this.connecte;
    }

    public Statement createStatement() throws SQLException {
        return this.connexion.createStatement();
    }

    public PreparedStatement prepareStatement(String requete) throws SQLException {
        return this.connexion.prepareStatement(requete);
    }
}