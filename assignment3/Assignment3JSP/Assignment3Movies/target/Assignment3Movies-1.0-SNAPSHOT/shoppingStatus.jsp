<%-- 
    Document   : shoppingStatus
    Created on : Feb 20, 2023, 5:27:40 PM
    Author     : pankt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Status</title>
        <style>
            .button{
                background-color: #eae8e8;
                padding: 2px 9px;
                margin: 15px;
                border: 1px solid black;
            }
        </style>
    </head>
    <body>
        <h1>&nbsp;The following item were altered/added in your shopping cart successfully</h1>
        <ul>
            <c:forEach var = "i" begin = "0" end = "${sessionScope.productCount - 1}">
                <li> &nbsp;${sessionScope.items[i]}</li>
            </c:forEach>
        </ul>
        <form method="get" action="ShoppingController" style="margin-top: 15px; display: inline-block">
            <input type="submit" name="submit" value="ViewCart" class="submit">
        </form>
        <a href="ShoppingController" class="button">Go to Shopping Page</a>
    </body>
</html>