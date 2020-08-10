package com.raja.model;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportExcel {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	private List<Fileupload> listuser;
	private int num=0;
	public ExportExcel(List<Fileupload> listuser) {
		super();
		this.listuser = listuser;
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Users");
	}

	public void writeHeaderRow() {
		Row row=sheet.createRow(0);
		
		Cell cell=row.createCell(0);
		cell.setCellValue("Trans ID");
		
		cell = row.createCell(1);
		cell.setCellValue("Accno");
		
		cell = row.createCell(2);
		cell.setCellValue("Date");
		
		cell = row.createCell(3);
		cell.setCellValue("Time");
		
		cell = row.createCell(4);
		cell.setCellValue("Description");
		
		cell = row.createCell(5);
		cell.setCellValue("Withdrawal");
		
		cell = row.createCell(6);
		cell.setCellValue("Credits");
		
		cell = row.createCell(7);
		cell.setCellValue("Running Balance");
	}
	
	public void writeDataRow() {
		int rowCount=1;
		
		for(Fileupload fileupload : listuser)
		{
			Row row = sheet.createRow(rowCount++);
				
				Cell cell = row.createCell(0);
				cell.setCellValue(fileupload.getTrans_id());
				
				cell = row.createCell(1);
				cell.setCellValue(fileupload.getAccno());
				
				cell = row.createCell(2);
				cell.setCellValue(fileupload.getDate());
				
				cell = row.createCell(3);
				cell.setCellValue(fileupload.getTime());
				
				cell = row.createCell(4);
				cell.setCellValue(fileupload.getDescription());
				
				cell = row.createCell(5);
				cell.setCellValue(fileupload.getWithdrawal());
				
				cell = row.createCell(6);
				cell.setCellValue(fileupload.getCredits());
				
				
				if(fileupload.getWithdrawal().equals("0"))
				{
					num+=Integer.parseInt(fileupload.getCredits());
					cell = row.createCell(7);
					cell.setCellValue(num);
				}else if(fileupload.getCredits().equals("0")) {
					num-=Integer.parseInt(fileupload.getWithdrawal());
					cell = row.createCell(7);
					cell.setCellValue(num);
				}			
			}
	}
	
	public void export(HttpServletResponse response) throws IOException{
		writeHeaderRow();
		writeDataRow();
		
		ServletOutputStream outputstream = response.getOutputStream();
		workbook.write(outputstream);
		workbook.close();
		outputstream.close();
	}
}
