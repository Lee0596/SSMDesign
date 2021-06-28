package jmu.lzz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jmu.lzz.mapper.DeanMapper;
import jmu.lzz.service.DeanService;
import jmu.lzz.vo.Student;
import jmu.lzz.vo.Study;
import jmu.lzz.vo.Choice;
import jmu.lzz.vo.Class;
import jmu.lzz.vo.Cou;
@Service
@Transactional
public class DeanServiceImpl implements DeanService {
	   @Resource(name="deanMapper")
	   private DeanMapper deanMapper;
	   
	   public List<Cou> selectCouBypage(int before,int after,String roomID) throws Exception{
			return this.deanMapper.selectCouBypage(before,after,roomID);
		}
	   
		public List<Study> selectStudyBypage(int before,int after,String couID) throws Exception {
			return this.deanMapper.selectStudyBypage(before, after, couID);
		}
		
		public List<Choice> selectChoiceBypage(int before,int after,String studyID) throws Exception {
			return this.deanMapper.selectChoiceBypage(before, after, studyID);
		}
		
	   public int couCount(String roomID) throws Exception{
			return this.deanMapper.couCount(roomID);
		}
	   
		public int studyCount(String couID) throws Exception {
			return this.deanMapper.studyCount(couID);
		}
		
		public int choiceCount(String studyID) throws Exception {
			return this.deanMapper.choiceCount(studyID);
		}
		
		public Cou findCou(String couID) throws Exception {
			return this.deanMapper.findCou(couID);
		}
		
		public Study findStudy(String studyID) throws Exception{
			return this.deanMapper.findStudy(studyID);
		}
	   
	   public void addStudent(List<Student> stuList) throws Exception{
			this.deanMapper.addStudent(stuList);
		}
	   
	   public void addClass(List<Class> classList) throws Exception{
			this.deanMapper.addClass(classList);
		}
	   
	   public void addCou(List<Cou> couList) throws Exception{
			this.deanMapper.addCou(couList);
		}
	   
		public void addStudy(Study study) throws Exception{
			this.deanMapper.addStudy(study);
		}
		
		public void addChoice(Choice choice) throws Exception{
			this.deanMapper.addChoice(choice);
		}
	}
