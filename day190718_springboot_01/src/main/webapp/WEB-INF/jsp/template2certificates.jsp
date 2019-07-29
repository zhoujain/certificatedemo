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
</head>
<body>
<input id="btn_save" type="button" value="保存证书" onclick="return saveCertificate()"/>
<input id="btn_next" type="button" value="下一份证书" onclick="return nextCertificate()"/>
<input id="Button1" type="button" value="隐藏/显示 标题栏"  onclick="return Button1_onclick()" />
<input id="Button2" type="button" value="隐藏/显示 菜单栏" onclick="return Button2_onclick()" />
<input id="Button3" type="button" value="隐藏/显示 自定义工具栏"  onclick="return Button3_onclick()" />
<input id="Button4" type="button" value="隐藏/显示 Office工具栏"  onclick="return Button4_onclick()" />
<div>${po_t2c}</div>
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
        alert("保存证书")
        document.getElementById("PageOfficeCtrl1").WebSave();
    }

    function nextCertificate() {
        alert("下一份证书尚未准备好，该功能尚未实现")
    }
</script>
</body>
</html>
