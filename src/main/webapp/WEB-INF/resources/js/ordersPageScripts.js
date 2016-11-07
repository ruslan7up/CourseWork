/**
 * Created by User on 27.10.2016.
 */
var goods = [];
function cleanAddModal() {
    $('#rows').html("<div style='display: inline-block;'> <input type='text' name='ordergn' placeholder='Название' style='width: 350px' class='form-control' > </div> <div style='display: inline-block'> <input type='number' name='orderquantity' placeholder='Количество' class='form-control'> </div>");
}

function showaddModal() {
    $('#addModal').modal('show');
}

function addRow() {
    $('#rows').append("<br><br><div style='display: inline-block;'> <input type='text'  name='ordergn' placeholder='Название' style='width: 350px' class='form-control' > </div> <div style='display: inline-block'> <input type='number' name='orderquantity'  placeholder='Количество' class='form-control'> </div>");
}

function addOrder() {
    var jsonRows=[];
    $('.rows').each(function (index,element) {
        jsonRows.push({
            'name':$($(element).children('.name')).val(),
            'quantity':$($(element).children('.quantity')).val()
        })
    });
    $.ajax({
        type:'post',
        url:'../orders',
        data:{
            'orderRows':JSON.stringify(jsonRows)
        },
        success:function (a) {
            alert(a);
        }
    })

}

function showOrderlist(id) {
    $.ajax({
        type: 'get',
        url:'http://localhost:8080/table/getOrderList',
        data: {
            param:id
        },
        success: function (data) {
            $('#orderlisttable').html(data);
            $('#orderlist').modal('show');
            $('#ordernumber').html('Состав заказа №'+id);
        },
        error: function() {
            alert('Возникла ошибка при загрузке состава заказа!')
        }
    })

}