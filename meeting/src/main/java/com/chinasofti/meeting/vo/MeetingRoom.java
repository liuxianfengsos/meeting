package com.chinasofti.meeting.vo;

public class MeetingRoom {
	
	private Integer roomid;
	private Integer roomnum;
	private Integer capacity;
	private String roomname;
	private String status;
	private String desciption;
	
	public Integer getRoomid() {
		return roomid;
	}
	public void setRoomid(Integer roomid) {
		this.roomid = roomid;
	}
	public Integer getRoomnum() {
		return roomnum;
	}
	public void setRoomnum(Integer roomnum) {
		this.roomnum = roomnum;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public String getRoomname() {
		return roomname;
	}
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	@Override
	public String toString() {
		return "MeetingRomm [roomid=" + roomid + ", roomnum=" + roomnum + ", capacity=" + capacity + ", roomname="
				+ roomname + ", status=" + status + ", desciption=" + desciption + "]";
	}
	
	
	
}
