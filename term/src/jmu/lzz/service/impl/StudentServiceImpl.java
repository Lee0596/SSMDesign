package jmu.lzz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jmu.lzz.mapper.StudentMapper;
import jmu.lzz.service.StudentService;
import jmu.lzz.vo.Choice;
import jmu.lzz.vo.Cou;
import jmu.lzz.vo.Elect;
import jmu.lzz.vo.Student;
import jmu.lzz.vo.Study;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	   @Resource(name="studentMapper")
	   private StudentMapper studentMapper;
		
	   public List<Choice> selectAllTermDateBypage(int before,int after,String classID) throws Exception {
		   return this.studentMapper.selectAllTermDateBypage(before, after, classID);
	   }
	   
	   public List<Integer> termDateCount(String classID) throws Exception {
		   return this.studentMapper.termDateCount(classID);
	   }
	   
		public List<Choice> findChoice(String classID,String termDate) throws Exception {
			return this.studentMapper.findChoice(classID, termDate);
		}
		
		public List<Study> findStudy(List<Choice> choiceList) throws Exception {
			return this.studentMapper.findStudy(choiceList);
		}
		
		public Cou findCou(String studyID) throws Exception {
			return this.studentMapper.findCou(studyID);
		}
		
		public Elect findElect(String studyID,String stuID) throws Exception {
			return this.studentMapper.findElect(studyID, stuID);
		}
	}
