<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 01.10.2016
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <script src="/resources/js/jquery-3.1.0.min.js"></script>
    <title>TEST PANEL</title>
    <script>
        var selectedRow;
        function getFunction (){
            var p1 = $('#param1').val();
            var p2 = $('#param2').val();

            $.ajax({
                type:'get',
                url:'http://localhost:8080/tests/test',
                data:{
                    param1:p1,
                    param2:p2
                },
                success: function(data){
                    $('#insertHere').append(data);
                    $('#status').html('<font color="green">DONE</font>');
                },
                error: function () {
                    $('#status').html('<font color="red">ERROR</font>');

                }
            });
        }
        function postFunction() {
            var p1 = $('#param1').val();
            var p2 = $('#param2').val();

            $.ajax(
                    {
                        type:'post',
                        url:'http://localhost:8080/tests/test',
                        data:{
                            param1:p1,
                            param2:p2
                        },
                        success: function (data) {
                            $('#insertHere').append(data);
                            $('#status').html('<font color="green">DONE</font>');
                        },
                        error: function () {
                            $('#status').html('<font color="red">ERROR</font>');
                        }
                    }
            );
        }
        function deleteTable() {
           $('#tableid').remove();
        }
        function addRowToTable() {
            <c:forEach var="i" begin="3" end="6">
            $('#tableid').append('<tr onclick="selectRow(this)"><td>${i}</td><td>number ${i}</td><td>dsffd ${i}</td></tr>')
            </c:forEach>

        }
        function loadAllUsers()
        {

            $.ajax(
                    {
                        type:'get',
                        url:'http://localhost:8080/table/users',
                        data:{

                        },
                        success: function (data) {
                            $('#tableid').html(data);
                        }
                    }
            )
        }
        function selectRow(row)
        {
            $(selectedRow).css({"background-color": "white","font-weight":"400"});
            selectedRow=row;
            $(selectedRow).css({"background-color" : "red","font-weight":"600"});
        }
        function deleteRow() {
            selectedRow.remove();
        }
    </script>
</head>
<body>
<input id="param1" type="text" placeholder="MESSAGE 1">
<input id="param2" type="text" placeholder="MESSAGE 2">
<button onclick="getFunction()" > GET FUNC </button>
<button onclick="postFunction()"> POST FUNC </button>
<button onclick="deleteTable()"> TEST DELETE TABLE ROWS FUNC</button>
<button onclick="addRowToTable()"> TEST ADD ROW FUNC</button>
<button onclick="loadAllUsers()">LOAD</button>
<button onclick="deleteRow()">DELETE SELECTED ROW</button>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Login</th>
        <th>Password</th>
    </tr>
    </thead>
    <tbody id="tableid">
    <tr onclick="selectRow(this)">
        <td>1</td>
        <td>TEST</td>
        <td>MESSAGE</td>
    </tr>
    </tbody>
</table>
    <h1 id="status">THE STATUS OF THE LAST REQUEST</h1>
    <h1 id="insertHere"></h1>

</body>
</html>