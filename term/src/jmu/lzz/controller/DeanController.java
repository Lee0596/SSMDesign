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
import jmu.lzz.vo.Class;
import jmu.lzz.vo.Cou;
import jmu.lzz.vo.Dean;
import jmu.lzz.vo.Room;
import jmu.lzz.vo.Student;
import jmu.lzz.vo.Study;
import jmu.lzz.vo.Teacher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import jmu.lzz.service.DeanService;
import jmu.lzz.service.StudentService;
import jmu.lzz.service.UploadService;
@Controller
@RequestMapping("Dean")
public class DeanController {
	@Resource(name="deanServiceImpl")
	private DeanService deanService;
	
	@Resource(name="uploadServiceImpl")
	private UploadService uploadService;
	
	@RequestMapping(value="/showAll")
	public void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path="dean_cou.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	
	@RequestMapping(value="/showStudy")
	public String showStudy(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String couID = request.getParameter("couID");
		Cou cou = this.deanService.findCou(couID);
		request.getSession().setAttribute("cou", cou);
		return "/Dean/dean_study";
	}
	
	@RequestMapping(value="/showChoice")
	public String showChoice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String studyID = request.getParameter("studyID");
		Study study = this.deanService.findStudy(studyID);
		request.getSession().setAttribute("study", study);
		return "/Dean/dean_choice";
	}
	
	
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		return "login";
	}
	
	@RequestMapping(value="/addStudy")
	@ResponseBody
	public Map<String,Object> addStudy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		String result = "success";
		String studyID = request.getParameter("studyID");
		String teacherID = request.getParameter("teacherID");
		String studyStatus = request.getParameter("studyStatus");
		String studyReport = request.getParameter("studyReport");
		String deadLine = request.getParameter("deadLine");
		if("".equals(studyID) || "null".equals(studyID)){
			result = "开课班级号为空";
		}else{
			if("".equals(teacherID) || "null".equals(teacherID)){
				result = "任课教师工号为空";
			}else{
				if("".equals(deadLine) || "null".equals(deadLine)){
					result = "成绩录入截止日期为空";
				}else{
					Cou cou = (Cou)request.getSession().getAttribute("cou");
					Study study = new Study();
					study.setStudyID(studyID);
					study.setTeacherID(teacherID);
					study.setCouID(cou.getCouID());
					study.setStudyStatus(studyStatus);
					study.setStudyReport(Boolean.parseBoolean(studyReport));
					study.setDeadLine(deadLine);
					try{
						this.deanService.addStudy(study);
					}catch(Exception e){
						result = e.toString();
					}
				}
			}
		}
		resultMap.put("result", result);
		return resultMap;
	}
	
	@RequestMapping(value="/addChoice")
	@ResponseBody
	public Map<String,Object> addChoice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		String result = "success";
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String term = request.getParameter("term");
		String classID = request.getParameter("classID");
		if("".equals(start) || "null".equals(start)){
			result = "开始学年为空";
		}else{
			if("".equals(end) || "null".equals(end)){
				result = "结束学年为空";
			}else{
				if("".equals(classID) || "null".equals(classID)){
					result = "上课班级号为空";
				}else{
					Study study = (Study)request.getSession().getAttribute("study");
					Choice choice = new Choice();
					String termDate = start+"-"+end+"-"+term;
					choice.setStudyID(study.getStudyID());
					choice.setClassID(classID);
					choice.setTermDate(termDate);
					try{
						this.deanService.addChoice(choice);
					}catch(Exception e){
						result = e.toString();
					}
				}
			}
		}
		resultMap.put("result", result);
		return resultMap;
	}
	
	@RequestMapping(value = "/uploadStu")
	@ResponseBody
	public String uploadStu(@RequestParam("file") MultipartFile file) {
		String msg = null;
		if (!file.isEmpty()) {
			List<Student> all = new ArrayList<Student>();
		try {
		String filename = file.getOriginalFilename();
		all = this.uploadService.uploadStu(filename, file);
		if(all.size()==0){
			msg = "信息导入失败";
		}else{
			try{
				this.deanService.addStudent(all);
				msg = "信息导入成功";
			}catch(Exception e){
				msg = e.toString();
			}
			}
		} catch (Exception e) {
		msg = e.toString();
			}
		}else{
			msg = "录入文件为空";
		}
        String jso = "{\"code\":0,\"msg\":\""+msg+"\"}";
        return jso;
	}
	
	@RequestMapping(value = "/uploadClass")
	@ResponseBody
	public String uploadClass(@RequestParam("file") MultipartFile file) {
		String msg = null;
		if (!file.isEmpty()) {
			List<Class> all = new ArrayList<Class>();
		try {
		String filename = file.getOriginalFilename();
		all = this.uploadService.uploadClass(filename, file);
		if(all.size()==0){
			msg = "信息导入失败";
		}else{
			try{
				this.deanService.addClass(all);
				msg = "信息导入成功";
			}catch(Exception e){
				msg = e.toString();
			}
			}
		} catch (Exception e) {
		msg = e.toString();
			}
		}else{
			msg = "录入文件为空";
		}
        String jso = "{\"code\":0,\"msg\":\""+msg+"\"}";
        return jso;
	}
	
	@RequestMapping(value = "/uploadCou")
	@ResponseBody
	public String uploadCou(@RequestParam("file") MultipartFile file) {
		String msg = null;
		if (!file.isEmpty()) {
			List<Cou> all = new ArrayList<Cou>();
		try {
		String filename = file.getOriginalFilename();
		all = this.uploadService.uploadCou(filename, file);
		if(all.size()==0){
			msg = "信息导入失败";
		}else{
			try{
				this.deanService.addCou(all);
				msg = "信息导入成功";
			}catch(Exception e){
				msg = e.toString();
			}
			}
		} catch (Exception e) {
		msg = e.toString();
			}
		}else{
			msg = "录入文件为空";
		}
        String jso = "{\"code\":0,\"msg\":\""+msg+"\"}";
        return jso;
	}
	
	@RequestMapping(value="/selectCouBypage")
	@ResponseBody
	 public String selectCouBypage(@RequestParam(defaultValue = "1") int page,
             @RequestParam(defaultValue = "10") int limit,HttpServletRequest request) throws Exception{
        int before;
        if(page!=1){
        	before = limit * (page - 1);
        }else{
        	before = 0;
        }
        int after = page * limit;
        Room room = (Room)request.getSession().getAttribute("room");
        List<Cou> all = this.deanService.selectCouBypage(before, after,room.getRoomID());
        int count = this.deanService.couCount(room.getRoomID());
        //用json来传值
        JSONArray json = JSONArray.fromObject(all);
        String js = json.toString();
        //*****转为layui需要的json格式，必须要这一步，博主也是没写这一步，在页面上数据就是数据接口异常
        String jso = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
        return jso;
    }
	
	@RequestMapping(value="/selectStudyBypage")
	@ResponseBody
	 public String selectStudyBypage(@RequestParam(defaultValue = "1") int page,
             @RequestParam(defaultValue = "10") int limit,HttpServletRequest request) throws Exception{
        int before;
        if(page!=1){
        	before = limit * (page - 1);
        }else{
        	before = 0;
        }
        int after = page * limit;
        Cou cou = (Cou)request.getSession().getAttribute("cou");
        List<Study> all = this.deanService.selectStudyBypage(before, after,cou.getCouID());
        int count = this.deanService.studyCount(cou.getCouID());
        //用json来传值
        JSONArray json = JSONArray.fromObject(all);
        String js = json.toString();
        //*****转为layui需要的json格式，必须要这一步，博主也是没写这一步，在页面上数据就是数据接口异常
        String jso = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
        return jso;
    }
	
	@RequestMapping(value="/selectChoiceBypage")
	@ResponseBody
	 public String selectChoiceBypage(@RequestParam(defaultValue = "1") int page,
             @RequestParam(defaultValue = "10") int limit,HttpServletRequest request) throws Exception{
        int before;
        if(page!=1){
        	before = limit * (page - 1);
        }else{
        	before = 0;
        }
        int after = page * limit;
        Study study = (Study)request.getSession().getAttribute("study");
        List<Choice> all = this.deanService.selectChoiceBypage(before, after,study.getStudyID());
        int count = this.deanService.choiceCount(study.getStudyID());
        //用json来传值
        JSONArray json = JSONArray.fromObject(all);
        String js = json.toString();
        //*****转为layui需要的json格式，必须要这一步，博主也是没写这一步，在页面上数据就是数据接口异常
        String jso = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
        return jso;
    }
}
