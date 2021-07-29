<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 7/27/2021
  Time: 3:56 PM
  To change this template use File | Settings | File Templates.
--%>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.18/dist/sweetalert2.all.min.js"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Customer</title>
</head>
<body>
<center>
    <h1>Customer Management</h1>
<%--    <h2>--%>
<%--        <a href="/customersManage?action=create">Add New Customer</a>--%>
<%--    </h2>--%>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <tr>
                <th>User Name:</th>
                <td>
                    <input type="text" name="name" id="name" size="45"/>
                </td>
            </tr>
            <tr>
                <th>User Email:</th>
                <td>
                    <input type="text" name="email" id="email" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Salary:</th>
                <td>
                    <input type="text" name="salary" id="salary" size="15"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <button type="submit">Create</button>
                </td>
            </tr>
            <tr>
                <div >
                    <c:if test='${requestScope["message"] != null}'>
                        <span class="message">${requestScope["message"]}</span>
                    </c:if>
                </div>
            </tr>
        </table>
    </form>
</div>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>

        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Salary</th>
            <th>Actions</th>
        </tr>

        <c:forEach var="user" items="${customers}">
            <tr>
                <td><c:out value="${user.getId()}"/></td>
                <td><c:out value="${user.getName()}"/></td>
                <td><c:out value="${user.getEmail()}"/></td>
                <td><fmt:formatNumber value="${user.getSalary()}" type="currency" currencySymbol="$" ></fmt:formatNumber></td>
                <td>
                    <a href="/customersManage?action=edit&id=${user.id}">Edit</a>
                    <a href="/customersManage?action=sent&id=${user.id}">Send</a>
                    <a href="/customersManage?action=delete&id=${user.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
