<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2020/2/23
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
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
<!-- Dashboard Wrapper Start -->
<div class="dashboard-wrapper">
    <!-- Right sidebar start -->
    <!-- Right sidebar end -->
    <!-- Main Container Start -->
    <div class="main-container">
        <!-- Container fluid Starts -->
        <div class="container-fluid">
            <!-- Spacer starts -->
            <div class="spacer-xs">


                <div class="row no-gutter">
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4>详细信息</h4>
                            </div>
                            <div class="panel-body">
                                <div class="panel-body">
                                    <table class="table">
                                        <tbody>
                                        <tr>
                                            <td><h5>商品编号: <small>${goods.number}</small></h5></td>
                                        </tr>
                                        <tr>
                                            <td><h5>商品名称: <small>${goods.name}</small></h5></td>
                                        </tr>
                                        <tr>
                                            <td><h5>商品简称: <small>${goods.forShort}</small></h5></td>
                                        </tr>
                                        <tr>
                                            <td><h5>商品进价: <small>${goods.purchasePrice}</small></h5></td>
                                        </tr>
                                        <tr>
                                            <td><h5>商品售价: <small>${goods.sellingPrice}</small></h5></td>
                                        </tr>
                                        <tr>
                                            <td><h5>商品类别: <small>${goods.categoryName}</small></h5></td>
                                        </tr>
                                        <tr>
                                            <td><h5>商品规格: <small>${goods.specification}</small></h5></td>
                                        </tr>
                                        <tr>
                                            <td><h5>商品单位: <small>${goods.unitId}</small></h5></td>
                                        </tr>
                                        <tr>
                                            <td><h5>供货商: <small>${goods.providerName}</small></h5></td>
                                        </tr>
                                        <tr>
                                            <td><h5>备注: <small>${goods.memo}</small></h5></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <hr />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Spacer ends -->
        </div>
        <!-- Container fluid ends -->
    </div>
    <!-- Main Container Start -->
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
<div id="modal-ajax" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content"></div>
    </div>
</div>
</body>
</html>
