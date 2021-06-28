package jmu.lzz.vo;

public class Elect {
	private String studyID;
	private String stuID;
	private String electStasus;
	private String finalGrade;
	public String getStudyID() {
		return studyID;
	}
	public void setStudyID(String studyID) {
		this.studyID = studyID;
	}
	public String getStuID() {
		return stuID;
	}
	public void setStuID(String stuID) {
		this.stuID = stuID;
	}
	public String getElectStasus() {
		return electStasus;
	}
	public void setElectStasus(String electStasus) {
		this.electStasus = electStasus;
	}
	public String getFinalGrade() {
		return finalGrade;
	}
	public void setFinalGrade(String finalGrade) {
		this.finalGrade = finalGrade;
	}
	@Override
	public String toString() {
		return "Elect [studyID=" + studyID + ", stuID=" + stuID + ", electStasus=" + electStasus + ", finalGrade="
				+ finalGrade + "]";
	}
}
