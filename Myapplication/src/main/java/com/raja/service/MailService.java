package com.raja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.raja.model.EmailClass;
import com.raja.model.LoginUser;

@Service
public class MailService {
	private JavaMailSender javamailsender;
	
	@Autowired
	public MailService(JavaMailSender javamailsender) {
		this.javamailsender=javamailsender;
	}
	
	public void sendEmail(LoginUser loginuser) {
		SimpleMailMessage mail = new SimpleMailMessage(); 
		String name=loginuser.getUsername();
		mail.setTo(loginuser.getEmail());
		mail.setFrom("cheifmanager@gmail.com");
		mail.setSubject("E-Statement account activation");
		mail.setText("Hi Mr/Mrs."+name.substring(0,1).toUpperCase()+name.substring(1)+" ,Your account has been activated by Manager of E-Bank.You can login using the details Email -"+loginuser.getEmail()+" and password- "+loginuser.getPassword());
		System.out.println("Mail has sended");
		javamailsender.send(mail);
	}
}
