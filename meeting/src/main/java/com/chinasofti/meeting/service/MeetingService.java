package com.chinasofti.meeting.service;

import java.sql.Timestamp;
import java.util.List;

import com.chinasofti.meeting.dao.MeetingDao;
import com.chinasofti.meeting.dao.MeetingParticipantsDao;
import com.chinasofti.meeting.vo.Employee;
import com.chinasofti.meeting.vo.Meeting;

public class MeetingService {

	private MeetingDao meetingDao = new MeetingDao();
	
	private MeetingParticipantsDao parDao = new MeetingParticipantsDao();
	
	public List<Meeting> viewMyBookingInfo(Integer reservationistid) {
		
		return meetingDao.selectAllMeetingsByReserId(reservationistid);
	}
	
	public static void main(String[] args) {
		MeetingService service = new MeetingService();
		List<Meeting> l = service.viewMyBookingInfo(8);
		for(Meeting m:l) {
			System.out.println(m);
		}
	}

	public void cancelMeeting(Integer meetingid) {
		meetingDao.update(meetingid,"1",new Timestamp(System.currentTimeMillis()));
		
	}

	public List<Meeting> viewMymeetingInfo(Integer employeeid) {
		
		return parDao.selectAllMeetingByPartId(employeeid);
	}
	//会议定制
	public void bookMeeting(Meeting meeting, List<Integer> employeeidList) {
		//保存会议
		Integer meetingid = meetingDao.insert(meeting);
		//System.out.println();
		//保存该会议相关人员  meetingid--employeeid
		for(Integer employeeid :employeeidList) {
			System.out.println(employeeid+","+meetingid);
			parDao.insert(meetingid,employeeid);
		}
		
		
	}

}
