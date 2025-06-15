import java.sql.Statement;

/**
 * Classe permettant de gérer les opérations liées aux livres dans la base de données.
 */
public class LivreBD {

    ConnexionMySQL laConnexion;
    Statement st;

    /**
     * Constructeur de la classe LivreBD.
     * Initialise la connexion à la base de données.
     * @param co la connexion MySQL à utiliser
     */
    public LivreBD(ConnexionMySQL co) {
        this.laConnexion = co;
    }

}

