package com.raja.service;


import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.raja.model.LoginUser;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Configuration;

@Service
public class MailService {
	
	private JavaMailSender javamailsender;
	
	@Autowired
	@Qualifier("emailConfigBean")
    private Configuration emailConfig;
	
	@Autowired
	public MailService(JavaMailSender javamailsender) {
		this.javamailsender=javamailsender;
	}
	
//	public void sendEmail(LoginUser loginuser) {
//		SimpleMailMessage mail = new SimpleMailMessage(); 
//		String name=loginuser.getUsername();
//		mail.setTo(loginuser.getEmail());
//		mail.setFrom("cheifmanager@gmail.com");
//		mail.setSubject("E-Statement account activation");
//		mail.setText("Hi Mr/Mrs."+name.substring(0,1).toUpperCase()+name.substring(1)+" ,Your account has been activated by Manager of E-Bank.You can login using the details Email -"+loginuser.getEmail()+" and password- "+loginuser.getPassword());
//		System.out.println("Mail has sended");
//		javamailsender.send(mail);
//	}
	
	public void signupMail(LoginUser loginuser) throws MessagingException, IOException, TemplateException {

        MimeMessage message = javamailsender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Template template = emailConfig.getTemplate("email.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, loginuser);

        mimeMessageHelper.setTo(loginuser.getEmail());
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject("E-Bank account activation");
        mimeMessageHelper.setFrom("cheifmanager@gmail.com");
        javamailsender.send(message);
        System.out.print("Mail is sended with template");
    }
}
