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
<%--                    <form method="post" action="/queryCerBy">--%>
                        <div class="input-group">
                            <select name="cid" class="form-control selectlogic">
                                <option selected="selected">请选择</option>
                                <option value="1">证书号</option>
                                <option value="2">委托单号</option>

                            </select>
<%--                            <input name="cid" id="cid" type="text" style="display: none" value="$('.form-control').val()" >--%>
                            <input name="cnumber" id="cnumber" type="text" class="form-control" placeholder="请输入条件" aria-describedby="Cerlabel">
                        </div>
                        <button style="margin-top: 10px;"  class="btn btn-success btn-block" onclick="queryCer()">查询</button>
                        <button style="margin-top: 10px;"  class="btn btn-success btn-block" onclick="queryList()">全部打印</button>
<%--                    </form>--%>



                </div>
            </div>
            <div class="ibox">
                <div class="ibox-title">结果</div>
                <div class="ibox-content">
                    <div class="list-group" style="overflow-y:auto;overflow-x: auto; height:400px ;width:100%;">
<%--                        <c:forEach var="cer" items="${CerList}">--%>
<%--                            <div style="border: 1px black solid;">--%>

<%--                                <div id ="div-check" class="checkbox i-checks" >--%>
<%--                                    <a target="mainFrame" href="/content_certificate?id=${cer.cid}">--%>
<%--                                        <label>--%>
<%--                                            <input type="checkbox" value="${cer.cnumber}"> <i>${cer.cnumber}</i> </label><br />--%>

<%--                                        <span style="color: blue;margin-left: 25px;font-size: 20px;">--%>
<%--        						        <b>${cer.ctoolname}</b>--%>
<%--        					            </span>--%>
<%--                                    </a>--%>

<%--                                </div>--%>

<%--                            </div>--%>
<%--                        </c:forEach>--%>

                    </div>
                </div>
            </div>

        </div>


        <a id="aGo" target="mainFrame" style="display: none;" href="">隐藏链接</a>
        <div class="col-sm-9 animated fadeInRight">
            <iframe name="mainFrame" width="100%" height="600px" frameborder="0">

            </iframe>
        </div>
    </div>
</div>
<%--进度条--%>
<div id="ProgressBarSide" style="color: Silver; width: 200px; visibility: hidden;
        position: absolute;  left: 40%; top: 50%; margin-top: -32px">
    <span style="color: gray; font-size: 12px; text-align: center;">正在生成并打印请稍候...</span><br />
    <div style=" border:solid 1px green;">
        <div id="ProgressBar" style="background-color: Green; height: 16px; width: 0%; border-width: 1px;border-style: Solid;">
        </div>
    </div>
</div>

<%--iframe隐藏域--%>
<div style="width: 1px; height: 1px; overflow: hidden;">
    <iframe id="iframe1" name="iframe1" src="" ></iframe>
</div>
<script src="../../js/jquery.min.js?v=2.1.4"></script>
<script src="../../js/bootstrap.min.js?v=3.3.6"></script>
<script src="../../js/content.min.js?v=1.0.0"></script>
<script src="../../js/plugins/iCheck/icheck.min.js"></script>
<script src="../../js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="pageoffice.js" id="po_js_main"></script>
<script>
    var checkBoxArr = [];
    var all;
    var count =1;
    $(document).ready(function(){

        // $('.checkbox').click(function(){
        //     //alert("111")
        //     $('#aGo').attr("href","/content_certificate?");
        //     $('#aGo')[0].click();
        // });



    });
    function func() {
        if(checkBoxArr.length==0){
            alert("请选择文档打印！！");
            return;
        }
        if(count<checkBoxArr.length+1){
            //设置进度条
            document.getElementById("ProgressBarSide").style.visibility = "visible";
            document.getElementById("ProgressBar").style.width = (count) * 25 - 1 + "%";

            //加载文档打印页面（可传参）
            document.getElementById("iframe1").src = "/Print?id=" + checkBoxArr[count-1];
            count++;
        }else{
            document.getElementById("ProgressBarSide").style.visibility = "hidden";
        //重置进度条
            count=1;
            checkBoxArr = [];
            document.getElementById("ProgressBar").style.width = "0%";
            alert("打印成功！！");
            return;
        }

    }
    //点击
    function queryList() {

        $('input[name="optionName"]:checked').each(function() {
            checkBoxArr.push($(this).val());
        });
        alert(checkBoxArr);
        func();

    }
    function queryCer() {
        //alert("11")
        if($('.form-control').val()=="请选择"&&$('#cnumber').val()!=null&&$('#cnumber').val()!=""){
            alert("选择查询条件");
            return;
        }
        $.ajaxSettings.async = false;
        $.post("/queryCerBy",
            {
                cid:$('.form-control').val(),
                cnumber:$('#cnumber').val()
            },
            function(rst){
                var optionData = rst;
                //var a = /;
                var str='暂无数据';
                if(optionData !=null&&optionData!=""){
                    str = '<div><input type="checkbox" id="allOptionId" value="all"/>全选</div>';
                    for(var i = 0;i<optionData.length;i++){
                        str+='<div id="all-div" style=""><div id ="div-check" class="checkbox i-checks" >\n'
                                if(!!window.ActiveXObject || "ActiveXObject" in window){
                                    str+='<a href="/content_certificate?id='+optionData[i].cid+'" target="mainFrame" >\n'
                                }else {
                                    str+='<a href="javascript:POBrowser.openWindowModeless(\'/content_certificate?id='+optionData[i].cid+'\',\'width=1200px;height=800px;\');">\n'
                                }

                            str+='<label><input type="checkbox" name="optionName" value="'+optionData[i].cid+'"> <i>'+optionData[i].cnumber+'</i> </label><br />\n' +
                            '                                        <span style="color: blue;margin-left: 25px;font-size: 20px;"><b>'+optionData[i].ctoolname+'</b></span>\n' +
                            '                                    </a>\n' +
                            '                                </div>\n' +
                            '                            </div>'
                    }
                }
                $('.list-group').html(str);
            }

        );
        $.ajaxSettings.async=true;
        $("#allOptionId").on('ifChecked',function (event) {
            $('input').iCheck('check')
        });

        $('#allOptionId').on('ifUnchecked',function (event) {
            $('input').iCheck('uncheck')
        });
        $("input").iCheck({
            checkboxClass:"icheckbox_square-green",
            radioClass:"iradio_square-green",
        });

        $('#all-div').mouseover(function(){
            //alert("111")
            $(this).attr("style","background:aliceblue");
        });
        $('#all-div').mouseout(function(){
            //alert("111")
            $(this).attr("style","background:white");
        });



    }

</script>

</body>
</html>