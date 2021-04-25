<%-- 
    Document   : HomePage
    Created on : Feb 18, 2020, 9:18:23 AM
    Author     : asus
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link href="css/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body> 
        <div class="container">
            <jsp:include page="Header.jsp"/>
            <div class="content">
                <div class="main">
                       <c:forEach items="${top1}" var="i">
                    <div class="tittle">
                          ${i.title}
                    </div>
                    <div class="image">
                        <img src="${i.image}"/>
                    </div>
                    <div class="text">
                        <p>${i.shortDes}</p>
                        <p>${i.description}</p>
                    </div>
                    <div class="signature">
                        <div class="icon1"></div>
                        <div class="icon2"></div>
                         By ${i.author} | ${i.timePost}
                    </div>
                       </c:forEach>
                </div>
                <jsp:include page="Right.jsp"/> 
            </div>
            <jsp:include page="Footer.jsp"/>
        </div>
    </body>
</html>
