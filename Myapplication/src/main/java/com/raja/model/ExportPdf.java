package com.raja.model;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ExportPdf {
	private List<Fileupload> listuser;
	private String find;
	private int num=0;
	
	public ExportPdf(List<Fileupload> listuser) {
		super();
		this.listuser = listuser;
	}
	
	private void writeTableHeader(PdfPTable  table) {
		PdfPCell cell=new PdfPCell();
		cell.setBackgroundColor(Color.white);
		cell.setPadding(5);
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.BLACK);
		
		cell.setPhrase(new Phrase("Trans_id",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Acc_No",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Date",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Time",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Description",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Withdrawal",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Credits",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Running Balance",font));
		table.addCell(cell);
	}
	
	private void writeTableData(PdfPTable  table) {
		for(Fileupload file : listuser)
		{	
				table.addCell(file.getTrans_id());
				table.addCell(file.getAccno());
				table.addCell(file.getDate());
				table.addCell(file.getTime());
				table.addCell(file.getDescription());
				table.addCell(file.getWithdrawal());
				table.addCell(file.getCredits());
				if(file.getWithdrawal().equals("0"))
				{
					num+=Integer.parseInt(file.getCredits());
					table.addCell(String.valueOf(num));
				}else if(file.getCredits().equals("0")) {
					num-=Integer.parseInt(file.getWithdrawal());
					table.addCell(String.valueOf(num));
				}
		}
	}
	
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		
		PdfWriter.getInstance(document,response.getOutputStream());
		
		document.open();
		document.add(new Paragraph("Account Statement Of "+find));
		PdfPTable  table = new PdfPTable(8);
		table.setWidthPercentage(100);
		table.setSpacingBefore(15);
		
		writeTableHeader(table);
		writeTableData(table);
		
		document.add(table);
		document.close();
	}
}
