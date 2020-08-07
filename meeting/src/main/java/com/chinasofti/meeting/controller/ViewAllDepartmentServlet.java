package com.chinasofti.meeting.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.DebugGraphics;

import com.chinasofti.meeting.service.DepartmentService;
import com.chinasofti.meeting.vo.Department;

public class ViewAllDepartmentServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DepartmentService departmentService = new DepartmentService();
		List<Department> departmentList = departmentService.viewAllDepartments();
		request.setAttribute("departmentList", departmentList);
		
		String code = request.getParameter("code");
		if(code!=null && "regist".equals(code)) {
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		
		
	}

}
