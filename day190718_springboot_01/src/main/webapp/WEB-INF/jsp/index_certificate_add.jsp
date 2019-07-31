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
            <div class="ibox float-e-margins">
                <div class="ibox-content mailbox-content">
                    <%--                    树结构--%>


                    <div id="using_json" style="font-size: 18px"></div>
                    <a id="aGo" target="mainFrame" style="display: none;" href="">隐藏链接</a>

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
            $.getJSON("/template/templateTree",function(rs){
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
                    if(selected.node.type ==2 ||selected.node.type ==3){
                        $("#aGo").attr("href","/openWordwithNumchanged?id="+selected.node.id);
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
