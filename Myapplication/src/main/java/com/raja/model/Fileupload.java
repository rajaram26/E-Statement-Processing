package com.raja.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="client_details")
public class Fileupload {
	
	@Id
	private String trans_id;
	private String accno;
	private String date;
	private String time;
	private String description;
	private String withdrawal;
	private String credits;
	
	@Transient
	private MultipartFile file;
	
	
	public Fileupload(String trans_id,String accno, String date, String time, String description, String withdrawal, String credits) {
		super();
		this.trans_id = trans_id;
		this.accno = accno;
		this.date = date;
		this.time = time;
		this.description = description;
		this.withdrawal = withdrawal;
		this.credits = credits;
	}
	
	
	public Fileupload() {
		// TODO Auto-generated constructor stub
	}

	
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWithdrawal() {
		return withdrawal;
	}
	public void setWithdrawal(String withdrawal) {
		this.withdrawal = withdrawal;
	}
	public String getCredits() {
		return credits;
	}
	public void setCredits(String credits) {
		this.credits = credits;
	}


	public String getTrans_id() {
		return trans_id;
	}

	public void setTrans_id(String trans_id) {
		this.trans_id = trans_id;
	}


	@Override
	public String toString() {
		return "Fileupload [trans_id=" + trans_id + ", accno=" + accno + ", date=" + date + ", time=" + time
				+ ", description=" + description + ", withdrawal=" + withdrawal + ", credits=" + credits + "]";
	}
	
	
}
