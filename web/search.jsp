<%-- 
    Document   : search
    Created on : Feb 1, 2024, 7:12:35 AM
    Author     : tanng
--%>

<%@page import="nghiepnlt.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <% 
            if (session != null){
                String username = (String)session.getAttribute("username");
                %>
                <h5 color="red">Welcome, <%= username %></h5><br/>
                <%
            }
        %>
        <%
            String deleteUrl = "DispatchServlet"
                    + "?btnAction=Logout";
        %>
        <a href="<%= deleteUrl %>">Logout</a>
        <h1>Search Page</h1>
        <form action="DispatchServlet" method="GET">
            <%
                String defaultSearchValue = "";
                if (request.getParameter("txtSearchValue") != null){
                    defaultSearchValue = request.getParameter("txtSearchValue");
                }    
            %>
            Search value <input type="text" name="txtSearchValue" value="<%= defaultSearchValue %>" />
            <input type="submit" value="Search" name="btnAction" />
        </form>
        <br/>
        <% 
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null && !searchValue.trim().isEmpty()){
                List<RegistrationDTO> result = (List<RegistrationDTO>)request.getAttribute("SEARCH_RESULT");
                if (result != null){ //search is found
                    %>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Full name</th>
                                <th>Role</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                int count = 0;
                                for (RegistrationDTO dto : result){
                                    //Khong de khoang trang, thieu dau ?, thieu dau &
                                    String urlRewriting = "DispatchServlet"
                                            + "?btnAction=Delete"
                                            + "&pk=" + dto.getUsername()
                                            + "&lastSearchValue=" + searchValue;
                                    %>
                                <form action="DispatchServlet" method="POST">            
                                    <tr>
                                        <td>
                                            <%= ++count %>
                                        </td>
                                        <td>
                                            <%= dto.getUsername() %>
                                            <input type="hidden" name="txtUsername" value="<%= dto.getUsername() %>"/>
                                        </td>
                                        <td>
                                            <input type="text" name="txtPassword" value="<%= dto.getPassword() %>" />
                                        </td>
                                        <td>
                                            <%= dto.getFullName() %>
                                        </td>
                                        <td>
                                            <input type="checkbox" name="chkAdmin" value="ON"
                                                   <%
                                                       if (dto.isRole()){
                                                           %> checked="checked" <%
                                                       }
                                                   %>
                                                   />
                                        </td>
                                        <td>
                                            <a href="<%= urlRewriting%>">Delete</a>
                                        </td>
                                        <td>
                                            <input type="hidden" name="lastSearchValue" value="<%= searchValue %>" />
                                            <input type="submit" value="Update" name="btnAction" />                           
                                        </td>
                                    </tr>
                                </form>
                                    <%
                                }// traverse result to get each account
                            %>
                        </tbody>
                    </table>
                    <%
                }
                else{
                    %>
                    <h2>
                        <font color="red">
                            No record is matched !!!
                        </font>
                    </h2>
                    <%
                }
            }// searchValue is null if user access directly
        %>  
    </body>
</html>
