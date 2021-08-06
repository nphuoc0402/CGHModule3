<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
    <%@include file="head.jsp" %>
    <style>
        <%@include file="css_create.jsp"%>
    </style>
</head>
<body>
<h1>Product Management</h1>
<h2>

</h2>
<div>
    <c:if test='${requestScope["success"] != null}'>
        <span class="message success">${requestScope["success"]}</span>
    </c:if>
    <c:if test='${requestScope["error"] != null}'>
        <span class="message error">${requestScope["error"]}</span>
    </c:if>
</div>
<div align="center" class="container">
    <form class="needs-validation" novalidate method="post">
        <fieldset>
            <legend>
                Edit Product
            </legend>
            <div class="form-group ">
                <c:if test="${product != null}">
                    <input class="form-control" type="hidden" name="id" value="<c:out value='${product.getId()}' />">
                </c:if>
            </div>

            <div class="col-4">
                <label>Product Name: </label>
                <input type="text" name="name" value="<c:out value='${product.getName()}' />" size="45"
                       class="form-control" id="validationCustom01" required>
                <div class="invalid-feedback">
                    Product Name is required
                </div>
            </div>
            <div class="col-4">
                <label>Price</label>
                <input type="text" name="price" value="<c:out value='${product.getPrice()}' />" id="price" size="45"
                       class="form-control" required>
                <div class="invalid-feedback">
                    Price is required
                </div>
            </div>
            <div class="col-4">
                <label>Quantity</label>
                <input type="text" name="quantity" value="<c:out value='${product.getQuantity()}' />" id="quantity"
                       size="45" class="form-control" required>
                <div class="invalid-feedback">
                    Quantity is required
                </div>
            </div>
            <div class="col-4">
                <label>Color</label>
                <input type="text" name="color" value="<c:out value='${product.getColor()}' />" class="form-control"
                       required>
                <div class="invalid-feedback">
                    Color is required
                </div>
            </div>
            <div class="col-4">
                <label>Description</label>
                <input type="text" name="description" value="<c:out value='${product.getDescription()}'/>"
                       class="form-control" required>
                <div class="invalid-feedback">
                    Description is required
                </div>
            </div>
            <div class="col-4">
                <label>Category</label>
                <select name="category" id="category">
                    <c:forEach items="${categories}" var="categories">
                        <option value="${categories.getName()}">${categories.getName()}</option>
                    </c:forEach>
                </select>

            </div>
            <button class="btn btn-primary" type="submit">Update</button>
            <a class="btn btn-warning" href="/products">List All Product</a>
        </fieldset>
    </form>

</div>
<%@include file="script.jsp" %>
</body>
</html>
