/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.assignment3Controller;

import com.mycompany.model.Books;
import com.mycompany.utilities.DbConnection;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pankt
 */
public class BooksController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        redirect( request, response, "/Books.jsp" );
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
        String name = request.getParameter("action-selection");
        
        if(name.equals("count")){
            int numberOfBooks = Integer.parseInt(request.getParameter("bookNumber"));
            request.setAttribute("count", numberOfBooks);
            redirect( request, response, "/addBooks.jsp" );
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
                redirect( request, response, "/booksResult.jsp");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
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
}