package aber.dcs.cs211.group07.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import aber.dcs.cs211.group07.data.Monster;

import aber.dcs.cs211.group07.data.Player;

public class PlayerTableConnector {

	//A statement from the connection, used to get result sets
	private Statement statement = null;
	//Results from the player table. Initialized at the begin on methods
	private ResultSet results = null;
	//SQL statement to read from the player table
	private String playerTable = "SELECT * FROM PLAYER_TEST";
	
	private MonsterTableConnector monTable = new MonsterTableConnector();


	public PlayerTableConnector() {

		//Enter address of database being used
		//Below is my local database
		String host = "jdbc:derby:/Users/dannyboi/MyDB;create=true";
		//Enter name of the actual database
		String databaseName = "MONSTER_MASH_TEST";
		//Enter password
		String password = "admin";
		
		try {
			//Creates a connection to the database and a statement
			Connection connection = DriverManager.getConnection(host,databaseName,password);
			statement = connection.createStatement();
			System.out.println("connection made");
		
		}

		catch(SQLException error) {
			//do something with error
			System.out.println("connection not made");
			error.printStackTrace();

		}
	}

	/**
	 * Creates a new row in the table the corresponds to a player
	 * 
	 * @param newPlayer - a player instance that needs to be added to the database
	 */
	public void createPlayer(Player newPlayer) {

		try {
			results = statement.executeQuery(playerTable);

			//need to set ID and serverID here as they will probably be null at construction

			statement.executeUpdate("INSERT INTO PLAYER_TEST(serverID,email,password,money)" + 
					" VALUES ("+newPlayer.serverID+
					",'"+newPlayer.email+"','"+newPlayer.password+
					"',"+newPlayer.money+")");
			
		} 
		catch (SQLException error) {
			error.printStackTrace();
		}

	}

	/**
	 * Deletes a player via the id
	 * 
	 * @param newPlayer
	 */
	public void deletePlayer(Player newPlayer) {

		try {
			results = statement.executeQuery(playerTable);
			statement.executeUpdate("DELETE FROM PLAYER" + 
					" WHERE ID="+newPlayer.id);
			monTable.deleteMonster(newPlayer.email);
			
		} 
		catch (SQLException error) {
			//report error
		}

	}


	/**
	 * Returns a player instance from the table 
	 * 
	 * @param name - name of the player to get
	 * @return a player instance
	 */
	public Player getPlayer(String name) {

		Player foundPlayer = null;

		try {
			results = statement.executeQuery(playerTable);
			while(results.next()) {

				if((results.getString("email")==name)) {
					int id = results.getInt("id");
					int serverID = results.getInt("serverID");
					String email = results.getString("email");
					String pass = results.getString("password");
					int money = results.getInt("money");
					Player p = new Player(id,serverID,email,pass,money);
					//create a player with a constructor using table row
					foundPlayer=p;
				}

			}
		} catch (SQLException error) {
			// report error	
		}

		return foundPlayer;

	}
	
	/**
	 * Returns a player instance from the table 
	 * 
	 * @param playerID - id of the player we want to get
	 * @return a player instance
	 */
	public Player getPlayer(int playerID) {

		Player foundPlayer = null;

		try {
			results = statement.executeQuery(playerTable);
			while(results.next()) {

				if((results.getInt("ID")==playerID)) {
					int id = results.getInt("id");
					int serverID = results.getInt("serverID");
					String email = results.getString("email");
					String pass = results.getString("password");
					int money = results.getInt("money");
					Player p = new Player(id,serverID,email,pass,money);
					//create a player with a constructor using table row
					foundPlayer=p;
				}

			}
		} catch (SQLException error) {
			// report error	
		}

		return foundPlayer;

	}

	/**
	 * Edits the money of a player in the table
	 * Also sets the money variable of the Player instance
	 * 
	 * @param player - the player to edit
	 * @param amount - the amount to edit by
	 */
	public void editMoney(Player player,int amount) {

		int id = player.id;

		try {
			results = statement.executeQuery(playerTable);
			while(results.next()) {
				
				if(results.getInt("id")==id) {
				
					int newMoney = player.money+amount;
				
					String sql = "UPDATE PLAYER SET MONEY="+newMoney+
							" WHERE id="+id;
			
					statement.executeUpdate(sql);
					
					//saves getting the updated player
					player.money = newMoney;
					
					break;
				}
				
			}
		} catch (SQLException error) {
			// report error	
		}

	}
	
	/**
	 * Simple code for login validation. If the user name exists in the database
	 * and the password is correct for the user name return the user else 
	 * return null
	 * 
	 * @param username - for account
	 * @param password - for account
	 * @return a user
	 */
	public Player login(String username,String password) {
		
		try {
			results = statement.executeQuery(playerTable);
			while(results.next()) {
				
				if(results.getString("email").equals(username)) {
				
					if(results.getString("password").equals(password)) {
						
						int id = results.getInt("id");
						int serverID = results.getInt("serverID");
						String email = results.getString("email");
						String pass = results.getString("password");
						int money = results.getInt("money");
						Player p = new Player(id,serverID,email,pass,money);
						//create a player with a constructor using table row
						return p;
					}
				}
				
			}
		} catch (SQLException error) {
			// report error	
		}
		return null;
	}
	
	/**
	 * Returns a list of id's of the player's monsters
	 * 
	 * @param owner - owner of monsters
	 * @return list of monster id's
	 */
	public List<Integer> getMonsters(Player owner) {
		
		return monTable.getMonster(owner.email);
		
	}
	
}
