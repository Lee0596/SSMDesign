package jmu.lzz.service;

import java.util.List;

import jmu.lzz.vo.Choice;
import jmu.lzz.vo.Cou;
import jmu.lzz.vo.Elect;
import jmu.lzz.vo.Student;
import jmu.lzz.vo.Study;

public interface StudentService {
	public List<Choice> selectAllTermDateBypage(int before,int after,String classID) throws Exception ;
	public List<Integer> termDateCount(String classID) throws Exception ;
	public List<Choice> findChoice(String classID,String termDate) throws Exception ;
	public List<Study> findStudy(List<Choice> choiceList) throws Exception ;
	public Cou findCou(String studyID) throws Exception ;
	public Elect findElect(String studyID,String stuID) throws Exception ;
}
