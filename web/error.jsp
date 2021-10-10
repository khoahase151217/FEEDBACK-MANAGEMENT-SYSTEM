<%-- 
    Document   : error
    Created on : Sep 19, 2021, 11:13:10 AM
    Author     : HieuTran
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <h1>Error: <%= session.getAttribute("ERROR_MESSAGE") %> </h1>
        
        <c:forEach var="item" items="${sessionScope.LIST_DETAIL}">
            <img src="data:image/jpg/png;base64,${item.image}" />
        </c:forEach>
          
    </body>
</html>
