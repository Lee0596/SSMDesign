package jmu.lzz.vo;

public class College {
	private String collegeID;
	private String collegeName;
	public String getCollegeID() {
		return collegeID;
	}
	public void setCollegeID(String collegeID) {
		this.collegeID = collegeID;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	@Override
	public String toString() {
		return "College [collegeID=" + collegeID + ", collegeName=" + collegeName + "]";
	}
}
