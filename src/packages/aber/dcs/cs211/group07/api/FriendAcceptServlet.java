package aber.dcs.cs211.group07.api;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * A servlet to handle friend accepts
 * 
 * @author Dan Cornwell
 *
 */

@WebServlet(name = "FriendshipAcceptServlet", urlPatterns = {"/friends/accept"})
public class FriendAcceptServlet extends HttpServlet {
	
	 @EJB
	 
	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
	 
	    response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();

		
		response.write(out);
		
}
	 
}
