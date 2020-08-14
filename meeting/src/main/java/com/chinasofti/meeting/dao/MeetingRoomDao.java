package com.chinasofti.meeting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chinasofti.meeting.util.ConnectionFactory;

import com.chinasofti.meeting.vo.MeetingRoom;

public class MeetingRoomDao {
	
	private Connection conn;
	
	//根据id查询会议室
	public MeetingRoom selectByRoomId(int roomid) {
		conn = ConnectionFactory.getConnection();
		MeetingRoom meetingRoom = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		
		String sql = "select * from meetingroom where roomid=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				meetingRoom = new MeetingRoom();
				meetingRoom.setRoomid(Integer.parseInt(rs.getString("roomid")));
				meetingRoom.setRoomnum(Integer.parseInt(rs.getString("roomnum")));
				meetingRoom.setCapacity(Integer.parseInt(rs.getString("capacity")));
				meetingRoom.setRoomname(rs.getString("roomname"));
				meetingRoom.setStatus(rs.getString("status"));
				meetingRoom.setDesciption(rs.getString("description"));
				
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(conn, pstmt, rs);
		}
		
		
		return meetingRoom;
	}
	
	public static void main(String[] args) {
		MeetingRoomDao dao = new MeetingRoomDao();
		MeetingRoom m = dao.selectByRoomId(8);
		System.out.println(m);
	}

}
