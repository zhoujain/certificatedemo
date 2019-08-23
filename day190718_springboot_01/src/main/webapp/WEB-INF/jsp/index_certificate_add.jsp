<%--
  Created by IntelliJ IDEA.
  User: 雨木林风
  Date: 2019/7/26
  Time: 20:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>证书添加</title>

    <link rel="shortcut icon" href="favicon.ico">
    <link href="../../css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="../../css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="../../css/plugins/jsTree/style.min.css" rel="stylesheet">
    <link href="../../css/animate.min.css" rel="stylesheet">
    <link href="../../css/style.min862f.css?v=4.1.0" rel="stylesheet">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-3">

            <div class="ibox">
                <div class="ibox-title">
                    证书查询
                </div>
                <div class="ibox-content">
                    <%--                    <form method="post" action="/queryCerBy">--%>
                    <div class="input-group">
                        <select name="cid" class="form-control selectlogic">
                            <option selected="selected">请选择</option>
                            <option value="1">证书号</option>
                            <option value="2">委托单号</option>

                        </select>
                        <%--                            <input name="cid" id="cid" type="text" style="display: none" value="$('.form-control').val()" >--%>
                        <input name="cnumber" id="cnumber" type="text" class="form-control" placeholder="请输入条件" aria-describedby="Cerlabel">
                    </div>
                    <button style="margin-top: 10px;"  class="btn btn-success btn-block" onclick="queryCer()">查询</button>

                    <%--                    </form>--%>



                </div>
            </div>
            <div class="ibox">
                <div class="ibox-title">结果</div>
                <div class="ibox-content">
                    <div class="list-group" style="overflow-y:auto;overflow-x: auto; height:400px ;width:100%;" >


                    </div>
                </div>
            </div>

        </div>


        <a id="aGo" target="mainFrame" style="display: none;" href="">隐藏链接</a>
        <div class="col-sm-3">
            <div id="using_json" style="font-size: 18px"></div>
        </div>
        <div class="col-sm-6 animated fadeInRight">
            <iframe name="mainFrame" width="100%" height="600px" frameborder="0">

            </iframe>
        </div>
    </div>
</div>
<%--进度条--%>


<%--iframe隐藏域--%>
<div style="width: 1px; height: 1px; overflow: hidden;">
    <iframe id="iframe1" name="iframe1" src="" ></iframe>
</div>

<script src="../../js/jquery.min.js?v=2.1.4"></script>
<script src="../../js/bootstrap.min.js?v=3.3.6"></script>
<script src="../../js/content.min.js?v=1.0.0"></script>
<script src="../../js/plugins/iCheck/icheck.min.js"></script>
<script src="../../js/plugins/jsTree/jstree.min.js"></script>
<script type="text/javascript" src="pageoffice.js" id="po_js_main"></script>
<script>
    //var checkBoxArr = [];
    var all;
    var count =1;
    $(document).ready(function(){

        // $('.checkbox').click(function(){
        //     //alert("111")
        //     $('#aGo').attr("href","/content_certificate?");
        //     $('#aGo')[0].click();
        // });



    });

    //点击
    //查询所有
    function queryCer() {
        //alert("11")
        if($('.form-control').val()=="请选择"&&$('#cnumber').val()!=null&&$('#cnumber').val()!=""){
            alert("选择查询条件");
            return;
        }
        $.ajaxSettings.async = false;
        $.post("/company/queryAllByLike",
            {
                cid:$('.form-control').val(),
                cnumber:$('#cnumber').val()
            },

            function(rst){
                var optionData = rst;
                //var a = /;
                var str='暂无数据';
                if(optionData !=null&&optionData!=""){
                    str = '<div id="div-cid" style="display: none" value="'+optionData[0].id+'"></div>';
                    for(var i = 0;i<optionData.length;i++){
                        if(i==0){
                            str+='<div class="all-div" value="'+optionData[i].id+'" style="background: #00FFFF">\n'
                        }else{
                            str+='<div class="all-div" value="'+optionData[i].id+'" style="">\n'
                        }
                        // if(!!window.ActiveXObject || "ActiveXObject" in window){
                        //     str+='<a href="/content_certificate?id='+optionData[i].cid+'" target="mainFrame" >\n'
                        // }else {
                        //     str+='<a href="javascript:POBrowser.openWindowModeless(\'/content_certificate?id='+optionData[i].cid+'\',\'width=1200px;height=800px;\');">\n'
                        // }

                        str+='<label style="font-size: 10px"><input type="hidden" name="optionName" > <i>'+optionData[i].cnumber+'</i> </label><br />\n' +
                            ' <span style="color: blue;margin-left: 10px;font-size: 20px;"><b>'+optionData[i].toolname+'</b></span><br/>\n' +
                            ' <span style="color: #00BFFF;margin-left: 10px;font-size: 15px;"><b>'+optionData[i].company.name+'</b></span>\n' +
                            ' </div>'
                    }
                }
                $('.list-group').html(str);
            }

        );
        $.ajaxSettings.async=true;
        // $("#allOptionId").on('ifChecked',function (event) {
        //     $('input').iCheck('check')
        // });
        //
        // $('#allOptionId').on('ifUnchecked',function (event) {
        //     $('input').iCheck('uncheck')
        // });
        // $("input").iCheck({
        //     checkboxClass:"icheckbox_square-green",
        //     radioClass:"iradio_square-green",
        // });

        var a = document.getElementsByClassName("all-div");
        for(var i=0;i<a.length;i++){
            a[i].onclick = function () {
                for(var j =0;j<a.length;j++){
                    if(a[j]==this)
                        continue;
                    a[j].style.background="";
                }
                if(this.style.background!="#00FFFF"){
                    this.style.background="#00FFFF";
                    document.getElementById("div-cid").setAttribute("value",this.getAttribute("value"));
                }else {
                    this.style.background="";
                }
            }
        }
        // $('#all-div').mouseout(function(){
        //     //alert("111")
        //     $(this).attr("style","background:white");
        // });



    }

</script>
<script>
    tzs = {};
    tzs.index = {
        //初始化页面
        init: function () {
            tzs.index.doCreateTree();
        },
        //给标签绑定事件
        event : function(){
        },
        doCreateTree : function(){
            $.getJSON("/template/templateTree?"+Date.UTC(new Date()),function(rs){
                $('#using_json').jstree({
                    "core" : {
                        "mutiple" : false,//没有多选
                        "check_callback" : true,//文本可修改
                        "data" : rs.data,//配置数据源
                        "themes":{
                            "stripes":false,//不显示条纹
                            "icons":true,//显示图标
                            "ellipsis":true,//过长省略
                            "dots":false
                        }
                    },
                    "plugins":["dnd","search",
                        "state", "types", "wholerow"],//types：设置样式，contextmenu：右键菜单可用
                    "types":{
                        "default":{
                            "icon": "fa fa-folder tree-item-icon-color icon-lg"
                        },
                        "1":{

                            "icon": "fa fa-folder tree-item-icon-color icon-lg"
                        },
                        "2":{
                            "a_attr":{
                                "style":"color:red"
                            },
                            "icon":"fa fa-file tree-item-icon-color icon-lg",

                        },
                        "3":{
                            "icon":"fa fa-file tree-item-icon-color icon-lg"
                        }
                    }
                })
            });

            //树节点左键相应函数（监听）
            $('#using_json').on("changed.jstree",function (e,selected) {
                //当前点击的对象的id
                //alert(selected.event.type);
                if(selected.event == undefined){
                    return 0;
                }
                if('click'==selected.event.type){
                    if(selected.node.type ==2||selected.node.type ==3){
                        if($('#div-cid').attr("value") == undefined){
                            alert("请点击查询并进行选择");
                        }else{
                            if(!!window.ActiveXObject || "ActiveXObject" in window){
                                $("#aGo").attr("href","/template2certificates?id="+selected.node.id+"&cid="+$('#div-cid').attr("value"))
                            }else {
                                $("#aGo").attr("href","javascript:POBrowser.openWindowModeless('/openWordwithNumchanged?id="+selected.node.id+"&cid="+$('#div-cid').attr("value")+"','width=1200px;height=1000px;');");
                            }
                            //$("#aGo").attr("href","/wordcheck?id="+selected.node.id);
                            $("#div-id").attr("value",selected.node.id)
                            //alert("/word1?text="+selected.node.text)
                            $("#aGo")[0].click();
                        }

                    }
                }
                // alert(e.which);


            })

        }
    };

    $(function(){
        tzs.index.init();
    });
</script>
</body>
</html>
