<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
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
                    人员管理 > 注册审批
                </div>
                <table class="listtable">
                    <caption>所有待审批注册信息：</caption>
                    <tr class="listheader">
                        <th>姓名</th>
                        <th>账号名</th>
                        <th>联系电话</th>
                        <th>电子邮件</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach var="emp" items="${requestScope.employeesList }" >
                    	<tr>
	                        <td>${emp.employeename }</td>
	                        <td>${emp.username }</td>
	                        <td>${emp.phone }</td>
	                        <td>${emp.email }</td>
	                        <td>
	                            <input type="button" class="clickbutton" value="通过"/>
	                            <input type="button" class="clickbutton" value="删除"/>
	                        </td>
                    	</tr>
                    </c:forEach>
                    
                   
                </table>
            </div>
        </div>
        
    </body>
</html>