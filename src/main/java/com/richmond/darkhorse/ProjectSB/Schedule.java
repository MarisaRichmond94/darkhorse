package com.richmond.darkhorse.ProjectSB;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Schedule implements Serializable{
	
	private static final long serialVersionUID = 8453756L;
	private String userID;
	private Map<Integer,LocalTime> startTimes,stopTimes;
	
	public Schedule(StaffMember staffMember) {
		this.userID = staffMember.getUserID();
		this.startTimes = new HashMap<Integer,LocalTime>();
		this.stopTimes = new HashMap<Integer,LocalTime>();
	}
	
	public void clear() {
		startTimes.clear();
		stopTimes.clear();
	}
	
	public void changeStartTime(int day,LocalTime startTime) {
		startTimes.replace(day,startTime);
	}
	
	public void changeEndTime(int day,LocalTime endTime) {
		stopTimes.replace(day, endTime);
	}

	public String getUserID() {
		return userID;
	}

	public Map<Integer,LocalTime> getStartTimes() {
		return startTimes;
	}

	public Map<Integer,LocalTime> getStopTimes() {
		return stopTimes;
	}
	
	public void setStartTimes(Map<Integer,LocalTime> startTimes) {
		this.startTimes = startTimes;
	}

	public void setStopTimes(Map<Integer,LocalTime> stopTimes) {
		this.stopTimes = stopTimes;
	}

	public StaffMember getStaffMember(String userID) {
		SpecialBeginnings sB = SpecialBeginnings.getInstance();
		Map<String,StaffMember> staffMembers = sB.getStaffMembers();
		if(staffMembers.containsKey(userID)) { return staffMembers.get(userID);}
		return null;
	}
	
}
