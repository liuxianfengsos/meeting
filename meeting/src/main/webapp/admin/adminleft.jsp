<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    <head>
        <title>CoolMeeting会议管理系统</title>
        <link rel="stylesheet" href="../styles/common.css"/>
    </head>
    <body>

        
            <div class="page-sidebar">
                <div class="sidebar-menugroup">
                    <div class="sidebar-grouptitle">个人中心</div>
                    <ul class="sidebar-menu">
                        <li class="sidebar-menuitem"><a href="notifications.html">最新通知</a></li>
                        <li class="sidebar-menuitem active"><a href="viewMyBookingServlet" target="main">我的预定</a></li>
                        <li class="sidebar-menuitem"><a href="viewMymeetingServlet"  target="main">我的会议</a></li>
                    </ul>
                </div>
                <div class="sidebar-menugroup">
                    <div class="sidebar-grouptitle">人员管理</div>
                    <ul class="sidebar-menu">                 
                        <li class="sidebar-menuitem"><a href="viewAllDepartmentServlet?code=viewalldepartments" target="main">部门管理</></li>
                        <li class="sidebar-menuitem"><a href="register.html">员工注册</a></li>
                   <!-- <li class="sidebar-menuitem"><a href="viewAllEmployeesServlet?code=approve" target="main">注册审批</a></li> -->
                        <li class="sidebar-menuitem"><a href="viewAllEmployeesFYServlet" target="main">注册审批</a></li>
                        <li class="sidebar-menuitem"><a href="searchemployees.html">搜索员工</a></li>
                    </ul>
                </div>
                <div class="sidebar-menugroup">
                    <div class="sidebar-grouptitle">会议预定</div>
                    <ul class="sidebar-menu">
                        <li class="sidebar-menuitem"><a href="addmeetingroom.jsp" target="main">添加会议室</a></li>
                        <li class="sidebar-menuitem"><a href="viewAllMeetingRoomsServlet" target="main">查看会议室</a></li>
                        <li class="sidebar-menuitem"><a href="bookmeeting.html">预定会议</a></li>
                        <li class="sidebar-menuitem"><a href="searchmeetings.html">搜索会议</a></li>
                    </ul>
                </div>
            </div>

    </body>
</html>