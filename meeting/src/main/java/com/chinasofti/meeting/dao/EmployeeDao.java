package com.chinasofti.meeting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	//向表employee中添加一行记录
	public void insert(Employee employee) {
		conn = ConnectionFactory.getConnection();
		String sql = "insert into employee"
				+ "(employeename,username,phone,email,status,departmentid,password,role)"
				+ "values(?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, employee.getEmployeename());
			pstmt.setString(2, employee.getUsername());
			pstmt.setString(3, employee.getPhone());
			pstmt.setString(4, employee.getEmail());
			pstmt.setString(5, employee.getStatus());
			pstmt.setInt(6, employee.getDepartmentid());
			pstmt.setString(7, employee.getPassword());
			pstmt.setString(8, employee.getRole());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(conn, pstmt, null);
		}
		
		
	}

	public List<Employee> selectAllEmployee() {
		conn = ConnectionFactory.getConnection();
		List<Employee> employeeList = new ArrayList<Employee>();
		Employee employee = null;
		//查询等待审核的普通用户
		String sql = "select * from employee where role='2' and status='0'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
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
				employeeList.add(employee);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(conn, pstmt, rs);
		}
		
		return employeeList;
	}
	
	
	public static void main(String[] args) {
		EmployeeDao dao = new EmployeeDao();
		//Employee emp = dao.selectByNamePwd("lilei", "123");
		//Employee employee = dao.selectByUserName("alibaba");
		//Employee e = new Employee(null, "呼呼啦", "hhl", "123", 1, "@@@", "1232132132", "0", "2");
		//dao.insert(e);
		List<Employee> list = dao.selectAllEmployee();
		
		for(Employee emp:list) {
			
			System.out.println(emp);
		}
		
		
		//System.out.println(employee);
	}
}
