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
import jmu.lzz.vo.Cou;
import jmu.lzz.vo.Dean;
import jmu.lzz.vo.Elect;
import jmu.lzz.vo.Exam;
import jmu.lzz.vo.Room;
import jmu.lzz.vo.Student;
import jmu.lzz.vo.Study;
import jmu.lzz.vo.Teacher;
import jmu.lzz.vo.Test;
import jmu.lzz.vo.Class;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import jmu.lzz.service.TeacherService;
@Controller
@RequestMapping("Teacher")
public class TeacherController {
	@Resource(name="teacherServiceImpl")
	private TeacherService teacherService;
	
	@RequestMapping(value="/showAll")
	public void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path="teacher_termDate.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	@RequestMapping(value="/showStudy")
	public String showStudy(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String termDate = request.getParameter("termDate");
		request.getSession().setAttribute("termDate", termDate);
		return "/Teacher/teacher_study";
	}
	
	@RequestMapping(value="/entryGrade")
	public String entryGrade(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String studyID = request.getParameter("studyID");
		Study study = (Study)this.teacherService.findStudy(studyID);
		Cou cou = (Cou)this.teacherService.findCou(study.getCouID());
		request.getSession().setAttribute("study", study);
		request.getSession().setAttribute("cou", cou);
		String termDate = (String)request.getSession().getAttribute("termDate");
		List<Choice> allChoice = this.teacherService.findChoice(studyID,termDate);
		List<Student> allStudent = this.teacherService.findStudent(allChoice);
		List<Exam> allExam = this.teacherService.findExam(studyID);
		request.setAttribute("studentList", allStudent);
		request.setAttribute("examList", allExam);
		return "/Teacher/teacher_entryGrade";
	}
	
	@RequestMapping(value="/entryGradeUpload")
	public String entryGradeUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Study study = (Study)request.getSession().getAttribute("study");
		String studyID = study.getStudyID();
		study.setStudyEntry(true);
		request.getSession().setAttribute("study", study);
		String[] stuID = request.getParameterValues("stuID");
		String[] examName = request.getParameterValues("examName");
		String[] electStasus = request.getParameterValues("electStasus");
		String[] finalGrade = request.getParameterValues("finalGrade");
		List<Elect> electList = new ArrayList<Elect>();
		List<Test> testList = new ArrayList<Test>();
		for(int i=0;i<stuID.length;i++){
			this.teacherService.electDelete(studyID, stuID[i]);
			this.teacherService.testDelete(studyID, stuID[i]);
			Elect elect = new Elect();
			Test test = new Test();
			elect.setStudyID(studyID);
			elect.setStuID(stuID[i]);
			elect.setElectStasus(electStasus[i]);
			elect.setFinalGrade(finalGrade[i]);
			electList.add(elect);
			test.setStudyID(studyID);
			test.setStuID(stuID[i]);
			testList.add(test);
		}
		for(int i=0;i<examName.length;i++){
			String[] testGrade = request.getParameterValues("exam"+i);
			for(int j=0;j<testGrade.length;j++){
				testList.get(j).setExamName(examName[i]);
				testList.get(j).setTestGrade(Double.parseDouble(testGrade[j]));
				this.teacherService.addTest(testList.get(j));
			}
		}
		this.teacherService.addElect(electList);
		this.teacherService.updateStudyEntry(study);
		return "/Teacher/teacher_study";
	}
	
	@RequestMapping(value="/reportGrade")
	public String reportGrade(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String studyID = request.getParameter("studyID");
		Study study = new Study();
		study.setStudyID(studyID);
		study.setStudyReport(true);
		this.teacherService.updateStudyReport(study);
		return "/Teacher/teacher_study";
	}
	
	@RequestMapping(value="/showGrade")
	public String showClass(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String studyID = request.getParameter("studyID");
		Study study = (Study)this.teacherService.findStudy(studyID);
		Cou cou = (Cou)this.teacherService.findCou(study.getCouID());
		request.getSession().setAttribute("study", study);
		request.getSession().setAttribute("cou", cou);
		String termDate = (String)request.getSession().getAttribute("termDate");
		List<Choice> allChoice = this.teacherService.findChoice(studyID,termDate);
		List<Student> allStudent = this.teacherService.findStudent(allChoice);
		List<Exam> allExam = this.teacherService.findExam(studyID);
		List<Elect> allElect = new ArrayList<Elect>();
		for(int i=0;i<allStudent.size();i++){
			Elect elect = new Elect();
			elect = this.teacherService.findElect(studyID, allStudent.get(i).getStuID());
			if(elect!=null){
				allElect.add(elect);
			}
		}
		for(int i=0;i<allExam.size();i++){
			List<Test> testList = new ArrayList<Test>();
			String examName = allExam.get(i).getExamName();
			for(int j=0;j<allStudent.size();j++){
				Test test = new Test();
				test = this.teacherService.findTest(studyID, allStudent.get(j).getStuID(), examName);
				if(test!=null){
					testList.add(test);
				}
			}
			if("平时成绩".equals(examName)&&testList.size()>0){
				request.setAttribute("normalTestList", testList);
			}
			if("实验成绩".equals(examName)&&testList.size()>0){
				request.setAttribute("experimentTestList", testList);
			}
			if("理论成绩".equals(examName)&&testList.size()>0){
				request.setAttribute("theoryTestList", testList);
			}
			if("口试成绩".equals(examName)&&testList.size()>0){
				request.setAttribute("oralTestList", testList);
			}
			if("上机成绩".equals(examName)&&testList.size()>0){
				request.setAttribute("operateTestList", testList);
			}
			if("期考成绩".equals(examName)&&testList.size()>0){
				request.setAttribute("termTestList", testList);
			}
		}
		if(allElect.size()>0){
			request.setAttribute("electList", allElect);
		}
		request.setAttribute("studentList", allStudent);
		request.setAttribute("examList", allExam);
		return "/Teacher/teacher_grade";
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
        Teacher teacher = (Teacher)request.getSession().getAttribute("user");
        List<Choice> all = this.teacherService.selectAllTermDateBypage(before, after, teacher.getTeacherID());
        List<Integer> termDate = this.teacherService.termDateCount(teacher.getTeacherID());
        int count = termDate.size();
        //用json来传值
        JSONArray json = JSONArray.fromObject(all);
        String js = json.toString();
        //*****转为layui需要的json格式，必须要这一步，博主也是没写这一步，在页面上数据就是数据接口异常
        String jso = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
        return jso;
    }
	
	@RequestMapping(value="/selectAllStudyBypage")
	@ResponseBody
	 public String selectAllStudyBypage(@RequestParam(defaultValue = "1") int page,
             @RequestParam(defaultValue = "10") int limit,HttpServletRequest request) throws Exception{
        int before;
        if(page!=1){
        	before = limit * (page - 1);
        }else{
        	before = 0;
        }
        int after = page * limit;
        Teacher teacher = (Teacher)request.getSession().getAttribute("user");
        String termDate = (String) request.getSession().getAttribute("termDate");
        List<Study> all = this.teacherService.selectAllStudyBypage(before, after, termDate, teacher.getTeacherID());
        int count = this.teacherService.studyCount(termDate, teacher.getTeacherID());
        //用json来传值
        JSONArray json = JSONArray.fromObject(all);
        String js = json.toString();
        //*****转为layui需要的json格式，必须要这一步，博主也是没写这一步，在页面上数据就是数据接口异常
        String jso = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
        return jso;
    }
	
	
	@RequestMapping(value="/editStudy")
	@ResponseBody
	public Map<String,Object> editStudy(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		String result = "success";
		String[] arrayName=request.getParameterValues("arrayName[]");
		String[] arrayValue=request.getParameterValues("arrayValue[]");
		String studyID = request.getParameter("studyID");
		String studyWay = request.getParameter("studyWay");
		String studyStyle = request.getParameter("studyStyle");
		Study study = this.teacherService.findStudy(studyID);
		study.setStudyWay(studyWay);
		study.setStudyStyle(studyStyle);
		study.setStudyEntry(false);
		if(arrayValue == null){
			result = "没有设置任何考试";
		}else{
			List<Exam> examList = new ArrayList<Exam>();
			for(int i=0;i<arrayValue.length;i++){
				Exam exam = new Exam();
				exam.setStudyID(studyID);
				exam.setExamName(arrayName[i]);
				exam.setExamRadio(Double.parseDouble(arrayValue[i])/100);
				examList.add(exam);
			}
			try{
				this.teacherService.electDeleteAll(studyID);
				this.teacherService.testDeleteAll(studyID);
				this.teacherService.examDelete(studyID);
				this.teacherService.addExam(examList);
				this.teacherService.updateStudy(study);
			}catch(Exception e){
				result = e.toString();
			}
		}
		resultMap.put("result", result);
		return resultMap;
	}
}
