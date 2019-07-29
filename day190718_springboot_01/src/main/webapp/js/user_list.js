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
});

function updateUser(uid) {
    layer.open({
        type: 2,
        content: '/index_update' //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
    });
}

function delUser(uid) {
    alert(uid)
}