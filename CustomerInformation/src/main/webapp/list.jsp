<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 7/23/2021
  Time: 6:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.18/dist/sweetalert2.all.min.js"></script>
<%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">--%>
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <style>
        a{
            text-decoration: none;
        }
        .btn{
            width: fit-content;
            height: max-content;
            padding: 10px;
            border: 1px solid black;
            color: white;
        }
        .btn:hover{
            background-color: greenyellow;
        }
        body{
            margin: 50px 100px;
            padding: 0;
        }
        td{
            width: 200px;
        }
        form{
            width: 400px;!important;
        }
    </style>
</head>
<body>
<h1>Customers</h1>
<form method="post">
    <fieldset>
        <legend>Customer information</legend>
        <table>
            <tr>
                <td>Name: </td>
                <td><input type="text" name="name" id="name"></td>
            </tr>
            <tr>
                <td>Email: </td>
                <td><input type="text" name="email" id="email"></td>
            </tr>
            <tr>
                <td>Address: </td>
                <td><input type="text" name="address" id="address"></td>
            </tr>
            <tr>
                <td>Gender: </td>
                <td><select name="gender" id="gender">
                    <option value="0">Male</option>
                    <option value="1">Female</option>
                </select></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Create customer"></td>
            </tr>
            <tr>
                <c:if test='${requestScope["message"] != null}'>
                <span class="message">${requestScope["message"]}</span>
            </c:if>
            </tr>
        </table>
    </fieldset>
</form>

<%--<p  class="btn">--%>
<%--    <a href="/customers?action=create">Create new customer</a>--%>
<%--</p>--%>
<table border="1"  >
    <tr>
        <td>Name</td>
        <td>Email</td>
        <td>Address</td>
        <td>Gender</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <c:forEach items='${requestScope["customers"]}' var="customer">
        <tr>
            <td ><a href="/customers?action=view&id=${customer.getId()}">${customer.getName()}</a></td>
            <td>${customer.getEmail()}</td>
            <td width: 250px;>${customer.getAddress()}</td>

            <c:if test="${customer.getGender() == 0}">
                <td><c:out value="Male"></c:out></td>
            </c:if>
            <c:if test="${customer.getGender() == 1}">
                <td><c:out value="Female"></c:out></td>
            </c:if>
            <td><a href="/customers?action=edit&id=${customer.getId()}"><i class="fa fa-edit"> Edit</i></a></td>
            <td><a href="/customers?action=delete&id=${customer.getId()}"><i class="fas fa-minus-circle">Delete</i></a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

