<%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 24.10.2016
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${shops == null}">
    <tr>
        <td colspan="4" style="text-align: center;">Магазинов нету</td>
    </tr>
</c:if>
<c:forEach items="${shops}" var="shop">
    <tr>
        <td align="center" style="width: 50px;">
            <a class="btn btn-danger" title="Удалить" <%--onclick="showDeleteModal(${shop.getId()})"--%>><em class="fa fa-trash" ></em></a>
        </td>
        <td class="hidden-xs" style="height: 50px;">${shop.getId()}</td>
        <td>${shop.getShopname()}</td>
        <td>${shop.getAddress()}</td>
        <td>${shop.getPhonenumber()}</td>
    </tr>
</c:forEach>