<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width,initial-scale=1.0" />
    <meta charset="utf-8" />
    <title>超市管理中心-<sitemesh:write property="title"></sitemesh:write></title>
    <link href="favicon.ico" rel="shortcut icon" type="image/x-icon"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <link rel="stylesheet" href="${ctx}/static/assets/lib/css/bootstrap.min.css" media="screen"/>
    <link rel="stylesheet" href="${ctx}/static/assets/lib/css/font-awesome.min.css" />
    <link rel="stylesheet" href="${ctx}/static/assets/lib/css/metrize.css" />
    <link rel="stylesheet" href="${ctx}/static/assets/lib/css/animate.css" />
    <link rel="stylesheet" href="${ctx}/static/assets/lib/css/toastr.min.css" />
    <link rel="stylesheet" href="${ctx}/static/assets/lib/css/bootstrap-datetimepicker.min.css" />
    <link rel="stylesheet" href="${ctx}/static/assets/lib/css/chosen.min.css" />
    <link rel="stylesheet" href="${ctx}/static/assets/lib/css/main.css" />
    <link rel="stylesheet" href="${ctx}/static/assets/lib/css/slidebars.css" />
    <link rel="stylesheet" href="${ctx}/static/assets/app/css/management/application.css" />
    <link rel="stylesheet" href="${ctx}/static/assets/lib/css/bootstrap-table.min.css" />

    <sitemesh:write property="css"></sitemesh:write>
    <!-- HTML5 shiv and Respond.js IE8 support of HTML5 elements and media \ -->
    <!--[if lt IE 9]>
    <script src="${ctx}/static/assets/lib/js/html5shiv.min.js"></script>
    <script src="${ctx}/static/assets/lib/js/respond.min.js"></script>
    <![endif]-->


</head>
<body>
<!-- Left sidebar start -->

<aside id="sidebar">
    <!-- Logo starts -->
    <a href="${ctx}/index" class="logo">
        <img alt="" src="${ctx}/static/assets/app/images/management/logo2.png" style="margin-bottom: 10px;"/>
        <p class="label">超市管理平台</p>
    </a>
    <!-- Logo ends -->
    <!-- Menu start -->
    <div id="menu">
        <ul>
            <c:forEach items="${login_permission}" var="permission">
                <li class="${not empty permission.children? 'has-sub':''} ">
                    <a href="${ctx}${permission.actionUrl}">
                        <div class="fs1 ${permission.digest}" aria-hidden="true"></div>
                        <span>${permission.name}</span>
                    </a>
                    <c:if test="${not empty permission.children}">
                        <ul>
                            <c:forEach  items="${permission.children}" var="permissionChild">
                                <li>
                                    <a href="${ctx}${permissionChild.actionUrl}">
                                        <div class="fs1 ${permissionChild.digest}" aria-hidden="true"></div>
                                        <span>${permissionChild.name}</span>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
    </div>
    <!-- Menu End -->
</aside>
<!-- Left sidebar end -->
<!-- Dashboard Wrapper Start -->
<div class="dashboard-wrapper">
    <!-- Header start -->
    <header>
        <ul class="pull-left" id="left-nav">
            <li >
                <div class="logo-mob clearfix">
                    <h2>
                        <div class="fs1" aria-hidden="true" data-icon="&#xe0db;"></div>
                        <span>管理中心</span>
                    </h2>
                </div>
            </li>
        </ul>
        <div class="pull-right">
            <ul id="mini-nav" class="clearfix">
                <li class="list-box hidden-xs dropdown">
                    <a href="#" role="button" class="dropdown-toggle current-user" data-toggle="dropdown">
                        ${account1.realName} <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu sm fadeInUp animated">
                        <li class="dropdown-content">
                            <a href="${ctx}/index" data-original-title="" title=""><i class="fa fa-user"></i> 个人资料</a>
                            <a href="${ctx}/logout" data-original-title="" title=""><i class="fa fa-power-off"></i> 退出登录</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </header>
    <!-- Header ends -->
    <!-- Right sidebar start -->
    <!-- Right sidebar end -->
    <!-- Main Container Start -->
    <sitemesh:write property="body"></sitemesh:write>
    <!-- Main Container Start -->
    <!-- Footer Start -->
    <footer>
        Copyright <strong><span>2020-2025</span></strong> . All Rights Reserved.
    </footer>
    <!-- Footer end -->
</div>
<!-- Dashboard Wrapper End -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${ctx}/static/assets/lib/js/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${ctx}/static/assets/lib/js/bootstrap.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<%--<script src="https://unpkg.com/bootstrap-table@1.15.3/dist/bootstrap-table.min.js"></script>--%>
<%--<!-- Latest compiled and minified Locales -->--%>
<%--<script src="https://unpkg.com/bootstrap-table@1.15.3/dist/locale/bootstrap-table-zh-CN.min.js"></script>--%>

<script src="${ctx}/static/assets/lib/js/bootstrap/bootstrap-table.min.js"></script>
<!-- Animated Right Sidebar -->
<script src="${ctx}/static/assets/lib/js/slidebars.js"></script>
<script src="${ctx}/static/assets/lib/js/toastr.min.js"></script>
<!-- Date time select -->
<script src="${ctx}/static/assets/lib/js/moment.min.js"></script>
<script src="${ctx}/static/assets/lib/js/moment.zh-cn.js"></script>
<script src="${ctx}/static/assets/lib/js/bootstrap-datetimepicker.min.js"></script>
<script src="${ctx}/static/assets/lib/js/chosen.jquery.min.js"></script>
<!-- layer -->
<script src="${ctx}/static/assets/lib/layer/layer.js"></script>
<script src="${ctx}/static/assets/lib/js/jsTree/jstree.js"></script>
<!-- Custom JS -->
<script src="${ctx}/static/assets/lib/js/custom.js"></script>
<script type="text/javascript">
    jQuery.ctx = "${ctx}"; //在js中可以直接通过$.ctx来获取应用程序上下文
</script>
<script type="text/javascript" src="${ctx}/static/assets/app/js/management/application.js"></script>
<sitemesh:write property="js"></sitemesh:write>
<div id="modal-ajax" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content"></div>
    </div>
</div>
</body>
</html>