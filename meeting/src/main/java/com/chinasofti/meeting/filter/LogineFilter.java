package com.chinasofti.meeting.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinasofti.meeting.service.EmployeeService;
import com.chinasofti.meeting.vo.Employee;

public class LogineFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		HttpSession session = request.getSession();
		
		EmployeeService service = new EmployeeService();
		//Employee employee = service.getLoginedEmployee();
		String employeename = (String) session.getAttribute("employeename");
		
		//System.out.println("LogineFilter............."+employee);
		if(employeename == null) {
			session.setAttribute("msg", "请先登录");
			response.sendRedirect("../login.jsp");
		}
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		
		
	}
	
}
