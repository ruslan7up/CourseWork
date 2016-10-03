<%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 03.10.2016
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:forEach var="good" items="${goods}">
    <tr>
        <td>${good.getId()}</td>
        <td>${good.getCategory()}</td>
        <td>${good.getName()}</td>
        <td>${good.getQuantity()}</td>
        <td>${good.getRetailPrice()}</td>
        <td>${good.getWholesalePrice()}</td>
    </tr>
</c:forEach>