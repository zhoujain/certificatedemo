<%--
  Created by IntelliJ IDEA.
  User: zhoujian
  Date: 2019/7/20
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!--360浏览器优先以webkit内核解析-->


    <title>H+ 后台主题UI框架 - 主页示例</title>

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
            <div class="ibox float-e-margins">
                <div class="ibox-content mailbox-content">
                    <%--                    树结构--%>


                    <div id="using_json" style="font-size: 18px"></div>
                    <a id="aGo" target="mainFrame" style="display: none;" href="">隐藏链接</a>
                    <%--                        存放复制或剪切用的id--%>
                    <div id="div-id" style="display: none" value=""></div>
                    <%--                        存放复制或剪切用的text--%>
                    <div id="div-text" style="display: none" value=""></div>
                    <%--                            判断剪切还是复制--%>
                    <%--                        1表示复制--%>
                    <%--                        2表示剪切--%>
                    <div id="div-iscopy" style="display: none" value=""></div>


                </div>
            </div>
        </div>


        <div class="col-sm-9 animated fadeInRight">
            <iframe name="mainFrame" width="100%" height="600px" frameborder="0">

            </iframe>

        </div>
    </div>
</div>
<script src="../../js/jquery.min.js?v=2.1.4"></script>
<script src="../../js/bootstrap.min.js?v=3.3.6"></script>
<script src="../../js/content.min.js?v=1.0.0"></script>
<script src="../../js/plugins/iCheck/icheck.min.js"></script>
<script src="../../js/plugins/jsTree/jstree.min.js"></script>
<script type="text/javascript" src="pageoffice.js" id="po_js_main"></script>
</script>
<script>
//     $(document).ready(function () {
//         window.addEventListener('message',function (e) {
//             alert("找到审核了")
//             if(e.data ==1){
//                 alert(" 审核验证了")
//                 $.post("/template/updateTtype",
//                     {
//                         id:$("#div-id").attr("value")
//                     },
//                     function (data) {
//                         alert("进来了")
//                         var div_id = $("#div-id").attr("value");
//                        $('#'+div_id ).children("a").css({"color":"black"});
//                     })
//             }
//         })
// });
</script>
<%--<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>--%>
<script>
    function updateCount(value) {
        if(value ==1){
            //alert(" 审核验证了")
            $.post("/ttemplate/tupdateTtype",
                {
                    id:$("#div-id").attr("value")
                },
                function (data) {
                    //alert("进来了")
                    var div_id = $("#div-id").attr("value");
                    $('#'+div_id ).children("a").css({"color":"black"});
                })
        }
        return 1;
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
                            "dots":true
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
                            "a_attr":{
                                "style":"color:black"
                            },
                            "icon":"fa fa-file tree-item-icon-color icon-lg"
                        }
                    },
                    "contextmenu":{

                    }
                })
            });

            //树节点左键相应函数（监听）
            $('#using_json').on("changed.jstree",function (e,selected) {
                //当前点击的对象的id
                //alert(selected.event.type);
                if('click'==selected.event.type||selected.node.type ==3){
                    if(selected.node.type ==2){
                        if(!!window.ActiveXObject || "ActiveXObject" in window){
                            $("#aGo").attr("href","/wordcheck?id="+selected.node.id);
                        }else {
                            $("#aGo").attr("href","javascript:POBrowser.openWindowModeless('/wordcheck?id="+selected.node.id+"','width=1200px;height=800px;');");
                        }
                        //$("#aGo").attr("href","/wordcheck?id="+selected.node.id);
                        $("#div-id").attr("value",selected.node.id)
                        //alert("/word1?text="+selected.node.text)
                        $("#aGo")[0].click();
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
