package com.chinasofti.meeting.service;

import java.util.List;

import com.chinasofti.meeting.dao.DepartmentDao;
import com.chinasofti.meeting.vo.Department;

public class DepartmentService {

	private DepartmentDao dao = new DepartmentDao();
	
	public List<Department> viewAllDepartments() {
		
		return dao.selectAll();
	}

}
