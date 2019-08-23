
function delAuthorize(id) {
    var r=confirm("确认删除？");
    if (r==true){
        $.get("/delAuthorizeById",{'id':id},function (data) {
            var $table = $('#table');
            $table.bootstrapTable('load',data);
        })
    }
}

function toAnother(id) {

        layer.open({
            type: 2,
            area: ['1000px', '600px'],
            fixed: false, //不固定
            maxmin: true,
            content: '/company/query2Update?id='+id,
            end:function () {
                location.reload();
            }
        });


}

function queryCertificatesByLogic() {
    if($('#isQueryAllCertificate').is(':checked')){
        $.get('/findAllAuth',function (data) {
            var $table = $('#table');
            $table.bootstrapTable('load',data);
            // $('#isQueryAllCertificate').prop("checked",false);
        });
    }
    else {

        //判断是否有select没有选择，或检索词为空
        //若有，则按默认查询 ‘一个月/所有’ 的数据
        var logicarr=$('.logicarr');
        $(logicarr).each(function () {
            var selectlogics=$(this).find(".selectlogic");
            var s0=$(selectlogics)[0]
            var s1=$(selectlogics)[1]
            if ( s0.selectedIndex == 0 || $(s1).val() == "" || $(s1).val().replace(/\s/g, '') == '') {
                $.get('/findAllAuth', function (data) {
                    var $table = $('#table');
                    $table.bootstrapTable('load', data);
                });
                return;
            }
        });

        var item = {};
        // var j = {};
        var logicarr=$('.logicarr');
        $(logicarr).each(function () {
                var selectlogics=$(this).find(".selectlogic");
                var s0=$(selectlogics)[0];
                var s1=$(selectlogics)[1];


                //检索项
                item.ret=$(s0).val();
                //检索词
                item.term=$.trim($(s1).val());
            }
        );


        $.ajax({
            url:'/findAllAuthByLogics',
            type:'POST',
            dataType:'json',
            contentType:'application/json;charset=UTF-8',
            data:JSON.stringify(item),
            success:function(data, status){
                var $table = $('#table');
                $table.bootstrapTable('load',data);
                if (data==null||data==""){
                    $table.bootstrapTable('load',null);
                }
            }
        });

    }
}

$(function () {
    var $table = $('#table');
    $.ajax({
        url: '/findAllAuth',
        type: 'post',
        dataType: 'json',
        success: function (d) {
            $table.bootstrapTable({
                data: d
            });
        }
    });

});


