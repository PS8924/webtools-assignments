/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.assignment2;

import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import static org.apache.poi.ss.usermodel.CellType.BOOLEAN;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;

/**
 *
 * @author pankt
 */
@WebServlet(name = "ServletPart5", urlPatterns = {"/readXlsFile.xls"})
public class ServletPart5 extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
        //creating ServletContext object  
        ServletContext context=getServletContext(); 
        
        //Getting the value of the initialization parameter and printing it  
        String csvPath=context.getInitParameter("excelFile");
        
        try (PrintWriter out = response.getWriter()) {
            
            String filepath= csvPath + "store.xls";
           // String excelPath = "/Users/kinjalthakkar/Documents/Kinjal/Assignment2/src/main/java/com/neu/assignment2/part4/store.xls";
          
            FileInputStream  fileInputStream = new FileInputStream(filepath);
            HSSFWorkbook hSSFWorkbook = new HSSFWorkbook(fileInputStream);
                        
            HSSFSheet sheetOne = hSSFWorkbook.getSheetAt(0);
            HSSFSheet sheetTwo = hSSFWorkbook.getSheetAt(1);
            HSSFSheet sheetThree = hSSFWorkbook.getSheetAt(2);

            int rows = sheetOne.getLastRowNum();  //get number of rows
            int cols = sheetOne.getRow(1).getLastCellNum();  //get number of columns
            
            int rowsTwo = sheetTwo.getLastRowNum();  //get number of rows
            int colsTwo = sheetTwo.getRow(1).getLastCellNum();  //get number of columns
            
            int rowsThree = sheetThree.getLastRowNum();  //get number of rows
            int colsThree = sheetThree.getRow(1).getLastCellNum();  //get number of columns
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletPart5</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<table border='1'");
            
            for(int r = 0; r < rows ; r ++) {
                out.println("<tr>");
                HSSFRow hSSFRow = sheetOne.getRow(r);
                
                //represents columns
                for(int c = 0 ; c < cols; c ++){
                    HSSFCell hSSFCell = hSSFRow.getCell(c);
                    
                    if(r  == 0){
                        out.println("<th>"+ hSSFCell.getStringCellValue() + "</td>");
                    }
                    else{
                    
                        switch(hSSFCell.getCellType()){
                            case STRING : out.println("<td>"+ hSSFCell.getStringCellValue() + "</td>");
                            break;

                            case NUMERIC : out.println("<td>"+hSSFCell.getNumericCellValue()+ "</td>");
                            break;

                            case BOOLEAN : out.println("<td>"+hSSFCell.getBooleanCellValue()+ "</td>");
                            break;
                        }
                    }
                }
               
            } out.println("</table>");
            
            
            
            out.println("<table border='1'>");
            for(int r = 0; r < rowsTwo ; r ++) {
                out.println("<tr>");
                HSSFRow hSSFRow = sheetTwo.getRow(r);
                
                //represents columns
                for(int c = 0 ; c < colsTwo; c ++){
                    HSSFCell hSSFCell = hSSFRow.getCell(c);
                    
                    if(r  == 0){
                        out.println("<th>"+ hSSFCell.getStringCellValue() + "</td>");
                    }
                    else{
                    
                        switch(hSSFCell.getCellType()){
                            case STRING : out.println("<td>"+ hSSFCell.getStringCellValue() + "</td>");
                            break;

                            case NUMERIC : out.println("<td>"+hSSFCell.getNumericCellValue()+ "</td>");
                            break;

                            case BOOLEAN : out.println("<td>"+hSSFCell.getBooleanCellValue()+ "</td>");
                            break;
                        }
                    }
                }
               
            }
            
            out.println("</table>");
            
            out.println("<table border='1'>");
            for(int r = 0; r < rowsThree ; r ++) {
                out.println("<tr>");
                HSSFRow hSSFRow = sheetThree.getRow(r);
                
                //represents columns
                for(int c = 0 ; c < colsThree; c ++){
                    HSSFCell hSSFCell = hSSFRow.getCell(c);
                    
                    if(r  == 0){
                        out.println("<th>"+ hSSFCell.getStringCellValue() + "</td>");
                    }
                    else{
                    
                        switch(hSSFCell.getCellType()){
                            case STRING : out.println("<td>"+ hSSFCell.getStringCellValue() + "</td>");
                            break;

                            case NUMERIC : out.println("<td>"+hSSFCell.getNumericCellValue()+ "</td>");
                            break;

                            case BOOLEAN : out.println("<td>"+hSSFCell.getBooleanCellValue()+ "</td>");
                            break;
                        }
                    }
                }
               
            }
            
            out.println("</table>");
            
            out.println("</body>");
            out.println("</html>");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
