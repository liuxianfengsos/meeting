package com.chinasofti.meeting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chinasofti.meeting.util.ConnectionFactory;
import com.chinasofti.meeting.vo.Employee;

public class EmployeeDao {
	
	Connection conn ;
	
	public Employee selectByNamePwd(String username,String pwd) {
		Employee employee = null;
		
		conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from employee where username=? and password=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {
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
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(conn, pstmt, rs);
		}
		
		return employee;
	}
	
	public static void main(String[] args) {
		EmployeeDao dao = new EmployeeDao();
		//Employee emp = dao.selectByNamePwd("lilei", "123");
		Employee employee = dao.selectByUserName("alibaba");
		System.out.println(employee);
	}
	/**
	 *       根据用户名查找是否有对应的对象；
	 * @param username 用户名
	 * @return employee对象
	 */
	public Employee selectByUserName(String username) {
		conn = ConnectionFactory.getConnection();
		Employee employee = null;
		PreparedStatement pstmt = null;
		String sql = "select * from employee where username=?";
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if(rs.next()) {
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
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(conn, pstmt, rs);
		}
		
		return employee;
	}
	
}
