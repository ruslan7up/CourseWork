/**
 * Created by ruslan on 24.10.2016.
 */
function removeShop()
{
    $.ajax(
        {
            type:'get',
            url:'http://localhost:8080/table/userRemove',
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

}