package com.chinasofti.meeting.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.service.MeetingService;

public class CancelMeetingServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String mid = request.getParameter("meetingid");
		//System.out.println(meetingid);
		Integer meetingid = Integer.parseInt(mid);
		MeetingService service = new MeetingService();
		service.cancelMeeting(meetingid);
		request.getRequestDispatcher("viewMyBookingServlet").forward(request, response);
		
		
	}

}
