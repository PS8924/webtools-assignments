<%-- 
    Document   : addBooks
    Created on : Feb 18, 2023, 11:08:42 AM
    Author     : pankt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Books</title>
    </head>
    <body>
        <div class="container">
            <form action="books.htm" method="post">
            <table>
                <tr>
                    <th>ISBN</th><th>Book Title</th><th>Author</th><th>Price</th>
                </tr>
                <c:forEach var = "i" begin = "1" end = "${count}">
                <tr>
                   <td><input type="text" name="isbn"></td>
                   <td><input type="text" name="title"></td>
                   <td><input type="text" name="author"></td>
                   <td><input type="text" name="price"></td>
                </tr>
               </c:forEach>   
            </table>
            <input type="submit" value="Add Books">
            <input type="hidden" name="action-selection" value="add-books">
        </form>
        </div>
    </body>
</html>