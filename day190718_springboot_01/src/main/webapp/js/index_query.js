
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