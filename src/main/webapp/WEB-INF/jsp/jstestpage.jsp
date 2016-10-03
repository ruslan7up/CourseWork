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

    <script>
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
        function deleteTable(trId) {
           $(trId).remove();
        }
        function addRowToTable() {
            <c:forEach var="i" begin="3" end="6">
            $('#usertable').append('<tr id="${i}"><td>${i}</td><td>number ${i}</td><td>dsffd ${i}</td></tr>')
            </c:forEach>

        }
        function loadAllUsers()
        {

        }
    </script>
</head>
<body>
<input id="param1" type="text">
<input id="param2" type="text">
<button onclick="getFunction()" > GET FUNC </button>
<button onclick="postFunction()"> POST FUNC </button>
<button onclick="deleteTable()"> TEST DELETE TABLE ROWS FUNC</button>
<button onclick="addRowToTable()"> TEST ADD ROW FUNC</button>
<table border="1" id="usertable">
    <thead>
    <tr>
        <th>dsfsd</th>
        <th>sdfdsf</th>
        <th>sdfdsf</th>
    </tr>
    </thead>
    <tbody>
    <tr id="sdfsdf">
        <td>1</td>
        <td>sqad</td>
        <td>fdvdfcvb</td>
        <td><button type="submit" onclick="deleteTable(this)">DEL</button> </td>
    </tr>
    <tr id="wefsd">
        <td>2</td>
        <td>sqfsdgvdf</td>
        <td>dfgdfhgj</td>
        <td><button type="submit" onclick="deleteTable(this)">DEL</button> </td>
    </tr>
    </tbody>
</table>
    <h1 id="status">STATUS</h1>
    <h1 id="insertHere"></h1>

</body>
</html>