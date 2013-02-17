import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Authentication extends HttpServlet{
    private ServletConfig config;
    public void go(ServletConfig config)
    throws ServletException{
        this.config = config;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        PrintWriter one = response.getWriter();
        String connectionURL ="www.thehiddennation.com";
        Connection connection = null;
        ResultSet rs;
        String userName = new String("");
        String passwrd = new String("");
        response.setContentType("text/html");
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection - DriverManager.getConnection(connectionURL, "handyman_group07", "3213560921*+*");
            
            String sql = "select user, password from User";
            Statement s = connection.createStatement();
            s.executeQuery(sql);
            rs = s.getResultSet();
            
            while(rs.next()) {
                userName = rs.getString("user");
                passwrd  = rs.getString("password");
            }
            
            rs.close();
            s.close();
        
        } catch(Exception a) {
            System.out.println("Exception is;"+e);
        }
        
        if(userName.equals(request.getParameter("user")) &&
           passwrd.equals(request.getParameter("pass"))){
            System.out.println("User Authenticated");
        }
        else {
            System.out.println("You are not an authentic user");
        }
    }
}