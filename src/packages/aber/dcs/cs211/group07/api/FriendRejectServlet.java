package aber.dcs.cs211.group07.api;

import java.io.PrintWriter;

@WebServlet(name = "FriendRejectServlet", urlPatterns = {"/friends/reject"})
public class FriendRejectServlet extends HttpServlet {
	
	@Override
	  protected void doGet(HttpServletRequest request, HttpServletResponse response)
			    throws ServletException, java.io.IOException {
			        
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
			  }
			}
}
