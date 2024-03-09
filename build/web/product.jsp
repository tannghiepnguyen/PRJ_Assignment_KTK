<%-- 
    Document   : product
    Created on : Feb 28, 2024, 2:50:59 PM
    Author     : tanng
--%>

<%@page import="java.util.List"%>
<%@page import="nghiepnlt.product.ProductDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product</title>
    </head>
    <body>
        <form action="DispatchServlet">
            Choose product <select name="cboBook">
                <c:forEach var="list" items="${requestScope.list}">
                    <option value="${list.sku}">${list.name}</option>
                </c:forEach>
            </select>
            <br/>
            <input type="submit" value="Add Book To Your Cart" name="btnAction" />
            <input type="submit" value="View Your Cart" name="btnAction" />
        </form>
    <%--    <%
            List<ProductDTO> list = (List)request.getAttribute("list");
        %>
        <h1>Book store</h1>
        <form action="DispatchServlet">
            Choose product <select name="cboBook">
                <%
                    for (ProductDTO product : list){
                        %>
                        <option value="<%= product.getSku()%>"><%= product.getName() %></option>
                <%
                    }
                %>
            </select>
            <br/>
            <input type="submit" value="Add Book To Your Cart" name="btnAction" />
            <input type="submit" value="View Your Cart" name="btnAction" />
        </form> --%>
    </body>
</html>
