<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>CoolMeeting会议管理系统</title>
        <link rel="stylesheet" href="../styles/common.css"/>
    </head>
    <body>
        <div class="page-header">
            <div class="header-banner">
                <img src="../images/header.png" alt="CoolMeeting"/>
            </div>
            <div class="header-title">
                欢迎访问Cool-Meeting会议管理系统
            </div>
            <div class="header-quicklink">
                欢迎您，<strong>${sessionScope.employeename }</strong>
               <!--  <a href="changepassword.html">[修改密码]</a> -->
                 <a href="logout" target="_parent">[退出]</a>
            </div>
            <div class="header-quicklink">
            	访问次数:[<font color='red'>${applicationScope.visitcount }</font>]
            </div>
        </div>
    </body>
</html>