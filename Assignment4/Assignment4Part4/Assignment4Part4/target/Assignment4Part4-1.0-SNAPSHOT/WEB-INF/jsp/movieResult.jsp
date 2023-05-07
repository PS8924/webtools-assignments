<%-- 
    Document   : searchMovies
    Created on : Feb 17, 2023, 3:39:05 PM
    Author     : pankt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movie Data</title>
    </head>
    <body>
        <h3>You Searched for ${keyword}</h3>
        <h4><b>Here are the search results!</b></h4>
        
        <label>Movie Title: ${movie.title}</label><br><br>
        <label>Lead Actor: ${movie.actor}</label><br><br><!-- comment -->
        <label>Lead Actress: ${movie.actress}</label><br><br>
        <label>Genre: ${movie.genre}</label><br><br>
        <label>Year: ${movie.year}</label><br><br>
        
        <a href="movies.htm">Click Here to go back!</a>
        </body>
</html>