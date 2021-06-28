package jmu.lzz.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jmu.lzz.vo.Student;
import jmu.lzz.vo.Class;
import jmu.lzz.vo.Cou;

public interface UploadService {
	
	public List<Student> uploadStu(String filename, MultipartFile file) throws IOException ;
	public List<Class> uploadClass(String filename, MultipartFile file) throws IOException ;
	public List<Cou> uploadCou(String filename, MultipartFile file) throws IOException ;
}
