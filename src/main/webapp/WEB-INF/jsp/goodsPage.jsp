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
    <link rel="shortcut icon" href="/resources/images/icon.ico" type="image/ico">
    <script scr="/resources/js/jquery-3.1.0.min.js"></script>
    <title>Склад</title> <!-- Заголовок страницы -->
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
            <a class="navbar-brand" href="http://www.bayansulu.kz/" target="_blank">Баян-сулу</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="/goods/goodsPanel">Склад</a></li>
            <li ><a href="/orders/info" >Заказы</a> </li>
            <li ><a href="/shop/info" >Магазины</a></li>
            <li><a href="/admin/allPanels">Сотрудники</a></li>
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
                            <button type="button" class="btn btn-sm btn-primary btn-create" onclick="getAllGoods()" title="Обновить таблицу"><em class="glyphicon glyphicon-refresh"></em></button>

                        </div>
                        <div class="col col-xs-6 pull-right" style="width: auto !important;" >
                            <input class="form-control" type="number" id="id" required placeholder="Артикул товара" min="1" style="width: 200px;display: inline-block;">
                            <button onclick="searchbyID()" class="btn btn-sm btn-primary btn-create">Искать</button>
                            <input type="text" class="form-control" id="name" required placeholder="Название товара" style="width: 200px;display: inline-block;">
                            <button onclick="searchbyName()"  type="button" class="btn btn-sm btn-primary btn-create" >Искать</button>
                            <select class="form-control" name="sort" id="sort" onchange="sortGoods(this)" style="width: 200px;display: inline-block">
                                <option disabled >Сортировать по....</option>
                                <option value="byid" selected>По Артикулу</option>
                                <option value="byiddesc" >По Артикулу (В обратном)</option>
                                <option value="byname" >По Имени</option>
                                <option value="bynamedesc" >По Имени (В обратном)</option>
                                <option value="byquantity">По Количеству</option>
                                <option value="byquantitydesc">По Количеству(В обратном)</option>
                            </select>
                            <button type="button" class="btn btn-sm btn-primary btn-create" onclick="printTable()">Распечатать</button>
                            <button type="button" class="btn btn-sm btn-primary btn-create" onclick="showaddGoodsModal()">Добавить</button>
                        </div>
                    </div>
                </div>
                <div class="panel-body"  style="height: auto !important; max-height: 80%; overflow-y:scroll;">
                    <table class="table table-striped table-bordered table-list" id="goods" border="1" >
                        <thead>
                        <tr>
                            <th><em class="fa fa-cog"></em></th>
                            <th class="hidden-xs">Артикул</th>
                            <th >Категория</th>
                            <th style="width:25%;">Название</th>
                            <th >Количество</th>
                            <th >Розничная цена</th>
                            <th >Оптовая цена</th>
                        </tr>
                        </thead>
                        <tbody id="goodstable">
                        <%@include file="goodsTable.jsp"%>
                        </tbody>
                        <thead>
                        <tr>
                            <th><em class="fa fa-cog" ></em></th>
                            <th class="hidden-xs">Артикул</th>
                            <th >Категория</th>
                            <th width="25%">Название</th>
                            <th >Количество</th>
                            <th >Розничная цена</th>
                            <th >Оптовая цена</th>
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
                <p>Вы действительно хотите удалить товар?</p>
                <p class="text-warning"><small>После удаления данный товар будет невозможно восстановить!</small></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" >Отмена</button>
                <button type="button" class="btn btn-danger" onclick="deleteGoods(selectedGoods)">Удалить</button>
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
                <h4 class="modal-title">Добавление товара</h4>
            </div>
            <div class="modal-body">
                <label for="goodsvc">Артикул</label>
                <input type="number" min="1" id="goodsvc" placeholder="Артикул" class="form-control">
                <br>
                <label for="goodscategory" >Категория</label>
                <select class="form-control" name="sort" id="goodscategory">
                    <option value="Без категории" selected>Без категории</option>
                    <option value="Вафли" >Вафли</option>
                    <option value="Драже" >Драже</option>
                    <option value="Зефир" >Зефир</option>
                    <option value="Ирис" >Ирис</option>
                    <option value="Карамель">Карамель</option>
                    <option value="Конфеты">Конфеты</option>
                    <option value="Мармелад">Мармелад</option>
                    <option value="Набор Шоколадных конфет">Набор шоколадных конфет</option>
                    <option value="Шоколад">Шоколад</option>
                    <option value="Печенье">Печенье</option>
                </select>
                <br>
                <label for="goodsname" >Название товара</label>
                <input type="text" id="goodsname" placeholder="Название" class="form-control">
                <br>
                <label for="goodsquantity" >Количество</label>
                <input type="number" min="0" id="goodsquantity" placeholder="Количество" class="form-control">
                <br>
                <label for="goodsrp" >Розничная цена</label>
                <input type="number"  onchange="calculatewp()" min="0" id="goodsrp" placeholder="Розничная цена" class="form-control">
                <br>
                <label for="goodswp" >Оптовая цена</label>
                <input type="number" min="0" id="goodswp" placeholder="Расчитает автоматически" class="form-control" disabled>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="cleanAddModal()">Отмена</button>
                <button type="button" class="btn btn-success" onclick="addGoods()">Добавить</button>
            </div>
        </div>
    </div>
</div>
<!-- Форма добавления Конец-->

<!-- Форма редактирования Начало -->
<div id="editModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Редактирование товара</h4>
            </div>
            <div class="modal-body">
                <label for="egoodsvc">Артикул</label>
                <input disabled type="number" min="1" id="egoodsvc" placeholder="Артикул" class="form-control">
                <br>
                <label for="egoodscategory" >Категория</label>
                <select class="form-control" name="sort" id="egoodscategory">
                    <option value="Без категории" selected>Без категории</option>
                    <option value="Вафли" >Вафли</option>
                    <option value="Драже" >Драже</option>
                    <option value="Зефир" >Зефир</option>
                    <option value="Ирис" >Ирис</option>
                    <option value="Карамель">Карамель</option>
                    <option value="Конфеты">Конфеты</option>
                    <option value="Мармелад">Мармелад</option>
                    <option value="Набор Шоколадных конфет">Набор шоколадных конфет</option>
                    <option value="Шоколад">Шоколад</option>
                    <option value="Печенье">Печенье</option>
                </select>
                <br>
                <label for="egoodsname" >Название товара</label>
                <input type="text" id="egoodsname" placeholder="Название" class="form-control">
                <br>
                <label for="egoodsquantity" >Количество</label>
                <input type="number" min="0" id="egoodsquantity" placeholder="Количество" class="form-control">
                <br>
                <label for="egoodsrp" >Розничная цена</label>
                <input type="number" min="0" id="egoodsrp" onchange="ecalculatewp()" placeholder="Розничная цена" class="form-control">
                <br>
                <label for="egoodswp" >Оптовая цена</label>
                <input type="number" min="0" id="egoodswp" disabled placeholder="Расчитает автоматически" class="form-control">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                <button type="button" class="btn btn-success" onclick="updateGoods()">Изменить</button>
            </div>
        </div>
    </div>
</div>
<!-- Форма редактирования Конец -->


<script>
    $(document).ready(function(){
        $("#addModal").on('hidden.bs.modal', function () {
            cleanAddModal();
        });
    });
</script>

<!-- Подключение скриптов для работы со складом -->
<script src="/resources/js/goodsPageScripts.js"></script>
</body>
</html>


