package com.chinasofti.meeting.vo;

public class Employee {
	
	private Integer employeeid;
	private String  employeename;
	private String  username;
	private String  password;
	private Integer departmentid;
	private String  email;
	private String  phone;
	private String  status; //0待审核  1审核通过   2审核未通过s  
	private String  role;   //1管理员   2员工
	
	
	
	public Integer getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(Integer employeeid) {
		this.employeeid = employeeid;
	}
	public String getEmployeename() {
		return employeename;
	}
	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Employee [employeeid=" + employeeid + ", employeename=" + employeename + ", username=" + username
				+ ", password=" + password + ", departmentid=" + departmentid + ", email=" + email + ", phone=" + phone
				+ ", status=" + status + ", role=" + role + "]";
	}
	
	
	
}
