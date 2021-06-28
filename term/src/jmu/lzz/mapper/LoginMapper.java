package jmu.lzz.mapper;

import jmu.lzz.vo.College;
import jmu.lzz.vo.Dean;
import jmu.lzz.vo.Room;
import jmu.lzz.vo.Student;
import jmu.lzz.vo.Teacher;
import jmu.lzz.vo.Class;

public interface LoginMapper {
	public Student findStudent(Student student) throws Exception ;
	public Teacher findTeacher(Teacher teacher) throws Exception ;
	public Dean findDean(Dean dean) throws Exception ;
	public Class findClass(String classID) throws Exception ;
	public Room findRoom(String roomID) throws Exception ;
	public College findCollege(String collegeID) throws Exception ;
}
