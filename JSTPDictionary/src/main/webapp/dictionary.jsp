<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 7/22/2021
  Time: 8:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Map" %>
<%@ page import="java.lang.String" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Simple Dictionary</title>
</head>
<body>
    <%!
        Map<String, String> list = new HashMap<>();
        %>
    <%
        list.put("hello","Xin chao");
        list.put("dog","Cho");
        list.put("cat","Meo");
        list.put("book","sach");

        String search = request.getParameter("search");
        String result = list.get(search);
        if (result != null) {
            out.println("Word: " + search + " it mean: ");
            out.println("Result: " + result);
        } else {
            out.println("Not found");
        }

    %>
</body>
</html>
