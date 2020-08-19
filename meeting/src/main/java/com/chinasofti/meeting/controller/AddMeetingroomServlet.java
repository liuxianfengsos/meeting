package com.chinasofti.meeting.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.service.MeetingRoomService;
import com.chinasofti.meeting.vo.MeetingRoom;

public class AddMeetingroomServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		Integer roomnum = Integer.parseInt(request.getParameter("roomnum"));
		Integer capacity = Integer.parseInt(request.getParameter("capacity"));
		String roomname = request.getParameter("roomname");
		String status = request.getParameter("status");
		String desciption = request.getParameter("description");
		
		MeetingRoom room = new MeetingRoom();
		room.setCapacity(capacity);
		room.setRoomnum(roomnum);
		room.setRoomname(roomname);
		room.setStatus(status);
		room.setDesciption(desciption);
		
		MeetingRoomService service = new MeetingRoomService();
		service.addMeetingroom(room);
		
		request.getRequestDispatcher("viewAllMeetingRoomsServlet").forward(request, response);
		
	}

}
