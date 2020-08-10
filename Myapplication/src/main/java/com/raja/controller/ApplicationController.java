package com.raja.controller;


import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.lowagie.text.DocumentException;
import com.raja.model.ExportExcel;
import com.raja.model.ExportPdf;
import com.raja.model.Fileupload;
import com.raja.model.LoginUser;
import com.raja.model.User;
import com.raja.respositary.LoginRepositary;
import com.raja.service.FileServiceImp;
import com.raja.service.LoginService;
import com.raja.service.UserService;

@Controller
public class ApplicationController {
	
	@Autowired
	private LoginService loginservice;
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private FileServiceImp fileserviceimp;
		
	@RequestMapping("/login")
	public String login(HttpServletRequest req,String error,Model model,String logout) {
		if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        
		req.setAttribute("mode","MODE_LOGIN");
		return "loginpage";
	}

	@RequestMapping("/home")
	public String home(HttpServletRequest req,Principal principal,Model model) {
		String email = principal.getName();
		LoginUser loginuser = loginservice.findByEmailid(email);
		model.addAttribute("user", loginuser);
		req.setAttribute("mode","MODE_HOME");
		return "homepage";
	}
	
	
	@RequestMapping("/logout")
	public void logout(HttpServletRequest req,HttpServletResponse res) throws IOException {
		req.setAttribute("message","You are logged out Successfully");
		HttpSession session=req.getSession();  
        session.invalidate();
        res.sendRedirect("/login");
    }
	
	@RequestMapping("/register")
	public String register(HttpServletRequest req) {
		req.setAttribute("mode","MODE_REGISTER");
		return "loginpage";
	}
	
	@RequestMapping("/edit")
	public String edit(HttpServletRequest req,Model model){
		List<LoginUser> lists = loginservice.findAll();
		model.addAttribute("lists", lists);
		req.setAttribute("mode","MODE_EDIT");
		return "homepage";
	}
	
	@RequestMapping("/delete-user")
	public void delete_user(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String id=req.getParameter("email");
		loginservice.delete(id);
		res.sendRedirect("/edit");
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest req,Model model){
		List<LoginUser> lists = loginservice.findAll();
		model.addAttribute("lists", lists);
		req.setAttribute("mode","MODE_ADD");
		return "homepage";
	}
	
	@PostMapping("/add-user")
	public String adduser(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String userid=req.getParameter("userid");
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String active=req.getParameter("active");
		String role=req.getParameter("role");
		String email=req.getParameter("email");
		role.toLowerCase();
		if(role.equals("user")) {
			role="ROLE_USER";
		}else if(role.equals("admin")) {
			role="ROLE_ADMIN";
		}
		if(userid.length()!=0 && username.length()!=0 && password.length()!=0 && role.length()!=0 && email.length()!=0 ) {
			loginservice.saveMyUser(userid,username,password,role,true,email);
			res.sendRedirect("/edit");
		}
			req.setAttribute("mode","MODE_ADD_USER");
			return "homepage";
	}
	
	@RequestMapping("/search")
	public String getSearch(HttpServletRequest req) {
		req.setAttribute("mode","MODE_SEARCH");
		return "homepage";
	}
	
	@RequestMapping("/about")
	public String getAbout(HttpServletRequest req) {
		req.setAttribute("mode","MODE_ABOUT");
		return "homepage";
	}

	
	@RequestMapping("/upload")
	public String upload(Model model,HttpServletRequest req) {
		model.addAttribute("fileupload",new Fileupload());
		List<Fileupload> files = fileserviceimp.findAll();
		model.addAttribute("files", files);
		req.setAttribute("mode","MODE_UPLOAD");
		return "homepage";
	}
	
	@RequestMapping("/profile")
	public String profile(HttpServletRequest req,Principal principal,Model model) {
		String email = principal.getName();
		LoginUser loginuser = loginservice.findByEmailid(email);
		model.addAttribute("user", loginuser);
		req.setAttribute("mode","MODE_PROFILE");
		return "homepage";
	}
	
	@RequestMapping("/update-password")
	public String updatepassword(HttpServletRequest req)
	{
		req.setAttribute("mode","MODE_UPDATE_PASSWORD");
		return "homepage";
	}
	
	@RequestMapping("/change-password")
	public void changepassword(HttpServletRequest req,HttpServletResponse res,Principal principal) throws IOException
	{
		String password = req.getParameter("password");
		String email = principal.getName();
		loginservice.changepassword(email,password);
		res.sendRedirect("/profile");
	}
	
	@PostMapping("/search")
	public String uploadFile(@ModelAttribute Fileupload fileupload,HttpServletRequest req,MultipartFile file,Model model)
	{
		boolean isVaild = fileserviceimp.saveDataFromUpload(file);
		if(isVaild) {
			List<Fileupload> files = fileserviceimp.findAll();
			model.addAttribute("files", files);
			req.setAttribute("mode","MODE_SEARCH");
			return "homepage";
		}else {
			model.addAttribute("error", "Invalid file format !!");
			req.setAttribute("mode","MODE_UPLOAD");
			return "homepage";
		}
	}
	
	@PostMapping("/search-clients")
	public String searchclient(HttpServletRequest req,Model model) {
		String name=req.getParameter("find");
		List<Fileupload> files = fileserviceimp.findByName(name);
		model.addAttribute("files", files);
		req.setAttribute("find",name);
		req.setAttribute("mode","MODE_SEARCH_CLIENT");
		if(files.size()==0)
		{
			model.addAttribute("error", "There is records in this account number");
			req.setAttribute("mode","MODE_SEARCH");
			return "homepage";
		}
		return "homepage";
	}
	
	@PostMapping("/search-clientsbydate")
	public String searchclientbydate(HttpServletRequest req,Model model) {
		String start=req.getParameter("fromDate");
		String end=req.getParameter("endDate");
		if(end.length()==0)
		{
			end=start;
		}
		String s[]=start.split("-");
		String s1[]=end.split("-");
		start="";
		end="";
		String month="";
		if(s[1].equals("01"))
		{
			month="Jan";
		}else if(s[1].equals("02")) {
			month="Feb";
		}else if(s[1].equals("03")) {
			month="Mar";
		}else if(s[1].equals("04")) {
			month="Apr";
		}else if(s[1].equals("05")) {
			month="May";
		}else if(s[1].equals("06")) {
			month="Jun";
		}else if(s[1].equals("07")) {
			month="Jul";
		}else if(s[1].equals("08")) {
			month="Aug";
		}else if(s[1].equals("09")) {
			month="Sep";
		}else if(s[1].equals("10")) {
			month="Oct";
		}else if(s[1].equals("11")) {
			month="Nov";
		}else if(s[1].equals("12")) {
			month="Dec";
		}
		
		String month1="";
		if(s1[1].equals("01"))
		{
			month1="Jan";
		}else if(s1[1].equals("02")) {
			month1="Feb";
		}else if(s1[1].equals("03")) {
			month1="Mar";
		}else if(s1[1].equals("04")) {
			month1="Apr";
		}else if(s1[1].equals("05")) {
			month1="May";
		}else if(s1[1].equals("06")) {
			month1="Jun";
		}else if(s1[1].equals("07")) {
			month1="Jul";
		}else if(s1[1].equals("08")) {
			month1="Aug";
		}else if(s1[1].equals("09")) {
			month1="Sep";
		}else if(s1[1].equals("10")) {
			month1="Oct";
		}else if(s1[1].equals("11")) {
			month1="Nov";
		}else if(s1[1].equals("12")) {
			month1="Dec";
		}
		start=s[2]+'-'+month+'-'+s[0].substring(2);
		end=s1[2]+'-'+month1+'-'+s1[0].substring(2);
		int cs=Integer.parseInt(s[2]);
		int ce=Integer.parseInt(s1[2]);
		if(cs>ce) {
			req.setAttribute("error","Invalid date range ,Please enter proper date !!");
			req.setAttribute("mode","MODE_SEARCH");
			return "homepage";
		}else {
			List<Fileupload> files = fileserviceimp.findByDate(start,end);
			if(files.size()==0)
			{
				req.setAttribute("error"," No transactions made between these date range");
				req.setAttribute("mode","MODE_SEARCH");
				return "homepage";
			}
			model.addAttribute("files", files);
			req.setAttribute("start",start);
			req.setAttribute("end",end);
			req.setAttribute("mode","MODE_DOWNLOAD_BY_DATE");
			return "homepage";
		}
		
	}
	
	// Download by Accno
	
	@PostMapping("/export-csv")
	public void exporttocsv(HttpServletRequest req,HttpServletResponse res) throws IOException {
		res.setContentType("text/csv");
		String export=req.getParameter("export");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String filename = "users_"+ currentDateTime + ".csv";

		String headerKey="Content-Disposition";
		String headerValue="attachment; filename=" + filename;
		res.setHeader(headerKey, headerValue);
		
		
		List<Fileupload> files = fileserviceimp.findByName(export);
		ICsvBeanWriter csvWriter = new CsvBeanWriter(res.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"Accno","Date","Time","Description","Withdrawal","Credits"};
		String[] namemapping = {"accno","date","time","description","withdrawal","credits"};
		
		csvWriter.writeHeader(csvHeader);
		
		for(Fileupload fileupload : files)
		{
			csvWriter.write(fileupload, namemapping);
		}
		
		csvWriter.close();
		req.setAttribute("mode","MODE_DOWNLOAD");
	}
	
	@PostMapping("/export-excel")
	public void exporttoexcel(HttpServletRequest req,HttpServletResponse res) throws IOException {
		res.setContentType("application/octet-stream");
		String export=req.getParameter("export");
		String headerKey="Content-Disposition";
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String filename = "users_"+ currentDateTime + ".xlsx";
 		String headerValue="attachment; filename=" + filename;
		res.setHeader(headerKey, headerValue);
		
		
		List<Fileupload> files = fileserviceimp.findByName(export);
		ExportExcel exportexcel=new ExportExcel(files);
		exportexcel.export(res);
		
		req.setAttribute("mode","MODE_DOWNLOAD");
	}
	
	@PostMapping("/export-pdf")
	public void exporttopdf(HttpServletRequest req,HttpServletResponse res) throws DocumentException, IOException{
		res.setContentType("application/pdf");
		String export=req.getParameter("export");
		String headerKey="Content-Disposition";
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String filename = "users_"+ currentDateTime + ".pdf";
 		String headerValue="attachment; filename=" + filename;
		res.setHeader(headerKey, headerValue);
		
		List<Fileupload> files = fileserviceimp.findByName(export);
		ExportPdf exportpdf=new ExportPdf(files);
		exportpdf.export(res);
		req.setAttribute("mode","MODE_DOWNLOAD_BY_DATE");
	}
	
	//Download by date
	
	@PostMapping("/exportbydate-csv")
	public void exportbydatetocsv(HttpServletRequest req,HttpServletResponse res) throws IOException {
		res.setContentType("text/csv");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String filename = "users_"+ currentDateTime + ".csv";
		
		String headerKey="Content-Disposition";
		String headerValue="attachment; filename=" + filename;
		res.setHeader(headerKey, headerValue);
		
		String start=req.getParameter("start");
		String end=req.getParameter("end");
		
		List<Fileupload> files = fileserviceimp.findByDate(start, end);
		ICsvBeanWriter csvWriter = new CsvBeanWriter(res.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"Accno","Date","Time","Description","Withdrawal","Credits"};
		String[] namemapping = {"accno","date","time","description","withdrawal","credits"};
		
		csvWriter.writeHeader(csvHeader);
		
		for(Fileupload fileupload : files)
		{
			csvWriter.write(fileupload, namemapping);
		}
		
		csvWriter.close();
		req.setAttribute("mode","MODE_DOWNLOAD");
	}
	
	@PostMapping("/exportbydate-excel")
	public void exportbydatetoexcel(HttpServletRequest req,HttpServletResponse res) throws IOException {
		res.setContentType("application/octet-stream");
		String headerKey="Content-Disposition";
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String filename = "users_"+ currentDateTime + ".xlsx";
 		String headerValue="attachment; filename=" + filename;
		res.setHeader(headerKey, headerValue);
		
		String start=req.getParameter("start");
		String end=req.getParameter("end");
		
		List<Fileupload> files = fileserviceimp.findByDate(start, end);
		ExportExcel exportexcel=new ExportExcel(files);
		exportexcel.export(res);
		
		req.setAttribute("mode","MODE_DOWNLOAD");
	}
	
	@PostMapping("/exportbydate-pdf")
	public void exportbydatetopdf(HttpServletRequest req,HttpServletResponse res) throws DocumentException, IOException{
		res.setContentType("application/pdf");
		String headerKey="Content-Disposition";
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String filename = "users_"+ currentDateTime + ".pdf";
 		String headerValue="attachment; filename=" + filename;
		res.setHeader(headerKey, headerValue);
		
		String start=req.getParameter("start");
		String end=req.getParameter("end");
		
		List<Fileupload> files = fileserviceimp.findByDate(start, end);
		ExportPdf exportpdf=new ExportPdf(files);
		exportpdf.export(res);
		req.setAttribute("mode","MODE_DOWNLOAD_BY_DATE");
	}
}
