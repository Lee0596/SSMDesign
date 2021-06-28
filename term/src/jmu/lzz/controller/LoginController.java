package jmu.lzz.controller;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import jmu.lzz.vo.Student;
import jmu.lzz.vo.Teacher;
import jmu.lzz.vo.Dean;
import jmu.lzz.vo.Room;
import jmu.lzz.vo.Class;
import jmu.lzz.vo.College;
import jmu.lzz.service.LoginService;
@Controller
public class LoginController {
	@Resource(name="loginServiceImpl")
	private LoginService loginService;
	@RequestMapping(value="/Login")
	public void findUser(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
		String path="login.jsp";
		String ID=request.getParameter("ID");
		String password=request.getParameter("password");
		String identify=request.getParameter("identify");	
		request.getSession().setAttribute("identify", identify);
		if("Student".equals(identify)){
			Student student = new Student();
			student.setStuID(ID);
			student.setStuPassword(password);
			Student user=this.loginService.findStudent(student);
			if(user!=null){
				Class stuClass = this.loginService.findClass(user.getClassID());
				College stuCollege = this.loginService.findCollege(stuClass.getCollegeID());
				request.getSession().setAttribute("stuClass", stuClass);
				request.getSession().setAttribute("college", stuCollege);
				request.getSession().setAttribute("uname", user.getStuName());
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("uidentify", "学生");
				path="login_success.jsp";
			}else{
				request.setAttribute("error", "ID或密码错误，请重新尝试");
			}
		}
		if("Teacher".equals(identify)){
			Teacher teacher = new Teacher();
			teacher.setTeacherID(ID);
			teacher.setTeacherPassword(password);
			Teacher user=this.loginService.findTeacher(teacher);
			if(user!=null){
				request.getSession().setAttribute("uname", user.getTeacherName());
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("uidentify", "教师");
				path="login_success.jsp";
			}else{
				request.setAttribute("error", "ID或密码错误，请重新尝试");
			}
		}
		if("Dean".equals(identify)){
			Dean dean = new Dean();
			dean.setDeanID(ID);
			dean.setDeanPassword(password);
			Dean user=this.loginService.findDean(dean);
			if(user!=null){
				Room deanRoom = this.loginService.findRoom(user.getRoomID());
				College deanCollege = this.loginService.findCollege(deanRoom.getCollegeID());
				request.getSession().setAttribute("room", deanRoom);
				request.getSession().setAttribute("college", deanCollege);
				request.getSession().setAttribute("uname", user.getDeanName());
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("uidentify", "教务员");
				path="login_success.jsp";
			}else{
				request.setAttribute("error", "ID或密码错误，请重新尝试");
			}
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
}
