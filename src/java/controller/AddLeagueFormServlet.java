package controller;

/**
 *
 * @author Pratik
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * It generates a dynamically configured "Add a new League" form. It uses a string array called SEASONS to generate the set of 
 * option tags for the season's dropdown list.The SEASONS array must be available before any HTTP request are processed 
 * by this servlet. Therefore this array will be constructed in the init() method of the servlet.
 * 
 * The SEASONS array is populated in the init() method, but the init() method must get the values out of the ServletConfig object.
 * The config object is created by the web container based on the initialisation parameters specified in the deployment
 * descriptor.
 */
public class AddLeagueFormServlet extends HttpServlet {
    
    //Default seasons
    private static final String Default_Seasons = "Spring,Summer,Autumn,Winter";
    
    private String[] SEASONS;
    
    //Dynamically configuring drop-down list by accepting the <option> tag values from <init-param> of web.xml
    @Override
    public void init(){
        String seasons_list = getInitParameter("seasons-list");
        if( seasons_list == null){
            seasons_list = Default_Seasons;
        }
        SEASONS = seasons_list.split(",");
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        generateView(request, response);
        //processRequest(request, response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        generateView(request, response);
        //processRequest(request, response);
    }
    
    public void generateView(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><body><p><b><h3>It is a dynamically configured soccer league form</b></h3></p></br></br>");
        
        // Retrieve the errorMsgs from the request-scope
        List errorMsgs = (List) request.getAttribute("errorMsgs");
        
        // Generate error if present
        if(errorMsgs != null){
        out.println("<p>");
        out.println("<font color='red'>Please correct the following errors:");
        out.println("<ul>");
        Iterator items = errorMsgs.iterator();
        while ( items.hasNext() ) {
            String message = (String) items.next();
            out.println("  <li>" + message + "</li>");
        }
        out.println("</ul>");
        out.println("Try again.");
        out.println("</font>");
        out.println("</p></br></br>");
        }
        
        //Generate Form
        out.println("<form action='AddLeague2Servlet' method='POST'>");
        
        //Display year field
        //Repopulate year field
        String year = request.getParameter("year");
        if( year== null){
            year = "";
        }
        out.println("Year:<input type='text' name='year' value='"+ year + "'/> </br></br>");
        
        
        //Customize season drop-down menu & repopulate the list
        String season = request.getParameter("season");
        
        out.println("Season: <select name='season'>");
        if( season==null || season.equals("UNKNOWN"))
        {
        out.println("<option value='UNKNOWN'>select...</option>");
        }
        for( int i=0; i< SEASONS.length; i++){
            out.println("<option value='"+SEASONS[i]+"'");
            if( SEASONS[i].equals(season)){
                out.print("selected");
            }
            out.println(">"+SEASONS[i]+"</option>");
        }
        out.println("</select></br></br>");
        
        //Display Title field
        out.println("Title: <input type='text' name='title' /> <br/><br/>");
        out.println("<input type='submit' value='Add League' />");
        
        out.println("</form></body></html>");
}
}
