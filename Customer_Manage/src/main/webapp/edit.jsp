<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 7/27/2021
  Time: 8:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<html>
<head>
    <title>User Management Application</title>
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
            border: 1px solid black;
            width: 400px;
            padding: 20px;
        }
        .form-control {
            width: 300px;
        }

        a{
            text-decoration: none;
        }



    </style>
</head>
<body>
<div class="container">
<center>
    <h1>User Management</h1>
    <h2>
        <a href="/customersManage">List All Users</a>
    </h2>
    <div >
        <c:if test='${requestScope["success"] != null}'>
            <span class="message success">${requestScope["success"]}</span>
        </c:if>
        <c:if test='${requestScope["error"] != null}'>
            <span class="message error">${requestScope["error"]}</span>
        </c:if>
    </div>
</center>
<div align="center">
    <form method="post">
        <fieldset>
            <legend>
                Edit Customer
            </legend>
            <div class="form-group ">
                <c:if test="${customers != null}">
                <input class="form-control" type="hidden" name="id" value="<c:out value='${customers.id}' />"/>
                </c:if>
            </div>
            <div class="form-group ">
                <label >User Name:</label>
                <input class="form-control" type="text" name="name" size="45"
                   value="<c:out value='${customers.name}' />"/>
            </div>
            <div class="form-group ">
                <label >User Email:</label>
                <input class="form-control" type="text" name="email" size="45"
                       value="<c:out value='${customers.email}' />"
                />
            </div>
            <div class="form-group ">
                <label >Salary:</label>
                <input class="form-control" type="text" name="salary" size="15"
                       value="<c:out value='${customers.salary}' />"
                />
            </div>
            <div class="form-grou">
                <button type="submit" class="btn btn-warning">Update</button>
            </div>
        </fieldset>
    </form>
</div>
</div>
</body>
</html>