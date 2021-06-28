package jmu.lzz.vo;

public class Room {
	private String roomID;
	private String collegeID;
	private String roomName;
	public String getRoomID() {
		return roomID;
	}
	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}
	public String getCollegeID() {
		return collegeID;
	}
	public void setCollegeID(String collegeID) {
		this.collegeID = collegeID;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	@Override
	public String toString() {
		return "Room [roomID=" + roomID + ", collegeID=" + collegeID + ", roomName=" + roomName + "]";
	}
}
