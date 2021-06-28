package jmu.lzz.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import jmu.lzz.service.UploadService;
import jmu.lzz.vo.Student;
import jmu.lzz.vo.Class;
import jmu.lzz.vo.Cou;

@Service
public class UploadServiceImpl implements UploadService {
	

	public List<Student> uploadStu(String filename, MultipartFile file) throws IOException {
		boolean isExcel2003 = true;
		if (filename.matches("^.+\\.(?i)(xlsx)$")) {
		isExcel2003 = false;
		}
		
		InputStream is = file.getInputStream();
		Workbook wb = null;
		if (isExcel2003) {
		wb = new HSSFWorkbook(is);
		} else {
		wb = new XSSFWorkbook(is);
		}
		Sheet sheet = wb.getSheetAt(0);
		if (sheet == null) {
			return null;
		}
		List<Student> allStu = new ArrayList<Student>();
		for (int r = 1; r <= sheet.getLastRowNum(); r++) {
		Row row = sheet.getRow(r);
		if (row == null) {
		continue;
		}else{
			Student stu = new Student();
			for(int cellNum=0;cellNum<row.getLastCellNum();cellNum++){
				String cell = null;
				if("NUMERIC" == row.getCell(cellNum).getCellType().toString()){  
		        	if(DateUtil.isCellDateFormatted(row.getCell(cellNum))){
		      			//用于转化为日期格式
		        		Date d = row.getCell(cellNum).getDateCellValue();
		        		DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		        		cell = formater.format(d);
		            }else{
		            	cell = row.getCell(cellNum).toString();
		            	cell = cell.substring(0, cell.indexOf("."));
		            }
		         }else{  
		        	 cell = row.getCell(cellNum).toString();
		         }
				switch(cellNum){
				case 0: stu.setStuID(cell); break;
				case 1: stu.setClassID(cell); break;
				case 2: stu.setStuName(cell); break;
				case 3: stu.setStuPassword(cell); break;
				case 4: stu.setJoinTime(cell); break;
				}
            }
			allStu.add(stu);
		  }
		}
		return allStu;
	}

	public List<Class> uploadClass(String filename, MultipartFile file) throws IOException {
		boolean isExcel2003 = true;
		if (filename.matches("^.+\\.(?i)(xlsx)$")) {
		isExcel2003 = false;
		}
		
		InputStream is = file.getInputStream();
		Workbook wb = null;
		if (isExcel2003) {
		wb = new HSSFWorkbook(is);
		} else {
		wb = new XSSFWorkbook(is);
		}
		Sheet sheet = wb.getSheetAt(0);
		if (sheet == null) {
			return null;
		}
		List<Class> allClass = new ArrayList<Class>();
		for (int r = 1; r <= sheet.getLastRowNum(); r++) {
		Row row = sheet.getRow(r);
		if (row == null) {
		continue;
		}else{
			Class stuClass = new Class();
			for(int cellNum=0;cellNum<row.getLastCellNum();cellNum++){
				String cell = null;
				if("NUMERIC" == row.getCell(cellNum).getCellType().toString()){  
		        	if(DateUtil.isCellDateFormatted(row.getCell(cellNum))){
		      			//用于转化为日期格式
		        		Date d = row.getCell(cellNum).getDateCellValue();
		        		DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		        		cell = formater.format(d);
		            }else{
		            	cell = row.getCell(cellNum).toString();
		            	cell = cell.substring(0, cell.indexOf("."));
		            }
		         }else{  
		        	 cell = row.getCell(cellNum).toString();
		         }
				switch(cellNum){
				case 0: stuClass.setClassID(cell);; break;
				case 1: stuClass.setCollegeID(cell);; break;
				case 2: stuClass.setClassName(cell);; break;
				case 3: stuClass.setClassNum(Integer.valueOf(cell));; break;
				}
            }
			allClass.add(stuClass);
		  }
		}
		return allClass;
	}
	
	public List<Cou> uploadCou(String filename, MultipartFile file) throws IOException {
		boolean isExcel2003 = true;
		if (filename.matches("^.+\\.(?i)(xlsx)$")) {
		isExcel2003 = false;
		}
		
		InputStream is = file.getInputStream();
		Workbook wb = null;
		if (isExcel2003) {
		wb = new HSSFWorkbook(is);
		} else {
		wb = new XSSFWorkbook(is);
		}
		Sheet sheet = wb.getSheetAt(0);
		if (sheet == null) {
			return null;
		}
		List<Cou> allCou = new ArrayList<Cou>();
		for (int r = 1; r <= sheet.getLastRowNum(); r++) {
		Row row = sheet.getRow(r);
		if (row == null) {
		continue;
		}else{
			Cou cou = new Cou();
			for(int cellNum=0;cellNum<row.getLastCellNum();cellNum++){
				String cell = null;
		        	 cell = row.getCell(cellNum).toString();
						switch(cellNum){
						case 0: cou.setCouID(cell); break;
						case 1: cou.setRoomID(cell); break;
						case 2: cou.setCouName(cell); break;
						case 3: cou.setCouCredit(Double.parseDouble(cell)); break;
						case 4: cou.setCouHours(Double.parseDouble(cell)); break;
						case 5: cou.setCouStyle(cell); break;
						}
		         }
			allCou.add(cou);
            }
		  }
		return allCou;
	}
}
