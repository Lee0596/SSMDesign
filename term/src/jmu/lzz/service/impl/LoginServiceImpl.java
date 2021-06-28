package jmu.lzz.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jmu.lzz.mapper.LoginMapper;
import jmu.lzz.service.LoginService;
import jmu.lzz.vo.College;
import jmu.lzz.vo.Dean;
import jmu.lzz.vo.Room;
import jmu.lzz.vo.Student;
import jmu.lzz.vo.Teacher;
import jmu.lzz.vo.Class;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	   @Resource(name="loginMapper")
	   private LoginMapper loginMapper;
		
		public Student findStudent(Student student) throws Exception {
			return this.loginMapper.findStudent(student);
		}
		
		public Teacher findTeacher(Teacher teacher) throws Exception {
			return this.loginMapper.findTeacher(teacher);
		}
		
		public Dean findDean(Dean dean) throws Exception {
			return this.loginMapper.findDean(dean);
		}
		
		public Class findClass(String classID) throws Exception {
			return this.loginMapper.findClass(classID);
		}
		
		public Room findRoom(String roomID) throws Exception {
			return this.loginMapper.findRoom(roomID);
		}
		
		public College findCollege(String collegeID) throws Exception {
			return this.loginMapper.findCollege(collegeID);
		}
	}
