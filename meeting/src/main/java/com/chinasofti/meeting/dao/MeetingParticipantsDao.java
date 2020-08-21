package com.chinasofti.meeting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinasofti.meeting.util.ConnectionFactory;
import com.chinasofti.meeting.vo.Employee;
import com.chinasofti.meeting.vo.Meeting;

public class MeetingParticipantsDao {
	private Connection conn;
	
// 查询所有参会人员
	public List<Employee> selectAllEmployeesByMeetingid(int meetingid) {
		conn = ConnectionFactory.getConnection();
		List<Employee> employeelist = new ArrayList<Employee>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM employee, meetingparticipants WHERE employee.employeeid ="
				+ " meetingparticipants.employeeid AND meetingparticipants.meetingid = "+meetingid;
		Employee employee = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				employee = new Employee();
				employee.setEmployeeid(rs.getInt("employeeid"));
				employee.setEmployeename(rs.getString("employeename"));
				employee.setUsername(rs.getString("username"));
				employee.setPhone(rs.getString("phone"));
				employee.setEmail(rs.getString("email"));
				employee.setStatus(rs.getString("status"));
				employee.setDepartmentid(rs.getInt("departmentid"));
				employee.setPassword(rs.getString("password"));
				employee.setRole(rs.getString("role"));
				employeelist.add(employee);
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(conn, pstmt, rs);
		}
		
		
		return employeelist;
	}

	

	public List<Meeting> selectAllMeetingByPartId(Integer employeeid) {
		conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Meeting meeting = null;
		List<Meeting> meetingList = new ArrayList<Meeting>();
		String sql = "select * from meeting,meetingparticipants "
				+ " where meeting.meetingid=meetingparticipants.meetingid "
				+ " and meetingparticipants.employeeid ="+employeeid;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				meeting = new Meeting();
				meeting.setMeetingid(rs.getInt("meetingid"));
				meeting.setMeetingname(rs.getString("meetingname"));
				meeting.setRoomid(rs.getInt("roomid"));
				meeting.setReservationistid(rs.getInt("reservationistid"));
				meeting.setNumberofparticipants(rs.getInt("numberofparticipants"));
				meeting.setStarttime(rs.getTimestamp("starttime"));
				meeting.setEndtime(rs.getTimestamp("endtime"));
				meeting.setReservationtime(rs.getTimestamp("reservationtime"));
				meeting.setCanceledtime(rs.getTimestamp("canceledtime"));
				meeting.setDescription(rs.getString("description"));
				meeting.setStatus(rs.getString("status"));
				meetingList.add(meeting);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(conn, pstmt, rs);
		}
		
		return meetingList;
	}
	
	

	public void insert(Integer meetingid, Integer employeeid) {
		conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into meetingparticipants values(?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, meetingid);
			pstmt.setInt(2, employeeid);
			pstmt.executeUpdate();
			System.out.println("会议人员存储成功.........");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(conn, pstmt, null);
		}
		
	}
	
	
	public static void main(String[] args) {
		MeetingParticipantsDao dao = new MeetingParticipantsDao();
		//List<Employee> list = dao.selectAllEmployeesByMeetingid(28);
		//List<Meeting> list = dao.selectAllMeetingByPartId(8);
		/*
		 * for(Meeting e:list){ System.out.println(e); }
		 */
		
		//dao.insert();
		
	}


}
