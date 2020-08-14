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
        <link rel="stylesheet" href="styles/common.css"/>
        <style type="text/css">

        </style>
		<script type="text/javascript">
			var xmlHttp;
			
			var passed;
			
            function createXMLHttpRequest(){
            	if(window.ActiveXObject){
            		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            	}else if(window.XMLHttpRequest){
            		xmlHttp = new XMLHttpRequest();
            	}
            }
            
            function vailidate(){
            //获取jaxa 对象
            	createXMLHttpRequest();
            	//alert(xmlHttp);
            	var username = document.getElementById("username").value;
            	//alert(username);
            	var url = "validateUsernameServlet?username="+escape(username);
            	xmlHttp.open("GET", url,true);
            	xmlHttp.onreadystatechange = callback;
            	xmlHttp.send(null);
            	
            }
            
            function callback(){
            //alert('11111111');
            	if(xmlHttp.readyState == 4){
            		if(xmlHttp.status == 200){
            			var message = xmlHttp.responseXML.getElementsByTagName("message")[0].firstChild.data;
            			    passed = xmlHttp.responseXML.getElementsByTagName("passed")[0].firstChild.data;
            			//alert(message);
            			setMessage(message,passed);
            		}
            	}
            }
            
            function setMessage(message,passed){
            	var vailidateMessage = document.getElementById("vailidateMessage");
            	var fontColor = "red";
            	if(passed == "true"){
            		fontColor = "green";
            	}
            	 vailidateMessage.innerHTML = "<font color="+fontColor+">"+message+"</font>";
            	
            }
            //注册校验
            function regist(){
            	//alert("0000000000");
            	var flag = 1;
            	var username = document.getElementById("username").value;
            	
            	var vailidateMessage = document.getElementById("vailidateMessage");
            	//password  passwordMessage   confirm  confirmMessage
            	var password = document.getElementById("password").value;
            	var passwordMessage = document.getElementById("passwordMessage");
            	
            	var confirm = document.getElementById("confirm").value;
            	var confirmMessage = document.getElementById("confirmMessage");
            	
            	
            	//用户名非空
            	console.log("username="+username);
            	
            	if(username == "" || username == null){
            		vailidateMessage.innerHTML = "<font color='red'>用户名不能为空</font>";
            		flag = 0;
            	}
            	
            	//密码非空
             	if(password == "" || password == null){
            		console.log(password);
            		passwordMessage.innerHTML = "<font color='red'>密码不能为空</font>";
            		flag = 0;
            	}
            	
            	//密码验证不能为空
            	if(confirm == "" || confirm == null){
            		confirmMessage.innerHTML = "<font color='red'>密码验证不能为空</font>";
            		flag = 0;
            	}
            	
            	//密码与密码校验要相等
            	
            	if(confirm != password){
            		confirmMessage.innerHTML = "<font color='red'>两次输入的密码不一致</font>";
            		flag = 0;
            	}
            	//jaxa返回false
            	if(passed == "false"){
            		flag = 0;
            	} 

            	if(flag == 1){
        			var form1 = document.getElementById("form1");
        			form1.method = "POST";
        			form1.action = "registServlet";
        			form1.submit();
        		}
            }
            
            
            
            
		</script>
    </head>
    <body>
        <div class="page-header">
            <div class="header-banner">
                <img src="images/header.png" alt="CoolMeeting"/>
            </div>
            <div class="header-title">
                欢迎访问Cool-Meeting会议管理系统
            </div>
            <div class="header-quicklink">
             
            </div>
        </div>
        <div class="page-body">
            <div class="page-sidebar">
                <div class="sidebar-menugroup">
                    
                </div>
                <div class="sidebar-menugroup">
                   
                </div>
                <div class="sidebar-menugroup">
                    
                </div>
            </div>
            <div class="page-content">
                <div class="content-nav">
                    人员管理 > 员工注册
                </div>
                <form id="form1">
                    <fieldset>
                        <legend>员工信息</legend>
                        <table class="formtable" style="width:50%">
                        	 <tr>
                                <td>提示：</td>
                                <td>${requestScope.msg }</td>
                            </tr>
                            <tr>
                                <td>姓名：</td>
                                <td>
                                    <input type="text" id="employeename" name="employeename" maxlength="20"/>
                                </td>
                            </tr>
                            <tr>
                                <td>账户名：</td>
                                <td>
                                    <input type="text" id="username" name="username" onchange="vailidate();" maxlength="20"/>
                                	<div id="vailidateMessage"></div>
                                </td>
                            </tr>
                            <tr>
                                <td>密码：</td>
                                <td>
                                    <input type="password" id="password" name="password" maxlength="20" placeholder="请输入6位以上的密码"/>
                                	<div id="passwordMessage"></div>
                                </td>
                            </tr>
                            <tr>
                                <td>确认密码：</td>
                                <td>
                                    <input type="password" id="confirm" maxlength="20"/>
                                    <div id="confirmMessage"></div>
                                </td>
                            </tr>
                            <tr>
                                <td>联系电话：</td>
                                <td>
                                    <input type="text" name="phone" id="phone" maxlength="20"/>
                                </td>
                            </tr>
                            <tr>
                                <td>电子邮件：</td>
                                <td>
                                    <input type="text" name="email" id="email" maxlength="20"/>
                                </td>
                            </tr>
							<td>所在部门：</td>
                                <td>
                                    <select name="deptid">    
                                     	<c:forEach var="department" items="${requestScope.departmentList }">
                                     		<option value="${department.departmentid }">${department.departmentname }</option>
                                     	</c:forEach>
                                     </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="6" class="command">
                                    <input type="button" onclick="regist();" class="clickbutton" value="注册"/>
                                    <input type="reset" class="clickbutton" value="重置"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
        <div class="page-footer">
            <hr/>
            更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a>
            <img src="images/footer.png" alt="CoolMeeting"/>
        </div>
    </body>
</html>