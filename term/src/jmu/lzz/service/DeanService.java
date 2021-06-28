package jmu.lzz.service;

import java.util.List;

import jmu.lzz.vo.Choice;
import jmu.lzz.vo.Class;
import jmu.lzz.vo.Cou;
import jmu.lzz.vo.Student;
import jmu.lzz.vo.Study;

public interface DeanService {
	public List<Cou> selectCouBypage(int before,int after,String roomID) throws Exception ;
	public List<Study> selectStudyBypage(int before,int after,String couID) throws Exception ;
	public List<Choice> selectChoiceBypage(int before,int after,String studyID) throws Exception ;
	public int couCount(String roomID) throws Exception ;
	public int studyCount(String couID) throws Exception ;
	public int choiceCount(String studyID) throws Exception ;
	public Cou findCou(String couID) throws Exception ;
	public Study findStudy(String studyID) throws Exception ;
	public void addStudent(List<Student> stuList) throws Exception ;
	public void addClass(List<Class> classList) throws Exception ;
	public void addCou(List<Cou> couList) throws Exception ;
	public void addStudy(Study study) throws Exception ;
	public void addChoice(Choice choice) throws Exception ;
}
