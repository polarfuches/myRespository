<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <title>登录页面</title>
    <link href="css/login.css" type="text/css" rel="stylesheet">
</head>
<%@include file="tag.jsp" %>
<script type="text/javascript" src="jquery/jquery-1.8.0.js"></script>
<style>
    #mark {
        width: 340px;
        height: 50px;
        line-height: 50px;
        text-align: center;
        color: #999;
        font-size: 18px;
        font-family: "微软雅黑";
    }

    #mark input {
        width: 18px;
        height: 18px;
    }

    #error {
        color: red;
        text-align: center;
        font-size: 15px;
    }
</style>
<body>

<div class="login">
    <div class="message">补&nbsp;考&nbsp;安&nbsp;排&nbsp;系&nbsp;统</div>
    <div id="darkbannerwrap"></div>

    <form action="login.do" method="post">
        <input name="userId" placeholder="用户名" type="text">
        <hr class="hr15">
        <input name="password" placeholder="密码" type="password">
        <hr class="hr15">
        <div id="mark">
            <input type="radio" name="mark" checked="checked" value="0">&nbsp;&nbsp;学&nbsp;生
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="mark" value="1">&nbsp;&nbsp;老&nbsp;师
        </div>
        <hr class="hr15">
        <input value="登录" style="width:100%;" type="button">
        <hr class="hr20">
        <!-- 帮助 <a onClick="alert('请联系管理员')">忘记密码</a> -->
    </form>

    <div id="error">${error }</div>
</div>
<script type="text/javascript">
    var userRule = /^\d{9}$/;
    var result;
    $("input[type=button]").click(function () {
        result = userRule.test($("input:first").val());
        if (result) {
            $("form")[0].submit();
        } else {
            $("#error").text("请正确填写用户ID");
            $("input:first").attr("placeholder", "请正确填写用户ID");
        }
        //placeholder
    });

</script>
</body>
</html>