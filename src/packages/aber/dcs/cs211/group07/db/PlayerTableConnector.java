/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/

/**
*
* @author adriangawryszewski
* @author Nathan Hand
* @author Daniel Cornwell
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import aber.dcs.cs211.group07.data.Monster;
import aber.dcs.cs211.group07.data.Player;

/**
* Codes for connecting to the player table in the database
*
* @author Daniel Cornwell
*
*/
public class PlayerTableConnector {
	
private	String host = "jdbc:mysql://74.53.183.226/handyman_monster";
	//Enter name of the actual database
private	String name = "handyman_group07";
	//Enter password
private	String password = "3213560921*+*";
//A statement from the connection, used to get result sets
private Statement statement = null;
//Results from the player table. Initialized at the begin on methods
private ResultSet results = null;
//SQL statement to read from the player table
private String playerTable = "SELECT * FROM 'player'";
//private MonsterTableConnector monTable = new MonsterTableConnector();
public PlayerTableConnector() {
//Enter address of database being used
//Below is my local database

	try {
		//Creates a connection to the database and a statement
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(host, name, password);
		statement = connection.createStatement();
		System.out.println("connection made");
	} 
	catch (SQLException error) {
		//do something with error
		System.out.println("connection not made");
		error.printStackTrace();
	}
	catch (ClassNotFoundException e){
	
}
}




/**
* Deletes a player from the table
*
* @param newPlayer - player to be deleted
* @return true if deleted, false otherwise
*/
public boolean deletePlayer(Object id) {
	try {
		results = statement.executeQuery(playerTable);
		statement.executeUpdate("DELETE FROM 'player'" + " WHERE ID=" + id);
		//monTable.deleteMonster(newPlayer.email);
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
				while (results.next()) {
					if (results.getString("email").equals(name)){
						int id = results.getInt("id");
						String email = results.getString("email");
						String pass = results.getString("password");
						int money = results.getInt("money");
						Player p = new Player(email, pass, money);
						//create a player with a constructor using table row
						return p;
					}
				}
		} 
	catch (SQLException error) {
// report error	
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
				while (results.next()) {
						if ((results.getInt("ID") == playerID)) {
							int id = results.getInt("id");
							String email = results.getString("email");
							String pass = results.getString("password");
							int money = results.getInt("money");
							Player p = new Player(email, pass);
							//create a player with a constructor using table row
							return p;
						}
				}
} 
	catch (SQLException error) {
// report error	
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
		results = statement.executeQuery(playerTable);
		while(results.next()) {

				int id = results.getInt("id");
				String email = results.getString("email");
				String pass = results.getString("password");
				int money = results.getInt("money");
				//Fix below player objects 
				//Player p = new Player(id,email,pass,money);
				//create a player with a constructor using table row
				//playerList.add(p);

		}
	} catch (SQLException error) {
		// report error	
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
		results = statement.executeQuery(playerTable);
			while (results.next()) {
				if (results.getInt("id") == id) {
					int newMoney = player.money + amount;
					String sql = "UPDATE PLAYER SET MONEY=" + newMoney + " WHERE id=" + id;
					statement.executeUpdate(sql);
//saves getting the updated player
					player.money = newMoney;
					return true;
				}
			}
} 
catch (SQLException error) {
// report error	
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
	int status = 0;
	String dbPassword = "3213560921*+*";
	try {
		Connection connection = DriverManager.getConnection(host, name, dbPassword);
		Statement s = connection.createStatement();
		String playerQuery = "SELECT * FROM `player` WHERE `email` = '" + username + "'";
		ResultSet loginQuery = null;
		loginQuery = s.executeQuery(playerQuery);
			while (loginQuery.next()) {
				if (loginQuery.getString("email").equals(username)) {
					if (loginQuery.getString("password").equals(password)) {
							status = 1;	
							Player newPlayer = getPlayer(username);
					}
				}
			}
	} catch (SQLException error) {
	// report error	
	}

return status;
}


/**
* Returns a list of id's of the player's monsters
*
* @param owner - owner of monsters
 * @return 
* @return list of monster id's
 * @throws SQLException 
*/
/*	public List<Integer> getMonsters(Player owner) {

return monTable.getMonster(owner.email);

}
*/
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
	Connection connection = DriverManager.getConnection(host, name, password);
	Statement s = connection.createStatement();
	
	String playerQuery = "SELECT * FROM `player` WHERE `email` = '" + name2 + "'";
	ResultSet playerResults = null;
	playerResults = s.executeQuery(playerQuery); //grab entire player table
		if (playerResults.next() == false){
			String sql ="INSERT INTO `player` (`email` ,`password` ,`money` )VALUES ('" + name2 + "',  '" + password2 + "',  '500')";
			s.executeUpdate(sql);
			s.close();
			return true;
		}
		else{
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
	
	//return monTable.getMonsters(ownerID);
	
}

/**
 * Closes the connection to the database
 * 
 */

public void close() {
	try {
		//connection.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
