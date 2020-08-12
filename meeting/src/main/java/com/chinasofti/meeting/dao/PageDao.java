package com.chinasofti.meeting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinasofti.meeting.util.ConnectionFactory;
import com.chinasofti.meeting.vo.Employee;

public class PageDao {
	
	private static Connection conn;
	private static ResultSet rs;
	private static PreparedStatement ps;
	//每页显示的数据条数
	private static int pagesize = 3;
	
	static {
		conn = ConnectionFactory.getConnection();
	}
	
	//遍历当亲前页面信息
	public static ArrayList<Employee> getEmpsList(int pageNo){
		ArrayList<Employee> list = new ArrayList<Employee>();
		//pagesize = 3;//每页显示3条数据
		int begin = (pageNo - 1)*pagesize;
		int end = pagesize;
		
		
		String sql = "select * from employee where role='2' and status='0' LIMIT ?,?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, begin);
			ps.setInt(2, end);
			rs = ps.executeQuery();
			while(rs.next()) {
				Employee employee = new Employee();
				employee.setEmployeeid(rs.getInt("employeeid"));
				employee.setEmployeename(rs.getString("employeename"));
				employee.setUsername(rs.getString("username"));
				employee.setPhone(rs.getString("phone"));
				employee.setEmail(rs.getString("email"));
				employee.setStatus(rs.getString("status"));
				employee.setDepartmentid(rs.getInt("departmentid"));
				employee.setPassword(rs.getString("password"));
				employee.setRole(rs.getString("role"));
				list.add(employee);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//ConnectionFactory.closeConnection(conn, ps, rs);
		}
		
		
		return list;
	}
	//获取总信息的条目数 （计算总页数）
	public static int getTotalPage() {
		int totalPage = 0;//总页数
		int totalCont = 0;//总条目数
		String sql = "select count(employeeid) from employee where role='2' and status='0'";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				totalCont = rs.getInt(1);
				//System.out.println("totalCont"+totalCont);
				totalPage = (totalCont-1)/pagesize+1;
				            // 5条                              2 条                  页？
				            //   2+1 = 3  
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return totalPage;
	}
	
	//通过审核
	public static void updateStatus(Integer employeeid, String status) {
		String sql = "update employee set status=? where employeeid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, employeeid);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		//int i = getTotalPage();
		//System.out.println(i);
		updateStatus(43,"1");
	}
}
