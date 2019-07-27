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

            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <strong>证书查询</strong>
                </div>
                <div class="ibox-content">
                    <table id="MultilogicCombo" class="table">
                        <thead>
                        <tr>
                            <td>逻辑：</td>
                            <td>检索项：</td>
                            <td>比较：</td>
                            <td>检索词：</td>
                            <td>操作：</td>
                        </tr>
                        </thead>
                        <tbody id="logictrs">
                        <tr class="logicarr" id="firstlogic">
                            <td>

                            </td>
                            <td><select class="form-control selectlogic">
                                <option selected="selected">请选择</option>
                                <option value="cnumber">证书编号</option>
                                <option value="ccompany">证书单位</option>
                          <%--      <option value="ctoolname">器具名称</option>
                                <option value="cmodel">型号规格</option>
                                <option value="coutnumber">出厂编号</option>
                                <option value="cmanufacturer">制造厂商</option>
                                <option value="cdelegate">委托单号</option>
                                <option value="ccheckdate">检定日期</option>
                                <option value="ccheckdepartment">检测部门</option>
                                <option value="uname">添加人</option>
                                <option value="puname">打印人</option>
                                <option value="cprintdate">打印日期</option>--%>
                            </select></td>
                            <td><select class="form-control selectlogic">
                                <option selected="selected">请选择</option>
                                <option value="like">包含</option>
                                <option value="=">等于</option>
                               <%-- <option value=">">大于</option>
                                <option value="<">小于</option>
                                <option value="!=">不等于</option>
                                <option value=">=">大于等于</option>
                                <option value="<=">小于等于</option>
                                <option value="not like">不包含</option>
                                <option value="not null">不为空</option>--%>
                            </select></td>
                            <td>
                                <input class="form-control selectlogic" type="text" placeholder="检索词">
                            </td>
                            <td>
                                <button class="btn_addLogic btn btn-block btn-info" style="width: 100px;float: left">添加</button>
                                <button class="btn_delLogic btn btn-block btn-danger" id="delfirst" style="width: 100px;float: left">删除</button>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                    <div class="row">
                        <div class="col-md-2 col-sm-2">
                            <input id="isQueryAllCertificate" type="checkbox">查询所有证书
                        </div>
                        <div class="col-md-10 col-sm-10"></div>
                    </div>

                    <div class="row text-center">
                        <button class="btn btn-primary" onclick="queryCertificatesByLogic()">查询</button>
                        <button class="btn btn-info" onclick="downloadExcel()">导出</button>
                    </div>
                </div>
            </div>


            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <%--证书列表表格 开始--%>
                    <table id="table" data-pagination="true" data-page-size="10" data-page-list="[5, 10, 15, 20,30]">

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

                    <%--说明--%>
                    <hr>
                    <p>说明</p>
                    <p>1：默认查询一个月内证书，勾上“查询所有证书后查询所有证书”</p>
                    <p>2：是否入库查询仅代表该证书有出入库记录，不代表证书还在库房，有可能已出库</p>
                    <p>3：原始记录生成的证书不允许直接删除，只能通过删除相应的原始记录来同步的删除证书</p>
                    <p>4：证书编号<span style="color: green">绿色</span>代表该证书是由原始记录生成的</p>
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
<script src="../../js/index_query.js" type="text/javascript"></script>

</body>
</html>
