package aber.dcs.cs211.group07.db;

/**
 * @author adriangawryszewski
 * @author Nathan Hand
 * @author Daniel Cornwell
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import aber.dcs.cs211.group07.data.*;

/**
 * Codes for connecting to the player table in the database
 *
 * @author Daniel Cornwell
 */
public class PlayerTableConnector extends TableConnector {
	//SQL statement to read from the player table
	private static final String QUERY_GET_ALL_PLAYERS = "SELECT * FROM 'player'";

	private MonsterTableConnector monster_table = new MonsterTableConnector();

	/**
	 * Deletes a player from the table
	 *
	 * @param newPlayer - player to be deleted
	 * @return true if deleted, false otherwise
	 */
	public boolean deletePlayer(Player player) {
		try {
			statement.executeUpdate("DELETE FROM 'player' WHERE ID='" + player.id + "'");
			monster_table.deleteAllMonsters(player.id);
			return true;
		} catch (SQLException e) {
			complain(e, "Could not delete player");
			return false;
		}
	}

	/**
	 * Returns a player instance from the table
	 *
	 * @param name - name of the player to get
	 * @return a player instance, or null
	 */
	public Player getPlayer(String name) {
		try {
			ResultSet results = statement.executeQuery(QUERY_GET_ALL_PLAYERS);
			while (results.next()) {
				if (results.getString("email").equals(name)){
					return new Player(
							results.getInt("id"),
							results.getString("email"),
							results.getString("password"),
							results.getInt("money"));
				}
			}
		} catch (SQLException e) {
			complain(e, "Could not get player");
		}
		return null;
	}

	/**
	 * Returns a player instance from the table
	 *
	 * @param playerID - id of the player we want to get
	 * @return a player instance, or null
	 */
	public Player getPlayer(int playerID) {
		try {
			ResultSet results = statement.executeQuery(QUERY_GET_ALL_PLAYERS);
			while (results.next()) {
				if ((results.getInt("ID") == playerID)) {
					return new Player(
							results.getInt("id"),
							results.getString("email"),
							results.getString("password"),
							results.getInt("money"));
				}
			}
		} catch (SQLException e) {
			complain(e, "Could not get player");
		}
		return null;
	}

	/**
	 * Returns a list of all the player current in the database
	 * 
	 * @return ArrayList of players in the database
	 */
	public ArrayList<Player> getAllPlayers() {
		ArrayList<Player> playerList = new ArrayList<Player>();

		try {
			ResultSet results = statement.executeQuery(QUERY_GET_ALL_PLAYERS);
			while(results.next()) {
				playerList.add(new Player(
						results.getInt("id"),
						results.getString("email"),
						results.getString("password"),
						results.getInt("money")));
			}
		} catch (SQLException e) {
			complain(e, "Could not get all players");
		}
		return playerList;
	}

	/**
	 * Edits the money of a player in the table Also sets the money variable of
	 * the Player instance
	 *
	 * @param player - player to edit
	 * @param amount - amount to edit by
	 * @return true if edited, false otherwise
	 */
	public boolean editMoney(Player player, int amount) {
		int id = player.id;
		try {
			ResultSet results = statement.executeQuery(QUERY_GET_ALL_PLAYERS);
			while (results.next()) {
				if (results.getInt("id") == id) {
					player.money = player.money + amount;
					statement.executeUpdate("UPDATE PLAYER SET MONEY=" + player.money + " WHERE id=" + id);
					return true;
				}
			}
		} 
		catch (SQLException e) {
			complain(e, "Could not edit player money");
		}
		return false;
	}


	/**
	 * Simple code for login validation. checks whether the user exists in the
	 * table, then checks the password for that user. against supplied password
	 * This is really insecure, needs validation checking on the password.
	 *
	 * @param username - for account
	 * @param password - for account
	 * @return a user, or null
	 */
	public int login(String username, String password) {
		try {
			String playerQuery = "SELECT * FROM `player` WHERE `email` = '" + username + "'";
			ResultSet loginQuery = statement.executeQuery(playerQuery);
			while (loginQuery.next()) {
				if (loginQuery.getString("email").equals(username)) {
					if (loginQuery.getString("password").equals(password)) {
						return 1;
					}
				}
			}
		} catch (SQLException e) {
			complain(e, "Could not login");
		}
		return 0;
	}

	/**
	 * Checks whether the email already exists, if not then create a new database record
	 *
	 * @param name2 - email of the new user
	 * @param password2 - password of the user
	 * @throws SQLException
	 * @return true if the player email doesn't exist in the table and is added,
	 * false otherwise
	 */
	public boolean createPlayer(String name2, String password2) throws SQLException {
		String playerQuery = "SELECT * FROM `player` WHERE `email` = '" + name2 + "'";
		ResultSet playerResults = statement.executeQuery(playerQuery);
		if (playerResults.next() == false){
			String sql ="INSERT INTO `player` (`email` ,`password` ,`money` )VALUES ('" + name2 + "',  '" + password2 + "',  '500')";
			statement.executeUpdate(sql);
			statement.close();
			return true;
		} else {
			statement.close();
			return false;
		}
	}
	
	/**
	 * Returns a list of id's of the player's monsters
	 *
	 * @param owner The player to fetch a list of monsters for
	 * @return A list of monsters
	 */
	public List<Monster> getMonsters(Player owner) {
		return this.getMonsters(owner.id);
	}

	/**
	 * Returns a list of the player's monsters
	 * 
	 * @param ownerID The id of the owner
	 * @return list of monsters
	 */
	public List<Monster> getMonsters(int ownerID) {
		return monster_table.getMonsters(ownerID);
	}
}
