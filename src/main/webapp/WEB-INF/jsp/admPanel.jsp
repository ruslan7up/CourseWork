<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 01.10.2016
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="/resources/images/icon1.ico" type="image/ico">
    <script scr="/resources/js/jquery-3.1.0.min.js"></script>
    <title>Сотрудники (BETA)</title> <!-- Заголовок страницы -->
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel='stylesheet' type='text/css'>
    <script src="/resources/js/jquery-3.1.0.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <style>
        body {
            background-image: url(/resources/images/sliderbg_01.png); /* Путь к фоновому изображению */
            background-size: 100% 100%; /* Растянуть фоновое изображение */
        }
    </style>

</head>

<!-- Шапка начало -->
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="http://www.bayansulu.kz/">Баян-сулу</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/goods/goodsPanel">Склад</a></li>
            <li><a href="/orders/info" >Заказы</a> </li>
            <li><a href="/shop/info" >Магазины</a></li>
            <li class="active"><a href="/admin/allPanels">Сотрудники</a></li>
        </ul>
        <ul class="nav navbar-nav pull-right">
            <li><a href="/users/logout">Выход</a></li>
        </ul>
    </div>
</nav>
<!-- Шапка конец -->

<!-- Таблица начало -->
<div class="container" style="width: auto !important;">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">

            <div class="panel panel-default panel-table">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col col-xs-6" style="width: auto !important;">
                            <button type="button" class="btn btn-sm btn-primary btn-create" onclick="getAllUsers()" title="Обновить таблицу"><em class="glyphicon glyphicon-refresh"></em></button>

                        </div>
                        <div class="col col-xs-6 pull-right" style="width: auto !important;" >
                            <input class="form-control" type="number" id="id" required placeholder="ID" min="1" style="width: 200px;display: inline-block;">
                            <button onclick="searchByID()" class="btn btn-sm btn-primary btn-create">Искать</button>
                            <input type="text" class="form-control" id="login" required placeholder="Логин" style="width: 200px;display: inline-block;">
                            <button onclick="searchByLogin()"  type="button" class="btn btn-sm btn-primary btn-create" >Искать</button>
                            <button type="button" class="btn btn-sm btn-primary btn-create" onclick="showaddModal()">Добавить</button>
                        </div>
                    </div>
                </div>
                <div class="panel-body"  style="height: auto !important; max-height: 80%; overflow-y:scroll;">
                    <table class="table table-striped table-bordered table-list" id="goods" border="1" >
                        <thead>
                        <tr>
                            <th><em class="fa fa-cog" ></em></th>
                            <th class="hidden-xs">ID</th>
                            <th>Ф.И.О.</th>
                            <th >Логин</th>
                            <th style="width:auto;">Пароль(MD5)</th>
                        </tr>
                        </thead>
                        <tbody id="acctable">
                        <%@include file="accountsTable.jsp"%>
                        </tbody>
                        <thead>
                        <tr>
                            <th><em class="fa fa-cog" ></em></th>
                            <th class="hidden-xs">ID</th>
                            <th>Ф.И.О.</th>
                            <th >Логин</th>
                            <th width="25%">Пароль(MD5)</th>
                        </tr>
                        </thead>
                    </table>

                </div>
                <div class="panel-footer" style="height: 30px;">
                    <div class="pull-right">
                        <p>&copy; 2016 <a href="https://vk.com/ruslanw" target="_blank">VK</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Таблица конец -->

<!-- Форма удаления Начало-->
<div id="deleteModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Подтверждение Удаления</h4>
            </div>
            <div class="modal-body">
                <p>Вы действительно хотите удалить аккаунт?</p>
                <p class="text-warning"><small>После удаления данный аккаунт будет невозможно восстановить!</small></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" >Отмена</button>
                <button type="button" class="btn btn-danger" onclick="removeAcc()">Удалить</button>
            </div>
        </div>
    </div>
</div>
<!-- Форма удаления Конец -->

<!-- Форма добавления Начало-->
<div id="addModal" class="modal fade" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Добавление пользователя</h4>
            </div>
            <div class="modal-body">
                <label for="accid">ID</label>
                <input type="number" min="1" id="accid" disabled placeholder="Сгенерируется автоматически" class="form-control">
                <br>
                <label for="accfn">Ф.И.О.</label>
                <input type="text" id="accfn" placeholder="Ф.И.О." class="form-control">
                <br>
                <label for="acclog" >Логин</label>
                <input type="text" id="acclog" placeholder="Логин" class="form-control">
                <br>
                <label for="accpass" >Пароль</label>
                <input type="password" id="accpass" placeholder="Пароль" class="form-control">
                <br>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="cleanAddModal()">Отмена</button>
                <button type="button" class="btn btn-success" onclick="addAcc()">Добавить</button>
            </div>
        </div>
    </div>
</div>
<!-- Форма добавления Конец-->
<script>
    $(document).ready(function(){
        $("#addModal").on('hidden.bs.modal', function () {
            cleanAddModal();
        });
    });
</script>

<!-- Подключение скриптов для работы с панелью администратора -->
<script src="/resources/js/accountsPageScripts.js"></script>
</body>
</html>


