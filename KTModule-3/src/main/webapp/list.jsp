<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        form{
            display: flex;;
        }
    </style>
</head>
<body>
<div align="center">
    <h1><a href="/products" style="text-decoration: none; color: black;">Product Management</a></h1>
    <div class="message">
        <c:if test='${requestScope["success"] != null}'>
            <div id="div1" class="alert alert-success" role="alert">
                    ${requestScope["success"]}
            </div>
        </c:if>
        <c:if test='${requestScope["error"] != null}'>
            <div id="div1" class="alert alert-danger" role="alert">
                    ${requestScope["error"]}
            </div>
        </c:if>
    </div>
    <div class="row">
        <div class="col-2">
            <a class="btn btn-primary"  href="/products?action=create">Create</a>
        </div>
        <div class="col-2">
            <form action="/products?action=search" method="post">
                <input type="text" name="search" class="form-control">
                <button type="submit">Search</button>
            </form>
        </div>
    </div>

    <table border="1" cellpadding="5" class="table">
        <h2>List of Users</h2>

        <thead class="thead-dark">
        <th scope="col">ID</th>
        <th scope="col">Name</th>
        <th scope="col">Price</th>
        <th scope="col">Quantity</th>
        <th scope="col">Color</th>
        <th scope="col">Description</th>
        <th scope="col">Category</th>
        <th scope="col">Action</th>
        </thead>

        <c:forEach var="products" items="${products}">
            <tr>
                <td><c:out value="${products.getId()}"/></td>
                <td><c:out value="${products.getName()}"/></td>
                <td><fmt:formatNumber value="${products.getPrice()}" type="currency"
                                      currencySymbol="$"></fmt:formatNumber></td>
                <td><c:out value="${products.getQuantity()}"/></td>
                <td><c:out value="${products.getColor()}"/></td>
                <td><c:out value="${products.getDescription()}"/></td>
                <td>${categories.get(products.getCat_id() - 1).getName()}</td>
                <td>
                    <a class="btn btn-warning" href="/products?action=edit&id=${products.getId()}" title="Edit"><i
                            class="fa fa-pencil-square-o" aria-hidden="true">Edit</i></a>
                    <a class="btn btn-danger" href="/products?action=delete&id=${products.getId()}" title="Delete"><i
                            class="fa fa-trash-o" aria-hidden="true">Delete</i></a>
                </td>
            </tr>
        </c:forEach>
    </table>

    </table>
</div>
</body>
</html>
