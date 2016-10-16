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
</head>

<script>
    var selectedRows = [];

    function getAllGoods() {
        $.ajax(
                {
                    type:'get',
                    url:'http://localhost:8080/table/goods',
                    success: function (data) {
                        $('#goodstable').html(data);
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
                     }
                 }
         )
     }
     function printTable()
     {
         var divToPrint=document.getElementById("table");
         newWin= window.open("");
         newWin.document.write(divToPrint.outerHTML);
         newWin.print();
         newWin.close();
     }
     function selectRow(tr) {
             $(tr).css({"background-color": "white"});
     }
     function deleteRows()
     {

     }
     function showDeleteModal(goods)
     {
         $('#deleteModal').modal('show');
     }
</script>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Курсовая</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#">Добавить товар</a></li>
            <li><a href="#">Page 2</a></li>
            <li><a href="#">Page 3</a></li>
        </ul>
    </div>
</nav>

<div style="text-align: center;">
<button onclick="getAllGoods()" >LOAD ALL GOODS</button>
<select name="sort" id="sort" onchange="sortGoods(this)">
    <option disabled selected>Сортировать по....</option>
    <option value="byid" >По ID</option>
    <option value="byiddesc" >По ID (В обратном)</option>
    <option value="byname" >По Имени</option>
    <option value="bynamedesc" >По Имени (В обратном)</option>
    <option value="byquantity">По Количеству</option>
    <option value="byquantitydesc">По Количеству(В обратном)</option>
</select>
        <input type="number" id="id" required placeholder="ID" min="1">
        <button onclick="searchbyID()">Искать</button>
        <input type="text" id="name" required placeholder="NAME">
        <button onclick="searchbyName()">Искать</button>
        <input type="number" id="vc" required placeholder="VENDOR CODE" min="1">
        <button onclick="deleteGoods()">Удалить</button>
        <button onclick="printTable()">Распечатать</button>
        <button onclick="addGoods()">ADD</button>
</div>


<div class="container" style="width: auto !important;">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">

            <div class="panel panel-default panel-table">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col col-xs-6">
                            <h3 class="panel-title">Panel Heading</h3>
                        </div>
                        <div class="col col-xs-6 text-right">
                            <button type="button" class="btn btn-sm btn-primary btn-create" >Добавить</button>
                        </div>
                    </div>
                </div>
                <div class="panel-body" >
                    <table class="table table-striped table-bordered table-list" >
                        <thead>
                        <tr>
                            <th><em class="fa fa-cog"></em></th>
                            <th class="hidden-xs">Артикул</th>
                            <th>Категория</th>
                            <th width="25%">Название</th>
                            <th>Количество</th>
                            <th>Розничная цена</th>
                            <th>Оптовая цена</th>
                        </tr>
                        </thead>
                        <tbody id="goodstable">
                        <c:forEach var="good" items="${goods}">
                        <tr >
                            <td align="center">
                                <a class="btn btn-default"><em class="fa fa-pencil"></em></a>
                                <a class="btn btn-danger"><em class="fa fa-trash" onclick="showDeleteModal(this)"></em></a>
                            </td>
                            <td class="hidden-xs">${good.getId()}</td>
                            <td>${good.getCategory()}</td>
                            <td>${good.getName()}</td>
                            <td>${good.getQuantity()}</td>
                            <td>${good.getRetailPrice()}</td>
                            <td>${good.getWholesalePrice()}</td>
                        </tr>
                        </c:forEach>
                        </tbody>
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
                <button type="button" class="btn btn-primary">Удалить</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>


