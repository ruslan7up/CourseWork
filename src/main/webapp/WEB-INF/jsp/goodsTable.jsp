<%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 05.10.2016
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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