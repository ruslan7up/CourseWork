<%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 03.10.2016
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:forEach var="acc" items="${accounts}">
    <tr onclick="selectRow(this)">
        <td>${acc.getId()}</td>
        <td>${acc.getLogin()}</td>
        <td>${acc.getPass()}</td>
    </tr>
</c:forEach>