<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 7/24/2021
  Time: 8:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <style>
        fieldset{
          width: 300px;
        }
        fieldset input[name="f-operand"]{
          position: relative;
          left: 20px;
        }
        select{
          position: relative;
          left: 48px;
        }
      input:last-child{
        margin-left: 50px;
      }
    </style>
  </head>
  <body>
    <h2>Simple Caculator</h2>
    <form action="/calculate" method="post">
      <fieldset>
        <legend>Calculator</legend>
        <label for="fOperand">First operand</label>
        <input type="text" name="f-operand"><br><br>
        <label for="operator">Operator</label>
        <select name="operator" id="">
          <option value="+">Addition</option>
          <option value="-">Subtraction</option>
          <option value="*">Multiplication</option>
          <option value="/">Division</option>
        </select><br><br>
        <label for="s=operand">Second operand</label>
        <input type="text" name="s-operand"><br><br>
        <input type="submit" value="Calculate">
      </fieldset>
    </form>
  </body>
</html>
