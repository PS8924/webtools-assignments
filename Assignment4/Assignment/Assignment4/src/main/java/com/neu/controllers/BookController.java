/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/AbstractController.java to edit this template
 */
package com.neu.controllers;

import com.neu.model.Books;
import com.neu.utilities.DbConnection;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class BookController extends AbstractController {
    
    public BookController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        DbConnection db = new DbConnection();
        String name = request.getParameter("action-selection");
        System.out.println(name);
        if(name!= null){
            if(name.equals("count")){
                int numberOfBooks = Integer.parseInt(request.getParameter("bookNumber"));
                request.setAttribute("count", numberOfBooks);
                return new ModelAndView("BooksAdd");
            }else if(name.equals("add-books")){
            
                String[] isbn = request.getParameterValues("isbn");
                String[] title = request.getParameterValues("title");
                String[] author = request.getParameterValues("author");
                String[] price = request.getParameterValues("price");
            
                // Create books
                List<Books> books = new ArrayList<>();
                for(int i = 0; i < price.length; i++){
                    Books book = new Books();
                    book.setIsbn(isbn[i]);
                    book.setAuthors(author[i]);
                    book.setPrice(Float.parseFloat(price[i]));
                    book.setTitle(title[i]);
                    books.add(book);
                }
            
                try {
                    db.addBooks(books, "moviedb");
                    // Place in scope
                    request.setAttribute("count", price.length);
                    return new ModelAndView("BooksResult","book", books);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            return new ModelAndView("Books");
        }
        return new ModelAndView("Books");
    }
}