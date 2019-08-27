<%--
  Created by IntelliJ IDEA.
  User: zhoujian
  Date: 2019/8/20
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>H+ 后台主题UI框架 - 表单验证 jQuery Validation</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico"> <link href="../../css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="../../css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="../../css/animate.min.css" rel="stylesheet">
    <link href="../../css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="../../js/plugins/layer/theme/default/layer.css" rel="stylesheet">
    <style>
        .button_in_table_add{
            margin-top: 1px;
            border-radius: 1px;
            color: white;
            background-color:#337AB7;
            border: 1px solid #337AB7;
        }
        .button_in_table_copy{
            margin-top: 1px;
            border-radius: 1px;
            color: white;
            background-color:#5BC0DE;
            border: 1px solid #5BC0DE;
        }
        .button_in_table_del{
            margin-top: 1px;
            border-radius: 1px;
            color: white;
            background-color:#D9534F;
            border: 1px solid #D9534F;
        }
    </style>
</head>
<body>
<div class="weituo">
    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5>委托添加</h5>
        </div>
        <div class="ibox-content">
            <form class="form-horizontal m-t" id="signupForm">
                <input type="hidden" id="companyId" value="" />
                <div class="form-group">
                    <label class="col-sm-1 control-label">委托单位：</label>
                    <div class="col-sm-8">
                        <div class="input-group">
                            <input type="text" class="form-control" id="test_data">
                            <div class="input-group-btn">
                                <button type="button" class="btn btn-white dropdown-toggle" data-toggle="dropdown">
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                </ul>
                            </div>
                        </div>
<%--                        <span class="help-block m-b-none"><i class="fa fa-info-circle"></i>下拉框</span>--%>
                    </div>
                    <div class="col-sm-1">
                        <input class="btn btn-primary" id="btnadd" type="button" value="添加单位" onclick="toAnother()"/>
                    </div>

                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label">联系人：</label>
                    <div class="col-sm-4">
                        <input id="linkMan" name="linkMan" class="form-control" type="text" aria-required="true" aria-invalid="false" class="valid">
                    </div>
                    <label class="col-sm-1 control-label">联系电话：</label>
                    <div class="col-sm-4">
                        <input id="linkPhone" name="linkPhone" class="form-control" type="text" aria-required="true" aria-invalid="false" class="valid">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label">委托日期：</label>
                    <div class="col-sm-4">
                        <input id="adate" name="adate" type="text" class="form-control" data-mask="9999-99-99" placeholder="">
                    </div>
                    <label class="col-sm-1 control-label">打印日期：</label>
                    <div class="col-sm-4">
                        <input id="pdate" name="pdate" type="text" class="form-control" data-mask="9999-99-99" placeholder="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label">客户要求单位地址：</label>
                    <div class="col-sm-4">
                        <input id="adress" name="adress" class="form-control" type="text">
                    </div>
                    <label class="col-sm-1 control-label">委托单号：</label>
                    <div class="col-sm-4">
                        <input id="aid" name="aid" class="form-control" type="text" readonly="readonly">
                    </div>
                </div>
            </form>
        </div>
        <div class="row">
            <div class="animated fadeInRight">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <!--<%--证书列表表格 开始--%>-->
                        <table class="table table-bordered">

                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>登记号</th>
                                <th>器具名称</th>
                                <th>器具规格</th>
                                <th>出厂编号</th>
                                <th>设备编号</th>
                                <th>制造厂商</th>
                                <th>套数</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id="tbody1">
                            <tr class="oneRow">
                                <td><span id="index"></span></td>
                                <td><input type="text" class="cnumber" readonly="readonly" value="只读"></td>
                                <td><input type="text" class="toolname"></td>
                                <td><input type="text" class="model"></td>
                                <td><input type="text" class="outnumber"></td>
                                <td><input type="text" class="toolId"></td>
                                <td><input type="text" class="manufacturer"></td>
                                <td><input type="number" class="number"></td>
                                <td><button class="button_in_table_add">增加</button><br><button class="button_in_table_copy">复制</button><br><button class="button_in_table_del">删除</button></td>
                            </tr>
                            </tbody>
                        </table>
                        <!-- <%--证书列表表格 结束--%>-->

                    </div>
                </div>
                <button style="margin-left: 30px;" class="btn btn-primary" onclick="getTableData()">提交</button>
            </div>
        </div>
    </div>
</div>
<script src="../../js/jquery.min.js?v=2.1.4"></script>
<script src="../../js/bootstrap.min.js?v=3.3.6"></script>
<script src="../../js/plugins/suggest/bootstrap-suggest.min.js"></script>
<script src="../../js/plugins/jasny/jasny-bootstrap.min.js"></script>
<script src="../../js/plugins/validate/jquery.validate.min.js"></script>
<script src="../../js/plugins/validate/messages_zh.min.js"></script>
<script src="../../js/plugins/layer/layer.js"></script>
<script src="../../js/demo/form-validate-demo.min.js"></script>
<script>
    $(function(){
        var index=1;
        $('#index').text(index);
        $(document).on("click",'.button_in_table_add',function(){
            $('#tbody1').append('<tr class="oneRow">\n' +
                '                            <td><span>'+(++index)+'</span></td>\n' +
                '                            <td><input type="text" class="cnumber" readonly="readonly" value="只读"></td>\n'+
                '                            <td><input type="text" class="toolname"></td>\n' +
                '                            <td><input type="text" class="model"></td>\n' +
                '                            <td><input type="text" class="outnumber"></td>\n' +
                '                            <td><input type="text" class="toolId"></td>\n' +
                '                            <td><input type="text" class="manufacturer"></td>\n' +
                '                            <td><input type="number" class="number"></td>\n' +
                '                            <td><button class="button_in_table_add">增加</button><br><button class="button_in_table_copy">复制</button><br><button class="button_in_table_del">删除</button></td>\n' +
                '                        </tr>')
        });
        $(document).on("click",'.button_in_table_copy',function(){
            var thisRow=$(this).parent().parent();
            var toolname = $(thisRow).find('.toolname').val();
            var model = $(thisRow).find('.model').val();
            var outnumber = $(thisRow).find('.outnumber').val();
            var toolId = $(thisRow).find('.toolId').val();
            var manufacturer = $(thisRow).find('.manufacturer').val();
            var number = $(thisRow).find('.number').val();
            $('#tbody1').append('<tr class="oneRow">\n' +
                '                            <td><span>'+(++index)+'</span></td>\n' +
                '                            <td><input type="text" class="cnumber" readonly="readonly" value="只读"></td>\n'+
                '                            <td><input type="text" class="toolname" value="'+toolname+'"></td>\n' +
                '                            <td><input type="text" class="model" value="'+model+'"></td>\n' +
                '                            <td><input type="text" class="outnumber" value="'+outnumber+'"></td>\n' +
                '                            <td><input type="text" class="toolId" value="'+toolId+'"></td>\n' +
                '                            <td><input type="text" class="manufacturer" value="'+manufacturer+'"></td>\n' +
                '                            <td><input type="number" class="number" value="'+number+'"></td>\n' +
                '                            <td><button class="button_in_table_add">增加</button><br><button class="button_in_table_copy">复制</button><br><button class="button_in_table_del">删除</button></td>\n' +
                '                        </tr>')
        })
        $(document).on("click",'.button_in_table_del',function(){
            var thisRow=$(this).parent().parent();
            $(thisRow).remove()
        })
    });

    /*将表格数据转化为json*/
    function getTableData() {
        var coid = $('#companyId').val();
        if(coid == ""){
            layer.alert('请添加单位',{icon:6});
             return;
        }
        var list=[];
        var rows=$('.oneRow');
        for (var i=0;i<rows.length;i++) {
            var r=rows[i];
            var toolname = $(r).find('.toolname').val();
            var model = $(r).find('.model').val();
            var outnumber = $(r).find('.outnumber').val();
            var toolId = $(r).find('.toolId').val();
            var manufacturer = $(r).find('.manufacturer').val();
            var number = $(r).find('.number').val();
            var name = $('#test_data').val();
            var linkMan = $('#linkMan').val();
            var linkPhone = $('#linkPhone').val();
            var adate = $('#adate').val();
            var pdate = $('#pdate').val();
            var adress = $('#adress').val();
            var aid = $('#aid').val();
            var company ={'id':coid,'name':name,'linkMan':linkMan,'linkPhone':linkPhone,'adate':adate,'pdate':pdate,'adress':adress,'aid':aid};
            var one={'toolname':toolname,'model':model,'outnumber':outnumber,'toolId':toolId,'manufacturer':manufacturer,'number':number,'company':company};
            list.push(one)
        }
        $.ajax({
            type: "POST",
            url:'/company/saveAuth',
            dataType:"json",
            contentType:"application/json",
            data:JSON.stringify(list),
            success:function (data) {
                if(data){
                    layer.alert('添加成功，在查询中查看登记号',{icon:6});
                    for(var i =0;i<rows.length;i++){
                        var r = rows[i];
                        $(r).find('.cnumber').val(data[i]);
                    }
                    $('#test_data').val("");
                    $('#companyId').val("");

                }else {
                    layer.alert('添加失败',{icon:6});
                }
            }
            })
        // $.post('/company/saveAuth',
        //     {
        //         list:JSON.stringify(list)
        //     },
        //     function (data) {
        //         if(data ==1){
        //             layer.alert('添加成功，在查询中查看登记号',{icon:6});
        //         }
        //     }
        // )
    }
</script>
<script type="text/javascript">
    var testdataBsSuggest=$("#test_data").bsSuggest('init',{
        ignorecase: true, //忽略大小写
        clearable: true,
        allowNoKeyword:false,//不允许无关键字查询
        idField:"id",
        keyField:"name",
        effectiveFields:["id","name"],
        effectiveFieldsAlias:{"id":"编号","name":"单位"},
        indexId:2,
        indexKey:1,
        url:"/company/queryLike"
  	}).on("onSetSelectValue",function (e,keyword) {
        ///console.log("选择完成"+keyword.id);
        $.post("/company/findByName",
            {
                id:keyword.id
            },
            function (data) {
                //console.log(data.linkPhone)
                $('#linkPhone').val(data.linkPhone);
                $('#linkMan').val(data.linkMan);
                $('#adate').val(data.adateStr);
                $('#pdate').val(data.pdateStr);
                $('#adress').val(data.adress);
                $('#aid').val(data.aid);
                $('#companyId').val(data.id)
            })
    });
</script>
<script type="text/javascript">
    function toAnother(id) {
        //alert("11")
            //$("#aGo").attr("href","/wordcheck?id="+selected.node.id);
            layer.open({
                type: 2,
                area: ['1000px', '450px'],
                fixed: false, //不固定
                maxmin: true,
                content: '/companyAdd',
                end:function () {
                    location.reload();
                }
            });

    }

</script>

</body>
</html>


