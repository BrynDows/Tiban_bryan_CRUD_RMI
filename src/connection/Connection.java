package connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.JDBC;

/**
 * Clase con patr�n de dise�o SingleTone la cual permite desde cualquier instancia de la misma obtener una nueva conexi�n
 * o la conexi�n acual de una base de datos.
 * @author Bryan Ti
 *
 */
public class Connection {
	
	private final String DRIVER_SQLITE = "org.sqlite.JDBC";
	private final String DATA_BASE = "tiban_bryan_CRUD.bd";
	private final String URL = "jdbc:sqlite:" +  DATA_BASE;
	private Statement terminal;
	private static java.sql.Connection connection;
	private static Connection myClassConnection = null;
	
	/**
	 * Constructor privado, crea una conexi�n hacia una base datos SQLite
	 * @throws ClassNotFoundException En caso de que el driver {@link #DRIVER_SQLITE} no se encuentre.
	 * @throws SQLException
	 */
	private Connection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_SQLITE);
		connection = (java.sql.Connection)DriverManager.getConnection(URL);
		terminal = connection.createStatement();
	}

	
	/**
	 * M�todo que provee de una conexi�n nueva o la conexi�n actual hacia la base de datos.
	 * @return {@link Connection} actual o nueva.
	 * @throws ClassNotFoundException EEn caso de que el driver {@link #DRIVER_SQLITE} no est� bien especificado
	 * @throws SQLException
	 */
	public static Connection getInstance() throws ClassNotFoundException, SQLException {
		
		if(myClassConnection == null) {
			return new Connection();
		}else {
			return myClassConnection;
		}
	}
	
	/**
	 * M�todo que provee del canal por el cual se pueden realizar consultas en la base de datos.
	 * @return {@link Statement}
	 */
	public Statement getStatement() {
		return terminal;
	}
	
}
