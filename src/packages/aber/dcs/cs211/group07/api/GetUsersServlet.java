package aber.dcs.cs211.group07.api;

import aber.dcs.cs211.group07.data.Player;
import aber.dcs.cs211.group07.db.PlayerTableConnector;

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

/**
 * A servlet that handles a request for our users
 * 
 * @author Dan Cornwell
 *
 */

@SuppressWarnings("serial")
@WebServlet(name = "Users", urlPatterns = {"/users"})
public class GetUsersServlet extends HttpServlet {
	
	 @EJB
	 PlayerTableConnector playerTable = new PlayerTableConnector();
	 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
 
    	response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();
	 
		try {
		
			// Get the value of the 'userID' parameter 
		    String userID = request.getParameter("userID");
		    // Change to an integer as thats what our unique IDs are
		    int numberUserID = Integer.parseInt(userID);
		    
		    // If no user ID specified then reponse with a list of all users
		    if (userID == null) {
			getJsonUsers().write(out);
		    } else {
			// Find the user object with the specified userId
			Player user = playerTable.getPlayer(numberUserID);	
			if (user != null) {
			    // User was found. Write it out in JSON
			    getJsonUser(user).write(out);
			} else {
			    // User was not found. Send a Bad Request HTTP error code.
			    response.sendError(HttpServletResponse.SC_BAD_REQUEST,
				    "User not found");
			}
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
    private JSONObject getJsonUser(Player user) throws JSONException {
		JSONObject jsonUser = new JSONObject();
		jsonUser.put("userID", user.id);
		jsonUser.put("name", user.email);
		jsonUser.put("money", user.money);
		return jsonUser;
    }
 
    /**
     * Returns all the players in our database
     * 
     * @return all players in a JSONArray
     * @throws JSONException
     */
    private JSONArray getJsonUsers() throws JSONException {
		JSONArray jsonUsers = new JSONArray();
	
		for(Player user: playerTable.getAllPlayers()) {
			jsonUsers.put(getJsonUser(user));
		}
		
		return jsonUsers;
    }
}
