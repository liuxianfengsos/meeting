package com.chinasofti.meeting.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/meeting?useUnicode=true&characterEncoding=utf8"
					,"root","123");
			System.out.println("Connection success");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void closeConnection(Connection conn,PreparedStatement pstmt,ResultSet rs) {
		
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// 
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// 
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Connection con = getConnection();
		System.out.println(con);
	}
}
