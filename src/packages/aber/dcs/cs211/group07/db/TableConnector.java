package aber.dcs.cs211.group07.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Base class for code shared between the various TableConnector classes
 * 
 * @author Sam Clements
 */
public class TableConnector {
	protected final String HOSTNAME = "jdbc:mysql://74.53.183.226/handyman_monster";
	protected final String USERNAME = "handyman_group07";
	protected final String PASSWORD = "3213560921*+*";
	
	public Connection connection;
	public Statement statement;
	
	public TableConnector () {
		try {
			connection = DriverManager.getConnection(HOSTNAME, USERNAME, PASSWORD);
			statement = connection.createStatement();
		} catch (SQLException e) {
			complain(e, "Could not connect to the database");
		}
	}
	
	public static void complain(Exception e) {
		complain(e, "ERROR: An error happened.");
	}
	
	public static void complain(Exception e, String message) {
		System.out.println("ERROR: " + message);
		e.printStackTrace();
	}
	
	/**
	 * Closes the connection to the database
	 */
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			complain(e, "Could not close the connection to the database");
		}
	}
}
