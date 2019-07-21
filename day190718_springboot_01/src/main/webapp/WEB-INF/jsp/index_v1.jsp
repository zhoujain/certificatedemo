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


    <title>H+ 后台主题UI框架 - 主页示例</title>

    <link rel="shortcut icon" href="favicon.ico">
    <link href="../../css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="../../css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="../../css/plugins/jsTree/style.min.css" rel="stylesheet">
    <link href="../../css/animate.min.css" rel="stylesheet">
    <link href="../../css/style.min862f.css?v=4.1.0" rel="stylesheet">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-3">
            <div class="ibox float-e-margins">
                <div class="ibox-content mailbox-content">
                    <%--                    树结构--%>


                    <div id="using_json"></div>


                </div>
            </div>
        </div>


        <div class="col-sm-9 animated fadeInRight">
            <div class="mail-box-header">

                <form method="get" action="http://www.zi-han.net/theme/hplus/index.html" class="pull-right mail-search">
                    <div class="input-group">
                        <input type="text" class="form-control input-sm" name="search" placeholder="搜索邮件标题，正文等">
                        <div class="input-group-btn">
                            <button type="submit" class="btn btn-sm btn-primary">
                                搜索
                            </button>
                        </div>
                    </div>
                </form>
                <h2>
                    收件箱 (16)
                </h2>
                <div class="mail-tools tooltip-demo m-t-md">
                    <div class="btn-group pull-right">
                        <button class="btn btn-white btn-sm"><i class="fa fa-arrow-left"></i>
                        </button>
                        <button class="btn btn-white btn-sm"><i class="fa fa-arrow-right"></i>
                        </button>

                    </div>
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" title="刷新邮件列表"><i
                            class="fa fa-refresh"></i> 刷新
                    </button>
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="标为已读"><i
                            class="fa fa-eye"></i>
                    </button>
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="标为重要"><i
                            class="fa fa-exclamation"></i>
                    </button>
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="标为垃圾邮件"><i
                            class="fa fa-trash-o"></i>
                    </button>

                </div>
            </div>
            <div class="mail-box">

                <table class="table table-hover table-mail">
                    <tbody>
                    <tr class="unread">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="mail_detail.html">支付宝</a>
                        </td>
                        <td class="mail-subject"><a href="mail_detail.html">支付宝提醒</a>
                        </td>
                        <td class=""><i class="fa fa-paperclip"></i>
                        </td>
                        <td class="text-right mail-date">昨天 10:20</td>
                    </tr>
                    <tr class="unread">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks" checked>
                        </td>
                        <td class="mail-ontact"><a href="mail_detail.html">Amaze UI</a>
                        </td>
                        <td class="mail-subject"><a href="mail_detail.html">Amaze UI Beta2 发布</a>
                        </td>
                        <td class=""></td>
                        <td class="text-right mail-date">上午10:57</td>
                    </tr>
                    <tr class="read">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="mail_detail.html">WordPress</a> <span
                                class="label label-warning pull-right">验证邮件</span>
                        </td>
                        <td class="mail-subject"><a href="mail_detail.html">wp-user-frontend-pro v2.1.9</a>
                        </td>
                        <td class=""></td>
                        <td class="text-right mail-date">上午9:21</td>
                    </tr>
                    <tr class="read">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="mail_detail.html">淘宝网</a>
                        </td>
                        <td class="mail-subject"><a href="mail_detail.html">史上最全！淘宝双11红包疯抢攻略！</a>
                        </td>
                        <td class=""></td>
                        <td class="text-right mail-date">中午12:24</td>
                    </tr>
                    <tr class="read">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="mail_detail.html">淘宝网</a> <span
                                class="label label-danger pull-right">AD</span>
                        </td>
                        <td class="mail-subject"><a href="mail_detail.html">亲，双11来啦！帮你挑货，还送你4999元红包！仅此一次！</a>
                        </td>
                        <td class=""><i class="fa fa-paperclip"></i>
                        </td>
                        <td class="text-right mail-date">上午6:48</td>
                    </tr>
                    <tr class="read">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="mail_detail.html">支付宝</a>
                        </td>
                        <td class="mail-subject"><a href="mail_detail.html">支付宝提醒</a>
                        </td>
                        <td class=""><i class="fa fa-paperclip"></i>
                        </td>
                        <td class="text-right mail-date">昨天 10:20</td>
                    </tr>
                    <tr class="read">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="mail_detail.html">Amaze UI</a>
                        </td>
                        <td class="mail-subject"><a href="mail_detail.html">Amaze UI Beta2 发布</a>
                        </td>
                        <td class=""></td>
                        <td class="text-right mail-date">上午10:57</td>
                    </tr>
                    <tr class="read">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="mail_detail.html">WordPress</a> <span
                                class="label label-warning pull-right">验证邮件</span>
                        </td>
                        <td class="mail-subject"><a href="mail_detail.html">wp-user-frontend-pro v2.1.9</a>
                        </td>
                        <td class=""></td>
                        <td class="text-right mail-date">上午9:21</td>
                    </tr>
                    <tr class="read">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="mail_detail.html">淘宝网</a>
                        </td>
                        <td class="mail-subject"><a href="mail_detail.html">史上最全！淘宝双11红包疯抢攻略！</a>
                        </td>
                        <td class=""></td>
                        <td class="text-right mail-date">中午12:24</td>
                    </tr>
                    <tr class="read">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="mail_detail.html">淘宝网</a> <span
                                class="label label-danger pull-right">AD</span>
                        </td>
                        <td class="mail-subject"><a href="mail_detail.html">亲，双11来啦！帮你挑货，还送你4999元红包！仅此一次！</a>
                        </td>
                        <td class=""><i class="fa fa-paperclip"></i>
                        </td>
                        <td class="text-right mail-date">上午6:48</td>
                    </tr>
                    <tr class="read">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="mail_detail.html">支付宝</a>
                        </td>
                        <td class="mail-subject"><a href="mail_detail.html">支付宝提醒</a>
                        </td>
                        <td class=""><i class="fa fa-paperclip"></i>
                        </td>
                        <td class="text-right mail-date">昨天 10:20</td>
                    </tr>
                    <tr class="read">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="mail_detail.html">Amaze UI</a>
                        </td>
                        <td class="mail-subject"><a href="mail_detail.html">Amaze UI Beta2 发布</a>
                        </td>
                        <td class=""></td>
                        <td class="text-right mail-date">上午10:57</td>
                    </tr>
                    <tr class="read">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="mail_detail.html">WordPress</a> <span
                                class="label label-warning pull-right">验证邮件</span>
                        </td>
                        <td class="mail-subject"><a href="mail_detail.html">wp-user-frontend-pro v2.1.9</a>
                        </td>
                        <td class=""></td>
                        <td class="text-right mail-date">上午9:21</td>
                    </tr>
                    <tr class="read">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="mail_detail.html">淘宝网</a>
                        </td>
                        <td class="mail-subject"><a href="mail_detail.html">史上最全！淘宝双11红包疯抢攻略！</a>
                        </td>
                        <td class=""></td>
                        <td class="text-right mail-date">中午12:24</td>
                    </tr>
                    <tr class="read">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="mail_detail.html">淘宝网</a> <span
                                class="label label-danger pull-right">AD</span>
                        </td>
                        <td class="mail-subject"><a href="mail_detail.html">亲，双11来啦！帮你挑货，还送你4999元红包！仅此一次！</a>
                        </td>
                        <td class=""><i class="fa fa-paperclip"></i>
                        </td>
                        <td class="text-right mail-date">上午6:48</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="../../js/jquery.min.js?v=2.1.4"></script>
<script src="../../js/bootstrap.min.js?v=3.3.6"></script>
<script src="../../js/content.min.js?v=1.0.0"></script>
<script src="../../js/plugins/iCheck/icheck.min.js"></script>
<script src="../../js/plugins/jsTree/jstree.min.js"></script>
<script>
    $(document).ready(function () {
        $(".i-checks").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green",})
    });
</script>
<%--<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>--%>
<style>
    .jstree-open > .jstree-anchor > .fa-folder:before {
        content: "\f07c"
    }

    .jstree-default .jstree-icon.none {
        width: 0
    }
</style>
<script>
    tzs = {};
    tzs.index = {
        //初始化页面
        init: function () {
            tzs.index.doCreateTree();
        },
        //给标签绑定事件
        event : function(){
        },
        doCreateTree : function(){
            $.getJSON("/queryJsTree",function(rs){
                $('#using_json').jstree({
                    "core" : {
                        "mutiple" : false,
                        "check_callback" : true,
                        "data" : rs.data
                    }
                });
            });

            //树节点左键相应函数（监听）
            $('#using_json').on("select_node.jstree",function (node,selected,event) {
                //当前点击的对象的id
                alert(selected.node.id);
            })
        }
    };

    $(function(){
        tzs.index.init();
    });
</script>
</body>
</html>
