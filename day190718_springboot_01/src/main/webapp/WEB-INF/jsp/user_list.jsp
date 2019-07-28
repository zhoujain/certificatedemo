<%--
  Created by IntelliJ IDEA.
  User: zhoujian
  Date: 2019/7/20
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!--360浏览器优先以webkit内核解析-->


    <title>证书查询</title>

    <link rel="shortcut icon" href="favicon.ico">
    <link href="../../css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="../../css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="../../css/animate.min.css" rel="stylesheet">
    <link href="../../css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="../../css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <style>
    </style>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="animated fadeInRight">

            <div class="ibox">
                <div class="ibox-title">
                    <strong>按用户名查询</strong>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <div class="col-md-3"></div>
                        <div class="col-md-3">
                            <div class="input-group">
                                <span class="input-group-addon" id="unamelabel">用户名：</span>
                                <input style="width: 200px" id="usernametext" type="text" class="form-control" placeholder="请输入用户名" aria-describedby="unamelabel">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <button style="margin-left: 20px" onclick="queryUserByUsername()" class="btn btn-primary">查询</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="ibox">
                <div class="ibox-title">
                    <strong>添加用户</strong>
                </div>
                <div class="ibox-content text-center">
                    <button class="btn btn-primary">添加用户</button>
                </div>
            </div>


            <div class="ibox">
                <div class="ibox-content">
                    <%--用户列表表格 开始--%>
                    <table id="table" data-pagination="true" data-page-size="10" data-page-list="[5, 10, 15, 20,30]">

                        <thead>
                        <tr>
                            <th data-field="uid">用户id</th>
                            <th data-field="username">用户名</th>
                            <th data-field="usertype">用户类型</th>
                            <th data-field="userstate">用户状态</th>
                            <th data-field="actions">操作</th>
                        </tr>
                        </thead>
                    </table>
                    <%--用户列表表格 结束--%>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../../js/jquery.min.js?v=2.1.4"></script>
<script src="../../js/bootstrap.min.js?v=3.3.6"></script>
<script src="../../js/content.min.js?v=1.0.0"></script>
<script src="../../js/plugins/iCheck/icheck.min.js"></script>
<script src="../../js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="../../js/user_list.js" type="text/javascript"></script>

</body>
</html>
