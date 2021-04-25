
<%-- 
    Document   : Header
    Created on : Feb 2, 2020, 8:50:36 AM
    Author     : asus
    
--%>
<%@page import="javax.naming.Context"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Search</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="container">
            <jsp:include page="Header.jsp"/>
            <div class="content">
                <div class="main">        
                    <c:if test="${textsearcherror !=  null}">
                        <p>${textsearcherror}</p>
                    </c:if>
                    <c:if test="${textsearcherror == null}">
                        <c:if test="${not empty errorMessage}">
                            <p>${errorMessage}</p>
                        </c:if>
                        <c:if test="${empty errorMessage}">
                            <c:forEach items="${listSearch}" var="x">
                                <div class="tittle">
                                    <a href="DetailController?id=${x.id}">      
                                        ${x.title}
                                    </a>
                                </div>
                                <div class="image_search">
                                    <img src="${x.image}" alt=""/> 
                                </div>
                                <div class="text_search">
                                    ${x.shortDes}
                                </div>
                                <br>
                            </c:forEach>
                            <div class="paging">

                                <c:if test="${totalPage > 1}">
                                    <c:forEach begin="1" end="${totalPage}" var="i">
                                        <a class="${i==index?"active":""}" href="SearchController?index=${i}&txtSearch=${txt}">${i}</a>
                                    </c:forEach>
                                </c:if>

                            </div>
                        </c:if>
                    </c:if>
                </div> 
                <jsp:include page="Right.jsp"/> 
            </div>
            <jsp:include page="Footer.jsp"/>
        </div>

    </body>
</html>
