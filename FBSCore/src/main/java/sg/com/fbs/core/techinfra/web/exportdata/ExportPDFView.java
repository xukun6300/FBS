package sg.com.fbs.core.techinfra.web.exportdata;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @Author Frank Xu $
 * @Created 1:57:11 pm 2 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class ExportPDFView extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<List<String>> result = (List<List<String>>) model.get("result");
		if (result != null && result.size() > 0) {
			int noOfColumns = result.get(0).size();
			
			PdfPTable table = new PdfPTable(noOfColumns);
			
			int j = 0;
			float[] widths = new float[noOfColumns];
			int controlledCnt = 0;
			//header
			for (String value : result.get(0)) {
				int lastIdxDelimiter = value.lastIndexOf("_");
				
				float tWVal = Float.valueOf(value.substring(lastIdxDelimiter+1)).floatValue();
				widths[j] = tWVal;
				if(tWVal!=0){
					controlledCnt++;
				}
				
				Phrase p = new Phrase(value.substring(0, lastIdxDelimiter), FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD));
				PdfPCell cell = new PdfPCell(p);
				table.addCell(cell);
				j++;			
			}
			
			if(controlledCnt == noOfColumns){
				table.setWidths(widths);
			}
			
			//1 for ignore header row
			for (int i = 1; i < result.size(); i++) {
				List<String> row = result.get(i);
				for (String value : row) {
					Phrase phrase = new Phrase(value, FontFactory.getFont(FontFactory.HELVETICA, 8));
					PdfPCell cell = new PdfPCell(phrase);
					cell.setNoWrap(false);
					table.addCell(cell);
				}
			}
			
			document.add(table);
		}
	}

	@Override
	protected Document newDocument() {
		Document document = new Document(PageSize.A4.rotate());
		document.setMargins(0, 0, 30, 0);
		return document;
	}
	
}















