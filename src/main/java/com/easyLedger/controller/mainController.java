package com.easyLedger.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.easyLedger.domain.CriteriaVO;
import com.easyLedger.domain.PagingVO;
import com.easyLedger.domain.boardVO;
import com.easyLedger.service.boardService;


@Controller
public class mainController {

	@Inject
	private boardService boardService;
	
	@GetMapping("/regist")
	public String registration() {
		return "registration";
	}

	@RequestMapping("/main")
	public String paging(Model m, @ModelAttribute CriteriaVO cri) {
	
		List<boardVO> blist=boardService.selectPaging(cri);
	
		int totalCount=boardService.getTotalCount(cri);
		
		PagingVO paging =new PagingVO(totalCount, cri);
		
		m.addAttribute("boardList",blist);
		m.addAttribute("totalCount",totalCount);
		m.addAttribute("paging",paging);
		
		
		return "main";
		
	}
	
	@PostMapping("/regist")
	public String regist(@ModelAttribute boardVO board,Model m) {
		
		int n=this.boardService.registration(board);
		
		String msg=(n>0)?"등록 성공":"등록 실패";
		String loc=(n>0)?"/easyLedger/regist":"javascript:history.back()";
		
		m.addAttribute("msg",msg);
		m.addAttribute("loc",loc);

		
		return "message";
	}
	
	@GetMapping("/modify")
	public String get(@RequestParam(defaultValue = "0") int name_id,Model m) {
		
		boardVO getNameId=boardService.getNameId(name_id);		
		m.addAttribute("board", getNameId );

		return "modify";
	}
	
	@PostMapping("/modify")
	public String modification(@RequestParam(defaultValue = "")String mode,@RequestParam(defaultValue = "")String mode2, 
			boardVO board, @RequestParam(defaultValue = "0") int name_id, Model m) {
		try {
		
		int n=0;
		String msg="";
		if(mode.equals("mdf")) {
			n=this.boardService.modify(board);
			msg="수정";
		}else if(mode2.equals("dlt")) {
			n=this.boardService.delete(name_id);
			msg="삭제";
		}
		
				
		msg+=(n>0)?" 성공":" 실패";
		String loc=(n>0)?"/easyLedger/modify?name_id="+name_id:"javascript:history.back()";
				
		m.addAttribute("msg",msg);
		m.addAttribute("loc",loc);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "message";
	}
	
	
}
