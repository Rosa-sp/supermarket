<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2020/1/3
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="${ctx}/static/assets/lib/layui/css/layui.css">
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
                <h4>修改 ${role.name} 角色权限</h4>
            </div>
            <div class="panel-body">
                <form id="systemAccount_form" class="form-horizontal" action="#" method="post">
                    <input type="hidden" name="id" value="${role.id}"/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="name">角色名称&nbsp;<span
                                style='color:#d15b47'>*</span></label>
                        <div class="col-xs-12 col-sm-5">
                            <input id="name" name="name" class="form-control" placeholder="请输入" required
                                   type="text" value="${role.name}" ${empty role? '' :'readonly="readonly"'}/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right"><span
                                style='color:#d15b47'></span></label>
                        <div class="col-xs-12 col-sm-7">
                            <div id="test4" class="demo-tree demo-tree-more"></div>
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
<script type="text/javascript" src="${ctx}/static/assets/lib/layui/layui.js"></script>
<script>
    var tree;
    //layui.use是调用layui的模块
    layui.use(['tree', 'util'], function(){
        tree = layui.tree
        var layer = layui.layer
            ,util = layui.util;
        var data1 = ${permission};
        var ids = new Array();

        <c:forEach items="${role.permissions}" var="permission">
        ids.push(${permission.id});
        </c:forEach>

        tree.render({
            elem: '#test4'
            ,id: "demoId1"
            ,showCheckbox: true  //是否显示复选框
            ,data: data1
            ,showSearch: true
            ,onlyIconControl : true
        })

        tree.setChecked('demoId1', ids); //勾选指定节点
    })
</script>
<script>
    function getIds(ids, datas){
        for (var i = 0; i < datas.length; i++){
            ids.push(datas[i].id);
            if (datas[i].children != undefined && datas[i].children.length > 0){
                getIds(ids, datas[i].children);
            }
        }
    }
    $(function () {
        $("#systemAccount_form").submit(function () {
            var form = document.getElementById("systemAccount_form");
            var selects = tree.getChecked('demoId1'); //获取选中节点的数据;
            var ids = new Array();
            for (var i = 0; i < selects.length; i++){
                ids.push(selects[i].id);
                if (selects[i].children != undefined && selects[i].children.length > 0){
                    getIds(ids, selects[i].children);
                }
            }
            var params = {
                id: form.id.value,
                ids: ids
            };
            $.ajax({
                url: $.ctx + "/role/iframe/role/edit",
                type: "post",
                data: params,
                traditional: true,
                dataType: "json",
                success: function (data) {
                    //修改之后
                    if (data.code == 1) {
                        //修改成功
                        parent.layer.msg('授权成功', {icon: 6, time: 2000});
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
            return false;
        })
    })

</script>
<div id="modal-ajax" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content"></div>
    </div>
</div>
</body>
</html>