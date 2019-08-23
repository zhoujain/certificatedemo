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
    <link href="../../js/plugins/layer/theme/default/layer.css" rel="stylesheet">
    <style>
        .btn_detail{
            border-radius: 3px;
            color: white;
            background-color: #1D84C6;
            border: 1px solid #1D84C6;
        }
        .btn_del{
            border-radius: 3px;
            color: white;
            background-color: red;
            border: 1px solid red;
        }
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
                    <table id="MultilogicCombo" class="table">
                        <thead>
                        <tr>
                            <td>检索项：</td>
                            <td>比较：</td>
                            <td>检索词：</td>
                        </tr>
                        </thead>
                        <tbody id="logictrs">
                        <tr class="logicarr">
                            <td><select class="form-control selectlogic">
                                <option selected="selected">请选择</option>
                                <option value="cnumber">登记号</option>
                                <option value="aid">委托号</option>
                            </select></td>
                            <td><select class="form-control">
                                <option selected="selected" value="like">包含</option>
                            </select></td>
                            <td>
                                <input class="form-control selectlogic" type="text" placeholder="检索词">
                            </td>
                        </tr>
                        </tbody>
                    </table>

                    <div class="row text-center">
                        <div class="checkbox" style="font-size: 15px">
                            <label>
                                <input style="margin-top: 2px" id="isQueryAllCertificate" type="checkbox">查询所有证书
                            </label>
                        </div>
                    </div>

                    <div class="row text-center">
                        <button class="btn btn-primary" onclick="queryCertificatesByLogic()">查询</button>
                        <button class="btn btn-success" onclick="queryCertificatesByLogic()">刷新表格</button>
                    </div>
                </div>
            </div>


            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <%--证书列表表格 开始--%>
                    <table id="table" data-pagination="true" data-page-size="10" data-page-list="[5, 10, 15, 20,30]">

                        <thead>
                        <tr>
                            <th data-field="id">序号</th>
                            <th data-field="cnumber">登记号</th>
                            <th data-field="toolname">器具名称</th>
                            <th data-field="model">器具规格</th>
                            <th data-field="outnumber">出厂编号</th>
                            <th data-field="toolId">设备编号</th>
                            <th data-field="manufacturer">制造厂商</th>
                            <th data-field="number">套数</th>
                            <th data-field="company.name">委托单位</th>
                            <th data-field="company.linkMan">联系人</th>
                            <th data-field="company.adateStr">委托日期</th>
                            <th data-field="company.pdateStr">打印日期</th>
                            <th data-field="company.aid">委托号</th>
                            <th data-field="action">操作</th>

                        </tr>
                        </thead>
                    </table>
                    <%--证书列表表格 结束--%>

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
<script src="../../js/plugins/layer/layer.js"></script>
<script type="text/javascript" src="pageoffice.js" id="po_js_main"></script>
<script src="../../js/company_query.js" type="text/javascript"></script>

</body>
</html>
