package aber.dcs.cs211.group07.api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aber.dcs.cs211.group07.db.FriendsTableConnector;
import aber.dcs.cs211.group07.db.PlayerTableConnector;
import aber.dcs.cs211.group07.db.TableConnector;

/**
 * A servlet that handles a friend request from another player
 * 
 * @author Dan Cornwell
 *
 */
@SuppressWarnings("serial")
@WebServlet(name = "FriendshipRequestServlet", urlPatterns = {"/friends/request"})
public class FriendshipRequestServlet extends HttpServlet {

	@EJB

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/plain;charset=UTF-8");

		//Check url parameters are correct, throw error if they are not
		Map<String, String[]> params = request.getParameterMap();

		String[] requiredParams = new String[] {"friendID", "localUserID", "remoteUserID", "remoteServerID"};
		if (!params.keySet().containsAll(Arrays.asList(requiredParams))) {
			response.sendError(
					HttpServletResponse.SC_BAD_REQUEST,
					"Parameter set incorrect");
		} else {
			TableConnector db = new TableConnector();
			try {
				db.statement.executeUpdate("INSERT INTO 'friend_request' VALUES("+params.get("friendID")+ "','" + 
						params.get("localUserID") + "','" + params.get("remoteUserID") + "','" + params.get("remoteServerID"));
			} catch (SQLException e) {
				TableConnector.complain(e);
			}
			PlayerTableConnector playerTable = new PlayerTableConnector();
			// Check that the localUserId actually exists on our server
			if (playerTable.getPlayer(Integer.parseInt(request.getParameter("localUserID")))!=null) {

				FriendsTableConnector friendTable = new FriendsTableConnector();
				boolean successs = friendTable.addFriend(
						request.getParameter("friendID"),
						request.getParameter("localUserID"), 
						request.getParameter("remoteUserID"),
						request.getParameter("removeServerID"));
				if(successs==true) {
					request.setAttribute("friendID", request.getParameter("friendID"));
				} else {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Incorrect parameters");
				}
				friendTable.close();
			} else {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Local user ID does not exist");
			}
			playerTable.close();
		}
	}
}

