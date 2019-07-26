function downloadExcel() {

    var form = $("<form>");
    form.attr("style","display:none");
    form.attr("target","");
    form.attr("method","post");
    form.attr("action","/downloadExcel");
    var input1 = $("<input>");
    input1.attr("type","hidden");
    input1.attr("name","actionName");
    input1.attr("value","downloadtheExcel");
    $("body").append(form);
    form.append(input1);
    form.submit();
    form.remove();
}

function queryCertificatesByLogic() {
    if($('#isQueryAllCertificate').is(':checked')){
        $.get('/getCertificatesDataJSON',function (data) {
            var $table = $('#table');
            $table.bootstrapTable('load',data);
            // $('#isQueryAllCertificate').prop("checked",false);
        });
    }
    else {

        var logiclist = [];
        // var j = {};
        var logicarr=$('.logicarr');
        $(logicarr).each(function () {

            if ($(this).attr('id')=="firstlogic") {
                var selectlogics=$(this).find(".selectlogic");
                var s1=$(selectlogics)[0]
                var s2=$(selectlogics)[1]
                var s3=$(selectlogics)[2]
                var item = {};

                //逻辑
                item.lg="firstlg";
                //检索项
                item.ret=$(s1).val();
                //比较
                item.com=$(s2).val();
                //检索词
                item.term=$(s3).val();
                logiclist.push(item);

            } else {
                var selectlogics=$(this).find(".selectlogic");
                var s0=$(selectlogics)[0]
                var s1=$(selectlogics)[1]
                var s2=$(selectlogics)[2]
                var s3=$(selectlogics)[3]
                var item = {};

                //逻辑
                item.lg=$(s0).val();
                //检索项
                item.ret=$(s1).val();
                //比较
                item.com=$(s2).val();
                //检索词
                item.term=$(s3).val();
                logiclist.push(item);
            }
        })


        $.ajax({
            url:'/getCertificatesDataJSONByLogics',
            type:'POST',
            dataType:'json',
            contentType:'application/json;charset=UTF-8',
            data:JSON.stringify(logiclist),
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
        url: '/getCertificatesDataJSON',
        type: 'post',
        dataType: 'json',
        success: function (d) {
            $table.bootstrapTable({
                data: d
            });
        }
    });


    // $('.btn_addLogic').click(function () {
    $(document).on("click",'.btn_addLogic',function(){
        $('#logictrs').append('<tr class="logicarr">\n' +
            '                            <td>' +
            '<select class="form-control selectlogic">\n' +
            '                                    <option selected="selected" disabled>请选择</option>\n' +
            '                                    <option value="and">而且</option>\n' +
            '                                    <option value="or">或者</option>\n' +
            '                                </select></td>\n' +
            '                            <td><select class="form-control selectlogic">\n' +
            '                                <option selected="selected" disabled>请选择</option>\n' +
            '                                <option value="cnumber">证书编号</option>\n' +
            '                                <option value="ccompany">证书单位</option>\n' +
            // '                                <option value="ctoolname">器具名称</option>\n' +
            // '                                <option value="cmodel">型号规格</option>\n' +
            // '                                <option value="coutnumber">出厂编号</option>\n' +
            // '                                <option value="cmanufacturer">制造厂商</option>\n' +
            // '                                <option value="cdelegate">委托单号</option>\n' +
            // '                                <option value="ccheckdate">检定日期</option>\n' +
            // '                                <option value="ccheckdepartment">检测部门</option>\n' +
            // '                                <option value="uname">添加人</option>\n' +
            // '                                <option value="puname">打印人</option>\n' +
            // '                                <option value="cprintdate">打印日期</option>\n' +
            '                            </select>\n' +
            '                            </td>\n' +
            '                            <td>\n' +
            '                                <select class="form-control selectlogic">\n' +
            '                                <option selected="selected" disabled>请选择</option>\n' +
            '                                <option value="like">包含</option>\n' +
            '                                <option value="=">等于</option>\n' +
            // '                                <option value=">">大于</option>\n' +
            // '                                <option value="<">小于</option>\n' +
            // '                                <option value="!=">不等于</option>\n' +
            // '                                <option value=">=">大于等于</option>\n' +
            // '                                <option value="<=">小于等于</option>\n' +
            // '                                <option value="not in">不包含</option>\n' +
            // '                                <option value="not null">不为空</option>\n' +
            '                            </select></td>\n' +
            '                            <td>\n' +
            '                                <input class="form-control selectlogic" type="text" placeholder="检索词">\n' +
            '                            </td>\n' +
            '                            <td>\n' +
            // '                                <button class="btn_addLogic btn btn-block btn-info" style="width: 100px;float: left">添加</button>\n' +
            '                                <button class="btn_delLogic btn btn-block btn-danger" style="width: 100px;float: left">删除</button>\n' +
            '                            </td>\n' +
            '                        </tr>')
    });

    $(document).on("click",'.btn_delLogic',function(){
        var selectLogics=$(this).parent().parent().find('.selectlogic');
        if ($(this).attr('id')=="delfirst"){
            $(selectLogics)[0].selectedIndex=0
            $(selectLogics)[1].selectedIndex=0
            $($(selectLogics)[2]).val("")
        }else {
            $(this).parent().parent().remove()
        }
    })


});
