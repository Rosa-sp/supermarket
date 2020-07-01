<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>超市管理平台-登录</title>
    <link href="favicon.ico" rel="shortcut icon" type="image/x-icon"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/lib/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/lib/css/font-awesome.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/app/css/management/signin.min.css"/>

</head>

<body class="login-layout">

<div id="main_container">
    <div class="topCon">
        <div class="lc">
            <span class="ico-to-right" style="position:relative; top:2px; margin-right:10px; "></span>欢迎登录超市管理平台
        </div>
        <div class="rc"></div>
    </div>
    <div class="logoCon"></div>

    <div class="loginFormCon">

        <form id="loginForm" name="myform" action="${pageContext.request.contextPath}/account/login" method="post" class="form" onSubmit="return formSubmit();">

            <div class="input-group form-group col-xs-9">
                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                <input type="text" name="account" onfocus="checkAccount()" value="${account}" class="form-control" placeholder="请输入登录账号" required>

            </div>
            <div class="input-group form-group col-xs-9">
                <span class="input-group-addon"><i class="fa fa-key"></i></span>
                <input type="password" name="password" value="${password}" class="form-control" placeholder="请输入登录密码" required>
            </div>

            <div class="input-group form-group col-xs-9">
                <span class="input-group-addon"><i id="validate-status" class="fa fa-hand-o-right"></i></span>
                <input type="text" id="captcha" name="captcha" maxlength="10" onblur="vlCode()"
                       class="form-control" style="width:85px" placeholder="验证码">
                <img class="jcaptcha-btn jcaptcha-img" style="margin-left: 15px;height:34px" src="${pageContext.request.contextPath}/captcha" title="点击更换验证码">
                <a class="jcaptcha-btn btn btn-link">换一张</a>
            </div>
            <div class="input-group form-group col-xs-9">
                <label><input type="checkbox" name="remember" value="true" ${empty account ? '' :'checked="checked"'}>记住密码</label>
                <button type="submit" class="btn btn-primary block col-xs-9" style="float: right">登录</button>
            </div>
            <div id="error-info" class="alert alert-danger alert-dismissable text-center col-xs-9" style=" margin-top: 0px;padding: 8px;${empty msg ? 'display: none' : ''}">
                ${msg}</div>
        </form>
    </div>
    <div class="bottomCon">
        超市管理平台版权所有&nbsp;&nbsp;2020&nbsp;&nbsp;
    </div>
</div>

<script src="${pageContext.request.contextPath}/static/assets/lib/js/jquery.js"></script>
<script type="text/javascript">
    jQuery.ctx = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/static/assets/app/js/management/signin.js"></script>


<script>
    function checkAccount() {
        var account = $('.name');
        var value =  account.val();
        var error = $('#error');
        if(account == ''){
            error.html("账户名不能为空");
            return false
        }
    }
</script>
</body>
</html>
