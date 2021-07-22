<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 7/22/2021
  Time: 8:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Converter</title>
</head>
<body>
<%
    float rate = Float.parseFloat(request.getParameter("rate"));
    float usd = Float.parseFloat(request.getParameter("usd"));

    float vnd = rate * usd;
%>
<h1>Rate: <%=rate%></h1>
<h1>USD: <strong>
    <fmt:formatNumber value="${param.usd}" type="currency" currencySymbol="$"/>
</strong></h1>
<h1>VND: <strong>
    <fmt:formatNumber value="${param.usd * param.rate}" type="currency"/>
</strong></h1>

</body>
</html>