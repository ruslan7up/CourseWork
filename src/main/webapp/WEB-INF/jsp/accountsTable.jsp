<%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 22.10.2016
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${accounts == null}">
    <tr>
        <td colspan="3" style="text-align: center;" >Пользователей нет</td>
    </tr>
</c:if>
<c:forEach var="acc" items="${accounts}" varStatus="loop">
    <tr>
        <td align="center" style="width: 50px;">
            <a class="btn btn-danger" title="Удалить" onclick="showDeleteModal(${acc.getId()})"><em class="fa fa-trash" ></em></a>
        </td>
        <td class="hidden-xs" style="height: 50px;">${acc.getId()}</td>
        <td>${acc.getLogin()}</td>
        <td>${acc.getPass()}</td>
    </tr>
</c:forEach>