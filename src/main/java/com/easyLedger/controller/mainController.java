package com.easyLedger.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.easyLedger.domain.boardVO;
import com.easyLedger.service.boardService;


@Controller
public class mainController {

	@Inject
	private boardService boardService;
	
	
	@GetMapping
	public String main(Model m) {
		
		List<boardVO> selectAll = this.boardService.selectAll();
		
		m.addAttribute("selectAll", selectAll);
			
		return "main";
	}
	

	
}
