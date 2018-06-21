<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<script type="text/javascript" src="jquery/jquery-1.8.0.js"></script>
<%@include file="tag.jsp" %>
<style>
    #serchBorder {
        width: 1000px;
    }

    #serchBorder table {
        border-collapse: collapse;
        width: 1000px;
        border-top: 1px dashed #ccc;
    }

    #serchBorder table td {
        height: 30px;
        text-align: center;
        line-height: 30px;
        border-bottom: 1px dashed #ccc;
    }
</style>
<body>
<div id="serchBorder">
    <table>
        <tr>
            <td style="width:300px;">课程</td>
            <td style="width:150px;">教室</td>
            <td style="width:150px;">日期</td>
            <td style="width:150px;">开始时间</td>
            <td style="width:150px;">结束时间</td>
            <td style="width:100px;">成绩</td>
        </tr>
        <c:forEach items="${test }" var="temp">
            <tr>
                <td>${temp.cname }</td>
                <td>${temp.addr}</td>
                <td>${temp.times }</td>
                <td>${temp.startime }</td>
                <td>${temp.endtime }</td>
                <td>
                    <c:choose>
                        <c:when test="${temp.score > 0 }">${temp.score}</c:when>
                        <c:otherwise>暂无</c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
