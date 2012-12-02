package aber.dcs.cs211.group07.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import aber.dcs.cs211.group07.data.Monster;
import aber.dcs.cs211.group07.data.Player;

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
			//Enter name of the actual datbase
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
		 */
		public void createMonster(Monster mon) {
			
			try {
				results = statement.executeQuery(monsterTable);

				//need to set ID here as they will probably be null at constuction

				statement.executeUpdate("INSERT INTO MONSTER" + 
						" VALUES ('"+mon.player+"','"+mon.name+
						"','"+mon.birth+"','"+mon.gender+
						"','"+mon.health_lost+"')");
			} 
			catch (SQLException error) {
				//report error
			}
			
		}
		
		/**
		 * Deletes a monster using its id
		 * 
		 * @param mon - monster to be deleted
		 */
		public void deleteMonster(Monster mon) {
			//need to add an id variable to monster
			try {
				results = statement.executeQuery(monsterTable);
				statement.executeUpdate("DELETE FROM MONSTER" + 
						" WHERE ID="+mon.id);
			} 
			catch (SQLException error) {
				//report error
			}
			
		}
		
		/**
		 * Returns a monster from the table
		 * 
		 * @param monID - id of the monster
		 * @return a monster instance
		 */
		public Monster getMonster(int monID) {
			
			Monster foundMonster = null;
			
			try {
				results = statement.executeQuery(monsterTable);
				while(results.next()) {
						
						if(results.getInt("ID")==monID)
						{
							//create a monster with some constructor
						}

				}
			} catch (SQLException error) {
				// report error	
			}
			
			return foundMonster;
		}
		
		/**
		 * Changes owner of a monster in the table
		 * also sets the instance of monster's player variable
		 * 
		 * @param mon - the monster to be edited
		 * @param newOwner - the new owner of the monster
		 */
		public void editOwner(Monster mon,Player newOwner) {
			
			try {
				results = statement.executeQuery(monsterTable);
				while(results.next()) {

					if(results.getInt("ID")==mon.id) {
						
						String sql = "UPDATE MONSTER SET PLAYER='"+newOwner+
								"' WHERE ID="+mon.id;
						
						statement.executeUpdate(sql);
						
						mon.player=newOwner;

						break;
					}

				}
			} catch (SQLException error) {
				// report error	
			}
			
		}
		
		public void editHealthLost(Monster mon, int amount) {
			
			try {
				results = statement.executeQuery(monsterTable);
				while(results.next()) {

					if(results.getInt("ID")==mon.id) {
						
						int newHealth = mon.health_lost+amount;
						
						String sql = "UPDATE MONSTER SET HEALTH_LOST="+newHealth+
								" WHERE ID="+mon.id;
						
						statement.executeUpdate(sql);
						
						mon.health_lost = newHealth;

						break;
					}

				}
			} catch (SQLException error) {
				// report error	
			}
			
		}
		
		public boolean getGender(Monster mon) {
			
			boolean gender = false;
			
			try {
				results = statement.executeQuery(monsterTable);
				while(results.next()) {

					if(results.getInt("ID")==mon.id) {
						
						gender = results.getBoolean("GENDER");

						break;
					}

				}
				
			} catch (SQLException error) {
				// report error	
			}
			
			return gender;
		}
	
}
