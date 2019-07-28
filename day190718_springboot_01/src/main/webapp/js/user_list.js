$(function () {
    var $table = $('#table');
    $.ajax({
        url: '/getUsersDataJSON',
        type: 'post',
        dataType: 'json',
        success: function (d) {
            $table.bootstrapTable({
                data: d
            });
        }
    });
})