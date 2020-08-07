package com.chinasofti.meeting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinasofti.meeting.util.ConnectionFactory;
import com.chinasofti.meeting.vo.Department;

public class DepartmentDao {

	private Connection conn;
	
	public List<Department> selectAll() {
		conn = ConnectionFactory.getConnection();
		List<Department> departmentList = new ArrayList<Department>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from department";
		Department  department;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				department = new Department();
				department.setDepartmentid(rs.getString("departmentid"));
				department.setDepartmentname(rs.getString("departmentname"));
				
				departmentList.add(department);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(conn, pstmt, rs);
		}
		
		return departmentList;
	}
	
	public static void main(String[] args) {
		DepartmentDao dao = new DepartmentDao();
		List<Department> departmentList = dao.selectAll();
		for(Department d:departmentList) {
			System.out.println(d);
		}
	}
}
