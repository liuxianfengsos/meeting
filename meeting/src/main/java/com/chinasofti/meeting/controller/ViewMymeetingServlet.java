package com.chinasofti.meeting.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinasofti.meeting.dao.EmployeeDao;
import com.chinasofti.meeting.service.MeetingRoomService;
import com.chinasofti.meeting.service.MeetingService;
import com.chinasofti.meeting.vo.Meeting;

public class ViewMymeetingServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取当前登陆者的信息
		HttpSession session = request.getSession();  
		String empid = session.getAttribute("employeeid").toString();
		System.out.println("empid..........."+empid);
		Integer employeeid = Integer.parseInt(empid);
		System.out.println("employeeid..........."+employeeid);
		MeetingService service = new MeetingService();
		//获取会议信息
		List<Meeting> meetingsList = service.viewMymeetingInfo(employeeid);
		
		//会议室
		MeetingRoomService  roomservice = new MeetingRoomService();
		List<String[]> nameList = new ArrayList<String[]>();
		//人员信息
		EmployeeDao empDao = new EmployeeDao();
		
		for(Meeting m:meetingsList) {
			//获取当前会议的预定者姓名
			String empName = empDao.selectById(m.getReservationistid()).getEmployeename();
			//获取当前会议的会议室名称
			String roomName = roomservice.viewOnMeetingRoom(m.getRoomid()).getRoomname();
			nameList.add(new String[] {empName,roomName});
		}
		
		Map<Meeting,String[]> map = new HashMap<Meeting, String[]>();
		for(int i=0;i<meetingsList.size();i++) {
			map.put(meetingsList.get(i), nameList.get(i));
		}
		
		request.setAttribute("map", map);
		request.getRequestDispatcher("mymeetings.jsp").forward(request, response);
	}

}
