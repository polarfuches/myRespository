<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>排考</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

</head>
<script type="text/javascript" src="jquery/jquery-1.8.0.js"></script>
<%@include file="tag.jsp" %>
<style>
    #req {
        width: 1000px;
        height: 500px;
        overflow: auto;
    }

    #sub {
        width: 300px;
        height: 40px;
    }

    #btn {
        width: 1000px;
        height: 40px;
        text-align: center;
        margin-top: 10px;
    }

    #testAbout {
        width: 1000px;
        margin-top: 10px;
    }
</style>
<body>

<div id="req">
    <div id="btn">
        <button id="sub">一键排考</button>
    </div>
    <div id="testAbout"></div>
</div>
<script type="text/javascript">
    $("#sub").click(function () {
        $.post("grphic.do", function (data) {
            $("#testAbout").html(data);
        });
    });
</script>
</body>
</html>
