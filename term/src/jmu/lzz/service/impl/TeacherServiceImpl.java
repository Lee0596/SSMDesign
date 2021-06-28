package jmu.lzz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jmu.lzz.mapper.TeacherMapper;
import jmu.lzz.service.StudentService;
import jmu.lzz.service.TeacherService;
import jmu.lzz.vo.Choice;
import jmu.lzz.vo.Class;
import jmu.lzz.vo.Cou;
import jmu.lzz.vo.Elect;
import jmu.lzz.vo.Exam;
import jmu.lzz.vo.Student;
import jmu.lzz.vo.Study;
import jmu.lzz.vo.Test;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
	   @Resource(name="teacherMapper")
	   private TeacherMapper teacherMapper;
	   
	   public List<Choice> selectAllTermDateBypage(int before,int after,String teacherID) throws Exception {
		   return this.teacherMapper.selectAllTermDateBypage(before, after, teacherID);
	   }
	   
	   public List<Study> selectAllStudyBypage(int before,int after,String termDate,String teacherID) throws Exception {
		   return this.teacherMapper.selectAllStudyBypage(before, after, termDate, teacherID);
	   }
		
	   public List<Choice> selectAllClassBypage(int before,int after,String termDate,String studyID) throws Exception {
		   return this.teacherMapper.selectAllClassBypage(before, after, termDate, studyID);
	   }
	   
	   public int studyCount(String termDate,String teacherID) throws Exception {
		   return this.teacherMapper.studyCount(termDate,teacherID);
	   }
	   
	   public List<Integer> termDateCount(String teacherID) throws Exception {
		   return this.teacherMapper.termDateCount(teacherID);
	   }
		
		public void addExam(List<Exam> examList) throws Exception {
			this.teacherMapper.addExam(examList);
		}
		
		public void addElect(List<Elect> electList) throws Exception {
			this.teacherMapper.addElect(electList);
		}
		
		public void addTest(Test test) throws Exception {
			this.teacherMapper.addTest(test);
		}
		
		public void examDelete(String studyID) throws Exception {
			this.teacherMapper.examDelete(studyID);
		}
		
		public void electDelete(String studyID,String stuID) throws Exception {
			this.teacherMapper.electDelete(studyID, stuID);
		}
		
		public void testDelete(String studyID,String stuID) throws Exception {
			this.teacherMapper.testDelete(studyID, stuID);
		}
		
		public void electDeleteAll(String studyID) throws Exception {
			this.teacherMapper.electDeleteAll(studyID);
		}
		
		public void testDeleteAll(String studyID) throws Exception {
			this.teacherMapper.testDeleteAll(studyID);
		}
		
		public void updateStudy(Study study) throws Exception {
			this.teacherMapper.updateStudy(study);
		}
		
		public void updateStudyEntry(Study study) throws Exception {
			this.teacherMapper.updateStudyEntry(study);
		}
		public void updateStudyReport(Study study) throws Exception {
			this.teacherMapper.updateStudyReport(study);
		}
	   
	   public Cou findCou(String couID) throws Exception {
		   return this.teacherMapper.findCou(couID);
	   }
	   
	   public Study findStudy(String studyID) throws Exception {
		   return this.teacherMapper.findStudy(studyID);
	   }
	   
	   public List<Choice> findChoice(String studyID,String termDate) throws Exception {
			return this.teacherMapper.findChoice(studyID,termDate);
		}
	   public List<Student> findStudent(List<Choice> choiceList) throws Exception {
		   return this.teacherMapper.findStudent(choiceList);
	   }
	   
	   public List<Exam> findExam(String studyID) throws Exception {
		   return this.teacherMapper.findExam(studyID);
	   }
	   
		public Elect findElect(String studyID,String stuID) throws Exception {
			return this.teacherMapper.findElect(studyID, stuID);
		}
		
		public Test findTest(String studyID,String stuID,String examName) throws Exception {
			return this.teacherMapper.findTest(studyID, stuID, examName);
		}
	}
