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
                url:'http://localhost:8080/tests/test2GET',
                data:{
                    param1:p1,
                    param2:p2
                },
                success: function(data){
                    $('#insertHere').append(data);
                },
                error: function () {
                    $('#insertHere').append('ERROR');

                }
            });
        }
        function postFunction() {
            var p1 = $('#param1').val();
            var p2 = $('#param2').val();

            $.ajax(
                    {
                        type:'post',
                        url:'http://localhost:8080/tests/test2POST',
                        data:{
                            param1:p1,
                            param2:p2
                        },
                        success: function (data) {
                            $('#insertHere').append(data);
                        },
                        error: function () {
                            $('#insertHere').append('ERROR');
                        }
                    }
            );
        }

        function deleteRow(trId) {
            $(trId).remove();

        }
    </script>
</head>
<body>
<input id="param1" type="text">
<input id="param2" type="text">
<button onclick="getFunction()" > GET FUNC </button>
<button onclick="postFunction()"> POST FUNC </button>
    <h1>ТЕКСТ</h1>
    <h1 id="insertHere"></h1>

</body>
</html>