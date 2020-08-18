package com.chinasofti.meeting.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.service.MeetingRoomService;
import com.chinasofti.meeting.vo.MeetingRoom;

public class ViewAllMeetingRoomsServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查询全部的会议室
		MeetingRoomService service = new MeetingRoomService();
		List<MeetingRoom> list = service.viewAllMeetingRooms();
		request.setAttribute("meetingroomlist", list);		
		request.getRequestDispatcher("allmeetingrooms.jsp").forward(request, response);
	}

}
