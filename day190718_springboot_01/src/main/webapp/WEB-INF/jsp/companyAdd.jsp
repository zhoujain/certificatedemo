<%--
  Created by IntelliJ IDEA.
  User: zhoujian
  Date: 2019/8/20
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>H+ 后台主题UI框架 - 表单验证 jQuery Validation</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico"> <link href="../../css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="../../css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="../../css/animate.min.css" rel="stylesheet">
    <link href="../../css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="../../js/plugins/layer/theme/default/layer.css" rel="stylesheet">
</head>
<body>
<div class="weituo">
    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5>委托添加</h5>
        </div>
        <div class="ibox-content">
            <form class="form-horizontal m-t" id="signupForm" action="/company/save">
                <div class="form-group">
                    <label class="col-sm-1 control-label">委托单位：</label>
                    <div class="col-sm-9">
                        <input id="companyName" name="name" class="form-control" type="text">
                        <span class="help-block m-b-none"><i class="fa fa-info-circle"></i>下拉框</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label">联系人：</label>
                    <div class="col-sm-4">
                        <input id="linkMan" name="linkMan" class="form-control" type="text" aria-required="true" aria-invalid="false" class="valid">
                    </div>
                    <label class="col-sm-1 control-label">联系电话：</label>
                    <div class="col-sm-4">
                        <input id="linkPhone" name="linkPhone" class="form-control" type="text" aria-required="true" aria-invalid="false" class="valid">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label">委托日期：</label>
                    <div class="col-sm-4">
                        <input id="adate" name="adate" type="text" class="form-control" data-mask="9999-99-99" placeholder="">
                    </div>
                    <label class="col-sm-1 control-label">打印时间：</label>
                    <div class="col-sm-4">
                        <input id="pdate" name="pdate" type="text" class="form-control" data-mask="9999-99-99" placeholder="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label">客户要求单位地址：</label>
                    <div class="col-sm-9">
                        <input id="adress" name="adress" class="form-control" type="text">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-1">
                        <button class="btn btn-primary" type="submit">添加单位</button>
                    </div>
                </div>
            </form>
        </div>

    </div>
</div>
<script src="../../js/jquery.min.js?v=2.1.4"></script>
<script src="../../js/bootstrap.min.js?v=3.3.6"></script>
<script src="../../js/content.min.js?v=1.0.0"></script>
<script src="../../js/plugins/jasny/jasny-bootstrap.min.js"></script>
<script src="../../js/plugins/validate/jquery.validate.min.js"></script>
<script src="../../js/plugins/validate/messages_zh.min.js"></script>
<script src="../../js/plugins/layer/layer.js"></script>
<script src="../../js/demo/form-validate-demo.min.js"></script>
<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
<script>
    if(${! empty message}){
        layer.alert('${message}',{icon:6});
    }
</script>
<script>

</script>
</body>
</html>

