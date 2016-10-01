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
        function myFunction (){
            var n = $('#name').val();
            var h = $('#phrase').val();

            $.ajax({
                type:'get',
                url:'http://localhost:8080/tests/test2GET',
                data:{
                    name:n,
                    hello:h
                },
                success: function(data){
                    $('#insertHere').append(data);
                },
                error: function () {
                    $('#insertHere').append('ERROR');
                }
            });
        }


        function deleteRow(trId) {
            $(trId).remove();

        }
    </script>
</head>
<body>
<input id="name" type="text">
<input id="phrase" type="text">
<button onclick="myFunction()" > asdfdsf </button>

<table>
    <thead>
    <tr>
        <th>
            name
        </th>
        <th>
            Hello
        </th>
    </tr>
    </thead>
    <tbody id="insertHere">

    </tbody>
</table>
</body>
</html>