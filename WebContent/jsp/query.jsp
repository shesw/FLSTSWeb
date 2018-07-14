<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD//XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    <html xmlns="http://www.w3.org/1999/xhtml">
    <html>

    <head>
        <meta http-equiv="content-type" content="text/html;charset=utf-8" />
        <title>查询</title>
    </head>

    <body>


    </body>
    <table border="1" cellspacing="0" cellpadding="0" width="100%" class="table">
        <tr class="titles" bgcolor="ff9900" style="font-weight:bold;">
            <th>日期</th>
            <th>项目</th>
            <th>金额</th>
            <th>余额</th>
        </tr>
    </table>

    <c:forEach items="${items}" var="items">
		<a>items</a>
    </c:forEach>
    <style>
        .table {
            text-align: center;
            margin-top: 100px;
        }

        .titles {
            margin-top: 100px;
        }
    </style>
    
    <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%
        List<Integer> items = new ArrayList<>();
        items.add(1);
        items.add(2);
        items.add(3);

    %>
    </html>

