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
        <td colspan="5" style="text-align: center;">Заказов нет</td>
    </tr>
</c:if>
<c:forEach items="${orders}" var="order">
    <tr>
        <td align="center" style="width: 100px;">
            <a class="btn btn-default" title="Изменить статус" onclick="showStatusEditModal(${order.getId()},'${order.getStatus()}')"><em class="glyphicon glyphicon-pushpin"></em></a>
            <a class="btn btn-danger" title="Удалить" onclick="showdelmodal(${order.getId()})"><em class="fa fa-trash" ></em></a>
        </td>
        <td class="hidden-xs" style="height: 50px;">${order.getId()}</td>
        <td>${order.getStatus()}</td>
        <td><button type="button" class="btn btn-info center-block " onclick="showOrderlist(${order.getId()})">Открыть состав</button></td>
        <td>${order.getDate()}</td>
    </tr>
</c:forEach>