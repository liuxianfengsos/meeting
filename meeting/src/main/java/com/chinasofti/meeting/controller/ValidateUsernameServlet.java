package com.chinasofti.meeting.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.dao.EmployeeDao;
import com.chinasofti.meeting.vo.Employee;

public class ValidateUsernameServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("1111111111111");
		boolean flag = true;
		String message = "";
		EmployeeDao dao = new EmployeeDao();
		//获取前端参数
		String username = request.getParameter("username");
		//查询是否已经存在该用户名
		Employee e = dao.selectByUserName(username);
		
		if(e == null) {
			message = "用户名可以使用";
		}else {
			flag = false;
			message = "用户名已经存在，请使用其他用户名";
		}
		
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = response.getWriter();
		response.setHeader("Cache-Control", "no-cache");
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<response>");
		out.println("<passed>"+Boolean.toString(flag)+"</passed>");
		out.println("<message>"+message+"</message>");
		out.println("</response>");
		out.close();
	}

}
