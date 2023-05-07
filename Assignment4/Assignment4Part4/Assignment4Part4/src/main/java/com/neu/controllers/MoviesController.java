/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/AbstractController.java to edit this template
 */
package com.neu.controllers;

import com.neu.model.Movie;
import com.neu.utilities.DbConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author pankt
 */
public class MoviesController extends AbstractController {
    
    DbConnection db = new DbConnection();
    
    public MoviesController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if(request.getMethod().equals("GET")){
          return  doGet(request, response);
        }else {
            return doPost(request, response);
        }
    }
    private ModelAndView doGet(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("movies");        
    }
        
    private ModelAndView doPost(HttpServletRequest request, HttpServletResponse response) {
        //get parameter
        String name = request.getParameter("action-selection");
        
        if(name.equals("browse")){
            return new ModelAndView("browseMovies" ); 
        }else if(name.equals("add")){
            return new ModelAndView("addMovies" );  
        }else if(name.equals("submitMovie")){
            
            String movieName = request.getParameter("mname");
            String actor = request.getParameter("actor");
            String actress = request.getParameter("actress");
            String genre = request.getParameter("genre");
            int year = Integer.parseInt(request.getParameter("year"));
            
            String query = "INSERT INTO moviedb.movies (title, actor, actress, genre, year) VALUES ("+"'"+movieName+"','"+actor+"','"+actress+"','"+genre+"',"+year+")";
            
            // add movies to database logic
            try{
            db.insert(query, "moviedb");
            return new ModelAndView("movieMessage"); 
            }catch (SQLException ex) {
                Logger.getLogger(MoviesController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MoviesController.class.getName()).log(Level.SEVERE, null, ex);
            }
              
        }else if(name.equals("searchMovie")){
            try{
                String keyword = request.getParameter("searchMovieName");
                String choice = request.getParameter("searchMovie");
            
                String query = "SELECT * FROM moviedb.movies WHERE " + choice + " = '" + keyword + "';";
                
                // add movies to database logic
                ResultSet rs = db.update(query, "moviedb");
                Movie mv = new Movie();
                //storing data in movie object
                try {
                    while(rs.next()){
                        String title = rs.getString("title");
                        mv.setTitle(title);
                        
                        String actor = rs.getString("actor");
                        mv.setActor(actor);
                        
                        String actress = rs.getString("actress");
                        mv.setActress(actress);
                        
                        String genre = rs.getString("genre");
                        mv.setGenre(genre);
                        
                        int year = rs.getInt("year");
                        mv.setYear(year);
                    }
                }catch (SQLException ex) {
                    Logger.getLogger(MoviesController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //set the movie object in request scope
                request.setAttribute("movie", mv);
                request.setAttribute("keyword", keyword);
                return new ModelAndView("movieResult"); 
            }catch (ClassNotFoundException ex) {
                Logger.getLogger(MoviesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}