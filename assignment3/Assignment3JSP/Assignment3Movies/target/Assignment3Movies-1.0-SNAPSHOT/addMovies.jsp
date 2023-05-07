<%-- 
    Document   : addMovies
    Created on : Feb 16, 2023, 9:47:33 PM
    Author     : pankt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Movies Form</title>
    </head>
    <body>
        <h1>Please enter the details below!</h1>
        <div class="container">
            <form method="post" action="MoviesController" name="addMovies">
                <label for="movieName">Movie name:</label><br>
                <input type="text" id="mname" name="mname"><br><br>
                
                <label for="lname">Lead Actor:</label><br>
                <input type="text" id="actor" name="actor"><br><br>
                
                <label for="lname">Lead Actress:</label><br>
                <input type="text" id="actress" name="actress"><br><br>
                
                <label for="lname">Genre:</label><br>
                <input type="text" id="genre" name="genre"><br><br>
                
                <label for="lname">Year:</label><br>
                <input type="text" id="year" name="year"><br> <br>
                
                <input type="hidden" name="action-selection" value="submitMovie"><!-- comment -->
                <input type="submit" value="Add Movies"/>
            </form>
        </div>
    </body>
</html>