<%-- 
    Document   : shoppingStore
    Created on : Feb 19, 2023, 10:12:09 PM
    Author     : pankt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Store</title>
        <style type='text/css'>
            #profile_leftPanel {
              width: 150px;
              float: left;
              border-right: 1px solid #000;
              margin-right: 20px;
              height: 90%;
              position: relative;
            }
            
            a.link{
              display: block;
              padding: 10px;
            }

            #profile_rightPanel_center {
              margin-left: auto;
              margin-right: auto;
            }

            .recentStatus {
              width: 400px;
              text-align: center;
              display: inline;
            }
            hr {
                display: block;
                height: 1px;
                border: 0;
                border-top: 1px solid #000;
                margin: 1em 0;
                padding: 0;
            }
            .list{
                display: block;
                font-size: 18px;
                padding: 10px;
            }
            .list input{
                margin-left: 10px;
                margin-right: 10px;
            }
            .submit{
                font-size: 13px;
                padding: 8px;
                border-radius: 5px;
                margin-left: 9px;
            }
            /* Style the tab */
            .tab {
              overflow: hidden;
              border: 1px solid #ccc;
              background-color: #f1f1f1;
            }

            /* Style the buttons inside the tab */
            .tab button {
              background-color: inherit;
              float: left;
              border: none;
              outline: none;
              cursor: pointer;
              padding: 14px 16px;
              transition: 0.3s;
              font-size: 17px;
            }

            /* Change background color of buttons on hover */
            .tab button:hover {
              background-color: #ddd;
            }

            /* Create an active/current tablink class */
            .tab button.active {
              background-color: #ccc;
            }
            .viewCart{    position: absolute;
                left: 30%;
                top: 30.6%;}
            /* Style the tab content */
            .tabcontent {
              display: none;
              padding: 6px 12px;
              border: 1px solid #ccc;
              border-top: none;
            }
            .tabcontent:first-child{
                display: block;
            }
        </style>
    </head>
    <body style="height: 700px">
        <div id="profile_leftPanel"class="tab">
            <button class="tablinks link" onclick="openContent(event, 'books')">Books</button>
            <button class="tablinks link" onclick="openContent(event, 'music')">Music</button>
            <button class="tablinks link" onclick="openContent(event, 'computers')">Computers</button>
        </div>
        <!-- leftPanel end -->
        <div id="profile_rightPanel">
            <div class="recentStatus">
                <p style="font-size: 26px;font-weight: 800;" id="title"></p>
            </div>
            <hr/>
          <div id="profile_rightPanel_center"class="tab-folder">
              <div id="books" class="tabcontent">
                <form action="shopping.htm" method="post">
                <c:forEach var = "i" begin = "0" end = "${sessionScope.bookscount - 1}">   
                    <label class="list" for="${sessionScope.books[i]}">
                        <input type="checkbox" name="product" value="${sessionScope.books[i]}">${sessionScope.books[i]}
                    </label>
                </c:forEach> 
                    <input type="hidden" name="action-selection" value="add-books">
                    <input type="submit" name="submit" value="Add to Cart" class="submit">
                </form>
                <form method="get" action="shopping.htm" style="margin-top: 15px">
                    <input type="submit" name="submit" value="ViewCart" class="submit">
                </form>
              </div>
              
              <div id="music" class="tabcontent">
                <form action="shopping.htm" method="post">
                <c:forEach var = "i" begin = "0" end = "${sessionScope.musiccount - 1}">   
                    <label class="list" for="${sessionScope.music[i]}">
                        <input type="checkbox" name="product" value="${sessionScope.music[i]}">${sessionScope.music[i]}
                    </label>
                </c:forEach>
                    <input type="hidden" name="action-selection" value="add-music">
                    <input type="submit" name="submit" value="Add to Cart" class="submit">
                </form>
                <form method="get" action="shopping.htm" style="margin-top: 15px">
                    <input type="submit" name="submit" value="ViewCart" class="submit">
                </form>
              </div>   
              
              <div id="computers" class="tabcontent">
                <form action="shopping.htm" method="post">
                <c:forEach var = "i" begin = "0" end = "${sessionScope.computercount - 1}">   
                    <label class="list" for="${sessionScope.computer[i]}">
                        <input type="checkbox" name="product" value="${sessionScope.computer[i]}">${sessionScope.computer[i]}
                    </label>
                </c:forEach>
                    <input type="hidden" name="action-selection" value="add-computers">
                    <input type="submit" name="submit" value="Add to Cart" class="submit">
                </form>
                <form method="get" action="shopping.htm" style="margin-top: 15px">
                    <input type="submit" name="submit" value="ViewCart" class="submit">
                </form>  
              </div>   
              
          </div>
          <!-- _center end -->
        </div>
        <!-- rightPanel end -->
        <script>
            function openContent(evt, cityName) {
              var i, tabcontent, tablinks;
              tabcontent = document.getElementsByClassName("tabcontent");
              console.log(tabcontent);
              for (i = 0; i < tabcontent.length; i++) {
                tabcontent[i].style.display = "none";
              }
              tablinks = document.getElementsByClassName("tablinks");
              for (i = 0; i < tablinks.length; i++) {
                tablinks[i].className = tablinks[i].className.replace(" active", "");
              }
              document.getElementById(cityName).style.display = "block";
              document.getElementById('title').innerHTML = 'Shop for ' + cityName ;
              evt.currentTarget.className += " active";
            }
        </script>
    </body>
</html>