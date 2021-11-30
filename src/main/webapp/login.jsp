<%@ page import="fun.juhua.library.servlet.LoginServlet" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>登录-图书馆管理系统</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/login.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="./lib/layui/layui.js" charset="utf-8"></script>
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="login-bg">
<div class="login layui-anim layui-anim-up">
    <div class="message">图书馆管理系统 - 登录</div>
    <div id="darkbannerwrap"></div>

    <form class="layui-form" action="/LoginServlet" method="post">
        <hr class="hr20">
        <input name="id" placeholder="用户ID" type="text" lay-verify="required" class="layui-input">
        <hr class="hr20">
        <input name="password" lay-verify="required" placeholder="密码" type="password" class="layui-input">
        <hr class="hr20">
        <c:if test="${errorMsg!=null}">
            <div class="layui-btn layui-btn-danger"
                 style="width: 100%;font-size: 20px;text-align: center">${errorMsg} </div>
            <hr class="hr20">
        </c:if>
        <a onclick="xadmin.open('注册账号','/register.jsp')"
           style="width:49%;height: 48px;text-align: center;align-content: center;line-height: 48px;font-size: 14px"
           class="layui-btn layui-btn-lg layui-btn-normal">前往注册</a>
        <input value="登录" lay-submit lay-filter="login" type="submit" style="width: 49%">
        <hr class="hr20">
    </form>
</div>

<script>
    layui.use(['form', 'layer'],
        function () {
            $ = layui.jquery;
            var form = layui.form,
                layer = layui.layer;
            //自定义验证规则
            form.verify({});
        });
</script>
<!-- 底部结束 -->
</body>
</html>