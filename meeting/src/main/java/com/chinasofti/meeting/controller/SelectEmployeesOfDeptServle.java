package com.chinasofti.meeting.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.dao.EmployeeDao;
import com.chinasofti.meeting.vo.Employee;

public class SelectEmployeesOfDeptServle extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer departmentid = Integer.parseInt(request.getParameter("departmentid"));
		EmployeeDao dao = new EmployeeDao();
		List<Employee> employeesList = dao.selectEmployeesByDept(departmentid);
		//将人员响应到页面
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<employees>");
		for(Employee e:employeesList) {
			out.println("<option>");
			out.println("<value>"+e.getEmployeeid()+"</value>");
			out.println("<text>"+e.getEmployeename()+"</text>");
			out.println("</option>");
		}
		out.println("</employees>");
		out.close();
		
	}

}
