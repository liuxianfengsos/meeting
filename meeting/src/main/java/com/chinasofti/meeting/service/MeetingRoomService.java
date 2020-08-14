package com.chinasofti.meeting.service;

import com.chinasofti.meeting.dao.MeetingRoomDao;
import com.chinasofti.meeting.vo.MeetingRoom;

public class MeetingRoomService {
	
	private MeetingRoomDao meetingroomdao = new MeetingRoomDao();
	
	public MeetingRoom viewOnMeetingRoom(int roomid) {
		
		return meetingroomdao.selectByRoomId(roomid);
	}

}
