import java.sql.*;

/**
 * Classe utilitaire pour gérer la connexion à une base de données MySQL.
 */
public class ConnexionMySQL {
	private Connection mysql=null;
	private boolean connecte=false;

	/**
	 * Constructeur : charge le driver MariaDB/MySQL.
	 * @throws ClassNotFoundException si le driver n'est pas trouvé
	 */
	public ConnexionMySQL() throws ClassNotFoundException{
		Class.forName("org.mariadb.jdbc.Driver");
	}

	/**
	 * Établit la connexion à la base de données avec les identifiants fournis.
	 * @param nomServeur le nom du serveur MySQL
	 * @param nomLogin le nom d'utilisateur
	 * @param motDePasse le mot de passe
	 * @throws SQLException en cas d'échec de connexion
	 */
	public void connecter(String nomServeur, String nomLogin, String motDePasse, String bd) throws SQLException {
		this.mysql=DriverManager.getConnection(
			"jdbc:mysql://"+nomServeur+"/"+bd, nomLogin, motDePasse);
		// si tout s'est bien passé la connexion n'est plus nulle
		this.connecte=this.mysql!=null;
	}

	/**
	 * Ferme la connexion à la base de données.
	 * @throws SQLException en cas d'erreur lors de la fermeture
	 */
	public void close() throws SQLException {
		// fermer la connexion
		this.mysql.close();
		this.connecte=false;
	}

	/**
	 * Indique si la connexion à la base de données est active.
	 * @return true si connecté, false sinon
	 */
	public boolean isConnecte() { return this.connecte;}

	/**
	 * Crée un objet Statement pour exécuter des requêtes SQL.
	 * @return un Statement SQL
	 * @throws SQLException en cas d'erreur SQL
	 */
	public Statement createStatement() throws SQLException {
		return this.mysql.createStatement();
	}

	/**
	 * Prépare une requête SQL paramétrée.
	 * @param requete la requête SQL à préparer
	 * @return un PreparedStatement prêt à l'emploi
	 * @throws SQLException en cas d'erreur SQL
	 */
	public PreparedStatement prepareStatement(String requete) throws SQLException{
		return this.mysql.prepareStatement(requete);
	}
	
}
