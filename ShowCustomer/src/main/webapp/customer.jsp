<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 7/24/2021
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!-- Khai báo sử dụng JSTL Core Tags -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Show Customer</title>
    <style>
        img{
            width: 50px;
            height: 50px;
        }
        td{
            width: 150px;
        }
        td:last-child{
            width: 50px;
        }
    </style>
</head>
<body>
<table border="1">
    <thead border="1">Danh Sach Khach Hang</thead>
    <thead>
    <th>Tên</th>
    <th>Ngày Sinh</th>
    <th>Địa chỉ</th>
    <th>Ảnh</th>
    </thead>
    <tbody>

            <c:forEach items="${customer}" var="cus">
                <tr>
                <td>${cus.name}</td>
                <td>${cus.dob}</td>
                <td>${cus.address}</td>
                <td><img src="${cus.img}" alt=""></td>
                </tr>
            </c:forEach>

    </tbody>


</body>
</html>
