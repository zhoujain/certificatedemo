
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

        var text=$($(firstSelects)[2]).val()
        if($(firstSelects)[0].selectedIndex=="0"||$(firstSelects)[1].selectedIndex=="0"||(text == ""||text == undefined || text == null || (text.length>0 && text.trim().length == 0))) {
            return
        }else {
            //执行查询

        }
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

    $('.btn_addLogic').click(function () {
        alert("btn_addLogic")
    })

    $('.btn_delLogic').click(function () {
        var firstSelects=$('.first-select');
        if ($(this).attr('id')=="delfirst"){
            $(firstSelects)[0].selectedIndex=0
            $(firstSelects)[1].selectedIndex=0
            $($(firstSelects)[2]).val("")
        }else {
            $(this).parent().parent().remove()
        }
    })


});
