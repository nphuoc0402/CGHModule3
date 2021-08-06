
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
    <%@include file="head.jsp"%>
    <style>
        <%@include file="css_create.jsp"%>
    </style>
</head>
<body>
<h1>Product Management</h1>

</center>
<div align="center">
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
        <a class="btn btn-warning" href="/products">List All Product</a>
    </form>
</div>
<%@include file="script.jsp"%>
</body>
</html>
