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
<%--                <div>--%>
<%--                    <input id="ButtonSave"  type="button" value="保存模板"  onclick="return Save()" />--%>
<%--                    <input id="ButtonClose"  type="button" value="关闭"  onclick="return CloseFile()" />--%>
<%--                    <input id="Button1" class="btn btn-primary" type="button" value="隐藏/显示 标题11111栏"  onclick="return Button1_onclick()" />--%>
<%--                    <input id="Button2" class="btn btn-info" type="button" value="隐藏/显示 菜单栏" onclick="return Button2_onclick()" />--%>
<%--                    <input id="Button3" class="btn btn-primary" type="button" value="隐藏/显示 自定义工具栏"  onclick="return Button3_onclick()" />--%>
<%--                    <input id="Button4" class="btn btn-info" type="button" value="隐藏/显示 Office工具栏"  onclick="return Button4_onclick()" />--%>
<%--                    <div style="width:500px;height:350px;" ></div>--%>
<%--                </div>--%>
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
<%--<script type="text/javascript">--%>

<%--    function Save() {--%>
<%--        document.getElementById("PageOfficeCtrl1").WebSave();--%>
<%--    }--%>
<%--    function PrintFile(){--%>
<%--        document.getElementById("PageOfficeCtrl1").ShowDialog(4);--%>

<%--    }--%>
<%--    function IsFullScreen(){--%>
<%--        document.getElementById("PageOfficeCtrl1").FullScreen = !document.getElementById("PageOfficeCtrl1").FullScreen;--%>

<%--    }--%>
<%--    function CloseFile(){--%>
<%--        window.external.close();--%>
<%--    }--%>

<%--    function BeforeBrowserClosed(){--%>
<%--        if (document.getElementById("PageOfficeCtrl1").IsDirty){--%>
<%--            if(confirm("提示：文档已被修改，是否继续关闭放弃保存 ？"))--%>
<%--            {--%>
<%--                return  true;--%>

<%--            }else{--%>

<%--                return  false;--%>
<%--            }--%>

<%--        }--%>
<%--    }--%>

<%--    // 隐藏/显示 标题栏--%>
<%--    function Button1_onclick() {--%>
<%--        var bVisible = document.getElementById("PageOfficeCtrl1").Titlebar;--%>
<%--        document.getElementById("PageOfficeCtrl1").Titlebar = !bVisible;--%>
<%--    }--%>

<%--    // 隐藏/显示 菜单栏--%>
<%--    function Button2_onclick() {--%>
<%--        var bVisible = document.getElementById("PageOfficeCtrl1").Menubar;--%>
<%--        document.getElementById("PageOfficeCtrl1").Menubar = !bVisible;--%>
<%--    }--%>


<%--    // 隐藏/显示 自定义工具栏--%>
<%--    function Button3_onclick() {--%>
<%--        var bVisible = document.getElementById("PageOfficeCtrl1").CustomToolbar;--%>
<%--        document.getElementById("PageOfficeCtrl1").CustomToolbar = !bVisible;--%>
<%--    }--%>
<%--    // 隐藏/显示 Office工具栏--%>
<%--    function Button4_onclick() {--%>
<%--        var bVisible = document.getElementById("PageOfficeCtrl1").OfficeToolbars;--%>
<%--        document.getElementById("PageOfficeCtrl1").OfficeToolbars = !bVisible;--%>
<%--    }--%>
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
                            "a_attr":{
                                "style":"color:red"
                            },
                            "icon":"fa fa-file tree-item-icon-color icon-lg",

                        },
                        "3":{
                            "icon":"fa fa-file tree-item-icon-color icon-lg"
                        }
                    },
                    "contextmenu":{
                        "items":customMenu,
                        "ccp":true
                    }
                })
            });

            //树节点左键相应函数（监听）
            $('#using_json').on("changed.jstree",function (e,selected) {
                //当前点击的对象的id
                //alert(selected.event.type);
                if('click'==selected.event.type){
                    if(selected.node.type ==2){
                        $("#aGo").attr("href","/word1?id="+selected.node.id);
                        //alert("/word1?text="+selected.node.text)
                        $("#aGo")[0].click();
                    }
                }
                   // alert(e.which);


            })
            
            
            
            function customMenu(node) {
							var items = {
									"add": {
                                "label": "增加目录",
                                "action": function (obj) {
                                    var inst = jQuery.jstree.reference(obj.reference);
                                    var clickedNode = inst.get_node(obj.reference);
                                    var newNode = inst.create_node(inst.get_node(obj.reference), '请输入目录名称', "last", "", "");
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
                                        //inst.delete_node(clickedNode);
                                        // var result = inst.delete_node(clickedNode);
                                        var r =confirm("确认删除？");
                                        if(r==true){
                                            //alert(clickedNode.id);
                                            $.post("/template/node_delete",
                                                {
                                                    id:clickedNode.id
                                                },
                                                function(data){
                                                //alert(data);
                                                    //inst.delete_node(inst.get_selected());
                                                    //重新建树
                                                    $('#using_json').jstree().destroy();
                                                    tzs.index.doCreateTree();
                                                    //return;
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
                                    var newNode = inst.create_node(inst.get_node(obj.reference), '请输入模板名称', "last", "", "");
                                    inst.edit(newNode, newNode.val,function(){
                                        var obj = this.get_node(newNode);
                                        $.post("/template/node_add1",
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
                            },
									"copy":{
										"label": "复制",
										"action": function(obj) {
                                            var inst = jQuery.jstree.reference(obj.reference);
                                            var clickedNode = inst.get_node(obj.reference);
                                            $('#div-id').attr("value",clickedNode.id);
                                            $('#div-text').attr("value",clickedNode.text);
                                            $('#div-iscopy').attr("value",1);
										}
									},
									"cut": {
                                        "label": "剪切",
                                        "action": function (obj) {
                                            var inst = jQuery.jstree.reference(obj.reference);
                                            var clickedNode = inst.get_node(obj.reference);
                                            $('#div-id').attr("value", clickedNode.id);
                                            $('#div-text').attr("value", clickedNode.text);
                                            $('#div-iscopy').attr("value", 2);
                                            //alert(clickedNode.id);

                                            //重新建树

                                        },
                                    },
                                        "paste": {
                                            "label": "粘贴",
                                            "action": function (obj) {
                                                var inst = jQuery.jstree.reference(obj.reference);
                                                var clickedNode = inst.get_node(obj.reference);
                                                var text1 = $('#div-text').attr("value");
                                                var idcopy = $('#div-iscopy').attr("value");
                                                //alert(idcopy)
                                                //alert(text1);
                                                var newNode = inst.create_node(inst.get_node(obj.reference), text1, "last", "", "");
                                                //对数据库进行添加操作
                                                //inst.edit(newNode, newNode.val,function(){

                                                //var obj = this.get_node(newNode);
                                                if (idcopy == 1) {
                                                    $.post("/template/node_add1_copy",
                                                        {
                                                            oldId: $('#div-id').attr("value"),
                                                            //id:obj.id,
                                                            text: $('#div-text').attr("value"),
                                                            parent: clickedNode.id,
                                                            type: "2",
                                                            //turl:obj.text
                                                        },
                                                        function (data) {
                                                            //alert(data);
                                                            $(inst.get_node(newNode)).attr("id", data);
                                                            $('#using_json').jstree().destroy();
                                                            tzs.index.doCreateTree();
                                                        }
                                                    );
                                                } else if (idcopy == 2) {
                                                    $.post("/template/node_add1_cut",
                                                        {
                                                            oldId: $('#div-id').attr("value"),
                                                            //id:obj.id,
                                                            text: $('#div-text').attr("value"),
                                                            parent: clickedNode.id,
                                                            type: "2",
                                                            //turl:obj.text
                                                        },
                                                        function (data) {
                                                            //alert(data);
                                                            //$(inst.get_node(newNode)).attr("id",data);

                                                        }
                                                    );
                                                    $('#using_json').jstree().destroy();
                                                    tzs.index.doCreateTree();
                                                } else {
                                                    alert("操作无效")
                                                }

                                                //});

                                                //var newNode = inst.create_node(inst.get_node(obj.reference), '请输入模板名称', "after", "", "");


                                            }
                                        }

								}
								//console.log(node);
							if (node.parent == '#') { //如果是根节点
								delete items.copy;
								delete items.paste;
								delete items.cut;
								delete items.delete;
								delete items.addTemplate;
							} else if (node.type == 1) { //如果是图谱
								delete items.copy;
								delete items.cut;
							} else if (node.type == 2) { //如果是图谱页
								delete items.add;
								delete items.addTemplate;
								delete items.paste;
							}
							return items; //注意要有返回值
						}
            
            
        }
    };

    $(function(){
        tzs.index.init();
    });
</script>



</body>
</html>
