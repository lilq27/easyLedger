package com.easyLedger.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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
import com.easyLedger.domain.PagingVO;
import com.easyLedger.domain.boardVO;
import com.easyLedger.domain.CriteriaVO;
import com.easyLedger.service.NotUserException;
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
	public String paging(Model m,@ModelAttribute CriteriaVO cri,
			/*HttpServletRequest req*/
			HttpSession ses){
	
	
		
	//if(blist!=null) {
		int totalCount=boardService.getTotalCount(cri);
		
		PagingVO paging =new PagingVO(totalCount, cri);
		CriteriaVO loginUser=(CriteriaVO)ses.getAttribute("loginUser");
		
		List<boardVO> blist=boardService.selectPaging(loginUser,cri);
		
		/*String sesEmail=req.getParameter(member.getEmail());*/
		
		m.addAttribute("boardList",blist);
		m.addAttribute("totalCount",totalCount);
		m.addAttribute("paging",paging);
		m.addAttribute("email",loginUser.getEmail());
		
		System.out.println(loginUser.getEmail());
	//}
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
	public String get(@RequestParam(defaultValue = "0") String member_email,Model m) {
		
		boardVO getNameId=boardService.getMemberEmail(member_email);		
		m.addAttribute("board", getNameId );

		return "modify";
	}
	
	@PostMapping("/modify")
	public String modification(@RequestParam(defaultValue = "")String mode,@RequestParam(defaultValue = "")String mode2, 
			boardVO board, @RequestParam(defaultValue = "0") String member_email, Model m) {
		try {
		
		int n=0;
		String msg="";
		if(mode.equals("mdf")) {
			n=this.boardService.modify(board);
			msg="수정";
		}else if(mode2.equals("dlt")) {
			n=this.boardService.delete(member_email);
			msg="삭제";
		}
		
				
		msg+=(n>0)?" 성공":" 실패";
		String loc=(n>0)?"/easyLedger/modify?name_id="+member_email:"javascript:history.back()";
				
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
	public String memberRegist(CriteriaVO member, Model m) {
		
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
		CriteriaVO loginUser=boardService.loginCheck(email, pwd);
		if(loginUser!=null) {
			ses.setAttribute("email", email);
			ses.setAttribute("loginUser", loginUser);
			
		}
		
		return "redirect:main";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession ses) {
		ses.invalidate(); 
		return "redirect:/";
	}
	
	
}
