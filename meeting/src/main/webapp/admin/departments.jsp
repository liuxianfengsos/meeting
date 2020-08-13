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
    </head>
    <body>

        <div class="page-body">
            
            <div class="page-content">
                <div class="content-nav">
                    人员管理 > 部门管理
                </div>
                <form action="addDeleteDepartmentServlet" method="post">
                    <fieldset>
                        <legend>添加部门</legend>
                        部门名称: 
                        <input type="text" id="departmentname" name="departmentname" maxlength="20"/>
                        <input type="hidden" name="code" value="add">
                        <input type="submit" class="clickbutton" value="添加"/>
                    </fieldset>
                </form>
                
                <c:if test="${requestScope.departmentList != null }">
	                 <table class="listtable">
	                    <caption>所有部门:</caption>
	                    <tr class="listheader">
	                        <th>部门编号</th>
	                        <th>部门名称</th>
	                        <th>操作</th>
	                    </tr>
	                    
	                    <c:forEach var="dept" items="${requestScope.departmentList}">
		                     <tr>
		                        <td>${dept.departmentid }</td>
		                        <td>${dept.departmentname }</td>
		                        <td>
		                            <a class="clickbutton" href="#">编辑</a>
		                            <a class="clickbutton" href="addDeleteDepartmentServlet?code=delete&departmentid=${dept.departmentid }">删除</a>
		                        </td>
		                    </tr>
	                    </c:forEach>
	                    
	                   
	                    
	                    
	                   <!--  <tr>
	                        <td>2</td>
	                        <td>
	                            <input type="text" value="销售部"/>
	                        </td>
	                        <td>
	                            <a class="clickbutton" href="#">编辑</a>
	                            <a class="clickbutton" href="#">取消</a>
	                            <a class="clickbutton" href="#">删除</a>
	                        </td>
	                    </tr> -->
	                   
	                    
	                </table>
                
                </c:if>
                
               
            </div>
        </div>
       
    </body>
</html>