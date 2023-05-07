/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.assignment3Controller;

import com.mycompany.utilities.DbConnection;
import com.mycompany.model.Movie;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;


import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pankt
 */
public class MoviesController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("welcomeText", "Welcome to movie store!");
        redirect( request, response, "/movies.jsp" );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DbConnection db = new DbConnection();
        
        //get parameter
        String name = request.getParameter("action-selection");
        
        if(name.equals("browse")){
            redirect( request, response, "/browseMovies.jsp" ); 
        }else if(name.equals("add")){
            redirect( request, response, "/addMovies.jsp" );  
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
            redirect( request, response, "/movieMessage.jsp"); 
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
                redirect( request, response, "/movieResult.jsp"); 
            }catch (ClassNotFoundException ex) {
                Logger.getLogger(MoviesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    protected void redirect(HttpServletRequest request, HttpServletResponse response, String url)throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
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