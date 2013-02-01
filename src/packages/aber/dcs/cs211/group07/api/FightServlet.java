package aber.dcs.cs211.group07.api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import aber.dcs.cs211.group07.actions.Fight;
import aber.dcs.cs211.group07.data.Monster;
import aber.dcs.cs211.group07.db.MonsterTableConnector;
import aber.dcs.cs211.group07.db.PlayerTableConnector;
import aber.dcs.cs211.group07.db.TableConnector;

/**
 * Handles requests from other users to fight
 * 
 * @author Dan Cornwell
 */

@SuppressWarnings("serial")
@WebServlet(name = "FriendshipRequest", urlPatterns = {"/fight/request"})
public class FightServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/plain;charset=UTF-8");

		// Gets a list of the parameters in the url
		Map<String, String[]> params = request.getParameterMap();

		// Checks that the parameters meet what is required
		String[] requiredParams = new String[] {"fightID", "localMonsterID", "remoteMonsterID", "remoteServerNumber"};
		if (!params.keySet().containsAll(Arrays.asList(requiredParams))) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parameter set incorrect");
			return;
		}
		
		try {
			TableConnector db = new TableConnector();
			db.statement.executeUpdate("INSERT INTO 'fight' VALUES("+params.get("fightID")+ "','" + 
					params.get("localMonsterID") + "','" + params.get("remoteMonsterID") + "','" + params.get("remoteServerNumber"));
			db.close();
		} catch (SQLException e) {
			TableConnector.complain(e);
		}
		
		// Connect to the database
		MonsterTableConnector monsterTable = new MonsterTableConnector();
		PlayerTableConnector playerTable = new PlayerTableConnector();
		
		Monster ourMonster, theirMonster;
		
		ourMonster = monsterTable.getMonster(Integer.parseInt(request.getParameter("localMonsterID")));
		if (ourMonster == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Local user ID does not exist");
		}
		
		try {
			MonsterClient monsterClient = new MonsterClient();
			theirMonster = monsterClient.getMonster(
					request.getParameter("remoteMonsterID"),
					Integer.parseInt(request.getParameter("remoteServerNumber")));
		} catch (NumberFormatException | JSONException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Malformed data from other server");
			return;
		}

		// Check that the localUserId actually exists on our server
    	Monster winner = Fight.fight(ourMonster, theirMonster);
    	int money;
    	if(winner == ourMonster) {
    		// TODO: Send lost request to them, and update our database
    		money = 100;
    	} else {
    		money = -100;
    		monsterTable.deleteMonster(ourMonster);
    	}
    	playerTable.editMoney(playerTable.getPlayer(ourMonster.ownerID), money);
	}
}

