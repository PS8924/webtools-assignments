<%-- 
    Document   : Books
    Created on : Feb 18, 2023, 10:51:54 AM
    Author     : pankt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Books </title>
    </head>
    <body>
        <h1>How many Books you want to add?</h1>
        <form action="BooksController" name="action-selection" method="post">
            <input type="text" name="bookNumber">
            <input type="submit" value="submit">
            <input type="hidden" name="action-selection" value="count">
        </form>
    </body>
</html>