<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<title>会议管理系统</title>
</head>
<frameset rows="150,*,93" cols="*" framespacing="0" frameborder="no" border="0">
 <frame src="admin/top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame"  marginwidth="0" marginheight="0"  frameborder="0" />
 <frameset cols="260,*" id="frame">
	<frame src="admin/adminleft.jsp" name="leftFrame" noresize="noresize" marginwidth="110px" marginheight="0" frameborder="0" scrolling="auto" target="main"  />
	<frame src="admin/01.html" name="main" marginwidth="50px" marginheight="40px" frameborder="0" scrolling="auto" target="_self"  />
  </frameset>
 <frame src="admin/02.html" name="bottomFrame" scrolling="No" noresize="noresize" id="bottomFrame" marginwidth="0" marginheight="0"/>
</frameset><noframes></noframes>
</html>
  
  
  
  
  
  
</html>
