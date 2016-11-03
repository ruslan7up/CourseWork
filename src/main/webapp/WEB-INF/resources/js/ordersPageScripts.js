/**
 * Created by User on 27.10.2016.
 */
var goods = [];
function cleanAddModal() {
    $('#rows').html("<div style='display: inline-block;'> <input type='text' id='ordergn' name='ordergn' placeholder='Название' style='width: 350px' class='form-control' > </div> <div style='display: inline-block'> <input type='number' id='orderquantity' name='orderquantity' placeholder='Количество' class='form-control'> </div>");
}

function showaddModal() {
    $('#addModal').modal('show');
}

function addRow() {
    $('#rows').append("<br><br><div style='display: inline-block;'> <input type='text' placeholder='Название' style='width: 350px' class='form-control' > </div> <div style='display: inline-block'> <input type='number' id='orderquantity' placeholder='Количество' class='form-control'> </div>");
}

function addOrder() {
    $('#rows').each(function () {
        if($('#this').name=='ordergn') {
            goods.push()
        }
        if($('#this').name=='orderquantity')
        {

        }
    })
}
