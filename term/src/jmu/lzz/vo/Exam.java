package jmu.lzz.vo;

public class Exam {
	private String studyID;
	private String examName;
	private Double examRadio;
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
	public Double getExamRadio() {
		return examRadio;
	}
	public void setExamRadio(Double examRadio) {
		this.examRadio = examRadio;
	}
	@Override
	public String toString() {
		return "Exam [studyID=" + studyID + ", examName=" + examName + ", examRadio=" + examRadio + "]";
	}
}
