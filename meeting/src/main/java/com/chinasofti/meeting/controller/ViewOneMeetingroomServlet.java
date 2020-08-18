package com.chinasofti.meeting.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.service.MeetingRoomService;
import com.chinasofti.meeting.vo.MeetingRoom;

public class ViewOneMeetingroomServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer roomid = Integer.parseInt(request.getParameter("roomid"));
		
		//System.out.println(roomid);
		MeetingRoomService service  = new MeetingRoomService();
		//根据roomid查询到相关的会议室
		MeetingRoom room = service.viewOnMeetingRoom(roomid);
		
		request.setAttribute("room", room);
		
		request.getRequestDispatcher("meetingroomdetails.jsp").forward(request, response);
	}

}
