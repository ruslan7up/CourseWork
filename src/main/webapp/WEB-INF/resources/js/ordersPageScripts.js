/**
 * Created by User on 27.10.2016.
 */
var goods = [];
function cleanAddModal() {
    $('#rows').html('<input type="text" class="name" name="ordergn" placeholder="Название" style="width: 350px" class="form-control" >' +
    '<input type="number" class="quantity"  name="orderquantity" placeholder="Количество" class="form-control">');
}

function showaddModal() {
    $('#addModal').modal('show');
}

function addRow() {
    $('#rows').append("<br><br>" +
        "<div class='rows'>" +
        "<input type='text'  class='name' name='ordergn' placeholder='Название' style='width: 350px' class='form-control' > " +
        "<input type='number' class='quantity' name='orderquantity'  placeholder='Количество' class='form-control'> </div>"
    );
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
        type:'get',
        url:'/table/OrderAdd',
        data:{
            'orderRows':JSON.stringify(jsonRows)
        },
        success:function (a) {
            alert(a);
        }
    })

}

function getAllOrders() {
 $.ajax({
     type:'get',
     url:'http://localhost:8080/table/orders',
     success: function (data) {
         $('#acctable').html(data);
         $('#id').val("");
     }
 })
}
function showOrderlist(id) {
    $.ajax({
        type: 'get',
        url: 'http://localhost:8080/table/getOrderList',
        data: {
            param: id
        },
        success: function (data) {
            $('#orderlisttable').html(data);
            $('#ordernumber').html('Состав заказа №' + id);
            $('#orderlist').modal('show');
        },
        error: function () {
            alert('Возникла ошибка при загрузке состава заказа!')
        }
    })
}
function searchByID() {
    $.ajax({
        type:'get',
        url:'/table/orders',
        data: {
            byid:$('#id').val()
        },
        success: function (data) {
            $('#acctable').html(data);
        }
    })

}