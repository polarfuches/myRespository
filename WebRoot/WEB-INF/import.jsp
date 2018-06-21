<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@include file="tag.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>数据导入</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <script type="text/javascript" src="jquery/jquery-1.8.0.js"></script>
    <style type="text/css">
        #excelBorder {
            width: 1000px;
            overflow: auto;
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
</head>

<body>
<div id="excelBorder">
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
<script type="text/javascript">
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
</body>
</html>
