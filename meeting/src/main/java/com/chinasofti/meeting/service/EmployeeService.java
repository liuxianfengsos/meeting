package com.chinasofti.meeting.service;

import com.chinasofti.meeting.dao.EmployeeDao;
import com.chinasofti.meeting.vo.Employee;

public class EmployeeService {
	
	private EmployeeDao dao = new EmployeeDao();
	
	private Employee loginedEmployee;//登录的员工
	
	
	public Employee getLoginedEmployee() {
		return loginedEmployee;
	}
	public void setLoginedEmployee(Employee loginedEmployee) {
		this.loginedEmployee = loginedEmployee;
	}
	
	public int login(String username,String pwd) {
		int flag = 3;//用户名密码错误
		Employee employee = dao.selectByNamePwd(username, pwd);
		
		if(employee != null) {
			loginedEmployee = employee;//记录登录员工的信息;
			String status = employee.getStatus();
			if(status != null && "1".equals(status)) {
				flag = 1;//审核通过
			}
			if(status != null && "0".equals(status)) {
				flag = 0;//等待审核
			}
			if(status != null && "2".equals(status)) {
				flag = 2;//审核未通过
			}
		}
		
		return flag;
	}
	public static void main(String[] args) {
		EmployeeService service = new EmployeeService();
		int flag = service.login("lilei", "123");
		System.out.println(flag);
	}
	
}