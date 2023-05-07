/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.neu.controllers;

import com.neu.model.Books;
import com.neu.model.Computer;
import com.neu.model.Music;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author pankt
 */
@Controller
public class ShoppingController {
    Hashtable users = new Hashtable();
    List<Books> books;
    List<Music> music;
    List<Computer> computer;
    
    public ShoppingController(){
        users.put("pankti:1234","allowed");
    }
    
    @RequestMapping(value = "/shopping.htm", method = RequestMethod.GET)
    public ModelAndView doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
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
             
                return new ModelAndView("shoppingStore" );
                 
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
                return new ModelAndView("viewCart" );
             }
         }           
         else if(!allowUser(auth)) {
              response.setHeader("WWW-Authenticate", "BASIC realm=\"users\"");
              response.sendError(response.SC_UNAUTHORIZED);
         } 
         return null;
    }   
    
    @RequestMapping(value = "/shopping.htm", method = RequestMethod.POST)
    public ModelAndView doPost(HttpServletRequest request, HttpServletResponse response){
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
            
            return new ModelAndView("shoppingStatus" );
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
            
            return new ModelAndView("shoppingStatus" );
        }
        return null;
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