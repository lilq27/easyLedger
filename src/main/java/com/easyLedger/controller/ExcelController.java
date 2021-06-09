package com.easyLedger.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
		
		ExcelDownloadView excelDownloadView = new ExcelDownloadView();
		
		mainController.sessionEmail(memberVo, cri);
		
		List<BoardVO> boardList = excelService.selectBoardList(cri.getEmail());
		
		SXSSFWorkbook workBook = excelService.excelWorkBook(boardList);
		
		model.addAttribute("locale", Locale.KOREA);
		model.addAttribute("workBook", workBook);
		model.addAttribute("workBookName", "easyLedger");
		
		return excelDownloadView;
	}
	
	@GetMapping("/excelForm")
	public String excelForm(Model model) {
		model.addAttribute("msg", "gs");
		return "excelForm";
	}
	
	@ResponseBody
	@PostMapping("/excel_upload")
	public Boolean uploadExcelFile(boolean longFile,
			MultipartFile[] file, CriteriaVO cri, MemberVO memberVo, HttpSession ses, Model model) {
		//boolean은 null불가, Boolean은 null 가능 
		mainController.sessionEmail(memberVo, cri);
		
		List<BoardVO> excelList = excelService.uploadExcelFile(file[0]);
		
		int n = 0;
		
		if(excelList != null && !excelList.isEmpty()) {	
			for(BoardVO board : excelList) {
				board.setMember_email(cri.getEmail());
				n = boardService.registration(board);
			}
		} else if(excelList == null) {
			longFile = true;
			return longFile;
		}
		
		longFile = (n > 0) ? false : true;
		return longFile;
	}
}
