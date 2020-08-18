package com.chinasofti.meeting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
		//List<Meeting> l = dao.selectAllMeetingsByReserId(8);
		/*
		 * for(Meeting m:l) { System.out.println(m); }
		 */
		
		Meeting m = dao.selectById(28);
		System.out.println(m);
	}

	public Meeting selectById(Integer meetingid) {
		conn = ConnectionFactory.getConnection();
		Meeting meeting = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		String sql = "select * from meeting where meetingid="+meetingid;
		try {
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			
			while(rs.next()){
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
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(conn, st, rs);
		}
		
		
		
		return meeting;
	}
	/**
	 * 
	 * @param meetingid 会议id
	 * @param flag 会议注销标识
	 * @param timestamp 
	 */
	public  void update(Integer meetingid, String flag, Timestamp cancledtime) {
		
		conn = ConnectionFactory.getConnection();
		String sql = "update meeting set status=?,canceledtime=? where meetingid="+meetingid;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, flag);
			pstmt.setTimestamp(2, cancledtime);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(conn, pstmt, null);
		}

	}
	
}
