package com.chinasofti.meeting.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinasofti.meeting.service.EmployeeService;
import com.chinasofti.meeting.vo.Employee;

public class LoginServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户名和密码   
		String username = request.getParameter("username");
		String password = request.getParameter("pwd");
		
		//获取免密登录天数
		String timelength = request.getParameter("timelength");
		
		int days = 0;
		if(timelength != null) {
			days = Integer.parseInt(timelength);
		}
		
		if(days != 0) {
			Cookie usernameCookie =  new Cookie("username", username);
			Cookie passwordCookie = new Cookie("password",password);
			usernameCookie.setMaxAge(days*24*3600);
			passwordCookie.setMaxAge(days*24*3600);
			response.addCookie(usernameCookie);
			response.addCookie(passwordCookie);
		}
		
		
		//调用service方法
		EmployeeService service = new EmployeeService();
		int flag = service.login(username, password);

		if(flag == 1) {
			//TODO 访问登录控制，网站访问量统计
			
			//获取session对象
			HttpSession  session = request.getSession();
			
			//两种角色role  --  管理员（看到全部内容）     普通该员工（看到员工内容）
			Employee loginedEmployee = service.getLoginedEmployee();//获取登录员工信息
			String role = loginedEmployee.getRole();//获取登录员工角色
			
			//将个人信息存放到session中
			session.setAttribute("employeename", loginedEmployee.getEmployeename());
			
			
			if("1".equals(role)) {//管理员
				request.getRequestDispatcher("admin/adminindex.jsp").forward(request, response);
			}
			if("2".equals(role)) {//员工页面
				request.getRequestDispatcher("employee/employeeindex.jsp").forward(request, response);
			}
			
		}else {
			
			if(flag == 0) {//等待审核
				request.setAttribute("msg", "正在审核请等待。");
			}
			if(flag == 2) {//审核未通过
				request.setAttribute("msg", "审核未通过请重新注册，或联系管理员。");
			}
			if(flag == 3) {//用户名或密码错误
				request.setAttribute("msg", "用户名或密码错误，请重试。");
			}
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		
	}

}