package jmu.lzz.vo;

public class Dean {
	private String deanID;
	private String roomID;
	private String deanName;
	private String deanPassword;
	public String getDeanID() {
		return deanID;
	}
	public void setDeanID(String deanID) {
		this.deanID = deanID;
	}
	public String getRoomID() {
		return roomID;
	}
	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}
	public String getDeanName() {
		return deanName;
	}
	public void setDeanName(String deanName) {
		this.deanName = deanName;
	}
	public String getDeanPassword() {
		return deanPassword;
	}
	public void setDeanPassword(String deanPassword) {
		this.deanPassword = deanPassword;
	}
	@Override
	public String toString() {
		return "Dean [deanID=" + deanID + ", roomID=" + roomID + ", deanName=" + deanName + ", deanPassword="
				+ deanPassword + "]";
	}
}
