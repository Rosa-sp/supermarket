<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="viewport" content="width=device-width,initial-scale=1.0" />
	<meta charset="utf-8" />
	<title>用户中心</title>
	<link href="favicon.ico" rel="shortcut icon" type="image/x-icon"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/lib/css/bootstrap.min.css" media="screen"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/lib/css/font-awesome.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/lib/css/metrize.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/lib/css/animate.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/lib/css/toastr.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/lib/css/bootstrap-datetimepicker.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/lib/css/chosen.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/lib/css/main.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/lib/css/slidebars.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/app/css/management/application.css" />
	<!-- HTML5 shiv and Respond.js IE8 support of HTML5 elements and media \ -->
	<!--[if lt IE 9]>
	<script src="${pageContext.request.contextPath}/static/assets/lib/js/html5shiv.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/assets/lib/js/respond.min.js"></script>
	<![endif]-->

</head>
<body>
	<!-- Header ends -->
	<!-- Right sidebar start -->
	<!-- Right sidebar end -->
	<!-- Main Container Start -->
	<div class="main-container">
		<!-- Top Bar Starts -->
		<div class="top-bar clearfix">
			
	<div class="page-title">
		<h4>
			<div class="fs1" aria-hidden="true" data-icon="&#xe0ac;"></div>
			首页 <a href="javascript:void(0)" class="samll">用户中心</a></h4>
	</div>

		</div>
		<!-- Top Bar Ends -->
		<!-- Container fluid Starts -->
		<div class="container-fluid">
			<!-- Spacer starts -->
			<div class="spacer-xs">


<div class="row no-gutter" id="user-profile">
    <div class="col-md-4">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4>管理员</h4>
            </div>
            <div class="panel-body">
<%--                <c:set var="${account1.headPic}" value="null" />--%>
<%--                <c:choose>--%>
<%--                    <c:when test="${account1.headPic == 'null'}">--%>
<%--                        <img id="avatar"--%>
<%--                             class="profile-img img-responsive center-block"--%>
<%--                             alt="管理员" src="${ctx}/static/assets/lib/img/admin11.png"/>--%>
<%--                    </c:when>--%>
<%--                    <c:otherwise>--%>
<%--                        <img id="avatar"--%>
<%--                             class="profile-img img-responsive center-block"--%>
<%--                             alt="管理员" src="${account1.headPic}"/>--%>
<%--                    </c:otherwise>--%>
<%--                </c:choose>--%>
    <img id="avatar"
    class="profile-img img-responsive center-block"
                                alt="管理员" src="${account1.headPic}"/>
                    <div class="profile-label">
                        <span class="label label-danger">管理员</span>
                    </div>
                    <div class="profile-since">
                        <a  class="btn btn-link"> <i
                                class="glyphicon glyphicon-time green"></i> 上次登录时间:
							<fmt:formatDate value="${account1.lastLoginAt}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </a>
                    </div>
                   <hr />
                    <div class="profile-message-btn center-block text-center">
                        <a href="${ctx}/account/updateHead"  class="btn btn-success">
                            <i class="glyphicon glyphicon-edit"></i>
                            修改头像
                        </a>
                        <a href="javascript:void(0)" onclick="changepwd()" class="btn btn-info">
                            <i class="fa fa-unlock-alt"></i>
                            修改密码
                        </a>
                    </div>
            </div>
        </div>
    </div>
    <div class="col-md-8">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4>角色信息</h4>
            </div>
            <div class="panel-body">
                <table class="table">
                    <tbody>
                    <tr>
                        <td><h5>账号: <small>${account1.account}</small></h5></td>
                    </tr>
                    <tr>
                        <td><h5>姓名: <small>${account1.realName}</small></h5></td>
                    </tr>
                    <tr>
                        <td><h5>状态: <small>${account1.locked == false ? "未锁定" : "锁定"}</small></h5></td>
                    </tr>
                    <tr>
                        <td><h5>注册时间: <small>
							<fmt:formatDate value="${account1.createAt}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </small></h5></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
            </div>
        </div>
    </div>

</body>

<js>
    <script type="text/javascript" src="${ctx}/static/assets/app/js/management/systemAccounts.js"></script>
</js>