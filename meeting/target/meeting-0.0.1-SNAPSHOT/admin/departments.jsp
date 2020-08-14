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
        <script type="text/javascript">
        	var val1;
            var inputvalue1;
            var index = 0;
        
        	function edit(btn){
        		//alert("111111111");
        		//console.log("1111111111");
        		var input1 = document.createElement("INPUT");
        		input1.setAttribute("type", "text");
        		//获取btn爸爸的哥哥
        		var td = btn.parentNode.parentNode.children[1];
        		var tdv = td.innerHTML;
        		//console.log(tdv)
        		//擦除td中的文字
        		td.innerHTML = "";
        		input1.setAttribute("value", tdv);//给input设置部门名称 
        		//为input1添加onblur
        		input1.setAttribute("onblur", "btnclick(this)");
        		
        		td.appendChild(input1);
        		//val1 = btn.nextElementSibling.value;
        		val1 = btn.parentNode.parentNode.children[0].innerText;
        		
        		console.log(val1);
        	}
        	
        	function btnclick(inp){
        		inputvalue1 = inp.value;
        		//alert(inputvalue1);
        		var a1 = document.createElement("a");//<a href="">提交</a>
        		a1.innerText = "提交";
        		a1.href = "addDeleteDepartmentServlet?code=edit&departmentid="+val1+"&departmentname="+inputvalue1;
        		//class="clickbutton"
        		a1.setAttribute("class", "clickbutton");
        		
        		inp.parentNode.parentNode.children[2].appendChild(a1);

        	}
        	
        </script>
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
		                            <a class="clickbutton" onclick="edit(this)">编辑</a>
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