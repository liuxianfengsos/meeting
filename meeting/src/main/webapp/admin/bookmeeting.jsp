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
            #divfrom{
                float:left;
                width:150px;
            }
            #divto{
                float:left;
                width:150px;
            }
            #divoperator{
                float:left;
                width:50px;
                padding:60px 5px;
            }
            #divoperator input[type="button"]{
                margin:10px 0;
            }
            #selDepartments{
                display:block;
                width:100%;
            }
            #selEmployees{
                display:block;
                width:100%;
                height:200px;
            }
            #selSelectedEmployees{
                display:block;
                width:100%;
                height:225px;
            }
        </style>
        <script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
        
        
        <script type="application/javascript">
        	var xmlHttp;
        //获取ajax对象
         	function createXMLHttpRequest(){
            	if(window.ActiveXObject){
            		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            	}else if(window.XMLHttpRequest){
            		xmlHttp = new XMLHttpRequest();
            	}
            }
        
            function showEmployees(){
            	createXMLHttpRequest();
            	var deptid = document.getElementById("selDepartments").value;
            	//alert(deptid);
            	var url = "selectEmployeesOfDeptServle?departmentid="+escape(deptid);
            	xmlHttp.open("GET",url,true);
            	xmlHttp.onreadystatechange = callback;
            	xmlHttp.send(null);
            }
            
            function callback(){
            	clearEmployyes();
            	var selEmployees = document.getElementById("selEmployees");
            	
            	if(xmlHttp.readyState == 4){
            		if(xmlHttp.status == 200){
            			var elements = xmlHttp.responseXML.getElementsByTagName("option");
            			for(var i=0;i<elements.length;i++){
            				var value = elements[i].getElementsByTagName("value")[0].firstChild.nodeValue;
            				var text = elements[i].getElementsByTagName("text")[0].firstChild.nodeValue;
            				//alert(value);
            				selEmployees.options.add(new Option(text,value));
            			}
            			
            		}
            	}
            }
            //清除人员
            function clearEmployyes(){
            	 document.getElementById("selEmployees").options.length=0;
            }
            
            //添加参会人员
            //selEmployees
            //selSelectedEmployees
            function selectEmployees(){
            	var selEmployees = document.getElementById("selEmployees");
            	var selSelectedEmployees = document.getElementById("selSelectedEmployees");
            	//遍历所有已知员工
            	for(var i=0;i<selEmployees.options.length;i++){
            	//判断是否被选中
            		if(selEmployees.options[i].selected){
            			var opt = new Option(selEmployees.options[i].text,selEmployees.options[i].value);
            			opt.selected = true;
            			selSelectedEmployees.options.add(opt);
            			selEmployees.options.remove(i);
            			
            		}
            	}
            }
            
           function deSelectEmployees(){
           		var selEmployees = document.getElementById("selEmployees");
            	var selSelectedEmployees = document.getElementById("selSelectedEmployees");
            	for(var i=0;i<selSelectedEmployees.options.length;i++){
            		if(selSelectedEmployees.options[i].selected){
            			selEmployees.options.add(new Option(selSelectedEmployees.options[i].text,selSelectedEmployees.options[i].value));
            			selSelectedEmployees.options.remove(i);
            		}
            	
            	}
            	
           }
            
            
        </script>
    </head>
    <body >
        
        <div class="page-body">
            
            <div class="page-content">
                <div class="content-nav">
                    会议预定 > 预定会议
                </div>
                <form action="bookMeetingServlet" method="post">
                    <fieldset>
                        <legend>会议信息</legend>
                        <table class="formtable">
                            <tr>
                                <td>会议名称：</td>
                                <td>
                                    <input type="text" name="meetingname" id="meetingname" maxlength="20"/>
                                </td>
                            </tr>
                            <tr>
                                <td>预计参加人数：</td>
                                <td>
                                    <input type="text" name="numofattendents" id="numofattendents" />
                                </td>
                            </tr>
                            <tr>
                                <td>预计开始时间：</td>
                                <td>
                                    <input class="Wdate" type="text" name="starttime" id="startdate" 
                                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                </td>
                            </tr>
                            <tr>
                                <td>预计结束时间：</td>
                                <td>
                                    <input  class="Wdate"  type="text"  name="endtime"  id="enddate" 
                                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                    
                                </td>
                            </tr>
							<tr>
                                <td>会议室名称：</td>
                                <td>
                                    <select name="roomid">   
                                    	<c:forEach items="${requestScope.roomsList}" var="room">
                                    		<option value="${room.roomid }">${room.roomname }</option>
                                    	</c:forEach> 
                                     	
                                     	
                                     </select>
                                </td>
                            </tr>
                            <tr>
                                <td>会议说明：</td>
                                <td>
                                    <textarea id="description" name="description" rows="5"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td>选择参会人员：</td>
                                <td>
                                    <div id="divfrom">
                                        <select id="selDepartments" onchange="showEmployees()">
                                        	<option>请选择部门</option>
                                        	<c:forEach var="dept" items="${requestScope.deptsList }">
                                        		<option value="${dept.departmentid }">${dept.departmentname }</option>
                                        	</c:forEach>
                                        	
                                        </select>
                                        
                                        <select id="selEmployees" multiple="multiple">
                                        </select>
                                        
                                    </div>
                                    <div id="divoperator">
                                        <input type="button" class="clickbutton" value="&gt;" onclick="selectEmployees()"/>
                                        <input type="button" class="clickbutton" value="&lt;" onclick="deSelectEmployees()"/>
                                    </div>
                                    <div id="divto">
                                        <select name="selSelectedEmployees" id="selSelectedEmployees" multiple="true">
                                        </select>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="command" colspan="2">
                                	<input type="hidden" name="code" value="book">
                                    <input type="submit" class="clickbutton" value="预定会议"/>
                                    <input type="reset" class="clickbutton" value="重置"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
       
    </body>
</html>