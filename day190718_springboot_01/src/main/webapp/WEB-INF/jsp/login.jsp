<%--
  Created by IntelliJ IDEA.
  User: zhoujian
  Date: 2019/7/22
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">


<!-- Mirrored from www.zi-han.net/theme/hplus/login_v2.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:49 GMT -->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <title>证书ERP打印系统登录</title>
    <meta name="keywords" content="证书ERP打印系统">
    <meta name="description" content="证书ERP打印系统">
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <link href="../../css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="../../css/animate.min.css" rel="stylesheet">
    <link href="../../css/style.min.css" rel="stylesheet">
    <link href="../../css/login.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>
        if(window.top!==window.self){window.top.location=window.location};
    </script>

</head>

<body class="signin">
<div class="signinpanel">
    <div class="row">
        <div class="col-sm-7">
            <div class="signin-info">
                <div class="logopanel m-b">
                    <h1>证书ERP打印系统</h1>
                </div>
                <div class="m-b"></div>
                <h4>欢迎使用 <strong>证书ERP打印系统</strong></h4>

            </div>
        </div>
        <div class="col-sm-5">
            <form method="post" action="/loginUser">
                <h4 class="no-margins">登录：</h4>
                <p class="m-t-md">证书ERP打印系统</p>
                <input type="text" class="form-control uname" name="username" placeholder="用户名" />
                <input type="password" class="form-control pword m-b" name="upwd" placeholder="密码" />
<%--                <a href="#">忘记密码了？</a>--%>
                <button class="btn btn-success btn-block">登录</button>
            </form>
        </div>
    </div>
    <div class="signup-footer">
        <div class="pull-left">
            &copy; 淮微软件技术有限公司
        </div>
    </div>
</div>
<script type="text/javascript">
    window.onload = function () {
        if(${not empty requestScope.message}){
            alert("用户名密码错误");
        }
    }
</script>
</body>


<!-- Mirrored from www.zi-han.net/theme/hplus/login_v2.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:52 GMT -->
</html>
