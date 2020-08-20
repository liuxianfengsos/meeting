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

public class ViewMymeetingDetailServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer meetingid = Integer.parseInt(request.getParameter("meetingid"));
		//System.out.println(meetingid);
		MeetingDao meetingDao = new MeetingDao();
		MeetingParticipantsDao parDao = new MeetingParticipantsDao();
		//获取指定会议的信息
		Meeting meeting = meetingDao.selectById(meetingid);
		//获取参会人员信息
		List<Employee> employeesList = parDao.selectAllEmployeesByMeetingid(meetingid);
		request.setAttribute("meeting", meeting);
		request.setAttribute("employeesList", employeesList);
		request.getRequestDispatcher("mymeetingdetails.jsp").forward(request, response);
	}

}
