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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer List</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <style>
        .message{
            color:white;
        }
        .error{
            color: red;
            font-family: Arial;
            font-weight: bold;
        }
        .success{
            color: lightgreen;
            font-weight: bold;
            font-family: Arial;
        }
        form{
            display: flex; !important;

        }
        .message{
            float: right;
            position: relative;
            top: 0px;
            right: 0px;
        }
        
        a{
            padding: 20px;
            width: 40px;
        }

    </style>
</head>
<body>
<div class="container">
    <h1>Customer Management</h1>
    <div class="message">
    <c:if test='${requestScope["success"] != null}'>
        <span class="message success">${requestScope["success"]}</span>
    </c:if>
    <c:if test='${requestScope["error"] != null}'>
        <span class="message error">${requestScope["error"]}</span>
    </c:if>
</div>

    <form method="post" class="row">
        <div class="row">
            <div class="col">
                <label>User Name: </label>
                <input type="text" name="name" id="name" size="45"/>
            </div>
            <div class="col">
            <label>Email: </label>
            <input  type="text" name="email" id="email" class="form-control" size="45"/>
            </div>
            <div class="col">
                <label>Salary: </label>
                <input type="text" name="salary" class="form-control">
            </div>
        </div>

        <button type="submit" class="btn btn-primary" >Create</button>

    </form>


    <table border="1" cellpadding="5" class="table">
        <h2>List of Users</h2>

        <thead class="thead-dark">
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Email</th>
            <th scope="col">Salary</th>
            <th scope="col">Actions</th>
        </thead>

        <c:forEach var="user" items="${customers}">
            <tr>
                <td><c:out value="${user.getId()}"/></td>
                <td><c:out value="${user.getName()}"/></td>
                <td><c:out value="${user.getEmail()}"/></td>
                <td><fmt:formatNumber value="${user.getSalary()}" type="currency" currencySymbol="$" ></fmt:formatNumber></td>
                <td>
                    <a class="btn btn-warning" href="/customersManage?action=edit&id=${user.id}"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a>
                    <a class="btn btn-success" href="/customersManage?action=sent&id=${user.id}"><i class="fa fa-exchange" aria-hidden="true"></i></a>
                    <a class="btn btn-danger" href="/customersManage?action=delete&id=${user.id}"><i class="fa fa-trash-o" aria-hidden="true"></i></a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
