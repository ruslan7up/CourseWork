/**
 * Created by ruslan on 24.10.2016.
 */
var selected;
function getallshops()
{
    $.ajax(
        {
            type:'get',
            url:'/table/shops',
            data :
            {

            },
            success: function (data) {
                $('#shoptable').html(data)
            }
        }
    )
}

function addshop()
{
    $.ajax(
        {
            type:'get',
            url:'/table/shopsAdd',
            data: {
                id:$('#shopid').val(),
                shopname:$('#shopname').val(),
                address:$('#shopaddress').val(),
                pn:$('#shoptn').val()
            },
            success: function () {
                $('#addModal').modal('hide');
                getallshops();
            },
            error: function () {
                alert('Ошибка! Проверьте правильность ввода данных.');
            }
        }
    )
}

function searchByID() {
    $.ajax(
        {
            type:'get',
            url:'/table/shops',
            data:
            {
                byid:$('#id').val()
            },
            success: function (data) {
                $('#shoptable').html(data)
            }
        }
    )
}

function searchByName() {
    $.ajax(
        {
            type:'get',
            url:'/table/shops',
            data:
            {
                byname:$('#name').val()
            },
            success: function (data) {
                $('#shoptable').html(data)
            }
        }
    )
}

function cleanaddform() {
    $('#shopid').val("");
    $('#shopname').val("");
    $('#shoptn').val("");
    $('#shopaddress').val("");
}

function showaddmodal() {
    $('#addModal').modal('show');
}

function showdelmodal(id) {
    selected=id;
    $('#deleteModal').modal('show');
}

function removeShop() {
    $.ajax(
        {
            type:'get',
            url:'/table/shopDelete',
            data: {
                param:selected
            },
            success: function () {
                $('#deleteModal').modal('hide');
              getallshops();
            }
        }
    )
}