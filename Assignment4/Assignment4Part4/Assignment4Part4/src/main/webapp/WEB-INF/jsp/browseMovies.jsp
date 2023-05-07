<%-- 
    Document   : browseMovies
    Created on : Feb 16, 2023, 10:01:55 PM
    Author     : pankt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Movies</title>
    </head>
    <body>
        <h1>Searching Movies!</h1>
        <form action="" method="post">
            <label for="keyword">Keyword:</label>
            <input type="text" name="searchMovieName"><br><br>

            <input type="radio" name="searchMovie" value="title">
            <label for="STitle">Search By Title:</label><br>
            <input type="radio" name="searchMovie" value="actor">
            <label for="SActor">Search By Actor:</label><br>
            <input type="radio" name="searchMovie" value="actress">
            <label for="SActress">Search By Actress:</label><br><br><!-- comment -->
            <input type="submit" value="Search Movies"/>
            <input type="hidden" name="action-selection" value="searchMovie">
        </form>
        </body>
</html>