/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/

/**
*
* @author adriangawryszewski
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
} catch (SQLException error) {
//do something with error
System.out.println("connection not made");
error.printStackTrace();
}catch (ClassNotFoundException e){
	
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
//results = statement.executeQuery(playerTable);
//creates a player in the table, requires serverID, email, password and money
//String sql = "INSERT INTO 'player' ('email', 'password', 'money')"
//+ "VALUES ('" + newPlayer.email + "','" + newPlayer.password + "'," + newPlayer.money + ")";
String sql ="INSERT INTO `player` (`email` ,`password` ,`money` )VALUES ('newtests',  'pass',  '500')";
Connection connection = DriverManager.getConnection(host, name, password);
Statement s = connection.createStatement();
s.executeUpdate(sql);
s.close();
return true;
} catch (SQLException error) {
//report error
return false;
}
}
//Testing player creation with simple SQL only scripts.
public boolean createPlayerSQL(Player p) throws ServletException, IOException{
    String connectionURL ="jdbc:mysql://74.53.183.226/handyman_monster";
    Connection connection = null;
    
    try{
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(connectionURL, "handyman_group07", "3213560921*+*");
        
       // String sql = "SELECT * FROM `player` WHERE 1";
        //String sql = "INSERT into 'player' VALUES ";
        String sql = "INSERT INTO `player` (`email` ,`password` ,`money`)VALUES ('" + p.email + "',  '" + p.password + "',  '" + p.money + "')";
        Statement s = connection.createStatement();
        //s.executeQuery(sql);
        s.executeUpdate(sql);
   
        s.close();
        return true;
    
    } catch(Exception a) {
     	a.printStackTrace();
     	return false;
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
statement.executeUpdate("DELETE FROM 'player'"
+ " WHERE ID=" + id);
//monTable.deleteMonster(newPlayer.email);
return true;
} catch (SQLException error) {
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
Connection connection = DriverManager.getConnection(host, name, password);
Statement s = connection.createStatement();
results = s.executeQuery(playerTable);
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
} catch (SQLException error) {
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
} catch (SQLException error) {
// report error	
}
return null;
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
String sql = "UPDATE PLAYER SET MONEY=" + newMoney
+ " WHERE id=" + id;
statement.executeUpdate(sql);
//saves getting the updated player
player.money = newMoney;
return true;
}
}
} catch (SQLException error) {
// report error	
}
return false;
}
/**
* Simple code for login validation. checks whether the user exists in the
* table
*
* @param username - for account
* @param password - for account
* @return a user, or null
*/
public int login(String username, String password) {
int status = 0;
try {
String sql = "SELECT email, password FROM PLAYER";
results = statement.executeQuery(playerTable);
while (results.next()) {
if (results.getString("email").equals(username)) {
if (results.getString("password").equals(password)) {
status = 1;
}
}
}
} catch (SQLException error) {
// report error	
}

return status;
}
/**
* Checks whether the email already exists, if not then create a new player
* and add them
*
* @param username - email of the new user
* @param password - password of the user
* @return true if the player email doesn't exist in the table and is added,
* false otherwise
*/
public boolean registerPlayer(String username, String password) {
try {
results = statement.executeQuery(playerTable);
while (results.next()) {
if (results.getString("email").equals(username)) {
return false;
}
}
Player newPlayer = new Player(username, password);
return createPlayer(newPlayer);
} catch (Exception error) {
// report error	
	error.fillInStackTrace();
return false;
}
}
/**
* Returns a list of id's of the player's monsters
*
* @param owner - owner of monsters
* @return list of monster id's
*/
/*	public List<Integer> getMonsters(Player owner) {

return monTable.getMonster(owner.email);

}
*/
}