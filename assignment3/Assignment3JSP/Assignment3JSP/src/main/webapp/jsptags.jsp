<%-- 
    Document   : jsptags
    Created on : Feb 16, 2023, 6:45:26 PM
    Author     : pankt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "sql" uri = "http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSTL Tags</title>
    </head>
    <body>
        <h1>JSTL Core tags</h1>
        <c:set var="num" value="324123.23234"/>
        Number after setting type attribute: <br/>
        
        Table of 2. <br/>
        <c:forEach var="i" begin="1" end="10">
            <c:out value="${i*2}"/><p>
        </c:forEach><br/><!-- comment -->
         
        <c:url value="/hello.jsp" var="helloUrl"/>
        <h4><a href="${helloUrl}">Click here</a></h4> <br/>
        
        <h1>JSTL Formatting tags</h1>
        <fmt:formatNumber value="${num}" type="currency"/> <br/>
        Number after setting maxIntegerDigits and maxFractionDigits attribute:
        <br/>
        <fmt:formatNumber type="number" maxIntegerDigits="4" 
        maxFractionDigits="3" value="${num}" /> <br/>
        Number after setting pattern attribute: <br/>
        <fmt:formatNumber type="number" pattern="##,###.##" value="${num}" /> <br/><!-- comment -->
        
        <h1>JSTL Function Tags</h1> <br/><!-- comment -->
        <c:set var="testString" 
            value="Hello this is a JSTL function example"/>
        Index of JSTL in the String: 
	<c:out value="${fn:indexOf(testString, 'JSTL')}" /><br/>
        
        Length of the given string: <br/>
        <c:out value="${fn:length(testString)}" /><br/>
        
        Given String: <br/>
        <c:out value="${testString}" /><br/><br/>
        Substring of the given string:<br/>
        <c:out value="${fn:substring(testString, 6, '29')}" /><br/><!-- comment -->
        
        
        <h1>JSTL SQL Tags</h1>
        <sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
         url = "jdbc:mysql://localhost/jsptable"
         user = "root"  password = "PanktiShah@1992"/>
        
        <sql:update dataSource = "${snapshot}" var = "count">
         INSERT INTO `table`(`id`, `name`) VALUES (2,'Kinjal');
        </sql:update>
         
        <sql:query dataSource = "${snapshot}" var = "result">
         SELECT * from `table`;
        </sql:query>
 
        <table border = "1" width = "100%">
           <tr>
              <th>ID</th>
              <th>Name</th>
           </tr>

           <c:forEach var = "row" items = "${result.rows}">
              <tr>
                 <td> <c:out value = "${row.id}"/></td>
                 <td> <c:out value = "${row.name}"/></td>
              </tr>
           </c:forEach>
          </table>
         <br/><!-- comment -->
         
         <h1>XML tags</h1>
        <p>Created a XML structure with vegetables as a root element & vegetable as the child element. The child element also has name & price as attributes.</p>
        <c:set var="vegetable">  
        <vegetables>  
            <vegetable>  
              <name>onion</name>  
              <price>40/kg</price>  
            </vegetable>  
         <vegetable>  
              <name>Potato</name>  
              <price>30/kg</price>  
            </vegetable>  
         <vegetable>  
              <name>Tomato</name>  
              <price>90/kg</price>  
            </vegetable>  
        </vegetables>  
        </c:set>  
        <x:parse xml="${vegetable}" var="output"/>  
        Name of the vegetable is:  
        <x:out select="$output/vegetables/vegetable[1]/name" /><br>  
        Price of the Potato is:  
        <x:out select="$output/vegetables/vegetable[2]/price" /></br>
        <x:set var="fragment" select="$output/vegetables/vegetable[3]/price"/>  
        Price of Tomato is:  
        <x:out select="$fragment" />
        
    </body>
</html>