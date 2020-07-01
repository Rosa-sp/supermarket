<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/27
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${ctx}/static/assets/lib/css/bootstrap.min.css" media="screen"/>
    <link rel="stylesheet" href="${ctx}/static/assets/lib/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${ctx}/static/assets/lib/css/metrize.css"/>
    <link rel="stylesheet" href="${ctx}/static/assets/lib/css/animate.css"/>
    <link rel="stylesheet" href="${ctx}/static/assets/lib/css/toastr.min.css"/>
    <link rel="stylesheet" href="${ctx}/static/assets/lib/css/bootstrap-datetimepicker.min.css"/>
    <link rel="stylesheet" href="${ctx}/static/assets/lib/css/chosen.min.css"/>
    <link rel="stylesheet" href="${ctx}/static/assets/lib/css/main.css"/>
    <link rel="stylesheet" href="${ctx}/static/assets/lib/css/slidebars.css"/>
    <link rel="stylesheet" href="${ctx}/static/assets/app/css/management/application.css"/>
    <!-- HTML5 shiv and Respond.js IE8 support of HTML5 elements and media \ -->
    <!--[if lt IE 9]>
    <script src="${ctx}/static/assets/lib/js/html5shiv.min.js"></script>
    <script src="${ctx}/static/assets/lib/js/respond.min.js"></script>
    <![endif]-->

    <link rel="stylesheet" href=" ${ctx}/static/assets/lib/css/dropzone.min.css"/>
    <link rel="stylesheet" href=" ${ctx}/static/assets/lib/css/summernote.css"/>
</head>
<body>
<div class="row no-gutter" style="margin: 0 ;padding:0;">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4>${empty role ? '新建角色':"修改角色信息"}</h4>
            </div>
            <div class="panel-body">
                <form id="systemAccount_form" class="form-horizontal" action="#" method="post">
                    <input type="hidden" name="id" value="${role.id}"/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="name">角色名称&nbsp;<span
                                style='color:#d15b47'>*</span></label>
                        <div class="col-xs-12 col-sm-5">
                            <input id="name" name="name" class="form-control" placeholder="请输入角色名" required
                                   type="text"
                                   value="${role.name}" ${empty role ? '' :'readonly="readonly"'}/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="description">描述</label>
                        <div class="col-xs-12 col-sm-5">
                            <textarea class="form-control" rows="3" name="description" id="description"></textarea>
                        </div>
                    </div>
                    <hr/>
                    <div class="form-group">
                        <div class="col-sm-3"></div>
                        <div class="col-sm-5">
                            <button type="submit" class="btn btn-primary btn-sm">
                                <i class="ace-icon fa fa-check bigger-110"></i>
                                保存
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- Dashboard Wrapper End -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${ctx}/static/assets/lib/js/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${ctx}/static/assets/lib/js/bootstrap.min.js"></script>
<!-- Animated Right Sidebar -->
<script src="${ctx}/static/assets/lib/js/slidebars.js"></script>
<script src="${ctx}/static/assets/lib/js/toastr.min.js"></script>
<!-- Date time select -->
<script src="${ctx}/static/assets/lib/js/moment.min.js"></script>
<script src="${ctx}/static/assets/lib/js/moment.zh-cn.js"></script>
<script src="${ctx}/static/assets/lib/js/bootstrap-datetimepicker.min.js"></script>
<script src="${ctx}/static/assets/lib/js/chosen.jquery.min.js"></script>
<!-- Custom JS -->
<script src="${ctx}/static/assets/lib/js/custom.js"></script>
<script type="text/javascript">
    jQuery.ctx = "${ctx}"; //在js中可以直接通过$.ctx来获取应用程序上下文
    jQuery.servletPath = "/management/systemAccounts/create";//在js中可以直接通过$.servletPath 来获取请求的路径
</script>
<script type="text/javascript" src="${ctx}/static/assets/app/js/management/application.js"></script>

<script type="text/javascript" src="${ctx}/static/assets/lib/js/summernote.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/lib/js/summernote-zh-CN.js"></script>
<script>
    $("#systemAccount_form").submit(function () {
        var form = document.getElementById("systemAccount_form");

        if (form.name.value.trim().length > 0 && form.name.value.trim().length < 20) {
            var url =  $.ctx + "/role/iframe/save";
            var params = {
                id: form.id.value,
                name: form.name.value,
                description: form.description.value
            };
            if (form.id.value != ""){
                var url =  $.ctx + "/role/iframe/update";
                // params._method = 'put';
            }
            $.ajax({
                url:url,
                type: "post",
                data: params,
                dataType: "json",
                success: function (data) {
                    //修改之后
                    if (data.code == 1) {
                        //修改成功
                        parent.layer.msg("添加成功", {icon: 6, time: 2000});
                        parent.reloadTable();
                        parent.layer.closeAll('iframe');
                    } else {
                        //修改失败
                        parent.layer.msg(data.message, {icon: 5, time: 2000});
                    }

                },
                error: function (x, e, s) {
                    parent.layer.msg('服务器异常，请联系管理员', {icon: 5, time: 2000});
                }
            });
        } else {
            parent.layer.msg('请填写角色名称', {icon: 5, time: 2000});
        }
        return false;
    })
</script>
<div id="modal-ajax" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content"></div>
    </div>
</div>
</body>
</html>
