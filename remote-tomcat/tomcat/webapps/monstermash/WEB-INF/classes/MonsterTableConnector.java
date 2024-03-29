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
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;


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
	//SQL statement to read from the player table
	private String monsterTable = "SELECT * FROM MONSTER";;


	public MonsterTableConnector() {

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
	 * Creates a new monster in the table
	 * 
	 * @param mon - monster instance needing to be added
	 * @return true if the monster is added, false otherwise
	 */
	public boolean createMonster(Monster mon) {

		try {
			results = statement.executeQuery(monsterTable);

			//need to set ID here as they will probably be null at construction

			statement.executeUpdate("INSERT INTO MONSTER" + 
					" VALUES ('"+mon.player.email+"','"+mon.name+
					"','"+mon.birth+
					"',"+mon.health_lost+")");
			return true;
		} 
		catch (SQLException error) {
			//report error
			return false;
		}

	}

	/**
	 * Deletes a monster using its id
	 * 
	 * @param mon - monster to be deleted
	 * @return true if monster deleted, false otherwise
	 */
	public boolean deleteMonster(Monster mon) {
	
		try {
			results = statement.executeQuery(monsterTable);
			statement.executeUpdate("DELETE FROM MONSTER" + 
					" WHERE ID="+mon.id);
			return true;
		} 
		catch (SQLException error) {
			//report error
			return false;
		}

	}

	/**
	 * Deletes a monster using its owner name
	 * 
	 * @param owner - name of the owner of the monster
	 * @return true if monster deleted, false otherwise
	 */
	public boolean deleteMonster(String owner) {
		//need to add an id variable to monster
		try {
			results = statement.executeQuery(monsterTable);
			statement.executeUpdate("DELETE FROM MONSTER" + 
					" WHERE PLAYER='"+owner+"'");
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
	 * @return a monster instance, or null
	
        
        
        ///// Problem- different constructor is Monster class
        
	public Monster getMonster(int monID) {


		try {
			results = statement.executeQuery(monsterTable);
			while(results.next()) {

				if(results.getInt("ID")==monID)
				{
					int id = results.getInt("id");
					String player = results.getString("owner");
					String name = results.getString("name");
					Date birth = results.getDate("birth");
					boolean sex = results.getBoolean("gender");
					Double health_Lost = results.getDouble("health_Lost");
					Monster newMon = new Monster(id,player,name,birth,sex,health_Lost);
					return newMon;
				}

			}
		} catch (SQLException error) {
			// report error	
		}

		return null;
	}*/

	/**
	 * Returns a list of monster id's from the table
	 * 
	 * @param owner - name of the player
	 * @return list of monster id's
	 */
	public List<Integer> getMonster(String owner) {

		List<Integer> monList = new ArrayList<Integer>();

		try {
			results = statement.executeQuery(monsterTable);
			while(results.next()) {

				if(results.getString("OWNER")==owner)
				{	
					int id = results.getInt("id");
				
					monList.add(id);
				}

			}
		} catch (SQLException error) {
			// report error	
		}

		return monList;
	}


	/**
	 * Changes owner of a monster in the table
	 * also sets the instance of monster's player variable
	 * 
	 * @param mon - the monster to be edited
	 * @param newOwner - the new owner of the monster
	 * @return true if edited, false otherwise
	 */
	public boolean editOwner(Monster mon,Player newOwner) {

		try {
			results = statement.executeQuery(monsterTable);
			while(results.next()) {

				if(results.getInt("ID")==mon.id) {

					String sql = "UPDATE MONSTER SET PLAYER='"+newOwner+
							"' WHERE ID="+mon.id;

					statement.executeUpdate(sql);

					//saves getting the updated monster
					mon.player=newOwner;

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

					String sql = "UPDATE MONSTER SET HEALTH_LOST="+newHealth+
							" WHERE ID="+mon.id;

					statement.executeUpdate(sql);

					//saves getting the updated monster
					mon.health_lost = newHealth;

					return true;
				}

			}
		} catch (SQLException error) {
			// report error	
		}
		return false;
	}

	/**
	 * Returns the sex of the monster - unsure about workings of this
	 * 
	 * @param mon - monster to check
	 * @return true is female, false if male
	 */
	public boolean getSex(Monster mon) {

		boolean sex = false;

		try {
			results = statement.executeQuery(monsterTable);
			while(results.next()) {

				if(results.getInt("ID")==mon.id) {

					sex = results.getBoolean("SEX");

					break;
				}

			}

		} catch (SQLException error) {
			// report error	
		}

		return sex;
	}

}