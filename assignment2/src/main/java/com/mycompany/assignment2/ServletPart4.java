/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.assignment2;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Logger;
import java.util.logging.Level;
import org.relique.jdbc.csv.CsvDriver;

/**
 *
 * @author pankt
 */
public class ServletPart4 extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
       
        String fileName = request.getParameter("fileName");
        String filePath = getServletContext().getInitParameter("csvFile");
        
        
        try (PrintWriter out = response.getWriter()) {
            
            Class.forName("org.relique.jdbc.csv.CsvDriver");
            Properties props = new Properties();
            props.put("separator", ",");
            
            Connection conn = DriverManager.getConnection("jdbc:relique:csv:" + filePath, props);
            Statement stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * FROM " + fileName);
            
            ResultSetMetaData meta = results.getMetaData();
            
            
             /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletPart4</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>Displaying data of " + fileName + "</h3>");
            
            List<List<String>> result = new ArrayList<>();

            for(int i = 0;i<meta.getColumnCount();i++){
                result.add(new ArrayList<String>());
                result.get(i).add(meta.getColumnName(i+1));
            }

            while (results.next()) {
                for(int i = 0;i<meta.getColumnCount();i++){
                    result.get(i).add(results.getString(i+1));
                }
            }
            
            if(result != null){
                out.println("<table border='1'>");
                for(int i = 0; i< result.get(0).size();i++){
                    out.print("<tr>");
                    for(int j = 0; j < result.size();j++)
                        if(i == 0)
                            out.print("<th>"+result.get(j).get(i)+"</th>");
                        else
                            out.print("<td>"+result.get(j).get(i)+"</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            }
            else{
                out.println("<h1>File not found</h1>");
            }
           
            //out.println("<h1>Servlet ServletPart4 at " + meta + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
            results.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
        
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
        try{
            processRequest(request, response);
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
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
        try{
            processRequest(request, response);
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
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

}
