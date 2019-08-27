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
    <title>Execl页面</title>
    <link href="../../css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
</head>
<body>
<input id="ButtonSave" class="btn btn-primary"  type="button" value="保存Execl"  onclick="return Save()" />
<input id="Button1" class="btn btn-primary" type="button" value="隐藏/显示 标题栏"  onclick="return Button1_onclick()" />
<input id="Button2" class="btn btn-info"  type="button" value="隐藏/显示 菜单栏" onclick="return Button2_onclick()" />
<input id="Button3" class="btn btn-primary" type="button" value="隐藏/显示 自定义工具栏"  onclick="return Button3_onclick()" />
<input id="Button4" class="btn btn-info" type="button" value="隐藏/显示 Office工具栏"  onclick="return Button4_onclick()" />
<input id="doc" class="btn btn-primary"  type="button" value="返回"  onclick="ToDoc()" />
<div style="width:1200px;height:800px;" >${pageoffice1}</div>
<a id="aGo1" target="_self" style="display: none;" href="">隐藏链接</a>
<div id="div-issave" style="display: none" value="1">是否保存的标志</div>
<script src="../../js/jquery.min.js?v=2.1.4"></script>

<script type="text/javascript">
    function ToDoc() {
        if($('#div-issave').attr("value")==1){
            alert("请进行execl保存");
            return false;
        }
        if(!!window.ActiveXObject || "ActiveXObject" in window){
            var scid =${scid};
            //alert(scid);
            $("#aGo1").attr("href","/record_display_word1?scid="+scid);
        }else {
            //alert(scid);
            var scid =${scid};
            $("#aGo1").attr("href","/record_display_word1?scid="+scid);
            //$("#aGo1").attr("href","javascript:POBrowser.openWindowModeless('/record_word2?id="+id+"','width=1200px;height=800px;');");
        }

        //alert("/word1?text="+selected.node.text)
        $("#aGo1")[0].click();
    }
    function Save() {
        alert("保存成功");
        $('#div-issave').attr("value","2");
        document.getElementById("PageOfficeCtrl2").WebSave();
    }
    function PrintFile(){
        document.getElementById("PageOfficeCtrl2").ShowDialog(4);

    }
    function IsFullScreen(){
        document.getElementById("PageOfficeCtrl2").FullScreen = !document.getElementById("PageOfficeCtrl2").FullScreen;

    }
    function CloseFile(){
        window.external.close();
    }

    function BeforeBrowserClosed(){
        //对session进行清除
<%--        <%--%>
<%--            HttpSession session1 = request.getSession();--%>
<%--            session1.removeAttribute("scid");--%>
<%--        %>--%>
<%--        alert("对session进行清除")--%>
        if (document.getElementById("PageOfficeCtrl2").IsDirty){
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
        var bVisible = document.getElementById("PageOfficeCtrl2").Titlebar;
        document.getElementById("PageOfficeCtrl2").Titlebar = !bVisible;
    }

    // 隐藏/显示 菜单栏
    function Button2_onclick() {
        var bVisible = document.getElementById("PageOfficeCtrl2").Menubar;
        document.getElementById("PageOfficeCtrl2").Menubar = !bVisible;
    }


    // 隐藏/显示 自定义工具栏
    function Button3_onclick() {
        var bVisible = document.getElementById("PageOfficeCtrl2").CustomToolbar;
        document.getElementById("PageOfficeCtrl2").CustomToolbar = !bVisible;
    }
    // 隐藏/显示 Office工具栏
    function Button4_onclick() {
        var bVisible = document.getElementById("PageOfficeCtrl2").OfficeToolbars;
        document.getElementById("PageOfficeCtrl2").OfficeToolbars = !bVisible;
    }
</script>
</body>
</html>
