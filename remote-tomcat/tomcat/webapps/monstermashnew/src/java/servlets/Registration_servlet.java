/*
 * 
 * 
 * 
 * Things to do:
 * ->Client side val (email, password length?)->
 * ->Display message if account already exists. ->
 * ->Password encrypting ->
 * \
 * 
 * 

 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Monster_classes.Player;
import DBManager.PlayerTableConnector;

/**
 *
 * @author adriangawryszewski
 */
@WebServlet(name = "Registration_servlet", urlPatterns = {"/Registration_servlet"})
public class Registration_servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>It works</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    /**
     * // <editor-fold defaultstate="collapsed" desc="HttpServlet methods.
     * Click on the + sign on the left to edit the code."> /** Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("email");
        String password = request.getParameter("password");
        String cname = request.getParameter("cemail");
        String cpassword = request.getParameter("cpassword");
        PlayerTableConnector ptc = new PlayerTableConnector();
        if (ptc != null) {
            if (name != null && cname != null) {
                if (name.equals(cname)) {
                    if (password != null && cpassword != null) {
                        if (password.equals(cpassword)) {
                            ptc.registerPlayer(name, password);
                            String cong = "Registration was succesfull. Please log in.";
                            request.setAttribute("message", cong);
                            request.getRequestDispatcher("/index.jsp").forward(request, response);
                        } else {
                            String error = "Please enter the same value in password and repeat password fields.";
                            request.setAttribute("message", error);
                            request.getRequestDispatcher("/registration.jsp").forward(request, response);
                        }
                    } else {
                        String error = "Please enter both password and repeat password fields.";
                        request.setAttribute("message", error);
                        request.getRequestDispatcher("/registration.jsp").forward(request, response);
                    }
                } else {
                    String error = "Please enter the same value in email and repeat email fields.";
                    request.setAttribute("message", error);
                    request.getRequestDispatcher("/registration.jsp").forward(request, response);
                }
            } else {
                String error = "Please enter both email and repeat email fields!";
                request.setAttribute("message", error);
                request.getRequestDispatcher("/registration.jsp").forward(request, response);
            }
        } else {
            String error = "No connection do DB!";
            request.setAttribute("message", error);
            request.getRequestDispatcher("/registration.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
