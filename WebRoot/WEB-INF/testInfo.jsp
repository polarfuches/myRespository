<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<script type="text/javascript" src="jquery/jquery-1.8.0.js"></script>
<%@include file="tag.jsp" %>
<style>
    #testinfoBorder {
        width: 1000px;
    }

    #testinfoBorder table {
        border-collapse: collapse;
        width: 1000px;
        border-top: 1px dashed #ccc;
    }

    #testinfoBorder table td {
        height: 30px;
        text-align: center;
        line-height: 30px;
        border-bottom: 1px dashed #ccc;
    }
</style>
<body>
<div id="testinfoBorder">
    <table>
        <tr>
            <td style="width:300px;">课程</td>
            <td style="width:200px;">教室</td>
            <td style="width:200px;">日期</td>
            <td style="width:150px;">开始时间</td>
            <td style="width:150px;">结束时间</td>
        </tr>
        <c:forEach items="${test }" var="temp">

            <c:forEach items="${temp }" var="test">
                <tr>
                    <td>${test.cname }</td>
                    <td>${test.floor.fno }-${test.floor.fname }</td>
                    <td>${test.date }</td>
                    <td>${test.startTime }</td>
                    <td>${test.endTime }</td>
                </tr>
            </c:forEach>

        </c:forEach>
    </table>
</div>
</body>
</html>
