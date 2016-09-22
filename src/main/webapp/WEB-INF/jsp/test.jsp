<html>
<head>
    <script src="/resources/js/jquery-3.1.0.min.js"></script>

    <script>
        function myFunction (){
            var n = $('#name').val();
            var h = $('#phrase').val();

            $.ajax({
                type:'get',
                url:'http://localhost:8080/users/greetings',
                data:{
                    user:n,
                    pass:h
                },
                success: function(data){
                    $('#testdiv').html(data);
                }
            });



        }
        function myPOSTFunction (){
            var n = $('#name').val();
            var h = $('#phrase').val();

            $.ajax({
                type:'post',
                url:'http://localhost:8080/FistApp/hello',
                data:{
                    name:n,
                    hello:h
                },
                success: function(data){
                    $('#testdiv').html(data);
                }
            });



        }
    </script>
</head>
<body>
<input id="name" type="text">
<input id="phrase" type="text">
<button onclick="myFunction()" > asdfdsf </button>
<button onclick="myPOSTFunction()" > POST Button </button>

<div id="testdiv"></div>
</body>
</html>