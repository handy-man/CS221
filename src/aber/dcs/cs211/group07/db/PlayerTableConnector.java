package aber.dcs.cs211.group07.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import aber.dcs.cs211.group07.data.Player;

public class PlayerTableConnector {

	//A statement from the connection, used to get result sets
	private Statement statement = null;
	//Results from the player table. Initialized at the begin on methods
	private ResultSet results = null;
	//SQL statement to read from the player table
	private String playerTable = "SELECT * FROM PLAYER";


	public PlayerTableConnector() {

		//Enter address of database being used
		String host = "database/address";
		//Enter name of the actual database
		String databaseName = "name of the database";
		//Enter password
		String password = "password";
		
		try {
			//Creates a connection to the database and a statement
			Connection connection = DriverManager.getConnection(host,databaseName,password);
			statement = connection.createStatement();
		
		}

		catch(SQLException error) {
			//do something with error
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

			//need to set ID and serverID here as they will probably be null at constuction

			statement.executeUpdate("INSERT INTO PLAYER" + 
					" VALUES ('"+newPlayer.id+"','"+newPlayer.serverID+
					"','"+newPlayer.email+"','"+newPlayer.password+
					"','"+newPlayer.money+"')");
		} 
		catch (SQLException error) {
			//report error
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
		} 
		catch (SQLException error) {
			//report error
		}

	}

	/**
	 * Returns a player instance from the table 
	 * 
	 * UNFINISHED
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
					Player p = new Player();
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
				
				if(results.getInt("ID")==id) {
				
					int newMoney = player.money+amount;
				
					String sql = "UPDATE PLAYER SET MONEY="+newMoney+
							" WHERE ID="+id;
			
					statement.executeUpdate(sql);

					player.money = newMoney;
					
					break;
				}
				
			}
		} catch (SQLException error) {
			// report error	
		}

	}

}
