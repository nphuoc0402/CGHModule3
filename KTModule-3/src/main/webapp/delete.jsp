<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete</title>
    <%@include file="head.jsp"%>
</head>
<body>
<h1>Delete Product</h1>
<p>
    <a href="/products">Back to Product List list</a>
</p>
<form method="post">
    <h3>Are you sure?</h3>
    <fieldset>
        <legend>Product information</legend>
        <table>
            <tr>
                <td>ID: </td>
                <td>${requestScope["product"].getId()}</td>
            </tr>
            <tr>
                <td>Product Name: </td>
                <td>${requestScope["product"].getName()}</td>
            </tr>
            <tr>
                <td>Price: </td>
                <td>${requestScope["product"].getPrice()}</td>
            </tr>
            <tr>
                <td>Quantity: </td>
                <td>${requestScope["product"].getQuantity()}</td>
            </tr>
            <tr>
                <td>Color: </td>
                <td>${requestScope["product"].getColor()}</td>
            </tr>
            <tr>
                <td>Description: </td>
                <td>${requestScope["product"].getDescription()}</td>
            </tr>
            <tr>

            </tr>
            <tr>
                <td><input class="btn btn-primary" type="submit" value="Delete Product"></td>
                <td><a class="btn btn-warning" href="/products">List All Product</a></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
