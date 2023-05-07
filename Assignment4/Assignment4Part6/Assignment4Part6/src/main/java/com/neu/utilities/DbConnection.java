/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.neu.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}