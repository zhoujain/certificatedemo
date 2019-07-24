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


                </div>
            </div>
        </div>


        <div class="col-sm-9 animated fadeInRight">
            <iframe name="mainFrame" width="100%" height="600px" frameborder="0">
                <div>
                    <input id="Button1" type="button" value="隐藏/显示 标题栏"  onclick="return Button1_onclick()" />
                    <input id="Button2" type="button" value="隐藏/显示 菜单栏" onclick="return Button2_onclick()" />
                    <input id="Button3" type="button" value="隐藏/显示 自定义工具栏"  onclick="return Button3_onclick()" />
                    <input id="Button4" type="button" value="隐藏/显示 Office工具栏"  onclick="return Button4_onclick()" />
                    <div style="width:500px;height:350px;" >${pageoffice}</div>
                </div>
            </iframe>

        </div>
    </div>
</div>
<script src="../../js/jquery.min.js?v=2.1.4"></script>
<script src="../../js/bootstrap.min.js?v=3.3.6"></script>
<script src="../../js/content.min.js?v=1.0.0"></script>
<script src="../../js/plugins/iCheck/icheck.min.js"></script>
<script src="../../js/plugins/jsTree/jstree.min.js"></script>
<!--文本框-->
<script type="text/javascript">

    function Save() {
        document.getElementById("PageOfficeCtrl1").WebSave();
    }
    function PrintFile(){
        document.getElementById("PageOfficeCtrl1").ShowDialog(4);

    }
    function IsFullScreen(){
        document.getElementById("PageOfficeCtrl1").FullScreen = !document.getElementById("PageOfficeCtrl1").FullScreen;

    }
    function CloseFile(){
        window.external.close();
    }

    function BeforeBrowserClosed(){
        if (document.getElementById("PageOfficeCtrl1").IsDirty){
            if(confirm("提示：文档已被修改，是否继续关闭放弃保存 ？"))
            {
                return  true;

            }else{

                return  false;
            }

        }
    }

    // 隐藏/显示 标题栏
    function Button1_onclick() {
        var bVisible = document.getElementById("PageOfficeCtrl1").Titlebar;
        document.getElementById("PageOfficeCtrl1").Titlebar = !bVisible;
    }

    // 隐藏/显示 菜单栏
    function Button2_onclick() {
        var bVisible = document.getElementById("PageOfficeCtrl1").Menubar;
        document.getElementById("PageOfficeCtrl1").Menubar = !bVisible;
    }


    // 隐藏/显示 自定义工具栏
    function Button3_onclick() {
        var bVisible = document.getElementById("PageOfficeCtrl1").CustomToolbar;
        document.getElementById("PageOfficeCtrl1").CustomToolbar = !bVisible;
    }
    // 隐藏/显示 Office工具栏
    function Button4_onclick() {
        var bVisible = document.getElementById("PageOfficeCtrl1").OfficeToolbars;
        document.getElementById("PageOfficeCtrl1").OfficeToolbars = !bVisible;
    }
</script>
<script>
    $(document).ready(function () {
        $(".i-checks").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green",})
    });
</script>
<%--<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>--%>
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
                            "dots":true
                        }
                    },
                    "plugins":["dnd","contextmenu","search",
                        "state", "types", "wholerow"],//types：设置样式，contextmenu：右键菜单可用
                    "types":{
                        "default":{
                            "icon": "fa fa-folder tree-item-icon-color icon-lg"
                        },
                        "1":{
                            "icon": "fa fa-folder tree-item-icon-color icon-lg"
                        },
                        "2":{
                            "icon":"fa fa-file tree-item-icon-color icon-lg"
                        }
                    },
                    "contextmenu":{
                        "items":{
                            "create":null,
                            "rename":null,
                            "remove":null,
                            "ccp":null,
                            "add": {
                                "label": "增加目录",
                                "action": function (obj) {
                                    var inst = jQuery.jstree.reference(obj.reference);
                                    var clickedNode = inst.get_node(obj.reference);
                                    var newNode = inst.create_node(inst.get_node(obj.reference), '请输入目录名称', "after", "", "");
                                    inst.edit(newNode, newNode.val,function(){
                                        //alert(newNode)

                                        var obj = this.get_node(newNode);
                                        //alert(clickedNode.id);
                                        //alert(obj.id);
                                        //alert(obj.text);

                                        $.post("/template/node_add",
                                            {
                                                id:obj.id,
                                                text:obj.text,
                                                parent:clickedNode.id,
                                                type:"1",
                                                turl:"#"
                                            },
                                            function(data){
                                                //alert(data);
                                                $(inst.get_node(newNode)).attr("id",data);
                                                $('#using_json').jstree().destroy();
                                                tzs.index.doCreateTree();
                                            }
                                        );
                                    });

                                }
                            },
                            "rename":{
                                    "label":"重命名",
                                    "action":function (obj) {
                                        // alert("修改分类");
                                        var inst = jQuery.jstree.reference(obj.reference);
                                        var clickedNode = inst.get_node(obj.reference);
                                        inst.edit(obj.reference,clickedNode.val,function () {
                                            $.post("/template/node_edit",
                                                {
                                                    id:clickedNode.id,
                                                    text:clickedNode.text,
                                                },
                                                function(){
                                                }
                                            );
                                        });
                                    }
                                },
                            "delete":{
                                    "label":"删除",
                                    "action":function (obj) {
                                        // alert("删除分类");
                                        var inst = jQuery.jstree.reference(obj.reference);
                                        var clickedNode = inst.get_node(obj.reference);
                                        // var result = inst.delete_node(clickedNode);
                                        var r =confirm("确认删除？");
                                        if(r=true){
                                            //alert(clickedNode.id);
                                            inst.delete_node(obj.reference);
                                            $.post("/template/node_delete",
                                                {
                                                    id:clickedNode.id
                                                },
                                                function(){
                                                }
                                            );
                                        }



                                    }
                            },
                            "addTemplate":{
                                "label":"添加模板",
                                "action":function (obj) {
                                    var inst = jQuery.jstree.reference(obj.reference);
                                    var clickedNode = inst.get_node(obj.reference);
                                    var newNode = inst.create_node(inst.get_node(obj.reference), '请输入模板名称', "after", "", "");
                                    inst.edit(newNode, newNode.val,function(){
                                        var obj = this.get_node(newNode);
                                        $.post("/template/node_add",
                                            {
                                                id:obj.id,
                                                text:obj.text,
                                                parent:clickedNode.id,
                                                type:"2",
                                                turl:obj.text
                                            },
                                            function(data){
                                                //alert(data);
                                                $(inst.get_node(newNode)).attr("id",data);
                                                $('#using_json').jstree().destroy();
                                                tzs.index.doCreateTree();
                                            }
                                        );
                                    });
                                }
                            }
                        }
                    }
                })
            });

            //树节点左键相应函数（监听）
            $('#using_json').on("select_node.jstree",function (node,selected,event) {
                //当前点击的对象的id
                // alert(selected.node.id);
                if(selected.node.type==2){
                    $.get("/word1",function () {
                        
                    })
                }
            })
        }
    };

    $(function(){
        tzs.index.init();
    });
</script>

</body>
</html>
