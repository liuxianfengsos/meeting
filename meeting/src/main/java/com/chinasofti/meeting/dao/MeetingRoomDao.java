package com.chinasofti.meeting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	public List<MeetingRoom> selectAllMeetingRooms() {
		conn = ConnectionFactory.getConnection();
		List<MeetingRoom> list = new ArrayList<MeetingRoom>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from meetingroom ";
		MeetingRoom meetingroom = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				meetingroom = new MeetingRoom();
				meetingroom.setRoomid(Integer.parseInt(rs.getString("roomid")));
				meetingroom.setRoomnum(Integer.parseInt(rs.getString("roomnum")));
				meetingroom.setCapacity(Integer.parseInt(rs.getString("capacity")));
				meetingroom.setRoomname(rs.getString("roomname"));
				meetingroom.setStatus(rs.getString("status"));
				meetingroom.setDesciption(rs.getString("description"));
				list.add(meetingroom);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(conn, pstmt, rs);
		}
		
		return list;
	}

	public void updateMeetingroom(MeetingRoom room) {
		conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		String sql = "update meetingroom set roomnum=?,roomname=?"
				+ ",capacity=?,status=?,description=? where roomid="+room.getRoomid();
		
		System.out.println("dao"+room);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, room.getRoomnum());
			pstmt.setString(2, room.getRoomname());
			pstmt.setInt(3, room.getCapacity());
			pstmt.setString(4, room.getStatus());
			pstmt.setString(5, room.getDesciption());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(conn, pstmt, null);
		}
		
	}

	public void insert(MeetingRoom room) {
		conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into meetingroom "
				+ "(roomnum,roomname,capacity,status,description)"
				+ "values(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, room.getRoomnum());
			pstmt.setString(2, room.getRoomname());
			pstmt.setInt(3, room.getCapacity());
			pstmt.setString(4, room.getStatus());
			pstmt.setString(5, room.getDesciption());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(conn, pstmt, null);
		}
		
		
	}

}
