package aber.dcs.cs211.group07.db;

/**
 *
 * @author adriangawryszewski
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import aber.dcs.cs211.group07.data.Monster;
import aber.dcs.cs211.group07.data.Player;

/**
 * Codes for connecting to the monster table in the database
 *
 * @author Daniel Cornwell
 */
public class MonsterTableConnector extends TableConnector {
	/**
	 * SQL statement to get all monsters from the table
	 */
	private static final String QUERY_GET_ALL_MONSTERS = "SELECT * FROM ROOT.OWNER";

	/**
	 * Creates a new monster in the table
	 *
	 * @param mon - monster instance needing to be added
	 * @return true if the monster is added, false otherwise
	 */
	public boolean createMonster(Monster mon) {
		try {            
			long time = new Date().getTime();

			String update = "INSERT INTO ROOT.OWNER (OWNERID, NAME, AGE_RATE, STRENGTH, DEFENCE, BIRTH_DATE) VALUES ('"
					+mon.ownerID+"','"+mon.name+"',"+mon.strength+","+mon.toughness+","+mon.toughness+","+time+")";
			statement.executeUpdate(update);
			return true;
		} catch (SQLException error) {
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
			statement.executeUpdate("DELETE FROM MONSTER WHERE ID=" + mon.id);
			return true;
		} catch (SQLException error) {
			//report error
			return false;
		}
	}

	/**
	 * Deletes a monster using its owner name
	 *
	 * @param ownerID - id of the owner of the monster
	 * @return true if monster deleted, false otherwise
	 */
	public boolean deleteAllMonsters(int ownerID) {
		try {
			statement.executeUpdate("DELETE FROM MONSTER WHERE ownerID='" + ownerID + "'");
			return true;
		} catch (SQLException error) {
			//report error
			return false;
		}

	}

	public Monster getMonster(int monID) {
		try {
			ResultSet results = statement.executeQuery(QUERY_GET_ALL_MONSTERS);
			while(results.next()) {
				if(results.getInt("ID")==monID) {
					return new Monster(
							results.getInt("ID"),
							results.getInt("ownerID"),
							results.getString("name"),
							results.getDate("birth"),
							results.getDouble("age_rate"),
							results.getDouble("health_lost"),
							results.getDouble("genetic_strength"),
							results.getDouble("genetic_defence"),
							results.getInt("breed_offer"),
							results.getInt("sale_offer"));
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
	 * @param owner The player to fetch a list of monsters for
	 * @return A list of monsters
	 */
	public List<Monster> getMonsters(Player owner) {
		return this.getMonsters(owner.id);
	}

	public List<Monster> getMonsters(int ownerID) {
		List<Monster> monList = new ArrayList<Monster>();
		try {
			ResultSet results = statement.executeQuery(QUERY_GET_ALL_MONSTERS);
			while (results.next()) {
				if (results.getInt("OWNERID") == ownerID) {
					monList.add(getMonster(results.getInt("id")));
				}
			}
		} catch (SQLException error) {
			// report error	
		}
		return monList;
	}

	/**
	 * Changes owner of a monster in the table also sets the instance of
	 * monster's player variable
	 *
	 * @param mon - the monster to be edited
	 * @param newOwner - the new owner of the monster
	 * @return true if edited, false otherwise
	 */
	public boolean editOwner(Monster mon, int newOwnerID) {
		try {
			ResultSet results = statement.executeQuery(QUERY_GET_ALL_MONSTERS);
			while (results.next()) {
				if (results.getString("name") == mon.name) {
					String sql = "UPDATE MONSTER SET PLAYER='" + newOwnerID + "' WHERE ID=" + mon.id;
					statement.executeUpdate(sql);
					//saves getting the updated monster
					mon.ownerID = newOwnerID;
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
			ResultSet results = statement.executeQuery(QUERY_GET_ALL_MONSTERS);
			while (results.next()) {
				if (results.getString("name") == mon.name) {
					double newHealth = mon.health_lost + amount;
					String sql = "UPDATE MONSTER SET HEALTH_LOST=" + newHealth + " WHERE NAME=" + mon.name;
					statement.executeUpdate(sql);
					mon.health_lost = newHealth;
					return true;
				}
			}
		} catch (SQLException error) {
			// report error	
		}
		return false;
	}
}
