<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 7/27/2021
  Time: 8:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
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

    </style>
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2>
        <a href="/customersManage">List All Users</a>
    </h2>
</center>
<div align="center" class="container">
    <form class="needs-validation" novalidate method="post">
        <h2>Transfer Transaction</h2>
        <div>
            <c:if test='${requestScope["success"] != null}'>
                <span class="message success">${requestScope["success"]}</span>
            </c:if>
            <c:if test='${requestScope["error"] != null}'>
                <span class="message error">${requestScope["error"]}</span>
            </c:if>
        </div>
        <div class="form-row">
            <div class="col-md-6 mb-3">
                <label for="validationCustom01">ID Sender</label>
                <input disabled type="text" class="form-control" name="id" id="validationCustom01" value="<c:out value='${customer.id}'/>" required>
            </div>
            <div class="col-md-6 mb-3">
                <label >Customer Name</label>
                <input disabled type="text" name="name" size="45" class="form-control"
                       value="<c:out value='${customer.name}'/>" required>
            </div>
        </div>
        <div class="form-row">
            <div class="col-md-4 mb-3">
                <label >Balance</label>
                <input disabled type="text" name="salarysent" class="form-control" value="<c:out value='${customer.salary}'/>" >

            </div>
            <div class="col-md-4 mb-3">
                <label >ID Receiver</label>
                <input type="text" name="id2" class="form-control" required >
                <div class="invalid-feedback">
                    Id Customer is required
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <label>Amount</label>
                <input type="text" name="salary" size="15" class="form-control" required/>
                <div class="invalid-feedback">
                    Amount is required
                </div>
            </div>
        </div>

        <button class="btn btn-primary" type="submit">Transfer Transaction</button>
    </form>

    <script>
        // Example starter JavaScript for disabling form submissions if there are invalid fields
        (function() {
            'use strict';
            window.addEventListener('load', function() {
                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.getElementsByClassName('needs-validation');
                // Loop over them and prevent submission
                var validation = Array.prototype.filter.call(forms, function(form) {
                    form.addEventListener('submit', function(event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();
    </script>
</div>

</body>
</html>