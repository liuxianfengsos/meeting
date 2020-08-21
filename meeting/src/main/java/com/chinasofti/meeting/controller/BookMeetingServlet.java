package com.chinasofti.meeting.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinasofti.meeting.dao.DepartmentDao;
import com.chinasofti.meeting.dao.MeetingRoomDao;
import com.chinasofti.meeting.service.MeetingService;
import com.chinasofti.meeting.vo.Department;
import com.chinasofti.meeting.vo.Employee;
import com.chinasofti.meeting.vo.Meeting;
import com.chinasofti.meeting.vo.MeetingRoom;

public class BookMeetingServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String code = request.getParameter("code");
		if(code!=null&&"prepare".equals(code)) {
			MeetingRoomDao roomDao = new MeetingRoomDao();
			DepartmentDao deptDao = new DepartmentDao();
			
			List<MeetingRoom> roomsList = roomDao.selectAllMeetingRooms();
			List<Department> deptsList = deptDao.selectAll();
			
			request.setAttribute("roomsList",roomsList);
			request.setAttribute("deptsList",deptsList);
			request.getRequestDispatcher("bookmeeting.jsp").forward(request, response);
			
		}
		
		if(code != null && "book".equals(code)) {
			//获取数据   meeting  employee
			//获取会议名称
			String meetingname = request.getParameter("meetingname");
			Integer roomid = Integer.parseInt(request.getParameter("roomid"));
			//获取定制会议者的id
			HttpSession session = request.getSession();
			Integer reservationistid = (Integer)session.getAttribute("employeeid");
			//预计参会人员
			Integer numofattendents = Integer.parseInt(request.getParameter("numofattendents"));

			Timestamp starttime = Timestamp.valueOf(request.getParameter("starttime"));
			Timestamp endtime = Timestamp.valueOf(request.getParameter("endtime"));
			
			Timestamp reservationtime = new Timestamp(System.currentTimeMillis());
			Timestamp canceledtime = null;
			
			String status = "0";
			String description = request.getParameter("description");
			
			
		    Meeting meeting = new Meeting(meetingname, roomid, reservationistid,
		    								numofattendents, starttime, endtime, reservationtime, canceledtime,
		    																					description, status);
			 
			//System.out.println("meeting---------"+meeting);
			String[] employeeId = request.getParameterValues("selSelectedEmployees");
			/*
			 * for(String str:employeeId) { System.out.println(str); }
			 */
			List<Integer> employeeidList = new ArrayList<Integer>();
			for(String str:employeeId) {
				employeeidList.add(Integer.parseInt(str));
			}
			MeetingService meetingservice = new MeetingService();
			//将定制的会议和参会人员写入数据库
			meetingservice.bookMeeting(meeting,employeeidList);
			
			request.getRequestDispatcher("viewMyBookingServlet").forward(request, response);
		}
		
		
	}

}
