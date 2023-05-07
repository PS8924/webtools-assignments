<%-- 
    Document   : movies
    Created on : Feb 16, 2023, 8:39:54 PM
    Author     : pankt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movies</title>
    </head>
    <body>
        <h1>Welcome to Movies</h1>
        <h3>Please make your selection below!</h3>
        <div class="container">
            <form method="post" action="movies.htm">
                <select name="action-selection">
                    <option value="browse">Browse movies</option>
                    <option value="add">Add New Movies to database</option>
                </select>
                <input type="submit"/>
            </form>
        </div>
    </body>
</html>