package aber.dcs.cs211.group07.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import aber.dcs.cs211.group07.data.Monster;

/**
 * Codes for connecting to the monster table in the database
 * 
 * @author Daniel Cornwell
 *
 */
public class MonsterTableConnector {

	//A statement from the connection, used to get result sets
	private Statement statement = null;
	//Results from the player table. Initialized at the begin on methods
	private ResultSet results = null;
	//A connection to the database
	private Connection connection=null;
	//SQL statement to read from the player table
	private String monsterTable = "SELECT * FROM monsters";;


	public MonsterTableConnector() {

		//Enter address of database being used
		String host = "thehiddennation.com";
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
	 * Creates a new monster in the table
	 * 
	 * @param mon - monster instance to be added
	 * @return true if the monster is added, false otherwise
	 */
	public boolean createMonster(Monster mon) {

		try {
		//	results = statement.executeQuery(monsterTable);

			statement.executeUpdate("INSERT INTO monsters " + 
					" VALUES ("+mon.ownerID+",'"+mon.name+
					"',"+mon.birth_date+","+mon.death_date+","+mon.age_rate
					+","+mon.health_lost+","+mon.health+","
					+mon.strength+","+mon.toughness+","+mon.evasion+","
					+mon.breed_offer+","+mon.sale_offer+")");
			return true;
		} 
		catch (SQLException error) {
			//report error
			return false;
		}

	}

	/**
	 * Deletes a monster using it's id
	 * 
	 * @param mon - monster to be deleted
	 * @return true if monster deleted, false otherwise
	 */
	public boolean deleteMonster(Monster mon) {
	
		try {
		//	results = statement.executeQuery(monsterTable);
			statement.executeUpdate("DELETE FROM monsters" + 
					" WHERE ID="+mon.id);
			return true;
		} 
		catch (SQLException error) {
			//report error
			return false;
		}

	}

	/**
	 * Deletes a monster using its owner's ID
	 * Used when a player unregisters
	 * 
	 * @param ownerID - ID of the owner of the monster
	 * @return true if monster deleted, false otherwise
	 */
	public boolean deleteAllMonsters(int ownerID) {

		try {
		//	results = statement.executeQuery(monsterTable);
			statement.executeUpdate("DELETE FROM monsters" + 
					" WHERE ownerID="+ownerID+")");
			return true;
		} 
		catch (SQLException error) {
			//report error
			return false;
		}

	}

	/**
	 * Returns a monster from the table
	 * 
	 * @param monID - id of the monster
	 * @return a monster instance, or null if monster wasn't found
	 */
	public Monster getMonster(int monID) {

		try {
			results = statement.executeQuery(monsterTable);
			while(results.next()) {

				if(results.getInt("ID")==monID)
				{
					int id = results.getInt("ID");
					int ownerID = results.getInt("ownerID");
					String name = results.getString("name");
					Date birth = results.getDate("birth");
					Date death = results.getDate("death_rate");
					double age_rate = results.getDouble("age_rate");
					double health_Lost = results.getDouble("health_lost");
					double health = results.getDouble("base_health");
					double strength = results.getDouble("genetic_strength");
					double toughness = results.getDouble("genetic_toughness");
					int breed_offer = results.getInt("breed_offer");
					int sale_offer = results.getInt("breed_offer");
//	Double evasion = results.getDouble("genetic_evasion");
					Monster newMon = new Monster(id,ownerID,name,birth,death,age_rate,
							health_Lost,health,strength,toughness,
							breed_offer, sale_offer);
					return newMon;
				}

			}
		} catch (SQLException error) {
			// report error	
		}

		return null;
	}
	
	/**
	 * Finds all the monsters of a player and returns them in a list
	 * 
	 * @param ownerID - owner ID of the player
	 * @return - a list of all their monsters
	 */
	public List<Monster> getMonsters(int ownerID) {
		
		List<Monster> monList = new ArrayList<Monster>();
		
		try {
			results = statement.executeQuery(monsterTable);
			while(results.next()) {

				if(results.getInt("ownerID")==ownerID)
				{	
					int id = results.getInt("ID");

					Monster mon = getMonster(id);
					
					monList.add(mon);
				}

			}
		} catch (SQLException error) {
			// report error	
		}

		return monList;
	}	

	/**
	 * Gives a monster to a player without any alive monsters
	 * 
	 * @param ownerID - the player ID to give a monster to
	 * @return true if a monster was added, false otherwise
	 */
	public boolean giveMonster(int ownerID) {
		
		try {
			results = statement.executeQuery(monsterTable);
			while(results.next()) {

				if(results.getInt("ownerID")==ownerID)
				{	
					return false;
				}

			}
			Monster newMon = new Monster(ownerID);
			createMonster(newMon);
			return true;
			
		} catch (SQLException error) {
			// report error	
			return false;
		}
		
	}

	/**
	 * Changes owner of a monster in the table
	 * also sets the instance of monster's player variable
	 * 
	 * @param mon - the monster to be edited
	 * @param newOwner - ID of the new owner of the monster
	 * @return true if edited, false otherwise
	 */
	public boolean editOwner(Monster mon,int newOwnerID) {

		try {
			results = statement.executeQuery(monsterTable);
			while(results.next()) {

				if(results.getInt("ID")==mon.id) {

					String sql = "UPDATE monsters SET ownerID="+newOwnerID+
							" WHERE ID="+mon.id;

					statement.executeUpdate(sql);

					return true;
				}

			}
		} catch (SQLException error) {
			// report error	
		}
		return false;
	}

	/**
	 * Edits the healthLost of a monster 
	 * 
	 * @param mon - monster to be edited 
	 * @param amount - amount to subtract from the health
	 * @return true if edited, false otherwise
	 */
	public boolean editHealthLost(Monster mon, double amount) {

		try {
			results = statement.executeQuery(monsterTable);
			while(results.next()) {

				if(results.getDouble("ID")==mon.id) {

					double newHealth = mon.health_lost+amount;

					String sql = "UPDATE monsters SET health_lost="+newHealth+
							" WHERE ID="+mon.id;

					statement.executeUpdate(sql);

					return true;
				}

			}
		} catch (SQLException error) {
			// report error	
		}
		return false;
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
