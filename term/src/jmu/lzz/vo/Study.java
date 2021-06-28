package jmu.lzz.vo;

public class Study {
	private String studyID;
	private String teacherID;
	private String couID;
	private String studyStatus;
	private String studyWay;
	private String studyStyle;
	private Boolean studyEntry;
	private Boolean studyReport;
	private String deadLine;
	public String getStudyID() {
		return studyID;
	}
	public void setStudyID(String studyID) {
		this.studyID = studyID;
	}
	public String getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}
	public String getCouID() {
		return couID;
	}
	public void setCouID(String couID) {
		this.couID = couID;
	}
	public String getStudyStatus() {
		return studyStatus;
	}
	public void setStudyStatus(String studyStatus) {
		this.studyStatus = studyStatus;
	}
	public String getStudyWay() {
		return studyWay;
	}
	public void setStudyWay(String studyWay) {
		this.studyWay = studyWay;
	}
	public String getStudyStyle() {
		return studyStyle;
	}
	public void setStudyStyle(String studyStyle) {
		this.studyStyle = studyStyle;
	}
	public Boolean getStudyEntry() {
		return studyEntry;
	}
	public void setStudyEntry(Boolean studyEntry) {
		this.studyEntry = studyEntry;
	}
	public Boolean getStudyReport() {
		return studyReport;
	}
	public void setStudyReport(Boolean studyReport) {
		this.studyReport = studyReport;
	}
	public String getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}
	@Override
	public String toString() {
		return "Study [studyID=" + studyID + ", teacherID=" + teacherID + ", couID=" + couID + ", studyStatus="
				+ studyStatus + ", studyWay=" + studyWay + ", studyStyle=" + studyStyle + ", studyEntry=" + studyEntry
				+ ", studyReport=" + studyReport + ", deadLine=" + deadLine + "]";
	}
}
