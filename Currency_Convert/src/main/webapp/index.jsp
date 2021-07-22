<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 7/22/2021
  Time: 3:27 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Currency Convert</title>
  </head>
  <body>
    <h2>Currency Converter</h2>
    <form action="converter.jsp" method="post">
      <label >Rate</label><br>
      <input type="text" name="rate" value="20000" ><br>
      <label >USD</label><br>
      <input type="text" name="usd" value="0"><br>
      <input type="submit" value="Converter">
    </form>
  </body>
</html>
