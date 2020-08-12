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

public class ViewAllEmployeesServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		//System.out.println("code="+code);
		EmployeeDao employeeDao = new EmployeeDao();
		List<Employee> employeesList = employeeDao.selectAllEmployee();
		request.setAttribute("employeesList", employeesList);
		if(code != null&&"approve".equals(code)) {
			request.getRequestDispatcher("approveaccount.jsp").forward(request, response);
			
		}
	}

}
