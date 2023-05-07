/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.assignment3Controller;

import com.mycompany.model.Books;
import com.mycompany.model.Computer;
import com.mycompany.model.Music;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Hashtable;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author pankt
 */
public class ShoppingController extends HttpServlet {

    Hashtable users = new Hashtable();
    List<Books> books;
    List<Music> music;
    List<Computer> computer;

        public void init(ServletConfig config) throws ServletException {
          super.init(config);
          users.put("pankti:1234",     "allowed");             
        }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        redirect( request, response, "/shoppingStore.jsp" );
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String auth = request.getHeader("Authorization");
        HttpSession session = request.getSession();
        
        String name = request.getQueryString();
        
         if(allowUser(auth)) {
             
             if(name == null) {
                 
                 //Store cart in session
                List<String> cart = (List<String>) session.getAttribute("cart") ;
                if(cart == null){
                    cart = new ArrayList<>();
                    session.setAttribute("cart", cart);
                }

                books = new ArrayList<>();

                // added books
                books.add(new Books("Book One",(float)25.36));
                books.add(new Books("Book Two", (float) 17.69));
                books.add(new Books("Book Three",(float)30.25));
                books.add(new Books("Book Four",(float)45.21));

                // Create bean
                session.setAttribute("books", books);
                session.setAttribute("bookscount", books.size());
                
                // Add music
                music = new ArrayList<>();

                music.add(new Music("Music One",12.25));
                music.add(new Music("Music Two",10.36));
                music.add(new Music("Music Three",5.32));
                music.add(new Music("Music Four",6.32));

                // Create bean
                session.setAttribute("music", music);
                session.setAttribute("musiccount", music.size());
                
                // Add Computers
                computer = new ArrayList<>();

                computer.add(new Computer("Laptop One",1750.25));
                computer.add(new Computer("Laptop Two",856.23));
                computer.add(new Computer("Laptop Three",1800.25));
                computer.add(new Computer("Laptop Four",900.25));

                // Create bean
                session.setAttribute("computer", computer);
                session.setAttribute("computercount", computer.size());
             
                redirect( request, response, "/shoppingStore.jsp" );
                 
             }else if(name.equals("submit=ViewCart")){
                List<String> initialCart = new ArrayList<>();
                List<String> cart = (List<String>)session.getAttribute("cart");

                if(cart == null){
                    cart = new ArrayList<>();
                    session.setAttribute("cart", cart);
                }

                if(cart.isEmpty())
                    initialCart.add("NA");
                else
                    for(String prod:cart){
                        if(!prod.equals("NA"))
                            initialCart.add(prod);
                    }

                // Place cart in scope
                session.setAttribute("cart", initialCart);
                session.setAttribute("cartcount", initialCart.size()); 
                redirect( request, response, "/viewCart.jsp" );
             }
         }           
         else if(!allowUser(auth)) {
              response.setHeader("WWW-Authenticate", "BASIC realm=\"users\"");
              response.sendError(response.SC_UNAUTHORIZED);
         } 
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name = request.getParameter("action-selection");
        
        if(name.equals("add-books") || name.equals("add-music") || name.equals("add-computers")){
            String[] products = request.getParameterValues("product");
            List<String> cart = (List<String>)session.getAttribute("cart");
            
            for(String product:products){
                cart.add(product);
            }

            // Place result in scope
            session.setAttribute("items", products);
            session.setAttribute("productCount", products.length);
            
            redirect( request, response, "/shoppingStatus.jsp" );
        }else if(name.equals("remove-product")){
            
            // Remove cart object
            String[] objects = request.getParameterValues("product");
            List<String> cart = (List<String>)session.getAttribute("cart");
            
            for(String val:objects){
                for (Iterator<String> iterator = cart.iterator(); iterator.hasNext(); ) {
                    String value = iterator.next();
                    if (value.equals(val)) {
                        iterator.remove();
                        break;
                    }
                }
            }
            
            // Place cart in session
            session.setAttribute("items", objects);
            session.setAttribute("cartcount", objects.length);
            
            redirect( request, response, "/shoppingStatus.jsp" );
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
     protected void redirect(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, String url)throws jakarta.servlet.ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
     
    protected boolean allowUser(String auth) throws IOException {
    if (auth == null) return false;  // no auth

    if (!auth.toUpperCase().startsWith("BASIC ")) 
      return false;  // we only do BASIC

    // Get encoded user and password, comes after "BASIC "
    String userpassEncoded = auth.substring(6);

   // Decode it, using any base 64 decoder
    byte[] decodedBytes = Base64.getUrlDecoder().decode(userpassEncoded);
    String userpassDecoded = new String(decodedBytes);
    
    // Check our user list to see if that user and password are "allowed"
    if ("allowed".equals(users.get(userpassDecoded)))

      return true;
    else
      return false;
  }
}
