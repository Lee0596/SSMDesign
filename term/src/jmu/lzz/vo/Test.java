package jmu.lzz.vo;

public class Test {
	private String stuID;
	private String studyID;
	private String examName;
	private Double testGrade;
	public String getStuID() {
		return stuID;
	}
	public void setStuID(String stuID) {
		this.stuID = stuID;
	}
	public String getStudyID() {
		return studyID;
	}
	public void setStudyID(String studyID) {
		this.studyID = studyID;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public Double getTestGrade() {
		return testGrade;
	}
	public void setTestGrade(Double testGrade) {
		this.testGrade = testGrade;
	}
	@Override
	public String toString() {
		return "Test [stuID=" + stuID + ", studyID=" + studyID + ", examName=" + examName + ", testGrade=" + testGrade
				+ "]";
	}
}
