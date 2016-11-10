/**
 * Created by User on 27.10.2016.
 */
var id;
function showStatusEditModal(orderid,currentstatus) {
    id=orderid;
    $('#statusid').html('Изменить статус заказа №'+orderid);
    $('#orderstatus').val(currentstatus);
    $('#statusEditModal').modal('show');

}

function setStatus() {
    $.ajax({
        type:'get',
        url:'/table/setNewStatus',
        data: {
            orderid:id,
            status:$('#orderstatus option:selected').val()
        },
        success: function () {
            $('#statusEditModal').modal('hide');
            getAllOrders();
        }
    })
}
function cleanAddModal() {
    $('#rows').html('<div class="rows"><input type="number" min="1" class="name form-control" name="ordergn" placeholder="Артикул" style="width: 350px" >' +
    '<input type="number" min="1" class="quantity form-control"  name="orderquantity" placeholder="Количество" ></div>');
}

function showaddModal() {
    $('#addModal').modal('show');
}

function addRow() {
    $('#rows').append("<br>" +
        "<div class='rows'>" +
        "<input type='number' min='1'  class='name form-control' name='ordergn' placeholder='Артикул' style='width: 350px' > " +
        "<input type='number' min='1' class='quantity form-control' name='orderquantity'  placeholder='Количество'> </div>"
    );
}
function addOrder() {
    var jsonRows=[];
    var a;
    var b;
    $('.rows').each(function (index,element) {
        jsonRows.push({
            'name':$.trim($($(element).children('.name')).val().trim()),
            'quantity':$.trim($($(element).children('.quantity')).val())
        })
    });
    $.ajax({
        type:'get',
        url:'/table/OrderAdd',
        data:{
            'orderRows':JSON.stringify(jsonRows)
        },
        success:function (a) {
            $('#addModal').modal('hide');
            getAllOrders();
        },
        error: function () {
            alert('Ошибка! Проверьте правильность ввода данных.');
        }
    })

}

function showdelmodal(orderid) {
    id=orderid;
    $('#deleteModal').modal('show');
}

function deleteOrder() {
    $.ajax({
        type:'get',
        url:'/table/OrderDelete',
        data: {
            param:id
        },
        success: function (data) {
            $('#deleteModal').modal('hide');
            getAllOrders();
        }
    })
}
function getAllOrders() {
 $.ajax({
     type:'get',
     url:'/table/orders',
     success: function (data) {
         $('#acctable').html(data);
         $('#id').val("");
     }
 })
}
function showOrderlist(id) {
    $.ajax({
        type: 'get',
        url: '/table/getOrderList',
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