import java.sql.Statement;

/**
 * Classe permettant de gerer les operations liees aux livres dans la base de donnees.
 */
public class LivreBD {

    ConnexionMySQL laConnexion;
    Statement st;

    /**
     * Constructeur de la classe LivreBD.
     * Initialise la connexion a la base de donnees.
     * @param co la connexion MySQL a utiliser
     */
    public LivreBD(ConnexionMySQL co) {
        this.laConnexion = co;
    }

}

