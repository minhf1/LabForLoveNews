<%-- 
    Document   : Error
    Created on : Oct 5, 2020, 9:36:47 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
     
           
          
           <c:if test="${error1!=null}">
                    <h3>${error1}</h3>
                </c:if>
                <c:if test="${error2!=null}">
                    <h3>${error2}</h3>
                </c:if>
                <c:if test="${error3!=null}">
                    <h3>${error3}</h3>
                </c:if>
                <c:if test="${error4!=null}">
                    <h3>${error4}</h3>
                </c:if>
            
            
       
    </body>
</html>
