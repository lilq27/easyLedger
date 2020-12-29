package com.easyLedger.etc;

import org.springframework.web.multipart.MultipartFile;

public class ExcelUploadView {

	public static MultipartFile getwWorkbook(MultipartFile excelFile) {
		
		
		String excelFileName = excelFile.getOriginalFilename();
		
		if(excelFileName.toUpperCase().endsWith(".XLS") ) {
			
			return excelFile;
			
		} else if(excelFileName.toUpperCase().endsWith(".XLSX")) {
			
			return excelFile;
		}
		
		return null;
	}
}
