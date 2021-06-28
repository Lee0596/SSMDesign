package jmu.lzz.vo;

public class Cou {
	private String couID;
	private String roomID;
	private String couName;
	private Double couCredit;
	private Double couHours;
	private String couStyle;
	public String getCouID() {
		return couID;
	}
	public void setCouID(String couID) {
		this.couID = couID;
	}
	public String getRoomID() {
		return roomID;
	}
	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}
	public String getCouName() {
		return couName;
	}
	public void setCouName(String couName) {
		this.couName = couName;
	}
	public Double getCouCredit() {
		return couCredit;
	}
	public void setCouCredit(Double couCredit) {
		this.couCredit = couCredit;
	}
	public Double getCouHours() {
		return couHours;
	}
	public void setCouHours(Double couHours) {
		this.couHours = couHours;
	}
	public String getCouStyle() {
		return couStyle;
	}
	public void setCouStyle(String couStyle) {
		this.couStyle = couStyle;
	}
	@Override
	public String toString() {
		return "Cou [couID=" + couID + ", roomID=" + roomID + ", couName=" + couName + ", couCredit=" + couCredit
				+ ", couHours=" + couHours + ", couStyle=" + couStyle + "]";
	}
}
