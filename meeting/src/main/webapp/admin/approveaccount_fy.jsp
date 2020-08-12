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
                   <c:forEach var="emp" items="${requestScope.empList }" >
                    	<tr>
	                        <td>${emp.employeename }</td>
	                        <td>${emp.username }</td>
	                        <td>${emp.phone }</td>
	                        <td>${emp.email }</td>
	                        <td>
	                            <input type="button" onclick="window.location.href='approveServlet?employeeid=${emp.employeeid}&oper=yes'" class="clickbutton" value="通过"/>
	                            <input type="button" class="clickbutton" value="未通过"/>
	                        </td>
                    	</tr>
                    </c:forEach>
                   
                </table>
                <!-- 分页 --> 
                <!-- 当前页是1 -->
                <c:if test="${currentpageNo==1 }">
                   		<a href="viewAllEmployeesFYServlet?currentPageNo=${requestScope.currentpageNo+1 } ">下一页</a>
                   		<a href="viewAllEmployeesFYServlet?currentPageNo=${totalPage } ">尾页</a>
                </c:if>
                
                <!-- 当前页是末页 -->
                <c:if test="${currentpageNo==totalPage }">
                	<a href="viewAllEmployeesFYServlet?currentPageNo=1 ">首页</a>
                	<a href="viewAllEmployeesFYServlet?currentPageNo=${requestScope.currentpageNo-1 }">上一页</a>
                </c:if>
                
                <!-- 当前页不是首页  也不是末页     2:16  -->
                <c:if test="${currentpageNo>1 && currentpageNo<totalPage }">
                	<a href="viewAllEmployeesFYServlet?currentPageNo=1 ">首页</a>
                	<a href="viewAllEmployeesFYServlet?currentPageNo=${requestScope.currentpageNo-1 }">上一页</a>
                	<a href="viewAllEmployeesFYServlet?currentPageNo=${requestScope.currentpageNo+1 } ">下一页</a>
                   	<a href="viewAllEmployeesFYServlet?currentPageNo=${totalPage } ">尾页</a>
                </c:if>
                
            </div>
        </div>
        
    </body>
</html>