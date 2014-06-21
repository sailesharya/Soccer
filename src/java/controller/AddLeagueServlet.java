/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.League;
import java.net.URL;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.File;
/**
 *
 * @author Pratik_Mishra
 */
public class AddLeagueServlet extends HttpServlet {

    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List errorMsgs = new LinkedList();
        // Store this set in the request scope, in case we need to
        // send the ErrorPage view.
        request.setAttribute("errorMsgs", errorMsgs);
        
        try {
            
            // Retrieve form parameters.
            String yearStr = request.getParameter("year");
            String season = request.getParameter("season");
            String title = request.getParameter("title");
            
            // Perform data conversions.
            int year = -1;
            try {
                year = Integer.parseInt(yearStr);
            } catch (NumberFormatException nfe) {
                errorMsgs.add("The 'year' field must be a positive integer.");
            }
            
            // Verify form parameters
            if ( (year != -1) && ((year < 2000) || (year > 2010)) ) {
                errorMsgs.add("The 'year' field must within 2000 to 2010.");
            }
            if ( season.equals("UNKNOWN") ) {
                errorMsgs.add("Please select a league season.");
            }
            if ( title.length() == 0 ) {
                errorMsgs.add("Please enter the title of the league.");
            }
            // Send the user back to the AddDVD form, if there were errors
            
            if ( ! errorMsgs.isEmpty() ) {
                RequestDispatcher view
                        = request.getRequestDispatcher("ErrorPageServlet");
                view.forward(request, response);
                return;
            }
            
            // The AddLeagueServlet creates a new League object and then stores that object in the request object.
            League league = new League(year, season, title);
            // Store the new league in the request-scope
            request.setAttribute("league", league);
            
            //Store the new league in the leagueList context-scope attribute.
//            ServletContext context = getServletContext();
//            List leaguelist = (List)context.getAttribute("leaguelist");
//            leaguelist.add("League");
            
//            ServletContext context = getServletContext(); 
//            List leaguelist = new LinkedList();
//            String leaguesfile =context.getInitParameter("leagues-file"); 
//            String leaguefile = "http://localhost:8084/Soccer/WEB-INF/data/leagues.txt";
            BufferedWriter leagueWriter = null;
            
            try{
            String classpath = "/WEB-INF/data/leagues.txt";
            String absoluteUrl = getServletContext().getRealPath(classpath);
            File file = new File(absoluteUrl);
//            File file = new File("C:\\Users\\Pratik\\Documents\\NetBeansProjects\\Soccer\\web\\WEB-INF\\data\\leagues.txt");
            leagueWriter = new BufferedWriter(new FileWriter(file,true));
            leagueWriter.write("\n");
            leagueWriter.write(yearStr);
            leagueWriter.write(",");
            leagueWriter.write(season);
            leagueWriter.write(",");
            leagueWriter.write(title);
            
            }
            catch(Exception e){
            System.err.println(e);
            //Clean up IO resources
            }
            finally{
                if(leagueWriter != null){
                 try{leagueWriter.close();}catch(Exception e){System.err.println(e);}
            }}
            
            /* AddLeagueServlet then forwards to the SuccessServlet. It calls the getRequestDispatcher method on the request
             * object. The method tells the web container to create return a javax.servlet.RequestDispatcher object.
             */
            RequestDispatcher view
                    = request.getRequestDispatcher("SucessServlet");
            view.forward(request, response);
            return;
            
            // Handle any unusual exceptions
            } catch (Exception e) {
            errorMsgs.add(e.getMessage());
            RequestDispatcher view
                    = request.getRequestDispatcher("AddLeagueServlet");
            view.forward(request, response);
            
            // Log stack trace
            e.printStackTrace(System.err);
            
        } // END of try-catch block
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
