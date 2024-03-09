<%-- 
    Document   : viewCart
    Created on : Feb 26, 2024, 9:04:55 AM
    Author     : tanng
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="nghiepnlt.product.ProductDTO"%>
<%@page import="java.util.Map"%>
<%@page import="nghiepnlt.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <h1>Your cart</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}"/>
            <c:if test="${not empty items}">
                <form action="DispatchServlet">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${items.keySet()}" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${item.name}</td>
                                    <td>${items.get(item)}</td>
                                    <td>
                                        <input type="checkbox" name="chkItem" value="${item.sku}" />
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="3">
                                    <c:url var="getProductList" value="DispatchServlet">
                                        <c:param name="btnAction" value="GetProductList"/>
                                    </c:url>
                                    <a href="${getProductList}">Add more</a>
                                </td>
                                <td>
                                    <input type="submit" name="btnAction" value="Remove Selected Items"></input>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
                <c:url var="checkout" value="DispatchServlet">
                    <c:param name="btnAction" value="Checkout"/>
                </c:url>
                <a href="${checkout}">Checkout</a>
            </c:if>
            <c:if test="${empty items}">
                <h2 style="color: red">No cart is not existed</h2>
            </c:if>
        </c:if>
        <c:if test="${empty cart}">
            <h2 style="color: red">No cart is not existed</h2>
        </c:if>
        <%--       <%
                    //1. Cust goes to cart
                    if (session != null) {
                        //2. Cust takes cart (existed)
                        CartObject cart = (CartObject) session.getAttribute("CART");
                        if (cart != null) {
                            Map<ProductDTO, Integer> items = cart.getItems();
                            if (items != null) {
                %>
                <form action="DispatchServlet">

            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (ProductDTO key : items.keySet()) {
                    %>
                    <tr>
                        <td><%= ++count%></td>
                        <td><%= key.getName() %></td>
                        <td><%= items.get(key)%></td>
                        <td>
                            <input type="checkbox" name="chkItem" value="<%= key.getSku() %>" />
                        </td>
                    </tr>
                    <%
                        }//traverse item
                    %>
                    <tr>
                        <td colspan="3">
                            <a href="DispatchServlet?btnAction=GetProductList">Add more</a>
                        </td>
                        <td>
                            <input type="submit" name="btnAction" value="Remove Selected Items"></input>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
        <a href="DispatchServlet?btnAction=Checkout">Checkout</a>
        <%
                        return;
                    }
                }
                //3. Cust gets items
                //4. Show all items
            }
        %>
        <h2 style="color: red">No cart is not existed</h2> --%>
    </body>
</html>
