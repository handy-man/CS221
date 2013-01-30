import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AuthenticationServlet extends HttpServlet{
    private ServletConfig config;
    public void go(ServletConfig config)
    throws ServletException{
        this.config = config;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        PrintWriter one = response.getWriter();
        String connectionURL ="jdbc:mysql://74.53.183.226/handyman_monster";
        Connection connection = null;
        ResultSet rs;
        String userName = new String("");
        String passwrd = new String("");
        response.setContentType("text/html");
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionURL, "handyman_group07", "3213560921*+*");
            
           // String sql = "SELECT * FROM `player` WHERE 1";
            //String sql = "INSERT into 'player' VALUES ";
            String sql = "INSERT INTO `player` (`email` ,`password` ,`money`)VALUES ('testing',  'reallytestgin',  '500')";
            Statement s = connection.createStatement();
            //s.executeQuery(sql);
            s.executeUpdate(sql);

            rs = s.getResultSet();
            /*
            while(rs.next()) {
                userName = rs.getString("email");
                passwrd  = rs.getString("password");
            }
            
            rs.close();
            */
            s.close();
        
        } catch(Exception a) {
         	PrintWriter out = response.getWriter();
        	out.println("exception" + a);
        }
        
        if(userName.equals(request.getParameter("user")) &&
           passwrd.equals(request.getParameter("pass"))){
        	PrintWriter out = response.getWriter();
        	out.println("user authenticated");
        	}
        else {
        	PrintWriter out = response.getWriter();
        	out.println("user not authenticated \n");
        //	out.println(userName);
        //	out.println(passwrd);

        	
        }
    }
}