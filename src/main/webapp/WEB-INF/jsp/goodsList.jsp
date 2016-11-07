<%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 06.11.2016
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${list == null}">
    <tr>
        <td colspan="2" style="text-align: center;">Товаров нет</td>
    </tr>
</c:if>
<c:forEach items="${list}" var="goods">
    <tr>
        <td>${goods.getGoodsname()}</td>
        <td>${goods.getQuantity()}</td>
    </tr>
</c:forEach>