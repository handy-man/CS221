package aber.dcs.cs211.group07.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import aber.dcs.cs211.group07.data.Monster;
import aber.dcs.cs211.group07.data.Player;


/**
 * Codes for connecting to the player table in the database
 * 
 * @author Daniel Cornwell
 *
 */
public class PlayerTableConnector {

	//A statement from the connection, used to get result sets
	private Statement statement = null;
	//Results from the player table. Initialised at the begin on methods
	private ResultSet results = null;
	//A connection to the database
	private Connection connection = null;
	//SQL statement to read from the player table
	private String playerTable = "SELECT * FROM player";
	
	private MonsterTableConnector monTable = new MonsterTableConnector();


	public PlayerTableConnector() {
		// TODO: Fix db information
		//Enter address of database being used
		//Below is my local database
		String host = "jdbc:derby:/Users/dannyboi/MyDB;create=true";
		//Enter username (usually root?)
		String userName = "root";
		//Enter password
		String password = "root";
		
		try {
			// Load JBBC driver "com.mysql.jdbc.Driver" unsure if needed .
			Class.forName("com.mysql.jdbc.Driver").newInstance();
						
			//Creates a connection to the database and a statement
			connection = DriverManager.getConnection(host,userName,password);
			statement = connection.createStatement();
		
		} catch(SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException error) {
			//do something with error
			error.printStackTrace();

		}
	}

	/**
	 * Creates a new player in the table given a player instance
	 * 
	 * @param newPlayer - instance of player to be added
	 * @return true if player was created, false otherwise
	 */
	public boolean createPlayer(Player newPlayer) {

		try {
		//	results = statement.executeQuery(playerTable);

			//creates a player in the table, requires serverID, email, password and money
			statement.executeUpdate("INSERT INTO player(email,password,money)" + 
					" VALUES ("+newPlayer.email+"','"+newPlayer.password+
					"',"+newPlayer.money+")");
			return true;
			
		} 
		catch (SQLException error) {
			//report error
			return false;
		}
		
	}

	/**
	 * Deletes a player from the table
	 * 
	 * @param delPlayer - player to be deleted
	 * @return true if deleted, false otherwise
	 */
	public boolean deletePlayer(Player delPlayer) {

		try {
		//	results = statement.executeQuery(playerTable);
			statement.executeUpdate("DELETE FROM player" + 
					" WHERE ID="+delPlayer.id);
			monTable.deleteAllMonsters(delPlayer.id);
			return true;
			
		} 
		catch (SQLException error) {
			//report error
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
			results = statement.executeQuery(playerTable);
			while(results.next()) {
				if((results.getString("email")==name)) {
					return new Player(
							results.getInt("ID"),
							results.getString("email"),
							results.getString("password"),
							results.getInt("money"));
				}
			}
		} catch (SQLException error) {
			// TODO: Catch error	
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
			results = statement.executeQuery(playerTable);
			while(results.next()) {
				if((results.getInt("ID")==playerID)) {
					return new Player(
							results.getInt("id"),
							results.getString("email"),
							results.getString("password"),
							results.getInt("money"));
				}
			}
		} catch (SQLException error) {
			// TODO: Catch error	
		}
		return null;
	}

	/**
	 * Edits the money of a player in the table
	 * Also sets the money variable of the Player instance
	 * 
	 * @param player - player to edit
	 * @param amount - amount to edit by
	 * @return true if edited, false otherwise
	 */
	public boolean editMoney(Player player, int amount) {
		try {
			results = statement.executeQuery(playerTable);
			while(results.next()) {
				if(results.getInt("ID")==player.id) {
				
					int newMoney = player.money+amount;
				
					String sql = "UPDATE player SET money="+newMoney+
							" WHERE ID="+player.id;
			
					statement.executeUpdate(sql);
					
					return true;
				}
			}
		} catch (SQLException error) {
			// TODO: Catch error	
		}
		return false;
	}
	
	/**
	 * Simple code for login validation. checks whether the user
	 * exists in the table 
	 * 
	 * @param username - for account
	 * @param password - for account
	 * @return a user, or null
	 */
	public Player login(String username,String password) {
		
		try {
			results = statement.executeQuery(playerTable);
			while(results.next()) {
				
				if(results.getString("email").equals(username)) {
				
					if(results.getString("password").equals(password)) {
						
						return getPlayer(username);
					}
				}
				
			}
		} catch (SQLException error) {
			// report error	
		}
		return null;
	}
	
	/**
	 * Checks whether the email already exists,
	 * if not then create a new player and add them
	 * 
	 * @param username - email of the new user
	 * @param password - password of the user
	 * @return true if the player email doesn't exist in the table and is added, false otherwise
	 */
	public boolean registerPlayer(String username,String password) {
		
		try {
			results = statement.executeQuery(playerTable);
			while(results.next()) {
				
				if(results.getString("email").equals(username)) {
					return false;
				}
			}
			int startMoney = 500;
			Player newPlayer = new Player(0,username, password, startMoney);
			return createPlayer(newPlayer);
		} 
		catch (SQLException error) {
			// report error	
			return false;
		}
		
	}
	
	/**
	 * Returns a list of id's of the player's monsters
	 * 
	 * @param owner - owner of monsters
	 * @return list of monster id's
	 */
	public List<Monster> getMonsters(int ownerID) {
		
		return monTable.getMonsters(ownerID);
		
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
