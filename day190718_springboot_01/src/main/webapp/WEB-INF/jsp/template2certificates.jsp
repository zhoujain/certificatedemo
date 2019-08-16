<%--
  Created by IntelliJ IDEA.
  User: 雨木林风
  Date: 2019/7/27
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>模板2证书</title>
    <script src="../../js/jquery.min.js?v=2.1.4"></script>
    <link href="../../css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
</head>
<body>
<input id="btn_save" class="btn btn-primary" type="button" value="保存证书" onclick="return saveCertificate()"/>
<input id="btn_next" class="btn btn-info" type="button" value="下一份证书" onclick="return nextCertificate()"/>
<input id="ButtonClose" class="btn btn-primary"  type="button" value="关闭"  onclick="return CloseFile()" />
<input id="Button1" class="btn btn-info" type="button" value="隐藏/显示 标题栏"  onclick="return Button1_onclick()" />
<input id="Button2" class="btn btn-primary" type="button" value="隐藏/显示 菜单栏" onclick="return Button2_onclick()" />
<input id="Button3" class="btn btn-info" type="button" value="隐藏/显示 自定义工具栏"  onclick="return Button3_onclick()" />
<input id="Button4" class="btn-primary" type="button" value="隐藏/显示 Office工具栏"  onclick="return Button4_onclick()" />
<div>${po_t2c}</div>
<div><a id="cGo" target="_self" style="display: none;" href="">HiddenLink</a></div>
<script type="text/javascript" src="pageoffice.js" id="po_js_main"></script>
<script>
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

    function saveCertificate() {
        alert("保存成功")
        document.getElementById("PageOfficeCtrl1").WebSave();
    }
    function CloseFile(){

        window.external.close();
    }

    function nextCertificate() {
        if(confirm("请确认文档已经保存")){
            var s= "<%=session.getAttribute("tid")%>";
            alert(s);
            // if(!!window.ActiveXObject || "ActiveXObject" in window){
            //     $("#aGo").attr("href","/openWordwithNumchanged?id="+s);
            // }else {
            //     $("#aGo").attr("href","javascript:POBrowser.openWindowModeless('/openWordwithNumchanged?id="+s+"','width=1200px;height=800px;');");
            // }
            $("#cGo").attr("href","/openWordwithNumchanged?id="+s);
            $("#cGo")[0].click();
        }else{
            return false;
        }

    }
</script>
</body>
</html>
