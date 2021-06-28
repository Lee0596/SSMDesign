package jmu.lzz.controller;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jmu.lzz.vo.Choice;
import jmu.lzz.vo.Dean;
import jmu.lzz.vo.Elect;
import jmu.lzz.vo.Student;
import jmu.lzz.vo.Study;
import jmu.lzz.vo.Class;
import jmu.lzz.vo.Cou;
import net.sf.json.JSONArray;
import jmu.lzz.service.StudentService;
@Controller
@RequestMapping("Student")
public class StudentController {
	@Resource(name="studentServiceImpl")
	private StudentService studentService;
	
	@RequestMapping(value="/showAll")
	public void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path="student_termDate.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	@RequestMapping(value="/showGrade")
	public String showGrade(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String termDate = request.getParameter("termDate");
		request.getSession().setAttribute("termDate", termDate);
        Student student = (Student)request.getSession().getAttribute("user");
        Class stuClass = (Class)request.getSession().getAttribute("stuClass");
        List<Choice> choiceList = this.studentService.findChoice(stuClass.getClassID(), termDate);
        List<Study> studyList = this.studentService.findStudy(choiceList);
        List<Cou> couList = new ArrayList<Cou>();
        List<Elect> electList = new ArrayList<Elect> ();
        for(int i=0;i<studyList.size();i++){
        	Cou cou = new Cou();
        	cou = this.studentService.findCou(studyList.get(i).getCouID());
        	Elect elect = new Elect();
        	if(studyList.get(i).getStudyReport()){
        		elect = this.studentService.findElect(studyList.get(i).getStudyID(), student.getStuID());
        	}else{
        		elect.setElectStasus("未录入");
        		elect.setFinalGrade("未录入");
        	}
        	couList.add(cou);
        	electList.add(elect);
        }
		request.setAttribute("couList", couList);
		request.setAttribute("electList", electList);
		return "/Student/student_grade";
	}
		
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		return "login";
	}
	
	@RequestMapping(value="/selectAllTermDateBypage")
	@ResponseBody
	 public String selectAllTermDateBypage(@RequestParam(defaultValue = "1") int page,
             @RequestParam(defaultValue = "10") int limit,HttpServletRequest request) throws Exception{
        int before;
        if(page!=1){
        	before = limit * (page - 1);
        }else{
        	before = 0;
        }
        int after = page * limit;
        Student student = (Student)request.getSession().getAttribute("user");
        Class stuClass = (Class)request.getSession().getAttribute("stuClass");
        List<Choice> all = this.studentService.selectAllTermDateBypage(before, after, stuClass.getClassID());
        List<Integer> termDate = this.studentService.termDateCount(stuClass.getClassID());
        int count = termDate.size();
        //用json来传值
        JSONArray json = JSONArray.fromObject(all);
        String js = json.toString();
        //*****转为layui需要的json格式，必须要这一步，博主也是没写这一步，在页面上数据就是数据接口异常
        String jso = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
        return jso;
    }

}
