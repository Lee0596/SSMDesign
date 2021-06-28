package jmu.lzz.vo;

public class Student {
	private String stuID;
	private String classID;
	private String stuName;
	private String stuPassword;
	private String joinTime;
	public String getStuID() {
		return stuID;
	}
	public void setStuID(String stuID) {
		this.stuID = stuID;
	}
	public String getClassID() {
		return classID;
	}
	public void setClassID(String classID) {
		this.classID = classID;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuPassword() {
		return stuPassword;
	}
	public void setStuPassword(String stuPassword) {
		this.stuPassword = stuPassword;
	}
	public String getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(String joinTime) {
		this.joinTime = joinTime;
	}
	@Override
	public String toString() {
		return "Student [stuID=" + stuID + ", classID=" + classID + ", stuName=" + stuName + ", stuPassword="
				+ stuPassword + ", joinTime=" + joinTime + "]";
	}
}
