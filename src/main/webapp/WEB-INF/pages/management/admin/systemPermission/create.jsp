<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/12/27
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>权限管理</title>
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
                <h4>${empty permission ? '新建权限':"修改权限信息"}</h4>
            </div>
            <div class="panel-body">
                <form id="systemAccount_form" name="form" class="form-horizontal" action="#" method="post">
                    <input type="hidden" id="id" name="id" value="${permission.id}"/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="parentId">父级权限&nbsp;<span
                                style='color:#d15b47'>*</span></label>
                        <div class="col-xs-12 col-sm-5">
                            <input type="hidden" id="parentId" lay-filter="parentId" name="parentId" class="layui-input"
                                   value="${pId}" />
                            <c:if test="${not empty name}">
                                <input type="text" id="parentName"  name="parentName" class="layui-input layui-disabled"
                                       value="${name}" disabled="disabled" />
                            </c:if>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="name">权限名称&nbsp;<span
                                style='color:#d15b47'>*</span></label>
                        <div class="col-xs-12 col-sm-5">
                            <input id="name" name="name" class="form-control" placeholder="权限名称" required
                                   type="text"
                                   value="${permission.name}" ${empty permission? '' :'readonly="readonly"'}/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="actionUrl">请求地址&nbsp;<span
                                style='color:#d15b47'></span></label>
                        <div class="col-xs-12 col-sm-5">
                            <input id="actionUrl" name="actionUrl" class="form-control" placeholder="请求地址"
                                   type="text" value="${permission.actionUrl}" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="digest">图标</label>
                        <div class="col-xs-12 col-sm-5">
                            <input id="digest" name="digest" class="form-control" placeholder="图标"
                                   type="text" value="${permission.digest}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="sort">排序</label>
                        <div class="col-xs-12 col-sm-5">
                            <input id="sort" name="sort" class="form-control" placeholder="排序"
                                   type="text" value="${permission.sort}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="type">类型</label>
                        <div class="col-xs-12 col-sm-5">
                            <select id="type" name="type" class="layui-select">
                                <option value="0" selected>菜单</option>
                                <option value="1">按钮</option>
                            </select>
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
    if($("#parentId").val() == ""){
        layui.use(['treeSelect','form'], function () {
            var treeSelect= layui.treeSelect;

            treeSelect.render({
                // 选择器
                elem: '#parentId',
                // 数据
                data: $.ctx + "/permission/list",
                // 异步加载方式：get/post，默认get
                // type: 'get',
                // 占位符
                placeholder: '请选择父级权限',
                // 是否开启搜索功能：true/false，默认false
                search: true,
                // 点击回调
                click: function(d){
                    $("#parentId").val(d.current.id);
                },
                // 加载完成后的回调函数
                success: function (d) {
//                选中节点，根据id筛选
                    treeSelect.checkNode('parentId', 0);

//                获取zTree对象，可以调用zTree方法
//                var treeObj = treeSelect.zTree('tree');
//                console.log(treeObj);

//                刷新树结构
//                treeSelect.refresh();
                }
                ,error: function(e,x,r){
                    console.log(e);
                }
            });
        });
    }
</script>
<script>
    $("#systemAccount_form").submit(function () {
        var form = document.getElementById("systemAccount_form");

        var url = $.ctx + "/permission/iframe/add"
        console.log($(form).serialize());
            if (form.id.value != ""){
                var url = $.ctx + "/permission/iframe/edit"
            }
            if(form.parentId.value == ""){
                parent.layer.msg("请选择父级权限", {icon: 5, time: 2000});
                return false;
            }
            $.ajax({
                url: url,
                type: "post",
                data: $("#systemAccount_form").serialize(),
                dataType: "json",
                success: function (data) {
                    //添加成功
                    if (data.code == 1) {
                        //添加成功
                        parent.layer.msg('更新成功', {icon: 6, time: 2000});
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
</script>
<div id="modal-ajax" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content"></div>
    </div>
</div>
</body>
</html>
