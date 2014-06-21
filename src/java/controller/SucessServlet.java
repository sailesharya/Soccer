/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.League;


/**
 *
 * @author Pratik_Mishra
 */
public class SucessServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        generateView(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        generateView(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private void generateView(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            String pageTitle = "Duke's Soccer League: Add League Success";
            // Retrieve the 'league' from the request-scope by calling getAttribute method.
            League league = (League) request.getAttribute("league");
            // Specify the content type is HTML
            response.setContentType("text/html");
            out = response.getWriter();
            // Generate the HTML response
            out.println("<html>");
            out.println("<head>");
            out.println("  <title>" + pageTitle + "</title>");
            out.println("</head>");
            out.println("<body bgcolor='white'>");
            // Generate page heading
            out.println("<!-- Page Heading -->");
            out.println("<table border='1' cellpadding='5' cellspacing='0' width='400'>");
            out.println("<tr bgcolor='#CCCCFF' align='center' valign='center' height='20'>");
            out.println("  <td><h3>" + pageTitle + "</h3></td>");
            out.println("</tr>");
            out.println("</table>");
            // Generate main body
            out.println("<p>");
            out.print("Your request to add the ");
            out.print("<i>" + league.getTitle() + "</i>");
            out.println(" league was successful.");
            out.println("</p>");
            out.println("</body>");
            out.println("</html>");
        } catch (IOException ex) {
            Logger.getLogger(SucessServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }
}
