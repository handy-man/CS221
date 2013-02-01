package aber.dcs.cs211.group07.db;

import java.sql.SQLException;

public class FriendsTableConnector extends TableConnector {
	// SQL statement to read from the player table
	// private String friendsTable = "SELECT * FROM player_friend";

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
			statement.executeUpdate("INSERT INTO player_friend " + 
					" VALUES ("+UID+","+friendID+","+friendServerID+","+userID+")");
			return true;
		} catch (SQLException e) {
			complain(e, "Could not add friend");
			return false;
		}
	}
}
