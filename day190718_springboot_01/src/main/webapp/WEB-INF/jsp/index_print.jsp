<%@ page import="org.apache.tomcat.util.json.JSONParser" %><%--
  Created by IntelliJ IDEA.
  User: zhoujian
  Date: 2019/7/20
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <link href="../../css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="../../css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="../../css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <style>
    </style>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-3">

            <div class="ibox">
                <div class="ibox-title">
                    证书查询
                </div>
                <div class="ibox-content">
                    <form method="post" action="/queryCerBy">
                        <div class="input-group">
                            <select name="cid" class="form-control selectlogic">
                                <option selected="selected">请选择</option>
                                <option value="1">证书号</option>
                                <option value="2">委托单号</option>

                            </select>
<%--                            <input name="cid" id="cid" type="text" style="display: none" value="$('.form-control').val()" >--%>
                            <input name="cnumber" id="cnumber" type="text" class="form-control" placeholder="请输入条件" aria-describedby="Cerlabel">
                        </div>
                        <button style="margin-top: 10px;"  class="btn btn-success btn-block">查询</button>
                    </form>



                </div>
            </div>
            <div class="ibox">
                <div class="ibox-title">结果</div>
                <div class="ibox-content">
                    <div class="list-group">
                        <c:forEach var="cer" items="${CerList}">
                            <div style="border: 1px black solid;">

                                <div id ="div-check" class="checkbox i-checks" >
                                    <label>
                                        <input type="checkbox" value="${cer.cnumber}"> <i>${cer.cnumber}</i> </label><br />

                                    <span style="color: blue;margin-left: 25px;font-size: 20px;">
        						<b>${cer.ctoolname}</b>
        					</span>
                                </div>

                            </div>
                        </c:forEach>

                    </div>
                </div>
            </div>

        </div>



        <div class="col-sm-9 animated fadeInRight">

        </div>
    </div>
</div>



<script src="../../js/jquery.min.js?v=2.1.4"></script>
<script src="../../js/bootstrap.min.js?v=3.3.6"></script>
<script src="../../js/content.min.js?v=1.0.0"></script>
<script src="../../js/plugins/iCheck/icheck.min.js"></script>
<script src="../../js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script>
    $(document).ready(function(){
        $("input").iCheck({
            checkboxClass:"icheckbox_square-green",
            radioClass:"iradio_square-green",
        });

        $('.checkbox').mouseover(function(){
            //alert("111")
            $('.checkbox').attr("style","background:yellow");
        });
        $('.checkbox').mouseout(function(){
            //alert("111")
            $('.checkbox').attr("style","");
        });

        <%--function queryCer() {--%>
        <%--    $.post("/queryCerById",--%>

        <%--        {--%>
        <%--            id:$('.form-control').val(),--%>
        <%--            text:$('#queryCertext').val()--%>
        <%--        },--%>
        <%--        function(data){--%>
        <%--            <%--%>
        <%--                request.getSession().setAttribute("cer",);--%>
        <%--            %>--%>
        <%--        }--%>

        <%--    );--%>
        <%--}--%>
    });

</script>

</body>
</html>