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


    <title>证书查询</title>

    <link rel="shortcut icon" href="favicon.ico">
    <link href="../../css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="../../css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="../../css/plugins/jsTree/style.min.css" rel="stylesheet">
    <link href="../../css/animate.min.css" rel="stylesheet">
    <link href="../../css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="../../css/plugins/footable/footable.bootstrap.min.css" rel="stylesheet">
    <link href="../../css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">


    <style>
    </style>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="animated fadeInRight">

            <div class="col-md-3">
                <div class="ibox">
                    <div class="ibox-content">
                        <button class="btn btn-primary" style="padding:0;width: 70px;height: 35px;font-size: 12px"
                                data-toggle="modal" data-target="#searchTempModal">搜索模板
                        </button>
                        <button class="btn btn-primary" style="padding:0;width: 85px;height: 35px;font-size: 12px"
                                data-toggle="modal" data-target="#searchHistoryModal">搜索历史证书
                        </button>
                        <br>
                        <br>

                        <h2>这里放模板的树</h2>
                    </div>
                </div>
            </div>
            <div class="col-md-9">
                <div class="ibox">
                    <div class="ibox-content">
                        <h2>word的操作</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 搜索模板 开始 -->
<div class="modal fade" id="searchTempModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="row">
                    <div class="col-md-10">
                        <h4 class="modal-title">搜索模板</h4>

                    </div>
                    <div class="col-md-2">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>

                    </div>
                </div>
            </div>
            <div class="modal-body">
                <h2>123</h2>
            </div>
        </div>
    </div>
</div>
<!-- 搜索模板 结束 -->

<!-- 搜索历史证书弹窗 开始 -->
<div class="modal fade" id="searchHistoryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="row">
                    <div class="col-md-10">
                        <h4 class="modal-title">搜索历史证书</h4>

                    </div>
                    <div class="col-md-2">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>

                    </div>
                </div>
            </div>
            <div class="modal-body">
                <strong>证书查询</strong>
                <hr>
                <div class="row text-center">
                    <div class="col-xs-1 col-sm-1 col-md-1">逻辑：</div>
                    <div class="col-xs-3 col-sm-3 col-md-3">检索项：</div>
                    <div class="col-xs-2 col-sm-2 col-md-2">比较：</div>
                    <div class="col-xs-3 col-sm-3 col-md-3">检索词：</div>
                    <div class="col-xs-3 col-sm-3 col-md-3"></div>
                </div>
                <div class="row text-center">
                    <div class="col-xs-1 col-sm-1 col-md-1"></div>
                    <div class="col-xs-3 col-sm-3 col-md-3">
                        <select class="form-control m-b">
                            <option selected="selected" disabled>证书编号</option>
                            <option>选项 2</option>
                            <option>选项 3</option>
                            <option>选项 4</option>
                        </select>
                    </div>
                    <div class="col-xs-2 col-sm-2 col-md-2">
                        <select class="form-control m-b">
                            <option selected="selected" disabled>包含</option>
                            <option>选项 2</option>
                            <option>选项 3</option>
                            <option>选项 4</option>
                        </select></div>
                    <div class="col-xs-3 col-sm-3 col-md-3"><input class="form-control" type="text" placeholder="检索词">
                    </div>
                    <div class="col-xs-3 col-sm-3 col-md-3">
                        <button class="btn btn-info">添加</button>
                        <button class="btn btn-danger">删除</button>
                    </div>
                </div>
            </div>


            <div class="ibox">
                <div class="ibox-content">
                    <%--证书列表表格 开始--%>
                    <table id="table">

                        <thead>
                        <tr>
                            <th data-field="cid">序号</th>
                            <th data-field="cnumber">证书编号</th>
                            <th data-field="ccompany">证书单位</th>
                            <th data-field="ctoolname">器具名称</th>
                            <th data-field="cmodel">型号规格</th>
                            <th data-field="coutnumber">出厂编号</th>
                            <th data-field="cmanfacturer">制造厂商</th>
                            <th data-field="cdelegate">委托单号</th>
                            <th data-field="ccheckdate">检定日期</th>
                            <th data-field="ccheckdepartment">检测部门</th>
                            <th data-field="uname">添加人</th>
                            <th data-field="puname">打印人</th>
                            <th data-field="cprintdate">打印日期</th>
                            <th data-field="cmoney">检测费</th>
                            <th data-field="actions">操作</th>
                        </tr>
                        </thead>
                    </table>
                    <%--证书列表表格 结束--%>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<!-- 搜索历史证书弹窗 结束 -->

<script src="../../js/jquery.min.js?v=2.1.4"></script>
<script src="../../js/bootstrap.min.js?v=3.3.6"></script>
<script src="../../js/content.min.js?v=1.0.0"></script>
<script src="../../js/plugins/iCheck/icheck.min.js"></script>
<script src="../../js/plugins/footable/footable.min.js"></script>
<script src="../../js/plugins/bootstrap-table/bootstrap-table.min.js"></script>


<script>
    $(document).ready(function () {
        $(".i-checks").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green",})
    });

    $(function () {
        var $table = $('#table');
        $.ajax({
            url: '/getCertificatesDataJSON',
            type: 'post',
            dataType: 'json',
            success: function (d) {
                $table.bootstrapTable({
                    data: d
                });
            }
        });
    })
</script>
</body>
</html>
