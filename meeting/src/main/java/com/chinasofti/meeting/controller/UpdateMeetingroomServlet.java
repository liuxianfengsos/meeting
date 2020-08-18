package com.chinasofti.meeting.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.service.MeetingRoomService;
import com.chinasofti.meeting.vo.MeetingRoom;

public class UpdateMeetingroomServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Integer roomid = Integer.parseInt(request.getParameter("roomid"));
		Integer roomnum = Integer.parseInt(request.getParameter("roomnum"));
		String roomname = request.getParameter("roomname");
		Integer capacity =  Integer.parseInt(request.getParameter("capacity"));
		
		String status = request.getParameter("status");
		String description = request.getParameter("description");
		
		MeetingRoom room = new MeetingRoom();
		room.setRoomid(roomid);
		room.setRoomnum(roomnum);
		room.setCapacity(capacity);
		room.setDesciption(description);
		room.setRoomname(roomname);
		room.setStatus(status);
		
		System.out.println(room);
		
		MeetingRoomService service = new MeetingRoomService();
		service.updateMeetingroom(room);
		request.getRequestDispatcher("viewAllMeetingRoomsServlet").forward(request, response);
	}

}
