package com.chinasofti.meeting.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.dao.MeetingDao;
import com.chinasofti.meeting.dao.MeetingParticipantsDao;
import com.chinasofti.meeting.vo.Employee;
import com.chinasofti.meeting.vo.Meeting;

public class ViewbookingDtailServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("11111111111111111111");
		String meetingid = request.getParameter("meetingid");
		System.out.println(meetingid);
		//查找相关会议
		MeetingDao meetingDao = new MeetingDao();
		Meeting meeting = meetingDao.selectById(Integer.parseInt(meetingid));
		//查找参会人员
		MeetingParticipantsDao parDao = new MeetingParticipantsDao();
		List<Employee> employeesList = parDao.selectAllEmployeesByMeetingid(meeting.getMeetingid());
		
		
		request.setAttribute("employeesList", employeesList);
		request.setAttribute("meeting", meeting);
		request.getRequestDispatcher("mymeetingdetails.jsp").forward(request, response);
	}

}