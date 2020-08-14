package com.chinasofti.meeting.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.chinasofti.meeting.service.MeetingRoomService;
import com.chinasofti.meeting.service.MeetingService;
import com.chinasofti.meeting.vo.Meeting;

public class ViewMyBookingServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("========reservationistid===");
		//获取预定者（登陆者）的id
		HttpSession session = request.getSession();
		Integer reservationistid =  (Integer)session.getAttribute("employeeid");
		System.out.println("reservationistid==="+reservationistid);
		//创建会议的对象  会议室
		MeetingService service = new MeetingService();
		MeetingRoomService roomService = new MeetingRoomService();
		//获取全部会议信息
		List<Meeting> meetingsList = service.viewMyBookingInfo(reservationistid);
		//获取会议室名称
		List<String> rommsNameList = new ArrayList<String>();
		
		for(Meeting m:meetingsList) {
			rommsNameList.add(roomService.viewOnMeetingRoom(m.getRoomid()).getRoomname());
		}
		
		Map<Meeting,String> map = new HashMap<Meeting,String>();
		for(int i=0;i<meetingsList.size();i++) {
			map.put(meetingsList.get(i), rommsNameList.get(i));
		}
		//检查 roomname   roomid对应关系
		Set<Entry<Meeting, String>>  ent = map.entrySet();
		for(Entry<Meeting, String> e:ent) {
			Meeting meeting = e.getKey();
			String roomname = e.getValue();
			System.out.println(meeting.getRoomid()+"===="+roomname);
		}
		
		request.setAttribute("map", map);
		
		request.getRequestDispatcher("mybookings.jsp").forward(request, response);
	}

}
