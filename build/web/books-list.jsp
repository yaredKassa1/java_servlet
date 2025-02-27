<%@page import="LibraryMS.DBConnect"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BOOKS LISTS</title>
        <style>
            tr,td,th{
                padding: 20px;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <br><br><br>
    <center>
        <%!
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
        %>
        <table border="2">
            <tr>
                <th>Book ID</th><th>Title</th><th>Author</th><th>Edition</th><th>Genre</th><th>Publisher</th><th>Publication Date</th><th>Books</th><th>Download</th>
            </tr>
            <%
                con = DBConnect.getConnection();
            String sql = "select * from books1";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
            %>
            <tr>
                <td><%=rs.getInt(1)%></td>
                <td><%=rs.getString(2)%></td>
                <td><%=rs.getString(3)%></td>
                <td><%=rs.getInt(4)%></td>
                <td><%=rs.getString(5)%></td>
                <td><%=rs.getString(6)%></td>
                <td><%=rs.getString(7)%></td>
                <td><%=rs.getString(8)%></td>
                <td><a href="BooksServlet?filename=<%=rs.getString(8)%>">Download</a></td>
            </tr>
            <%
                }
            %>
            
        </table><br>
        <a href="index.html">Home</a>
    </center>
    </body>
</html>
    
 