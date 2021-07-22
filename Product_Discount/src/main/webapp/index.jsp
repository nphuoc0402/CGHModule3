<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 7/22/2021
  Time: 7:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <h2>Product Discount Calculator</h2>
    <form action="/display-discount" method="post">
      <label>Product Description</label><br>
      <input type="text" name="decription"><br>
      <label >List Price</label><br>
      <input type="text" name="price"><br>
      <label >Discount Percent</label><br>
      <input type="text" name="discount"><br>
      <input type="submit" value="Calculate Discount">
    </form>
  </body>
</html>
