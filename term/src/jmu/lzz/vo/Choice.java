package jmu.lzz.vo;

public class Choice {
	private String studyID;
	private String classID;
	private String termDate;
	public String getStudyID() {
		return studyID;
	}
	public void setStudyID(String studyID) {
		this.studyID = studyID;
	}
	public String getClassID() {
		return classID;
	}
	public void setClassID(String classID) {
		this.classID = classID;
	}
	public String getTermDate() {
		return termDate;
	}
	public void setTermDate(String termDate) {
		this.termDate = termDate;
	}
	@Override
	public String toString() {
		return "Choice [studyID=" + studyID + ", classID=" + classID + ", termDate=" + termDate + "]";
	}
}
