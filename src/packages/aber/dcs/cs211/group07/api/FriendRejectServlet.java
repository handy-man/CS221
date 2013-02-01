package aber.dcs.cs211.group07.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A servlet to handle friend rejects
 * 
 * @author Dan Cornwell
 *
 */

@SuppressWarnings("serial")
@WebServlet(name = "FriendRejectServlet", urlPatterns = {"/friends/reject"})
public class FriendRejectServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		response.setContentType("text/plain;charset=UTF-8");
	}
}
