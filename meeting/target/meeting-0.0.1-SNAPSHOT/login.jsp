<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    <head>
        <title>CoolMeeting会议管理系统</title>
        <link rel="stylesheet" href="styles/common.css"/>
        <script type="text/javascript">
        var checkmsg;
        
        	function login(){
        		var flag = 1;
        		var username = document.getElementById("username").value;
        		var pwd = document.getElementById("pwd").value;
				var usernamemsg = document.getElementById("usernamemsg");
				var pwdmsg = document.getElementById("pwdmsg");
        		
        		if(username == null || username==""){
        			usernamemsg.innerHTML = "<font color='red'>用户名不能为空</font>";
        			flag = 0;
        		}
        		
        		if(pwd == null || pwd==""){
        			pwdmsg.innerHTML = "<font color='red'>密码不能为空</font>";
        			flag = 0;
        		}
        		//校验码为fail 则不允许登录
        		if(checkmsg == "fail"){
        			res.innerHTML = "<font color='red'>校验码错误</font>";
        			flag = 0;
        		}
        		
        		if(flag == 1){
        			var form1 = document.getElementById("form1");
        			form1.action = "login";
        			form1.submit();
        		}
        	}
        </script>
        <script type="text/javascript">
        	var xmlHttp;
        	
        	function createXMLHttpRequest(){
        		if(window.ActiveXObject){
        			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        		}else if(window.XMLHttpRequest){
        			xmlHttp = new XMLHttpRequest();
        		}

        	}
        	
        	
        	function check(){
        		 createXMLHttpRequest();
        		 //alert(xmlHttp);
        		 //获取输入的校验码
        		 var rand = document.getElementById("rand").value;
        		 var res = document.getElementById("res");
        		 xmlHttp.open("get", "check?rand="+rand,true);
        		 xmlHttp.send();
        		 xmlHttp.onreadystatechange = function(){
        		 	if(xmlHttp.readyState == 4){
        		 		checkmsg = xmlHttp.responseText;
        		 		//res.innerText = xmlHttp.responseText;
        		 		res.innerHTML = "<font color='red'>"+xmlHttp.responseText+"</font>";
        		 	}else{
        		 		res.innerText = "wait......";
        		 	}
        		 }

        	}
        
        </script>
        
        
    </head>
    <body>
    	<%
    		String username = null;
    		String password = null;
    		Cookie[] cookies = request.getCookies();
    		if(cookies != null){
    			for(Cookie cookie:cookies){
    				if("username".equals(cookie.getName())){
    					username = cookie.getValue();
    				}
    				
    				if("password".equals(cookie.getName())){
    					password = cookie.getValue();
    				}
    				
    			}
    		}
    		
    		if(username !=null & password != null){
    			request.getRequestDispatcher("login?username="+username+"&pwd="+password).forward(request, response);
    		}
    		
    	 %>
    
    
    
    
        <div class="page-header">
            <div class="header-banner">
                <img src="images/header.png" alt="CoolMeeting"/>
            </div>
            <div class="header-title">
                欢迎访问Cool-Meeting会议管理系统
            </div>
<!--             <div class="header-quicklink">
                欢迎您，<strong>admin</strong>
                <a href="changepassword.html">[修改密码]</a>
            </div> -->
        </div>
        <div class="page-body">
            <div class="page-sidebar">
                <div class="sidebar-menugroup">
                    <!-- <div class="sidebar-grouptitle">个人中心</div>
                    <ul class="sidebar-menu">
                        <li class="sidebar-menuitem"><a href="notifications.html">最新通知</a></li>
                        <li class="sidebar-menuitem active"><a href="mybookings.html">我的预定</a></li>
                        <li class="sidebar-menuitem"><a href="mymeetings.html">我的会议</a></li>
                    </ul> -->
                </div>
                <div class="sidebar-menugroup">
                    <!-- <div class="sidebar-grouptitle">人员管理</div>
                    <ul class="sidebar-menu">
                        <li class="sidebar-menuitem"><a href="departments.html">部门管理</></li>
                        <li class="sidebar-menuitem"><a href="register.html">员工注册</a></li>
                        <li class="sidebar-menuitem"><a href="approveaccount.html">注册审批</a></li>
                        <li class="sidebar-menuitem"><a href="searchemployees.html">搜索员工</a></li>
                    </ul> -->
                </div>
                <div class="sidebar-menugroup">
                   <!--  <div class="sidebar-grouptitle">会议预定</div>
                    <ul class="sidebar-menu">
                        <li class="sidebar-menuitem"><a href="addmeetingroom.html">添加会议室</a></li>
                        <li class="sidebar-menuitem"><a href="meetingrooms.html">查看会议室</a></li>
                        <li class="sidebar-menuitem"><a href="bookmeeting.html">预定会议</a></li>
                        <li class="sidebar-menuitem"><a href="searchmeetings.html">搜索会议</a></li>
                    </ul> -->
                </div>
            </div>
            <div class="page-content">
                <div class="content-nav">
                    登录
                </div>
                <form id="form1" action="login" method="post">
                    <fieldset>
                        <legend>登录信息</legend>
                        <table class="formtable" style="width:50%">
                        	<tr>
                                <td>登录信息:</td>
                                <td>
                                    <font color='red'>${requestScope.msg }</font>
                                </td>
                            </tr>
                        
                            <tr>
                                <td>账号名:</td>
                                <td>
                                    <input id="username"  name="username" type="text" />
                                </td>
                                <td>
                                	<div id="usernamemsg"></div>
                                </td>
                            </tr>
                            <tr>
                                <td>密码:</td>
                                <td>
                                    <input id="pwd" name="pwd" type="password" />
                                </td>
                                <td>
                                	<div id="pwdmsg"></div>
                                </td>
                            </tr>
                            <tr>
                                <td>选择免密登录时间:</td>
                                <td>
                                    <select id="" name="timelength">
                                    	<option value="0" selected>每次都登录</option>
                                    	<option value="10" >10天免密登录</option>
                                    	<option value="15">15天免密登录</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                	<img alt="校验码" src="imageServlet">
                                </td>
                                <td>
                                    <input id="rand" name="rand" type="text" onblur="check()" />
                                </td>
                                <td>
                                	<div id="res"></div>
                                </td>
                            </tr>
                            
                            <tr>
                                <td colspan="2" class="command">
                                	<input type="button" value="登录" onclick="login()" class="clickbutton"/>
                                	<!-- <input type="submit" value="登录" class="clickbutton"/> -->
                                   <!--  <input type="submit" value="登录" class="clickbutton" onclick="window.location.href='notifiactions.html';"/> -->
                                    <input type="button" value="注册" class="clickbutton" onclick="window.location.href='viewAllDepartmentServlet?code=regist'"/>
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