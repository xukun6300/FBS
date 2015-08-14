package sg.com.fbs.core.techinfra.web.exportdata;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 * @Author Frank Xu $
 * @Created 1:57:06 pm 2 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class ExportExcelView extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<List<String>> result = (List<List<String>>) model.get("result");
		
		HSSFSheet sheet = workbook.createSheet("exportdata");
		
		if(result!=null && result.size()>0){
			//header row
			HSSFRow excelRow = sheet.createRow(0);
			HSSFCellStyle headerStyle = workbook.createCellStyle();
			HSSFFont font = workbook.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			headerStyle.setFont(font);
			
			List<String> header = result.get(0);
			
			for (int i = 0; i < header.size(); i++) { //iterate header columns
				int lastIdxDelimiter = header.get(i).lastIndexOf("_");//to remove the _width
				HSSFCell cell = excelRow.createCell(i);
				cell.setCellValue(header.get(i).substring(0, lastIdxDelimiter));
				cell.setCellStyle(headerStyle);
			}
			
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setWrapText(true);
			
			for (int i = 1; i < result.size(); i++) {
				List<String> row = result.get(i);
				excelRow = sheet.createRow(i);
				
				for (int j = 0; j < row.size(); j++) {
					HSSFCell cell = excelRow.createCell(j);
					cell.setCellValue(row.get(j));
					cell.setCellStyle(cellStyle);
				}
			}
			
			for (int i = 0; i < sheet.getWorkbook().getSheetAt(0).getPhysicalNumberOfRows(); i++) {
				excelRow = sheet.getWorkbook().getSheetAt(0).getRow(i);
				
				for (int j = 0; j < excelRow.getPhysicalNumberOfCells(); j++) {
					String cellValue = excelRow.getCell(j).getStringCellValue();
					String fontName = excelRow.getCell(j).getCellStyle().getFont(sheet.getWorkbook()).getFontName();
					short fontHeight = excelRow.getCell(j).getCellStyle().getFont(sheet.getWorkbook()).getFontHeightInPoints();
					
					if(cellValue.length()>256){
						excelRow.getSheet().setColumnWidth(j, 20000);
					}else {
						excelRow.getSheet().autoSizeColumn(j);
					}
					
					if(cellValue.length()>0){
						excelRow.setHeight((short)(excelRow.getHeight()*getRowHeight(cellValue, fontName,(int)fontHeight)));
					}
				}
			}
			
		}
		response.setHeader("Content-disposition", "attachment; filename=export_data.xls");
	}

	//??
	private int getRowHeight(String cellValue, String fontName, int fontSize){
		Font currFont = new Font(fontName, 0, fontSize);
		AttributedString attrStr = new AttributedString(cellValue);
		attrStr.addAttribute(TextAttribute.FONT, currFont);
		
		FontRenderContext frc = new FontRenderContext(null, true, true);
		LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
		
		int nextPos = 0;
		int lineCnt = 0;
		while(measurer.getPosition()<cellValue.length()){
			nextPos = measurer.nextOffset(256);
			lineCnt++;
			measurer.setPosition(nextPos);
		}
		return lineCnt;
	}
	
	
}


























