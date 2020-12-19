package com.easyLedger.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.easyLedger.domain.BoardVO;
import com.easyLedger.domain.CriteriaVO;
import com.easyLedger.domain.MemberVO;
import com.easyLedger.etc.ExcelDownloadView;
import com.easyLedger.service.ExcelService;
import com.easyLedger.service.boardService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class ExcelController {
	
	@Autowired
	private mainController mainController; 
	
	@Autowired
	private ExcelService excelService;
	
	@Autowired
	private boardService boardService;

	@GetMapping("/excel_down")
	public ExcelDownloadView excelDown(CriteriaVO cri, MemberVO memberVo, HttpSession ses, Model model) {
		
		mainController.sessionEmail(ses, memberVo, cri);
		
		List<BoardVO> boardList = excelService.selectBoardList(cri.getEmail());
		
		SXSSFWorkbook workBook = excelService.excelWorkBook(boardList);
		
		model.addAttribute("locale", Locale.KOREA);
		model.addAttribute("workBook", workBook);
		model.addAttribute("workBookName", "easyLedger");
		
		ExcelDownloadView excelDownloadView = new ExcelDownloadView();
		
		return excelDownloadView;
	}
	
	@GetMapping("excel_upload")
	public String uploadExcelFile(MultipartHttpServletRequest request, Model model) {
		
		MultipartFile file = null;
		Iterator<String> iterator = request.getFileNames();
		if(iterator.hasNext()) {
			file = request.getFile(iterator.next());
		}
		
		List<BoardVO> excelList = excelService.uploadExcelFile(file);
		
		int n = 0;
		for(BoardVO board : excelList) {
			n = boardService.registration(board);
		}
		
		String msg = (n > 0) ? "업로드 완료" : "업로드 실패";
		
		model.addAttribute("msg", msg);
		
		return "redirect:main";
	}
}
