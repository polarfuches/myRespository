<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>主页</title>

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
    #inside {
        margin: 0 auto;
        width: 1000px;
    }

    #head {
        width: 1000px;
        height: 80px;
        border-radius: 25px 25px 0 0;
        background-color: #113965;
    }

    .headFont {
        font-size: 50px;
        font-family: '楷体';
        /*color:#3c9dfe;*/
        color: white;
        line-height: 80px;
        margin-left: 30px;
        font-weight: bold;
    }

    .user {
        float: right;
        color: white;
        margin-top: 50px;
        margin-right: 30px;
    }

    #menu {
        width: 1000px;
        height: 100px;
        padding: 0px;
        margin: 0px;
        background-color: #5184bd;
    }

    #menu li {
        display: inline-block;
        list-style: none;
        width: 150px;
        height: 100px;
        float: left;
        cursor: pointer;
    }

    #menu li div {
        height: 50px;
        width: 150px;
        line-height: 0px;
        text-align: center;
        padding-top: 15px;
        color: white;
    }

    #border {
        width: 998px;
        height: 500px;
        border: 1px solid #113965;
    }

    #foot {
        width: 1000px;
        height: 50px;
    }

    #form {
        width: 600px;
        margin: 20px auto;
        text-align: center;
    }

    #formBtn {
        width: 600px;
        margin: 10px auto;
        text-align: center;
    }

    #formBtn input {
        width: 100px;
        height: 40px
    }

    #excel-info {
        display: none;
        margin: 10px auto;
        width: 1000px;
        height: 300px;
        overflow: auto;
    }

    #excel-info table {
        margin: 10px auto;
        border-collapse: collapse;
        border-left: 1px dashed #ccc;
        border-top: 1px dashed #ccc;
    }

    #excel-info td {
        width: 100px;
        height: 30px;
        line-height: 30px;
        text-align: center;
        border-right: 1px dashed #ccc;
        border-bottom: 1px dashed #ccc;
    }
</style>
<body>
<div id="inside">
    <div id="head">
        <span class="headFont">补考管理系统</span>
        <div class="user">欢迎您&nbsp;<span style="font-size: 19px">${user.sname}</span>&nbsp;&nbsp;<a href="exit.do"
                                                                                                    id="exit">退出</a>
        </div>
        <input id="sno" type="hidden" value="${user.sno}">
    </div>
    <ul id="menu">
        <c:if test="${user.mark == 0 }">
            <li id="serch">
                <div><img src="images/serch.png"></div>
                <div>补考查询</div>
            </li>
            <li id="score">
                <div><img src="images/navi03.png"></div>
                <div>补考成绩查询</div>
            </li>
        </c:if>
        <c:if test="${user.mark == 1 }">
            <li id="import">
                <div><img src="images/import.png"></div>
                <div>数据导入</div>
            </li>
            <li id="request">
                <div><img src="images/request.png"></div>
                <div>一键排考</div>
            </li>
        </c:if>

    </ul>
    <div id="border">
        <div id="form">
            <form id="excel-frm" action="imputExcel/uploadExcel.do" method="post" enctype="multipart/form-data">
                <input type="file" name="file" value="" id="excel-File">
                <input type="radio" name="flag" value="student" checked="checked">学生表
                <input type="radio" name="flag" value="course">课程表
                <input type="radio" name="flag" value="studentinfo">补考名单
                <input type="radio" name="flag" value="score">补考成绩
                <div id="formBtn"><input type="button" id="excel-btn" value="提交"></div>
            </form>
        </div>
        <div style="display: none" id="teacher-div">
            ${excelInfo}
        </div>

        <div id="excel-info">
            <table>
                <tr>

                </tr>
            </table>
        </div>
    </div>
</div>
</body>
<script>
    var sno = $("#sno").val();
    $("#menu li:first").css("background-color", "#113965");
    $("#menu li").mouseenter(function () {
        $(this).css("background-color", "#113965");
        $("#menu li").not(this).css("background-color", "#5184bd");
    });
    $("#menu li").click(function () {
        $(this).css("background-color", "#113965");
        $("#menu li").not(this).css("background-color", "#5184bd");
        //此处是ajax请求
        $.post("menuJump.do", {"flag": this.id, "sno": sno}, function (data) {
            $("#border").html(data);
        });
    });
    $(function () {
        var data = $("#teacher-div").html();
        if (trim(data) != "") {
            var json = eval("(" + data + ")");
            //console.log(json);

            for (var i = 0; i < json.length; i++) {
                var str = "";
                str = str + "<tr><td>";
                //$("#excel-info table tbody").append("<tr><td>"+json[i][0]+"</td><td>"+json[i][1]+"</td><td>"+json[i][2]+"</td><td>"+json[i][3]+"</td></tr>");
                for (var j = 0; j < json[i].length - 1; j++) {
                    str = str + json[i][j] + "</td><td>";
                }
                str = str + json[i][json[i].length - 1] + "</td></tr>";
                $("#excel-info table tbody").append(str);
            }
            $("#excel-info").css("display", "block");
        }
    });

    //去左右空格;
    function trim(s) {
        return s.replace(/(^\s*)|(\s*$)/g, "");
    }

    $("#excel-frm #excel-btn").click(function () {
        if ($("#excel-File").val() != "") {
            $("#excel-frm").submit();
        } else {
            alert("文件不能为空");
        }
    });
</script>
</html>
