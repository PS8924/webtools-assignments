<%-- 
    Document   : viewCart
    Created on : Feb 20, 2023, 9:57:41 PM
    Author     : pankt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
    </head>
    <body>
        <h1>These are the items in your cart!</h1>
        <form action="ShoppingController" method="post">
            <c:forEach var = "i" begin = "0" end = "${sessionScope.cartcount - 1}">   
                <label class="list" for="${sessionScope.cart[i]}">
                    <input type="checkbox" name="product" value="${sessionScope.cart[i]}">${sessionScope.cart[i]}
                </label>
            </c:forEach>
                <input type="hidden" name="action-selection" value="remove-product">
                <input type="submit" name="submit" value="Remove Product" class="submit">
        </form>
    </body>
</html>
