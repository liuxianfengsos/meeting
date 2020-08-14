package com.chinasofti.meeting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinasofti.meeting.util.ConnectionFactory;
import com.chinasofti.meeting.vo.Meeting;

public class MeetingDao {
	
	private Connection conn;
	
	//查询某员工预定的所有会议
	public List<Meeting> selectAllMeetingsByReserId(int reservationistid){
		conn = ConnectionFactory.getConnection();
		List<Meeting> meetingsList = new ArrayList<>();
		Meeting meeting = null;
		String sql = "select * from meeting where reservationistid="+reservationistid;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
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
				meetingsList.add(meeting);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(conn, pstmt, rs);
		}
		
		
		return meetingsList;
	}
	
	public static void main(String[] args) {
		MeetingDao dao = new MeetingDao();
		List<Meeting> l = dao.selectAllMeetingsByReserId(8);
		for(Meeting m:l) {
			System.out.println(m);
		}
	}
	
}
