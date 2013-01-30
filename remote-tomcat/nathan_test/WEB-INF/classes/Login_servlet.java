/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import DBManager.PlayerTableConnector;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adriangawryszewski
 */
@WebServlet(name = "Login_servlet", urlPatterns = {"/Login_servlet"})
public class Login_servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login_servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login_servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
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
        processRequest(request, response);
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        PlayerTableConnector ptc = new PlayerTableConnector();
        if (ptc != null) {
            if (!email.equals("") && !password.equals("")) {// right or wrong? ->QA             
                    if(ptc.login(email, password)==1){
                    processRequest(request,response);     
                    }else{
                       String error = "Wrong email or password";
                    request.setAttribute("message", error);
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    }
            } else {
                String error = "Please enter email and password.";
                request.setAttribute("message", error);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } else {
            String error = "No connection to DB";
            request.setAttribute("message", error);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
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
