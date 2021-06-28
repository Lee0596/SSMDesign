package jmu.lzz.service;

import java.util.List;

import jmu.lzz.vo.Choice;
import jmu.lzz.vo.Class;
import jmu.lzz.vo.Cou;
import jmu.lzz.vo.Elect;
import jmu.lzz.vo.Exam;
import jmu.lzz.vo.Student;
import jmu.lzz.vo.Study;
import jmu.lzz.vo.Test;

public interface TeacherService {
	public List<Choice> selectAllTermDateBypage(int before,int after,String teacherID) throws Exception ;
	public List<Study> selectAllStudyBypage(int before,int after,String termDate,String teacherID) throws Exception ;
	public List<Choice> selectAllClassBypage(int before,int after,String termDate,String studyID) throws Exception ;
	public int studyCount(String termDate,String teacherID) throws Exception ;
	public List<Integer> termDateCount(String teacherID) throws Exception ;
	public void examDelete(String studyID) throws Exception ;
	public void electDelete(String studyID,String stuID) throws Exception ;
	public void testDelete(String studyID,String stuID) throws Exception ;
	public void electDeleteAll(String studyID) throws Exception ;
	public void testDeleteAll(String studyID) throws Exception ;
	public void addExam(List<Exam> examList) throws Exception ;
	public void addElect(List<Elect> electList) throws Exception ;
	public void addTest(Test test) throws Exception ;
	public void updateStudy(Study study) throws Exception ;
	public void updateStudyEntry(Study study) throws Exception ;
	public void updateStudyReport(Study study) throws Exception ;
	public Cou findCou(String couID) throws Exception ;
	public Study findStudy(String studyID) throws Exception ;
	public List<Choice> findChoice(String studyID,String termDate) throws Exception ;
	public List<Student> findStudent(List<Choice> choiceList) throws Exception ;
	public List<Exam> findExam(String studyID) throws Exception ;
	public Elect findElect(String studyID,String stuID) throws Exception ;
	public Test findTest(String studyID,String stuID,String examName) throws Exception ;
}
