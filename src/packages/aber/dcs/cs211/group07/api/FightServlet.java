package aber.dcs.cs211.group07.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Map;

import org.json.JSONObject;

import aber.dcs.cs211.group07.actions.Fight;
import aber.dcs.cs211.group07.data.Monster;
import aber.dcs.cs211.group07.db.FriendsTableConnector;
import aber.dcs.cs211.group07.db.MonsterTableConnector;
import aber.dcs.cs211.group07.db.PlayerTableConnector;

/**
 * Handles requests from other users to fight
 * 
 * @author Dan Cornwell
 *
 */

@WebServlet(name = "FriendshipRequest", urlPatterns = {"/fight/request"})
public class FightServlet extends HttpServlet {

	

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
 
    response.setContentType("text/plain;charset=UTF-8");
	PrintWriter out = response.getWriter();

	//Gets a list of the parameters in the url
	Map params = request.getParameterMap();
 
	//Checks that the parameters meet what is required
	//Throws error if doesn't
	String[] requiredParams = new String[] {"fightID", "localMonsterID", "remoteMonsterID", "remoteServerNumber"};
	if (!params.keySet().containsAll(Arrays.asList(requiredParams))) {
	    response.sendError(
		    HttpServletResponse.SC_BAD_REQUEST,
		    "Parameter set incorrect");
	} else {
		
		//add to fight database
		String host = "jdbc:mysql://74.53.183.226/handyman_monster";
		String userName = "handyman_group07";
		String password = "3213560921*+*";
		
		//Creates a connection to the database and a statement
		Connection connection = DriverManager.getConnection(host,userName,password);
		Statement statement = connection.createStatement();
				
		statement.executeUpdate("INSERT INTO 'fight' VALUES("+params.get("fightID")+ "','" + 
				params.get("localMonsterID") + "','" + params.get("remoteMonsterID") + "','" + params.get("remoteServerNumber"));
		/*
		MonsterTableConnector monsterTable = new MonsterTableConnector();
		PlayerTableConnector ptc = new PlayerTableConnector();
		MonsterClient mon = new MonsterClient();
		Monster ourMon = monsterTable.getMonster((int) params.get("localMonsterID"));
		Monster theirMon = mon.getMonster((String)params.get("remoteMonsterID"), (int)params.get("remoteServerNumber"));
	    // Check that the localUserId actually exists on our server
	    if (ourMon!=null && theirMon!=null) {
	    	Fight fight = new Fight();
	    	Monster winner = fight.fight(ourMon, theirMon);
	    	
	    	PlayerTableConnector playerTable = new PlayerTableConnector();
	    	
	    	if(winner==ourMon) {
	    		//send lost request to them, update our stuff
	    		playerTable.editMoney(ptc.getPlayer(ourMon.ownerID), 100);
	    	}
	    	else {
	    		playerTable.editMoney(ptc.getPlayer(ourMon.ownerID), -100);
	    		monsterTable.deleteMonster(ourMon);
	    	}
	    	
	    } 
	    
	    else {
		response.sendError(
			HttpServletResponse.SC_BAD_REQUEST,
			"Local user ID does not exist");
	    }
	    */
	}
	
	}
	
}

