<%--
  Created by IntelliJ IDEA.
  User: zhoujian
  Date: 2019/7/20
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <style>
    </style>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="animated fadeInRight">

            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <strong>证书查询</strong>
                </div>
                <div class="ibox-content">
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
                            <select class="form-control m-b" >
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
                        <div class="col-xs-3 col-sm-3 col-md-3"><input class="form-control" type="text" placeholder="检索词"></div>
                        <div class="col-xs-3 col-sm-3 col-md-3"><button class="btn btn-block btn-info" style="width: 100px;float: left">添加</button><button class="btn btn-block btn-danger" style="width: 100px;float: left">删除</button></div>
                    </div>
                </div>
            </div>


            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <table id="myTable" style="font-size: 12px"
                           class="footable table-bordered table table-stripped toggle-arrow-tiny footable-loaded tablet breakpoint"
                           data-empty="没有数据" data-paging="true">
                        <%--<thead>
                            <tr>
                                <th name="cid" data-toggle="true" class="footable-sortable footable-first-column">序号<span class="footable-sort-indicator"></span></th>
                                <th name="cnumber" class="footable-sortable">证书编号<span class="footable-sort-indicator"></span></th>
                                <th name="ccompany" class="footable-sortable">证书单位<span class="footable-sort-indicator"></span></th>
                                <th name="ctoolname" class="footable-sortable">器具名称<span class="footable-sort-indicator"></span></th>
                                <th name="cmodel" class="footable-sortable">型号规格<span class="footable-sort-indicator"></span></th>
                                <th name="coutnumber" class="footable-sortable">出厂编号<span class="footable-sort-indicator"></span></th>
                                <th name="cmanfacturer" class="footable-sortable">制造厂商<span class="footable-sort-indicator"></span></th>
                                <th class="footable-sortable">委托单号<span class="footable-sort-indicator"></span></th>
                                <th class="footable-sortable">检定日期<span class="footable-sort-indicator"></span></th>
                                <th class="footable-sortable">检测部门<span class="footable-sort-indicator"></span></th>
                                <th class="footable-sortable">添加人<span class="footable-sort-indicator"></span></th>
                                <th class="footable-sortable">打印人<span class="footable-sort-indicator"></span></th>
                                <th class="footable-sortable">打印日期<span class="footable-sort-indicator"></span></th>
                                <th class="footable-sortable footable-last-column">检测费<span class="footable-sort-indicator"></span></th>
                            </tr>
                        </thead>--%>
                    </table>
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
<script src="../../js/plugins/footable/footable.min.js"></script>
<script>
    $(document).ready(function () {
        $(".i-checks").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green",})
    });
</script>
<script src="js/index_query.js" type="text/javascript"></script>
</body>
</html>
