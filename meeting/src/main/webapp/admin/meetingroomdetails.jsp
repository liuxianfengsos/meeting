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
                    会议预定 > 修改会议室信息
                </div>
                <form>
                    <fieldset>
                        <legend>会议室信息</legend>
                        <table class="formtable">
                            <tr>
                                <td>门牌号:</td>
                                <td>
                                    <input id="roomnumber" type="text" value="${requestScope.room.roomid }" maxlength="10" />
                                </td>
                            </tr>
                            <tr>
                                <td>会议室名称:</td>
                                <td>
                                    <input id="capacity" type="text" value="${requestScope.room.roomname }" maxlength="20"/>
                                </td>
                            </tr>
                            <tr>
                                <td>最多容纳人数：</td>
                                <td>
                                    <input id="${requestScope.room.capacity }" type="text" value="15"/>
                                </td>
                            </tr>
                            <tr>
                                <td>当前状态：</td>
                                <td>
                                	<c:if test="${requestScope.room.status eq 0 }">
                                		<input type="radio" id="status" name="status" checked="checked" value="0"/><label for="status">可用</label>
                                    	<input type="radio" id="status" name="status"/><label for="status" value="1">不可用</label>
                                		
                                	</c:if>
                                	
                                	<c:if test="${requestScope.room.status eq 1 }">
                                		<input type="radio" id="status" name="status"  value="0"/><label for="status">可用</label>
                                    	<input type="radio" id="status" name="status" checked="checked" /><label for="status" value="1">不可用</label>
                                		
                                	</c:if>
                                	
                                  
                                </td>
                            </tr>
                            <tr>
                                <td>备注：</td>
                                <td>                                                                                   
                                    <textarea id="description" maxlength="200" rows="5" cols="60" >${requestScope.room.desciption }</textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="command">
                                    <input type="submit" value="确认修改" class="clickbutton"/>
                                    <input type="button" class="clickbutton" value="返回" onclick="window.history.back();"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
      
    </body>
</html>