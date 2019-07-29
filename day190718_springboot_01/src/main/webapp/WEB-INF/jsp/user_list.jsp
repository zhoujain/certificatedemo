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
                    用户查询
                </div>
                <div class="ibox-content">
                    <div class="form-inline text-center">
                        <div class="input-group">
                            <span class="input-group-addon" id="unamelabel">用户名：</span>
                            <input id="usernametext" type="text" class="form-control" placeholder="请输入用户名" aria-describedby="unamelabel">
                        </div>
                        <button style="margin-left: 20px" onclick="queryUserByLikeUsername()" class="btn btn-primary">查询</button>
                    </div>
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

<!-- 模态框（Modal） -->
<div class="modal fade" id="updateUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    修改用户信息
                </h4>
            </div>
            <div class="modal-body">
                <div class="input-group">
                    <span class="input-group-addon">用户编号</span>
                    <input id="uid" readonly="readonly" type="text" class="form-control">
                </div>
                <br>
                <div class="input-group">
                    <span class="input-group-addon">用户名</span>
                    <input id="username" type="text" class="form-control">
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
                <%--<div class="input-group">
                    <span class="input-group-addon">用户状态</span>
                    <select id="ustate" type="text" class="form-control">
                        <option value="1">正常</option>
                        <option value="0">停用</option>
                    </select>
                </div>
                <br>--%>
                <div class="input-group">
                    <span class="input-group-addon">密码</span>
                    <input id="upwd" type="text" class="form-control">
                </div>
                <br>
                <div class="input-group">
                    <span class="input-group-addon">确认密码</span>
                    <input id="upwd2" type="text" class="form-control">
                </div>
                <br>
                <button class="btn btn-info" onclick="$('#upwd').val('');$('#upwd2').val('')" style="margin-left: 20px">清空密码</button>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-info" data-dismiss="modal">取消
                </button>
                <button onclick="commitUpdateUser()" type="button" class="btn btn-primary">
                    提交修改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<script src="../../js/jquery.min.js?v=2.1.4"></script>
<script src="../../js/bootstrap.min.js?v=3.3.6"></script>
<script src="../../js/content.min.js?v=1.0.0"></script>
<script src="../../js/plugins/iCheck/icheck.min.js"></script>
<script src="../../js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="../../js/user_list.js"></script>

</body>
</html>
