<%--
  Created by IntelliJ IDEA.
  User: zhoujian
  Date: 2019/7/23
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="../../js/jquery.min.js"></script>
    <script type="text/javascript" src="pageoffice.js" id="po_js_main"></script>
</head>
<body>
<a href="javascript:POBrowser.openWindowModeless('/word1','width=1200px;height=800px;');" target="mainFrame">打开文件</a>
<div>
    <iframe name="mainFrame" width="100%" height="600px" style="border: 1px black solid">

    </iframe>
</div>
</body>
</html>
