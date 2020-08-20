<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
        <title>CoolMeeting会议管理系统</title>
        <link rel="stylesheet" href="../styles/common.css"/>
        <style type="text/css">
            
        </style>
    </head>
    <body>
        
        <div class="page-body">
           
            <div class="page-content">
                <div class="content-nav">
                    个人中心 > 我的会议
                </div>
                <table class="listtable">
                    <caption>我将参加的会议：</caption>
                    <tr class="listheader">
                        <th>会议名称</th>
                        <th>会议室名称</th>
                        <th>会议开始时间</th>
                        <th>会议结束时间</th>
                        <th>会议预定时间</th>
                        <th>预定者</th>
                        <th>操作</th>
                    </tr>
                    
                    <c:forEach items="${requestScope.map }" var="item">
	                    <tr>
	                        <td>${item.key.meetingname }</td>
	                        <td>${item.value[1] }</td>
	                        <td>${item.key.starttime }</td>
	                        <td>${item.key.endtime }</td>
	                        <td>${item.key.reservationtime }</td>
	                        <td>${item.value[0] }</td>
	                        <td>
	                            <a class="clickbutton" href="meetingdetails.html">查看详情</a>
	                        </td>
	                    </tr>
                    </c:forEach>
                    
                   
                </table>
            </div>
        </div>
       
    </body>
</html>