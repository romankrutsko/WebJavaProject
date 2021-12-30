<%@ page import="com.example.workshop.entity.Order" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Roma Kr
  Date: 29.12.2021
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="table-wrapper">
    <table class="fl-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Users_ID</th>
            <th>Status</th>
            <th>Date</th>
            <th>Master</th>
            <th>Price</th>
        </tr>
        </thead>
        <tbody>
            <%List<Order> orders = (List<Order>) request.getAttribute("orders");
        for (Order order : orders) {%>
        <tr>
            <td><%=order.getId()%>
            </td>
            <td><%=order.getUsers_id()%>
            </td>
            <td><%=order.getStatus()%>
            </td>
            <td><%=order.getDate()%>
            </td>
            <td><%=order.getMaster()%>
            </td>
            <td><%=order.getPrice()%>
            </td>
        </tr>
            <%}%>
        <tbody>
    </table>
</div>
</body>
</html>
