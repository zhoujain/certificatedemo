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


    <title>原始记录</title>

    <link rel="shortcut icon" href="favicon.ico">
    <link href="../../css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="../../css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="../../css/plugins/jsTree/style.min.css" rel="stylesheet">
    <link href="../../css/animate.min.css" rel="stylesheet">
    <link href="../../css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="../../js/plugins/layer/theme/default/layer.css" rel="stylesheet">
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
            <iframe name="mainFrame" width="100%" height="900px" frameborder="0">

            </iframe>

        </div>
    </div>
</div>
<script src="../../js/jquery.min.js?v=2.1.4"></script>
<script src="../../js/bootstrap.min.js?v=3.3.6"></script>
<script src="../../js/content.min.js?v=1.0.0"></script>
<script src="../../js/plugins/iCheck/icheck.min.js"></script>
<script src="../../js/plugins/jsTree/jstree.min.js"></script>
<script src="../../js/plugins/layer/layer.js"></script>
<script type="text/javascript" src="pageoffice.js" id="po_js_main"></script>
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
            $.getJSON("/ttemplate/ttemplateTree?"+Date.UTC(new Date()),function(rs){
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
                if(selected.event == undefined){
                    return 0;
                }
                if('click'==selected.event.type){
                    if(selected.node.type ==2 ||selected.node.type ==3){

                        if(!!window.ActiveXObject || "ActiveXObject" in window){
                            $("#aGo").attr("href","/record_word1?id="+selected.node.id);
                        }else {
                            $("#aGo").attr("href","javascript:POBrowser.openWindowModeless('/record_word1?id="+selected.node.id+"','width=1200px;height=800px;');");
                        }

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
                            var ref = $('#using_json').jstree(true);
                            sel = ref.get_selected();
                            if(!sel.length){
                                return false;
                            }
                            sel = sel[0];
                            //获取id
                            var tree_id;
                            $.ajaxSettings.async = false;
                            $.post("/ttemplate/tnode_add",

                                {
                                    id:1,
                                    text:1,
                                    parent:1,
                                    type:"1",
                                    turl:1
                                },
                                function(data){
                                    //alert(data+" 111");
                                    tree_id = data;
                                }

                            );
                            $.ajaxSettings.async = true;
                            //alert(tree_id);
                            sel = ref.create_node(sel,{"type":"1","id":tree_id});
                            if(sel){
                                ref.edit(sel,sel.val,function () {
                                    var obj = ref.get_node(sel);
                                    ///alert(obj.id+" "+obj.text+" "+obj.parent);
                                    $.post("/ttemplate/tnode_edit",
                                        {
                                            id:obj.id,
                                            text:obj.text,
                                            parent:obj.parent
                                        },
                                        function(){

                                        }
                                    );
                                })
                            }

                        }
                    },
                    "rename":{
                        "label":"重命名",
                        "action":function (obj) {

                            var ref = $('#using_json').jstree(true);
                            sel = ref.get_selected();
                            if(!sel.length){
                                return false;
                            }
                            sel = sel[0];
                            ref.edit(sel,sel.val,function () {
                                var obj = ref.get_node(sel);
                                $.post("/ttemplate/tnode_edit",
                                    {
                                        id:obj.id,
                                        text:obj.text,
                                        parent:obj.parent
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
                            var ref = $('#using_json').jstree(true);
                            sel = ref.get_selected();
                            var p = ref.get_parent(sel);
                            var plength = $("#"+p).children.length;//目录下长度
                            //alert(sel.length);
                            //alert(plength);
                            if(!sel.length){
                                return false;
                            }
                            sel = sel[0];
                            if(plength ==0){
                                layer.confirm("这是唯一目录，确认要删除！！！",{
                                    btn:['确认','放弃'],
                                    skin:'layui-layer-molv',
                                    icon:5
                                },function(){
                                    layer.closeAll('dialog');
                                    $.post("/ttemplate/tnode_delete",
                                        {
                                            id:sel
                                        },function (data) {

                                        })
                                    ref.delete_node(sel);


                                },function(){
                                    layer.closeAll('dialog');
                                });
                            }else{
                                layer.confirm("你确认要删除！！！",{
                                    btn:['确认','放弃'],
                                    skin:'layui-layer-molv',
                                    icon:5
                                },function(){
                                    layer.closeAll('dialog');
                                    $.post("/ttemplate/tnode_delete",{
                                        id:sel
                                    },function (data) {

                                    })
                                    ref.delete_node(sel);

                                },function(){
                                    layer.closeAll('dialog');
                                });
                            }



                        }
                    },
                    "addTemplate":{
                        "label":"添加模板",
                        "action":function (obj) {
                            var ref = $('#using_json').jstree(true);
                            sel = ref.get_selected();
                            if(!sel.length){
                                return false;
                            }
                            sel = sel[0];
                            //获取id
                            var tree_id;
                            $.ajaxSettings.async = false;
                            $.post("/ttemplate/tnode_add",

                                {
                                    id:1,
                                    text:1,
                                    parent:1,
                                    type:"2",
                                    turl:1
                                },
                                function(data){
                                    //alert(data+" 111");
                                    tree_id = data;
                                }

                            );
                            $.ajaxSettings.async = true;
                            //alert(tree_id);
                            sel = ref.create_node(sel,{"type":"2","id":tree_id});
                            if(sel){
                                ref.edit(sel,sel.val,function () {
                                    var obj = ref.get_node(sel);
                                    ///alert(obj.id+" "+obj.text+" "+obj.parent);
                                    $.post("/ttemplate/tnode_edit_add",
                                        {
                                            id:obj.id,
                                            text:obj.text,
                                            parent:obj.parent
                                        },
                                        function(){

                                        }
                                    );
                                })
                            }
                        }
                    },
                    "copy":{
                        "label": "复制",
                        "action": function(obj) {
                            var ref = $('#using_json').jstree(true);
                            sel = ref.get_selected(true);
                            //alert(sel);
                            if(!sel.length){
                                return false;
                            }
                            sel= sel[0];
                            var obj = ref.get_node(sel);
                            $('#div-id').attr("value",obj.id);
                            $('#div-text').attr("value",obj.text);
                            $('#div-iscopy').attr("value",1);
                        }
                    },
                    "cut": {
                        "label": "剪切",
                        "action": function (obj) {
                            var ref = $('#using_json').jstree(true);
                            sel = ref.get_selected(true);
                            if(!sel.length){
                                return false;
                            }
                            sel= sel[0];
                            var obj = ref.get_node(sel);
                            $('#div-id').attr("value",obj.id);
                            $('#div-text').attr("value",obj.text);
                            $('#div-iscopy').attr("value",1);
                            ref.delete_node(sel);


                        },
                    },
                    "paste": {
                        "label": "粘贴",
                        "action": function (obj) {
                            var ref = $('#using_json').jstree(true);
                            sel = ref.get_selected();
                            if(!sel.length){
                                return false;
                            }
                            sel= sel[0];
                            var text1 = $('#div-text').attr("value");
                            var idcopy = $('#div-iscopy').attr("value");
                            var tree_id;
                            $.ajaxSettings.async = false;
                            $.post("/ttemplate/tnode_add",

                                {
                                    id:1,
                                    text:1,
                                    parent:1,
                                    type:"2",
                                    turl:1
                                },
                                function(data){
                                    //alert(data+" 111");
                                    tree_id = data;
                                }

                            );
                            $.ajaxSettings.async = true;
                            sel = ref.create_node(sel,{"type":"2","text":text1,"id":tree_id});
                            if (idcopy == 1) {
                                var obj = ref.get_node(sel);
                                $.post("/ttemplate/tnode_add1_copy",
                                    {
                                        id:tree_id,
                                        oldId: $('#div-id').attr("value"),
                                        text: $('#div-text').attr("value"),
                                        parent: obj.parent
                                    },
                                    function () {

                                    }
                                );
                            } else if (idcopy == 2) {
                                var obj = ref.get_node(sel);
                                $.post("/ttemplate/tnode_add1_cut",
                                    {
                                        oldId: $('#div-id').attr("value"),
                                        text: $('#div-text').attr("value"),
                                        parent: obj.parent
                                    },
                                    function () {

                                    }
                                );


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
                }  else if (node.type == 2 ||node.type == 3) { //如果是图谱页
                    delete items.add;
                    delete items.addTemplate;
                    delete items.paste;
                }else { //如果是图谱
                    delete items.copy;
                    delete items.cut;
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
