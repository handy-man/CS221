package aber.dcs.cs211.group07.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

import org.json.JSONObject;

import aber.dcs.cs211.group07.actions.Fight;
import aber.dcs.cs211.group07.data.Monster;
import aber.dcs.cs211.group07.db.FriendsTableConnector;
import aber.dcs.cs211.group07.db.MonsterTableConnector;
import aber.dcs.cs211.group07.db.PlayerTableConnector;

@WebServlet(name = "FriendshipRequest", urlPatterns = {"/fight/request"})
public class FightServlet extends HttpServlet {

	

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
 
    response.setContentType("text/plain;charset=UTF-8");
	PrintWriter out = response.getWriter();
    	
	// Get a map of all the parameters set in the URL,
	// including the parameter names and values
	Map params = request.getParameterMap();
 
	// We need to make sure all the parameters we need are set
	// If not, send a HTTP error code
	String[] requiredParams = new String[] {"fightID", "localMonsterID", "remoteMonsterID", "remoteServerNumber"};
	if (!params.keySet().containsAll(Arrays.asList(requiredParams))) {
	    response.sendError(
		    HttpServletResponse.SC_BAD_REQUEST,
		    "Parameter set incorrect");
	} else {
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
	}
	
	}
	
}

