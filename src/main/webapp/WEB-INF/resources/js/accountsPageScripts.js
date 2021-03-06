/**
 * Created by ruslan on 22.10.2016.
 */
var selectedAccount;

function cleanAddModal() {
    $('#accid').val("");
    $('#accfn').val("");
    $('#acclog').val("");
    $('#accpass').val("");
}

function showDeleteModal(id)
{
    selectedAccount=id;
    $('#deleteModal').modal('show');
}

function showaddModal() {
    $('#addModal').modal('show');
}

function getAllUsers() {
    $.ajax({
        type:'get',
            url:'/table/users',
        success: function (data) {
            $('#acctable').html(data);
            $('#id').val("");
            $('#login').val("");
        }
    })
}
function addAcc()
{
    $.ajax(
        {
            type:'get',
            url:'/table/userAdd',
            data:
            {
                fn: $('#accfn').val(),
                login: $('#acclog').val(),
                password: $('#accpass').val(),
            },
            success: function () {
                getAllUsers();
                $('#addModal').modal('hide')
                cleanAddModal();
            },
            error: function () {
                alert('Ошибка! Проверьте правильность ввода данных.');
            }
        }
    )
}
function removeAcc()
{
    $.ajax(
        {
            type:'get',
            url:'/table/userRemove',
            data:
            {
                param: selectedAccount
            },
            success: function () {
                getAllUsers();
                $('#deleteModal').modal('hide')
            }
        }
    )
}

function searchByID() {
    var id = $('#id').val();
    if(id.length != 0) {
        $.ajax(
            {
                type: 'get',
                url: '/table/users',
                data: {
                    byid: id
                },
                success: function (data) {
                    $('#acctable').html(data);
                }
            }
        )
    }
}

function searchByLogin() {
    var login=$('#login').val();
    if(login.length != 0) {
        $.ajax(
            {
                type: 'get',
                url: '/table/users',
                data: {
                    byname: login
                },
                success: function (data) {
                    $('#acctable').html(data);
                }
            }
        )
    }
}