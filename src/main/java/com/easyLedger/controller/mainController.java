package com.easyLedger.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easyLedger.domain.CriteriaVO;
import com.easyLedger.domain.MemberVO;
import com.easyLedger.domain.PagingVO;
import com.easyLedger.domain.boardVO;
import com.easyLedger.service.NotUserException;
import com.easyLedger.service.boardService;

import lombok.extern.log4j.Log4j;


@Controller
@Log4j
public class mainController {

	@Inject
	private boardService boardService;
	
	@GetMapping("/regist")
	public String registration() {
		return "registration";
	}
	public void sessionEmail(HttpSession ses, MemberVO memberVo, CriteriaVO cri) {
		MemberVO loginUser = (MemberVO)ses.getAttribute("loginUser");
		memberVo.setEmail(loginUser.getEmail());
		cri.setEmail(loginUser.getEmail());
	}


	@RequestMapping("/main")
	/**mainController.paging*/
	public String paging(Model m,@ModelAttribute CriteriaVO cri,
			MemberVO memberVo,/*HttpServletRequest req*/
			HttpSession ses){

		
		sessionEmail(ses, memberVo, cri);
		List<boardVO> blist = boardService.selectPaging(cri);
		int totalCount = boardService.getTotalCount(cri);
		PagingVO paging = new PagingVO(totalCount, cri);
		m.addAttribute("boardList",blist);
		System.out.println("blist"+blist);
		m.addAttribute("totalCount",totalCount);
		System.out.println("totalCount"+totalCount);
		m.addAttribute("paging",paging);
		m.addAttribute("email",cri.getEmail());
		
		log.info("cri.getEmail() "+cri.getEmail());
		log.info(blist);
		
		return "main";
		
	}
	
	@PostMapping("/regist")
	public String regist(@ModelAttribute boardVO board, Model m, 
			HttpSession ses, MemberVO memberVo, CriteriaVO cri) {
		
		sessionEmail(ses, memberVo, cri);
		board.setMember_email(memberVo.getEmail());
		int n = this.boardService.registration(board);
		
		String msg = (n > 0) ? "등록 성공":"등록 실패";
		String loc = (n > 0) ? "javascript:opener.parent.location.reload(); "
								+ "window.close();" : "javascript:history.back()";

		m.addAttribute("msg",msg);
		m.addAttribute("loc",loc);
	
		return "message";
	}
	
	@GetMapping("/modify")
	public String get(@RequestParam(defaultValue = "0") String bno,
						Model m) {
		
		boardVO boardVo = boardService.getBno(bno);
		m.addAttribute("board", boardVo);

		return "modify";
	}
	
	@PostMapping("/modify")
	public String modification(@RequestParam(defaultValue = "")String mode, 
							   @RequestParam(defaultValue = "")String mode2, 
							   boardVO board, @RequestParam(defaultValue = "0") String bno, 
							   Model m) {
		try {
		
		int n = 0;
		String msg = "";
		if(mode.equals("mdf")) {
			n = this.boardService.modify(board);
			msg = "수정";
		}else if(mode2.equals("dlt")) {
			n = this.boardService.delete(bno);
			msg = "삭제";
		}
		
				
		msg += (n > 0) ? " 성공" :" 실패";
		String loc=(n > 0) ? "javascript:opener.parent.location.reload(); "
				+ "window.close();" : "javascript:history.back()";
				
		m.addAttribute("msg",msg);
		m.addAttribute("loc",loc);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "message";
	}

	@GetMapping("/signup")
	public String memberRegist() {
		return "Signup";
	}
	
	@PostMapping("/signup")
	public String memberRegist(MemberVO member, Model m) {
		
		int n=boardService.memberRegist(member);
		
		String msg=(n>0)?"회원가입 성공":"회원가입 실패";
		String loc=(n>0)?"/easyLedger":"javascript:history.back()";
		
		m.addAttribute("msg",msg);
		m.addAttribute("loc",loc);
						
		return "message";
	}

	@RequestMapping(value = "signup/emailcheck", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody int emailCheck(@RequestParam("email") String email) {
		
		int n=boardService.emailCheck(email);
		System.out.println(email);
		
		return n;
		
	}
	
	@GetMapping("/signin")
	public String signin() {
		return "Signin";
	}
	
	@PostMapping("/signin")
	public String signin(
			@RequestParam(name="email", defaultValue ="")String email,
			@RequestParam(name="pwd", defaultValue = "")String pwd,
			Model m, HttpSession ses, HttpServletResponse res)
			throws NotUserException{
		
		if(email.trim().isEmpty()||pwd.trim().isEmpty()) {
			return "redirect:/";
		}
		MemberVO loginUser=boardService.loginCheck(email, pwd);
		if(loginUser!=null) {
			ses.setAttribute("email", email);
			ses.setAttribute("loginUser", loginUser);
			ses.setMaxInactiveInterval(-1);
			
		}
		
		return "redirect:main";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession ses) {
		if(ses.getAttribute("loginUser") != null) {
			ses.invalidate(); 
		}
		return "redirect:/";
	}
	
	
}
