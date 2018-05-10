<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Creative - Bootstrap 3 Responsive Admin Template">
    <meta name="author" content="GeeksLabs">
    <meta name="keyword" content="Creative, 主页, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
    <link rel="shortcut icon" href="img/favicon.png">

    <title>登录页面</title>

    <!-- Bootstrap CSS -->    
    <link href="${basePath}resource/css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="${basePath}resource/css/bootstrap-theme.css" rel="stylesheet">
    <!--external css-->
    <!-- font icon -->
    <link href="${basePath}resource/css/elegant-icons-style.css" rel="stylesheet" />
    <link href="${basePath}resource/css/font-awesome.css" rel="stylesheet" />
    <!-- Custom styles -->
    <link href="${basePath}resource/css/style.css" rel="stylesheet">
    <link href="${basePath}resource/css/style-responsive.css" rel="stylesheet" />
    <script src="${basePath}resource/js/jquery-1.8.3.min.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
    <!--[if lt IE 9]>
    <script src="${basePath}resource/js/html5shiv.js"></script>
    <script src="${basePath}resource/js/respond.min.js"></script>
    <![endif]-->
</head>

  <body class="login-img3-body">

    <div class="container">

      <form class="login-form" method="post" action="/login">
        <div class="login-wrap">
            <p class="login-img"><i class="icon_lock_alt"></i></p>
            <div class="input-group">
              <span class="input-group-addon"><i class="icon_profile"></i></span>
              <input  id="username" name="userName" type="text" class="form-control" placeholder="Username" autofocus>
            </div>
            <div class="input-group">
                <span class="input-group-addon"><i class="icon_key_alt"></i></span>
                <input name="password" id="password" type="password" class="form-control" placeholder="Password">
            </div>
            <label class="checkbox">
                <input type="checkbox" value="remember-me"> 记住我
                <span class="pull-right"> <a href="#">忘记密码?</a></span>
            </label>
            <button class="btn btn-primary btn-lg btn-block" type="submit">登录</button>
            <button class="btn btn-info btn-lg btn-block" type="submit">注册</button>
            <p id="warning" style="color: red;font-size: 20px;">
                <c:if test="${failMsg!=null}">
                ${failMsg}
                </c:if>
            </p>
        </div>
      </form>
    </div>
  </body>
</html>
