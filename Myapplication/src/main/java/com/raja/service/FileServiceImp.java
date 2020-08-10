package com.raja.service;

import java.io.InputStreamReader;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.raja.model.Fileupload;
import com.raja.respositary.FileUploadRepositary;


@Service
@Transactional
public class FileServiceImp implements FileService {
	
	@Autowired
	private FileUploadRepositary fileuploadrepositary;
	
	@Override
	public  List<Fileupload> findAll() {
		
		return (List<Fileupload>) fileuploadrepositary.findAll();
	}

	public boolean saveDataFromUpload(MultipartFile file) {
		boolean isFlag=false;
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		if(extension.equalsIgnoreCase("csv"))
		{
			isFlag=readDataFromCsv(file);
		}else {
			return false;
		}
		return isFlag;
	}

	private boolean readDataFromCsv(MultipartFile file) {
		try {
			InputStreamReader reader = new InputStreamReader(file.getInputStream());
			CSVReader csvreader = new CSVReaderBuilder(reader).withSkipLines(1).build();
			List<String[]> rows = csvreader.readAll();
			for(String[] row : rows )
			{
				fileuploadrepositary.save(new Fileupload(row[0], row[1], row[2], row[3],row[4],row[5],row[6]));
			}
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	

	public List<Fileupload> findByDate(String start, String end) {
		return (List<Fileupload>) fileuploadrepositary.findbydate(start,end);
	}

	
	
	public List<Fileupload> findByName(String name) {
		return (List<Fileupload>) fileuploadrepositary.findbyname(name);
	}
	
}
