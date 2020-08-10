package com.chinasofti.meeting.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.dao.DepartmentDao;
import com.chinasofti.meeting.service.EmployeeService;
import com.chinasofti.meeting.vo.Department;
import com.chinasofti.meeting.vo.Employee;

public class RegistServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//获取表单参数
		String employeename = request.getParameter("employeename");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirm = request.getParameter("confirm");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		Integer deptid = Integer.parseInt(request.getParameter("deptid"));
		
		//两次输入密码不一致
		/*
		 * if(!password.equals(confirm)) { request.setAttribute("msg", "两次密码出入不匹配");
		 * request.getRequestDispatcher("register.jsp").forward(request, response); }
		 */
		//System.out.println("两次密码一致吗"+password.equals(confirm));
		//创建对象 employee 将请求参数进行封装
		Employee employee = new Employee(null, employeename, username, password, deptid, email, phone, "0", "2");
		
		EmployeeService service = new EmployeeService();
		//调用业务逻辑进行存储；
		Integer flag = service.regist(employee);
		//System.out.println(flag == 1);
		if(flag == 1) {
			//System.out.println("111111111111111111");
			request.setAttribute("msg", "注册成功，正在审核...");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else {
			//注册失败？  -页面  --怎么跳转？
			request.setAttribute("msg", "用户名已存在，请重新注册！");
			DepartmentDao dao = new DepartmentDao();
			List<Department> departmentList = dao.selectAll();
			request.setAttribute("departmentList", departmentList);
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		
	}

}
