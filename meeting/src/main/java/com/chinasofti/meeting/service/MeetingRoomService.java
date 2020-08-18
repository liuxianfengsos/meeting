package com.chinasofti.meeting.service;

import java.util.List;

import com.chinasofti.meeting.dao.MeetingRoomDao;
import com.chinasofti.meeting.vo.MeetingRoom;

public class MeetingRoomService {
	
	private MeetingRoomDao meetingroomdao = new MeetingRoomDao();
	
	public MeetingRoom viewOnMeetingRoom(int roomid) {
		
		return meetingroomdao.selectByRoomId(roomid);
	}

	public List<MeetingRoom> viewAllMeetingRooms() {
		
		return meetingroomdao.selectAllMeetingRooms();
	}

	public void updateMeetingroom(MeetingRoom room) {
		meetingroomdao.updateMeetingroom(room);
		
	}

}
