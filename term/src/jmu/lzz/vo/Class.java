package jmu.lzz.vo;

public class Class {
	private String classID;
	private String collegeID;
	private String className;
	private int classNum;
	public String getClassID() {
		return classID;
	}
	public void setClassID(String classID) {
		this.classID = classID;
	}
	public String getCollegeID() {
		return collegeID;
	}
	public void setCollegeID(String collegeID) {
		this.collegeID = collegeID;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getClassNum() {
		return classNum;
	}
	public void setClassNum(int classNum) {
		this.classNum = classNum;
	}
	@Override
	public String toString() {
		return "Class [classID=" + classID + ", collegeID=" + collegeID + ", className=" + className + ", classNum="
				+ classNum + "]";
	}
}
