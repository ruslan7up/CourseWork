<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.10.2016
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${orders == null}">
    <tr>
        <td colspan="4" style="text-align: center;">Заказов нет</td>
    </tr>
</c:if>
<c:forEach items="${orders}" var="order">
    <tr>
        <td align="center" style="width: 50px;">
            <a class="btn btn-danger" title="Удалить" onclick="showdelmodal(${order.getId()})"><em class="fa fa-trash" ></em></a>
        </td>
        <td class="hidden-xs" style="height: 50px;">${order.getId()}</td>
        <td><button type="button" class="btn btn-info pull-center">Открыть состав</button></td>
        <td>${order.getDate()}</td>
    </tr>
</c:forEach>