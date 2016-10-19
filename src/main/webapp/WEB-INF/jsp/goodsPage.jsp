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
    <script scr="/resources/js/jquery-3.1.0.min.js"></script>
    <title>Склад</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel='stylesheet' type='text/css'>
    <script src="/resources/js/jquery-3.1.0.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <style>
        body {
            background-image: url(http://blacktie.co/demo/premium/dashio/frontend/demos/sliderbg_01.png); /* Путь к фоновому изображению */
            background-size: 100% 100%;
        }
    </style>

</head>

<script>
    function getAllGoods() {
        $.ajax(
                {
                    type:'get',
                    url:'http://localhost:8080/table/goods',
                    success: function (data) {
                        $('#goodstable').html(data);
                        $('#goodstable').refresh();
                    }
                }
        )
    }
    function sortGoods(sorttype) {
        var type = sorttype.value;
        $.ajax(
                {
                    type:'get',
                    url:'http://localhost:8080/table/goods',
                    data:{
                        sort:type
                    },
                    success: function (data) {
                        $('#goodstable').html(data);
                    }
                })
     }
     function searchbyID() {
         var id = $('#id').val();
         $.ajax(
                 {
                     type:'get',
                     url:'http://localhost:8080/table/goods',
                     data:
                     {
                         byid:id
                     },
                    success: function (data) {
                        $('#goodstable').html(data);
                    }
                 }
         )
     }
     function searchbyName() {
         var name = $('#name').val();
         $.ajax(
                 {
                     type:'get',
                     url:'http://localhost:8080/table/goods',
                     data:
                     {
                         byname:name
                     },
                     success: function (data) {
                         $('#goodstable').html(data);
                     }
                 }
         )
     }
     function deleteGoods()
     {
         var vc = $('#vc').val();
         $.ajax({
             type:'get',
             url:'http://localhost:8080/table/goodsRemove',
             data:
             {
                 param:vc
             },
             success: function() {
                 $('#delresult').html('<font color="green">DONE</font>');
             },
             error: function () {
                 $('#delresult').html('<font color="red">ERROR</font>')
             }
         })
     }
     function addGoods()
     {
         $.ajax(
                 {
                     type:'get',
                     url:'http://localhost:8080/table/goodsAdd',
                     data:
                     {
                         vc:"2281337",
                         category:"dgdfg",
                         name:"jyhghj",
                         quantity:"34",
                         rp:"331",
                         wp:"228"
                     },
                     success: getAllGoods()
                 }
         )
     }
     function printTable()
     {
         var divToPrint=document.getElementById("goods");
         newWin= window.open("");
         newWin.document.write(divToPrint.outerHTML);
         newWin.print();
         newWin.close();
     }
     function showDeleteModal(info)
     {
         $('#deleteModal').modal('show');
     }
     function showaddGoodsModal()
     {
         $('#addModal').modal('show');
     }
     function showEditModal(info) {
         $('#editModal').modal('show');
     }
</script>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Курсовая</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Главная</a></li>
            <li><a href="#">Страница 1</a></li>
            <li><a href="#">Страница 2</a></li>
            <li><a href="#">Страница 3</a></li>
        </ul>
    </div>
</nav>

<div style="text-align: center;">
        <input type="number" id="vc" required placeholder="VENDOR CODE" min="1">
        <button onclick="deleteGoods()">Удалить</button>
</div>


<div class="container" style="width: auto !important;">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">

            <div class="panel panel-default panel-table">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col col-xs-6" style="width: auto !important;">
                            <button type="button" class="btn btn-sm btn-primary btn-create" onclick="getAllGoods()"><em class="glyphicon glyphicon-refresh"></em></button>

                        </div>
                        <div class="col col-xs-6 text-right" style="width: auto !important;">
                            <input class="form-control" type="number" id="id" required placeholder="Артикул товара" min="1" style="width: 200px;display: inline-block;">
                            <button onclick="searchbyID()" class="btn btn-sm btn-primary btn-create">Искать</button>
                            <input type="text" class="form-control" id="name" required placeholder="Название товара" style="width: 200px;display: inline-block;">
                            <button onclick="searchbyName()"  type="button" class="btn btn-sm btn-primary btn-create" >Искать</button>
                            <select class="form-control" name="sort" id="sort" onchange="sortGoods(this)" style="width: 200px;display: inline-block">
                                <option disabled selected>Сортировать по....</option>
                                <option value="byid" >По ID</option>
                                <option value="byiddesc" >По ID (В обратном)</option>
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
                <div class="panel-body" >
                    <table class="table table-striped table-bordered table-list" id="goods">
                        <thead>
                        <tr>
                            <th><em class="fa fa-cog"></em></th>
                            <th class="hidden-xs">Артикул</th>
                            <th >Категория</th>
                            <th width="25%">Название</th>
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
                <div class="panel-footer">

                </div>
            </div>
        </div>
    </div>
</div>

<div id="deleteModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Подтверждение Удаления</h4>
            </div>
            <div class="modal-body">
                <p>Вы действительно хотите удалить товар?</p>
                <p class="text-warning"><small>Бла бла бла</small></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                <button type="button" class="btn btn-danger">Удалить</button>
            </div>
        </div>
    </div>
</div>
<div id="addModal" class="modal fade">
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
                <input type="text" id="goodscategory" placeholder="Категория" class="form-control">
                <br>
                <label for="goodsname" >Название товара</label>
                <input type="text" id="goodsname" placeholder="Название" class="form-control">
                <br>
                <label for="goodsquantity" >Количество</label>
                <input type="number" min="0" id="goodsquantity" placeholder="Количество" class="form-control">
                <br>
                <label for="goodsrp" >Розничная цена</label>
                <input type="number" min="0" id="goodsrp" placeholder="Розничная цена" class="form-control">
                <br>
                <label for="goodswp" >Оптовая цена</label>
                <input type="number" min="0" id="goodswp" placeholder="Оптовая цена" class="form-control">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                <button type="button" class="btn btn-success">Добавить</button>
            </div>
        </div>
    </div>
</div>
<div id="editModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Добавление товара</h4>
            </div>
            <div class="modal-body">
                <label for="egoodsvc">Артикул</label>
                <input disabled type="number" min="1" id="egoodsvc" placeholder="Артикул" class="form-control">
                <br>
                <label for="egoodscategory" >Категория</label>
                <input type="text" id="egoodscategory" placeholder="Категория" class="form-control">
                <br>
                <label for="egoodsname" >Название товара</label>
                <input type="text" id="egoodsname" placeholder="Название" class="form-control">
                <br>
                <label for="egoodsquantity" >Количество</label>
                <input type="number" min="0" id="egoodsquantity" placeholder="Количество" class="form-control">
                <br>
                <label for="egoodsrp" >Розничная цена</label>
                <input type="number" min="0" id="egoodsrp" placeholder="Розничная цена" class="form-control">
                <br>
                <label for="egoodswp" >Оптовая цена</label>
                <input type="number" min="0" id="egoodswp" placeholder="Оптовая цена" class="form-control">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                <button type="button" class="btn btn-success">Добавить</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>


