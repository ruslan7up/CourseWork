 var selectedGoods;
    function getAllGoods() {
        $.ajax(
                {
                    type:'get',
                    url:'/table/goods',
                    data :
                    {
                      sort:$('#sort').val()
                    },
                    success: function (data) {
                        $('#goodstable').html(data);
                        $('#id').val("");
                        $('#name').val("");
                    }
                }
        )
    }
    function sortGoods(sorttype) {
        var type = sorttype.value;
        $.ajax(
                {
                    type:'get',
                    url:'/table/goods',
                    data:{
                        sort:type
                    },
                    success: function (data) {
                        $('#goodstable').html(data);
                    }
                })
     }
     function searchbyID() {
         var id = $('#id').val();
         if(id.length != 0) {
             $.ajax(
                 {
                     type: 'get',
                     url: '/table/goods',
                     data: {
                         byid: id
                     },
                     success: function (data) {
                         $('#goodstable').html(data);
                     }
                 }
             )
         }
     }
     function searchbyName() {
         var name = $('#name').val();
         if(name.length != 0) {
             $.ajax(
                 {
                     type: 'get',
                     url: '/table/goods',
                     data: {
                         byname: name
                     },
                     success: function (data) {
                         $('#goodstable').html(data);
                     }
                 }
             )
         }
     }
     function deleteGoods(vc)
     {
         $.ajax({
             type:'get',
             url:'/table/goodsRemove',
             data:
             {
                 param:vc
             },
             success: function() {
                getAllGoods();
                 $('#deleteModal').modal('hide');
             }
         })
     }
     function addGoods()
     {
         $.ajax(
                 {
                     type:'get',
                     url:'/table/goodsAdd',
                     data:
                     {
                         vc: $('#goodsvc').val(),
                         category: $('#goodscategory option:selected').val(),
                         name: $('#goodsname').val(),
                         quantity: $('#goodsquantity').val(),
                         rp: $('#goodsrp').val(),
                         wp: $('#goodswp').val(),
                     },
                     success: function () {
                         getAllGoods();
                         $('#addModal').modal('hide')
                        cleanAddModal();
                     },
                     error: function () {
                         alert('Ошибка! Проверьте правильность ввода данных.');
                     }
                 }
         )
     }
     function updateGoods()
     {
         $.ajax(
             {
                 type:'get',
                 url:'/table/goodsUpdate',
                 data:
                 {
                     vc: $('#egoodsvc').val(),
                     category: $('#egoodscategory option:selected').val(),
                     name: $('#egoodsname').val(),
                     quantity: $('#egoodsquantity').val(),
                     rp: $('#egoodsrp').val(),
                     wp: $('#egoodswp').val(),
                 },
                 success: function () {
                     getAllGoods();
                     $('#editModal').modal('hide');
                 },
                 error: function () {
                     alert('Ошибка! Проверьте правильность ввода данных.');
                 }
             }
         )
     }
     function cleanAddModal() {
         $('#goodsvc').val("");
         $('#goodscategory').val("Без категории");
         $('#goodsname').val("");
         $('#goodsquantity').val("");
         $('#goodsrp').val("");
         $('#goodswp').val("");
     }


     function printTable()
     {
         var divToPrint=document.getElementById("goods");
         newWin= window.open("");
         newWin.document.write(divToPrint.outerHTML);
         newWin.print();
         newWin.close();
     }
     function showDeleteModal(vc)
     {
         selectedGoods=vc;
         $('#deleteModal').modal('show');
     }
     function showaddGoodsModal()
     {
         $('#addModal').modal('show');
     }
     function showEditModal(vc,category,name,quantity,rp,wp) {
         $('#egoodsvc').val(vc);
         $('#egoodscategory').val(category);
         $('#egoodsname').val(name);
         $('#egoodsquantity').val(quantity);
         $('#egoodsrp').val(rp);
         $('#egoodswp').val(wp);
         $('#editModal').modal('show');
     }

     function calculatewp() {
         $('#goodswp').val((parseFloat($('#goodsrp').val())*0.9).toFixed(2));
     }

     function ecalculatewp() {
         $('#egoodswp').val((parseFloat($('#egoodsrp').val())*0.9).toFixed(2));
     }