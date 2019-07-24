<%--
  Created by IntelliJ IDEA.
  User: zhoujian
  Date: 2019/7/20
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!--360浏览器优先以webkit内核解析-->


    <title>用户列表</title>

    <link rel="shortcut icon" href="favicon.ico">
    <link href="../../css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="../../css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="../../css/plugins/jsTree/style.min.css" rel="stylesheet">
    <link href="../../css/animate.min.css" rel="stylesheet">
    <link href="../../css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="../../css/plugins/footable/footable.bootstrap.min.css" rel="stylesheet">
    <link href="../../css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="animated fadeInRight">

            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <strong>用户列表</strong>
                </div>
                <div class="ibox-content">
                    查询或者排序操作
                </div>
            </div>


            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <%--证书列表表格 开始--%>
                    <table id="table">

                        <thead>
                        <tr>
                            <th data-field="uid">用户id</th>
                            <th data-field="username">用户姓名</th>
                            <th data-field="usertype">用户类型</th>
                            <th data-field="actions">操作</th>
                        </tr>
                        </thead>
                    </table>
                    <%--证书列表表格 结束--%>
                </div>
            </div>

            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    备注：
                </div>
                <div class="ibox-content">
                    管理员权限：证书管理，原始记录管理，用户管理，审核<br>
                    审核用户权限：证书管理，原始记录管理，审核<br>
                    管理员权限：证书管理，原始记录管理<br>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../../js/jquery.min.js?v=2.1.4"></script>
<script src="../../js/bootstrap.min.js?v=3.3.6"></script>
<script src="../../js/content.min.js?v=1.0.0"></script>
<script src="../../js/plugins/iCheck/icheck.min.js"></script>
<script src="../../js/plugins/jsTree/jstree.min.js"></script>
<script src="../../js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script>
    $(document).ready(function () {
        $(".i-checks").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green",})
    });
</script>
<script type="text/javascript">
    $(function () {
        var $table = $('#table');
        $.ajax({
            url: '/getUsersDataJSON',
            type: 'post',
            dataType: 'json',
            success: function (d) {
                $table.bootstrapTable({
                    data: d
                });

            }
        });

    });
</script>
</body>
</html>
