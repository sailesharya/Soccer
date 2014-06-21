/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Pratik_Mishra
 */
public class League {
    public int year;
    public String season;
    public String title;
    
    public League(int year, String season, String title) {
        this.year = year;
        this.season = season;
        this.title = title;
    }
    
    public int getYear() {
        return year;
    }
    public String getSeason() {
        return season;
    }
    public String getTitle() {
        return title;
    }
    
    public String toString() {
        return title;
    }
}
