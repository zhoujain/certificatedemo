<%--
  Created by IntelliJ IDEA.
  User: zhoujian
  Date: 2019/7/23
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="../../css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
</head>
<body>
<input id="ButtonSave" class="btn btn-primary"  type="button" value="保存记录模板"  onclick="return Save()" />
<input id="ButtonCheck" class="btn btn-primary"  type="button" value="审核"  onclick="return CheckFile(1)" />
<input id="ButtonClose" class="btn btn-info"  type="button" value="关闭"  onclick="return CloseFile()" />
<input id="Button1" class="btn btn-primary" type="button" value="隐藏/显示 标题栏"  onclick="return Button1_onclick()" />
<input id="Button2" class="btn btn-info"  type="button" value="隐藏/显示 菜单栏" onclick="return Button2_onclick()" />
<input id="Button3" class="btn btn-primary" type="button" value="隐藏/显示 自定义工具栏"  onclick="return Button3_onclick()" />
<input id="Button4" class="btn btn-info" type="button" value="隐藏/显示 Office工具栏"  onclick="return Button4_onclick()" />
<input id="execl" class="btn btn-primary"  type="button" value="Execl"  onclick="ToExecl()" />
<div style="width:1200px;height:800px;" >${pageoffice}</div>

<a id="aGo1" target="_self" style="display: none;" href="">隐藏链接</a>
<%--<iframe name="frame" width="100%" height="800px" frameborder="0">--%>
<%--    --%>
<%--</iframe>--%>
<script src="../../js/jquery.min.js?v=2.1.4"></script>
<script type="text/javascript">
    //发送通过审核的消息
    function CheckFile(value) {
        var sResult = window.external.CallParentFunc("updateCount("+value+");");
        if(sResult=='poerror:parentlost'){
            alert('父页面关闭或跳转刷新了，导致父页面函数没有调用成功！');
            return false;
        }
        document.getElementById("PageOfficeCtrl1").Alert("审核成功");
    }
    function ToExecl() {
        if(!!window.ActiveXObject || "ActiveXObject" in window){
            var id =${id};
            $("#aGo1").attr("href","/record_word2?id="+id);
        }else {
            var id =${id};
            $("#aGo1").attr("href","/record_word2?id="+id);
            //$("#aGo1").attr("href","javascript:POBrowser.openWindowModeless('/record_word2?id="+id+"','width=1200px;height=800px;');");
        }

        //alert("/word1?text="+selected.node.text)
        $("#aGo1")[0].click();
    }
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
</body>
</html>
