package com.easyLedger.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.easyLedger.domain.BoardVO;

public interface ExcelService {

	void selectExcelList(HttpServletResponse response);
	
	List<BoardVO> selectBoardList(String email);
	
	SXSSFWorkbook excelWorkBook(List<BoardVO> boardList);
	
	List<BoardVO> uploadExcelFile(MultipartFile excelFile);

}
