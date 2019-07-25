
function queryCertificatesByLogic() {
    if($('#isQueryAllCertificate').is(':checked')){
        $.get('/getCertificatesDataJSON',function (data) {
            var $table = $('#table');
            $table.bootstrapTable('load',data);
            // $('#isQueryAllCertificate').prop("checked",false);
        });
    }
    else {
        var firstSelects=$('.first-select');

        var select01=$($(firstSelects)[0]);
        var text03=$($(firstSelects)[2]).val()
        // alert(text == ""||text == undefined || text == null || (text.length>0 && text.trim().length == 0))

            /*.each(function(i,n){
            if($(n).is(":checked")==false){

            }
            // alert($(n).find("option:selected").val())
        })*/
    }
}

$(function () {
    var $table = $('#table');
    $.ajax({
        url: '/getCertificatesDataJSON',
        type: 'post',
        dataType: 'json',
        success: function (d) {
            $table.bootstrapTable({
                data: d
            });
        }
    });

});
