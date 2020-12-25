package com.easyLedger.etc;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelUploadView {

	public static Workbook getwWorkbook(String filePath) {
		
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Workbook workBook = null;
		
		if(filePath.toUpperCase().endsWith(".XLS") ) {
			try {
				workBook = new HSSFWorkbook(fileInputStream);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(filePath.toUpperCase().endsWith(".XLSX")) {
			try {
				workBook = new HSSFWorkbook(fileInputStream);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return workBook;
	}
}
