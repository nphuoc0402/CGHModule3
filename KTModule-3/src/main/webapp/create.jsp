
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
        label,input {
            padding: 10px;
        }
        a{
            text-decoration: none;
        }
        h1{
           text-align: center;
        }
    </style>
</head>
<body>
<h1>Product Management</h1>
<h1>
    <a href="/products">List All Users</a>

</h1>
</center>
<div align="center">
    <h2><a href="/products" style="text-decoration: none; color: black;">Product Management</a></h2>
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
    <form class="needs-validation" novalidate method="post">
        <div class="col-4">
            <label for="validationCustom01">Product Name: </label>
            <input type="text" name="name" size="45" class="form-control"  id="validationCustom01" required >
            <div class="invalid-feedback">
                Product Name is required
            </div>
        </div>
        <div class="col-4">
            <label >Price</label>
            <input  type="text" name="price" id="price" size="45" class="form-control" required>
            <div class="invalid-feedback">
                Price is required
            </div>
        </div>
        <div class="col-4">
            <label >Quantity</label>
            <input  type="text" name="quantity" id="quantity" size="45" class="form-control" required>
            <div class="invalid-feedback">
                Quantity is required
            </div>
        </div>
        <div class="col-4">
            <label >Color</label>
            <input type="text" name="color" class="form-control" required>
            <div class="invalid-feedback" >
                Color is required
            </div>
        </div>
        <div class="col-4">
            <label >Description</label>
            <input type="text" name="description" class="form-control" required>
            <div class="invalid-feedback" >
                Description is required
            </div>
        </div>
        <div class="col-4">
            <label >Category</label>
            <select name="category" id="category">
                <c:forEach items="${categories}" var="categories">
                    <option value="${categories.getName()}">${categories.getName()}</option>
                </c:forEach>
            </select>

        </div>
        <button class="btn btn-primary" type="submit">Create</button>

    </form>
</div>
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
</body>
</html>
