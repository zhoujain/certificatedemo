
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!--360浏览器优先以webkit内核解析-->


    <title>添加用户</title>

    <link rel="shortcut icon" href="favicon.ico">
    <link href="../../css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="../../css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="../../css/animate.min.css" rel="stylesheet">
    <link href="../../css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <style>
    </style>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="animated fadeInRight">

            <div class="ibox">
                <div class="ibox-title">
                    添加用户
                </div>
                <div class="ibox-content">
                    <div class="text-center">
                        <div class="input-group">
                            <span class="input-group-addon">用户名：</span>
                            <input id="usernametext" type="text" class="form-control" placeholder="请输入用户名">
                        </div>
                        <br>
                        <div class="input-group">
                            <span class="input-group-addon">密码</span>
                            <input id="upwdtext" type="password" class="form-control" placeholder="请输入用户名">
                        </div>
                        <br>
                        <div class="input-group">
                            <span class="input-group-addon">确认密码</span>
                            <input id="upwd2text" type="password" class="form-control" placeholder="请再次输入用户名">
                        </div>
                        <br>
                        <div class="input-group">
                            <span class="input-group-addon">用户类型</span>
                            <select id="usertype" type="text" class="form-control">
                                <option value="1">管理员</option>
                                <option value="2">审核员</option>
                                <option value="3">普通用户</option>
                            </select>
                        </div>
                        <br>
                        <div class="checkbox" style="font-size: 15px">
                            <label>
                                <input id="idShowPwd" type="checkbox" style="margin-top: 2px">显示密码
                            </label>
                        </div>
                        <br>
                        <div class="form-inline">
                            <button id="btn_commitInsert" class="btn btn-primary">确定</button>
                            <button id="btn_clear" class="btn btn-danger">清空</button>
                        </div>
                        <br>
                    </div>
                </div>
            </div>


        </div>
    </div>


</div>

<script src="../../js/jquery.min.js?v=2.1.4"></script>
<script src="../../js/bootstrap.min.js?v=3.3.6"></script>
<script src="../../js/content.min.js?v=1.0.0"></script>
<script src="../../js/plugins/iCheck/icheck.min.js"></script>
<script src="../../js/user_add.js"></script>
</body>
</html>
