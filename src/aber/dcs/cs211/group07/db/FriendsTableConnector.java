package aber.dcs.cs211.group07.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FriendsTableConnector {

		//A statement from the connection, used to get result sets
		private Statement statement = null;
		//Results from the player table. Initialized at the begin on methods
		private ResultSet results = null;
		//SQL statement to read from the player table
		private String friendsTable = "SELECT * FROM player_friend";;


		public FriendsTableConnector() {

			//Enter address of database being used
			String host = "database/address";
			//Enter name of the actual database
			String userName = "name of the database";
			//Enter password for the database (unsure if necessary)
			String password = "password";


			try {
				//Creates a connection to the database and a statement
				Connection connection = DriverManager.getConnection(host,userName,password);
				statement = connection.createStatement();

			}

			catch(SQLException error) {
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
		public boolean addFriend(int userID,int friendServerID,int friendID) {
			
			try {
				results = statement.executeQuery(friendsTable);

				statement.executeUpdate("INSERT INTO player_friend " + 
						" VALUES ("+userID+","+friendServerID+","+friendID+")");
				return true;
			} 
			catch (SQLException error) {
				//report error
				return false;
			}
		}
	
}
