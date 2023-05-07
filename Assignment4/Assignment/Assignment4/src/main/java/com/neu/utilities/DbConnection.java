/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.neu.utilities;

import com.neu.model.Books;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author pankt
 */
public class DbConnection {
    public Connection initializeDatabase() throws SQLException, ClassNotFoundException
    {
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql:// localhost:3306/";
        // Database name to access
        String dbName = "moviedb";
        String dbUsername = "root";
        String dbPassword = "PanktiShah@1992";
        Class.forName(dbDriver);
        Connection con = DriverManager.getConnection(dbURL + dbName,
                                                     dbUsername, 
                                                     dbPassword);
        return con;
    }
    
    public void insert(String query, String dbName) throws SQLException, ClassNotFoundException{
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql:// localhost:3306/";
        // Database name to access
        String dbUsername = "root";
        String dbPassword = "PanktiShah@1992";
        Class.forName(dbDriver);
        try{
           Connection con = DriverManager.getConnection(dbURL + dbName,
                                                     dbUsername, 
                                                     dbPassword);
           Statement stmt = con.createStatement();
           stmt.executeUpdate(query);
        } catch (SQLException e) {
           e.printStackTrace();
        } 
    }
    
    public ResultSet update(String query, String dbName) throws ClassNotFoundException{
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql:// localhost:3306/";
        // Database name to access
        String dbUsername = "root";
        String dbPassword = "PanktiShah@1992";
        Class.forName(dbDriver);
        try{
           Connection con = DriverManager.getConnection(dbURL + dbName,
                                                     dbUsername, 
                                                     dbPassword);
           Statement stmt = con.createStatement();
           ResultSet set =  stmt.executeQuery(query);
           return set;
        } catch (SQLException e) {
           e.printStackTrace();
        } 
        return null;
    }
    
    public void addBooks( List<Books> entities,String dbName) throws ClassNotFoundException, SQLException{
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql:// localhost:3306/";
        // Database name to access
        String dbUsername = "root";
        String dbPassword = "PanktiShah@1992";
        Class.forName(dbDriver);
        
        String query = "INSERT INTO booksdb.books (isbn,title,authors,price) VALUES (?,?,?,?)";
        
        try (
            Connection connection = DriverManager.getConnection(dbURL + dbName,dbUsername, dbPassword);
            PreparedStatement statement = connection.prepareStatement(query);
        ) {
            int i = 0;

            for (Books book : entities) {
                statement.setString(1, book.getIsbn());
                statement.setString(2, book.getTitle());
                statement.setString(3, book.getAuthors());
                statement.setFloat(4, book.getPrice());
                statement.addBatch();
                i++;
                
                if (i % 1000 == 0 || i == entities.size()) {
                    statement.executeBatch(); // Execute every 1000 items.
                }
            }
        }
    }
}
