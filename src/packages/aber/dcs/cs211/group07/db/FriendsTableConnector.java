package aber.dcs.cs211.group07.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class FriendsTableConnector {

		//A statement from the connection, used to get result sets
		private Statement statement = null;
		// Results from the player table. Initialized at the begin on methods
		// private ResultSet results = null;
		// A connection to the database
		private Connection connection=null;
		// SQL statement to read from the player table
		// private String friendsTable = "SELECT * FROM player_friend";


		public FriendsTableConnector() {

			//Enter address of database being used
			String host = "jdbc:mysql://74.53.183.226/handyman_monster";
			//Enter username (usually root?)
			String userName = "handyman_group07";
			//Enter password
			String password = "3213560921*+*";
			
			try {
				// Load JBBC driver "com.mysql.jdbc.Driver" unsure if needed .
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				
				//Creates a connection to the database and a statement
				connection = DriverManager.getConnection(host,userName,password);
				statement = connection.createStatement();

			}

			catch(SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException error) {
				//do something with error
			}
		}
	
		/**
		 * Adds a friend relationship to the table
		 * 
		 * @param userID - user ID of the player on our server
		 * @param friendServerID - serverID of the friend
		 * @param friendID - friend ID of the friend
		 * @return true if added, false otherwise
		 */
		public boolean addFriend(String UID, String userID,String friendServerID,String friendID) {
			
			try {
			//	results = statement.executeQuery(friendsTable);

				statement.executeUpdate("INSERT INTO player_friend " + 
						" VALUES ("+UID+","+friendID+","+friendServerID+","+userID+")");
				return true;
			} 
			catch (SQLException error) {
				//report error
				return false;
			}
		}
	
		/**
		 * Closes the connection to the database
		 * 
		 */
		public void close() {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
