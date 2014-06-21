/**
 *
 * @author Pratik
 */
package web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
//support classes
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
//model classes
import model.League;
import java.util.List;
import java.util.LinkedList;

public class InitializeLeagues implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext(); /*The method retrieves the context object from the event argument. */
        List leaguelist = new LinkedList();
        String leaguesfile =context.getInitParameter("leagues-file"); /*The leagues-file context parameter is retrieved.THis parameter is configured in web.xml */
        InputStream is= null;
        BufferedReader reader = null;
        
        /*The next task is to read the data file and create the League objects for each row in the file. */
        
        try{
            is=context.getResourceAsStream(leaguesfile); /*It uses the context object to create an input stream to data file.The getResourceAsStream method on the context object provides read-only access to the data file. */ 
            reader = new BufferedReader(new InputStreamReader(is)); /* It decorates the inputstream with a BufferedReader so that the method can read the data file one row at a time. */
            String record;
            
            //Read every record (one per line)
            while((record = reader.readLine()) != null){
                String[] fields= record.split(",");
                
                //Extract the data fields for the record
                int year = Integer.parseInt(fields[0]);
                String season = fields[1];
                String title= fields[2];
                
                //Add the new League item to the list
                League item = new League(year,season,title);
                leaguelist.add(item);
            }
            context.setAttribute("leaguelist",leaguelist);
            context.log("The leaguelist has been loaded");
            
        }
        catch(Exception e){
            context.log("Exception occured",e);
        }
        finally{
            if(is != null){
                try{
                    is.close();
                }
                catch(Exception e){}
            }
            if(reader != null){
                try{
                    reader.close();
                }
                catch(Exception e){}
            }
            }
        }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}