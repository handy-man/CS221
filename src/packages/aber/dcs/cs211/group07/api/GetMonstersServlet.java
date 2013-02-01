package aber.dcs.cs211.group07.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import aber.dcs.cs211.group07.data.Monster;
import aber.dcs.cs211.group07.db.MonsterTableConnector;

/**
 * A servlet that handles requests for our monsters
 * 
 * @author Dan Cornwell
 *
 */

@SuppressWarnings("serial")
@WebServlet(name = "Monsters", urlPatterns = {"/monsters"})
public class GetMonstersServlet extends HttpServlet {

	 @EJB
	 MonsterTableConnector monsterTable = new MonsterTableConnector();
	 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
 
		// Setup what kind of content we are returning and the PrintWriter
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();
	 
		try {
		    // Get the value of the 'userID' parameter.
		    String userID = request.getParameter("userID");
		    String monsterID = request.getParameter("monsterID");
		    
		    if (userID == null && monsterID != null) {
		    	// We want a single monster
		    	int monID = Integer.parseInt(monsterID);
		    	getJsonMonster(monsterTable.getMonster(monID)).write(out);
		    } else if (userID != null && monsterID == null) {
				// We want all the monsters of this user
		    	int uID = Integer.parseInt(userID);
		    	getJsonMonsters(uID).write(out);
			} else {
			    // User was not found. Send a Bad Request HTTP error code.
			    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User not found");
			}
		} catch (JSONException ex) {
		    // A JSONException may occur if any malformed data is passed
		    // to the producers.
		    System.err.println("JSON Exception:");
		    System.err.println(ex);
		    response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
		} finally {
		    out.close();
		}
    }
 
    /**
     * Constructs a user as a JSONObject
     * 
     * @param user - player to get
     * @return player as a JSONObject
     * @throws JSONException
     */
    private JSONObject getJsonMonster(Monster mon) throws JSONException {
		JSONObject jsonUser = new JSONObject();
		jsonUser.put("monsterID", Integer.toString(mon.id));
		jsonUser.put("userID", Integer.toString(mon.ownerID));
		jsonUser.put("baseStrength", mon.strength);
		jsonUser.put("currentStrength",mon.getHealth());
		jsonUser.put("baseDefence", mon.toughness);
		jsonUser.put("currentDefence", mon.getToughness());
		jsonUser.put("baseHealth", mon.health);
		jsonUser.put("currentHealth", mon.getHealth());
		jsonUser.put("birthDate", (int) mon.birth_date.getTime());
		//jsonUser.put("lifespan", (int) (mon.death_date.getTime()-mon.birth_date.getTime()));
		jsonUser.put("breedOffer", mon.breed_offer);
		jsonUser.put("saleOffer", mon.sale_offer);
		return jsonUser;
    }
 
    /**
     * Returns all the monsters belonging to a user in our database
     * 
     * @return a JSONArray of monsters
     * @throws JSONException
     */
    private JSONArray getJsonMonsters(int ownerID) throws JSONException {
		JSONArray jsonMonsters = new JSONArray();

		for(Monster mon: monsterTable.getMonsters(ownerID)) {
			jsonMonsters.put(getJsonMonster(mon));
		}
		
		return jsonMonsters;
    }
}

