package aber.dcs.cs211.group07.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import aber.dcs.cs211.group07.db.FriendsTableConnector;
import aber.dcs.cs211.group07.db.PlayerTableConnector;
 
/**
 * This is an example of a servlet which serves the /friends/request resource.
 * Feel free to take this example and adapt it to your application's data model.
 *
 * @author James Bowcott
 *
 */
@WebServlet(name = "FriendshipRequestServlet", urlPatterns = {"/friends/request"})
public class FriendshipRequestServlet extends HttpServlet {
 
    @EJB
 
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
	String[] requiredParams = new String[] {"friendID", "localUserID", "remoteUserID", "remoteServerID"};
	if (!params.keySet().containsAll(Arrays.asList(requiredParams))) {
	    response.sendError(
		    HttpServletResponse.SC_BAD_REQUEST,
		    "Parameter set incorrect");
	} else {
		//add to friend database
		
		//Enter address of database being used
		String host = "jdbc:mysql://74.53.183.226/handyman_monster";
		//Enter username (usually root?)
		String userName = "handyman_group07";
		//Enter password
		String password = "3213560921*+*";
		
		//Creates a connection to the database and a statement
		Connection connection = DriverManager.getConnection(host,userName,password);
		Statement statement = connection.createStatement();
		
		statement.executeUpdate("INSERT INTO 'friend_request' VALUES("+params.get("friendID")+ "','" + 
				params.get("localUserID") + "','" + params.get("remoteUserID") + "','" + params.get("remoteServerID"));
		/*
		PlayerTableConnector playerTable = new PlayerTableConnector();
	    // Check that the localUserId actually exists on our server
	    if (playerTable.getPlayer(Integer.parseInt((String) params.get("localUserID")))!=null) {
	    	
	    	FriendsTableConnector friendTable = new FriendsTableConnector();
	    	if(friendTable.addFriend((String)params.get("friendID"),(String) params.get("localUserID"),(String) params.get("remoteUserID"),(String) params.get("removeServerID"))==true) {

	    	//	request.setAttribute("friendID",(String)params.get("friendID"));
	    		
	    	}
	    	else {
			    HttpServletResponse.SC_BAD_REQUEST,
			    "Parameter set incorrect");
	    	}
	    	friendTable.close();
	    } 
	    
	    else {
		response.sendError(
			HttpServletResponse.SC_BAD_REQUEST,
			"Local user ID does not exist");
	    }
	    playerTable.close();
	}
	*/
    	}
    }
 
}
    
