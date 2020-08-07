package com.chinasofti.meeting.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String rand = request.getParameter("rand");
		PrintWriter pw = response.getWriter();
		
		String nrand = (String) request.getSession().getAttribute("rand");
		if(rand.equals(nrand)) {
			pw.print("success");
		}else {
			pw.print("fail");
		}
		
	}

}
