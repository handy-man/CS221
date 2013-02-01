package aber.dcs.cs211.group07.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A servlet to handle friend accepts
 * 
 * @author Dan Cornwell
 *
 */

@SuppressWarnings("serial")
@WebServlet(name = "FriendshipAcceptServlet", urlPatterns = {"/friends/accept"})
public class FriendAcceptServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain;charset=UTF-8");
		// PrintWriter out = response.getWriter();
	}
}
