package com.chinasofti.meeting.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.service.DepartmentService;

public class AddDeleteDepartmentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		//获取code的值
		String code = request.getParameter("code");
		String departmentname = request.getParameter("departmentname");
		//System.out.println(code+","+departmentname);
		DepartmentService service = new DepartmentService();
		//根据请求添加部门
		if(code != null && "add".equals(code)) {
			service.addDepartment(departmentname);
		}
		//根据请求 删除部门
		if(code != null && "delete".equals(code)) {
			service.deleteDepartment(Integer.parseInt(request.getParameter("departmentid")));
		}
		//修改
		if(code != null && "edit".equals(code)) {
			service.updateNameById(Integer.parseInt(request.getParameter("departmentid")),departmentname);
			
		}
		
		
		//跳转页面
		request.getRequestDispatcher("viewAllDepartmentServlet?code=viewalldepartments").forward(request, response);
		
	}

}
