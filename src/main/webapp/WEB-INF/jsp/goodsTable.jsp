<%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 05.10.2016
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${goods == null}">
    <tr>
        <td colspan="7" style="text-align: center;" >Товаров нет</td>
    </tr>
</c:if>
<c:forEach var="good" items="${goods}" varStatus="loop">
    <tr>
        <td align="center" style="width: 100px;">
            <a class="btn btn-default" title="Редактировать" onclick="showEditModal(this)"><em class="fa fa-pencil"></em></a>
            <a class="btn btn-danger" title="Удалить" onclick="showDeleteModal(${good.getId()})"><em class="fa fa-trash" ></em></a>
        </td>
        <td class="hidden-xs" style="height: 50px;">${good.getId()}</td>
        <td >${good.getCategory()}</td>
        <td>${good.getName()}</td>
        <td>${good.getQuantity()}</td>
        <td>${good.getRetailPrice()}</td>
        <td>${good.getWholesalePrice()}</td>
    </tr>
</c:forEach>