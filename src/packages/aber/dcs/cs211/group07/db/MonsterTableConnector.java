/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aber.dcs.cs211.group07.db;

/**
 *
 * @author adriangawryszewski
 */
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
 */
public class MonsterTableConnector {


    //A statement from the connection, used to get result sets
    private Statement statement = null;
    //Results from the player table. Initialised at the begin on methods
    private ResultSet results = null;
    //SQL statement to read from the player table
    private String monsterTable = "SELECT * FROM ROOT.OWNER";
    ;
        public Date birth = new Date();

    public MonsterTableConnector() {

   		//Enter address of database being used
    	String host = "thehiddennation.com";
    	//Enter username (usually root?)
    	String userName = "handyman_group07";
    	//Enter password
    	String password = "3213560921*+*";

        try {
            //Creates a connection to the database and a statement
            Connection connection = DriverManager.getConnection(host, userName, password);
            statement = connection.createStatement();
        } catch (SQLException error) {
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

            long time = new Date().getTime();

            String update = "INSERT INTO ROOT.OWNER (OWNERID,NAME,AGE_RATE, STRENGTH, DEFENCE, BIRTH_DATE) VALUES ('"+mon.ownerID+"','"+mon.name+"',"+mon.strength+","+mon.toughness+
                    ","+mon.toughness+","+time+")";
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
            results = statement.executeQuery(monsterTable);
            statement.executeUpdate("DELETE FROM ROOT.MONSTER"
                    + " WHERE ID=" + mon.id);
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
        //need to add an id variable to monster
        try {
            results = statement.executeQuery(monsterTable);
            statement.executeUpdate("DELETE FROM MONSTER"
                    + " WHERE ownerID='" + ownerID + "'");
            return true;
        } catch (SQLException error) {
            //report error
            return false;
        }

    }

    	public Monster getMonster(int monID) {
    	
    		try {
    			results = statement.executeQuery(monsterTable);
    			while(results.next()) {
    	
    				if(results.getInt("ID")==monID)
    				{
    					int id = results.getInt("ID");
    					int ownerID = results.getInt("ownerID");
    					String name = results.getString("name");
    					Date birth_date = results.getDate("birth");
    					double age_rate = results.getDouble("age_rate");
    					double health_lost = results.getDouble("health_lost");
    					double strength = results.getDouble("genetic_strength");
    					double toughness = results.getDouble("genetic_defence");
    					int breed_offer = results.getInt("breed_offer");
    					int sale_offer = results.getInt("sale_offer");
    					Monster monster = new Monster(id, ownerID, name, birth_date,
    							age_rate, health_lost, strength, toughness,
    							breed_offer, sale_offer);
    					return monster;
    				}
    	
    			}
    		} catch (SQLException error) {
    			// report error	
    		}
    	
    		return null;
    	}


    
    public List<Monster> getMonsters(int ownerID) {

        List<Monster> monList = new ArrayList<Monster>();

        try {
            results = statement.executeQuery(monsterTable);
            while (results.next()) {

                if (results.getInt("OWNERID") == ownerID) {
                    int id = results.getInt("id");
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
     * Changes owner of a monster in the table also sets the instance of
     * monster's player variable
     *
     * @param mon - the monster to be edited
     * @param newOwner - the new owner of the monster
     * @return true if edited, false otherwise
     */
    public boolean editOwner(Monster mon, int newOwnerID) {

        try {
            results = statement.executeQuery(monsterTable);
            while (results.next()) {

                if (results.getString("name") == mon.name) {

                    String sql = "UPDATE MONSTER SET PLAYER='" + newOwnerID
                            + "' WHERE ID=" + mon.id;

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
            results = statement.executeQuery(monsterTable);
            while (results.next()) {

                if (results.getString("name") == mon.name) {

                    double newHealth = mon.health_lost + amount;

                    String sql = "UPDATE MONSTER SET HEALTH_LOST=" + newHealth
                            + " WHERE NAME=" + mon.name;

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
}

