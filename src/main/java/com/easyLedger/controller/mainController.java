package com.easyLedger.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easyLedger.domain.BoardVO;
import com.easyLedger.domain.CriteriaVO;
import com.easyLedger.domain.MemberVO;
import com.easyLedger.domain.PagingVO;
import com.easyLedger.service.NotUserException;
import com.easyLedger.service.boardService;

import lombok.extern.log4j.Log4j;


@Controller
@Log4j
public class mainController {

	@Inject
	private boardService boardService;
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private HttpServletRequest httpRequest;
	
	public MemberVO sessionEmail() {
		MemberVO loginUser = (MemberVO)httpSession.getAttribute("loginUser");
		System.out.println("getloginUser22222");
		if(loginUser != null && !loginUser.toString().isEmpty()) {
			System.out.println("getloginUser33333"+ loginUser.getEmail() + loginUser.getPwd());
			return loginUser;
		} else {
			
			
			String email = null;
			String pwd = null;
			
			Cookie [] cookies = httpRequest.getCookies();
		   	try {
				for(Cookie cookie : cookies) {
					
					
					if("email".equals(cookie.getName())) {
						email = cookie.getValue();
					}
					
					if("pwd".equals(cookie.getName())) {
						pwd = cookie.getValue();
					}
				}
			    
			    loginUser = boardService.loginCheck(email.toString(), pwd.toString());
			    
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return loginUser;
		}
	}

	@RequestMapping("/main")
	/** mainController.paging */
	public String paging(Model m, @ModelAttribute CriteriaVO cri,
			MemberVO memberVo, HttpSession ses) {
		
		if(sessionEmail() != null) {
			cri.setEmail(sessionEmail().getEmail());
			List<BoardVO> blist = boardService.selectPaging(cri);
			int totalCount = boardService.getTotalCount(cri);
			PagingVO paging = new PagingVO(totalCount, cri);
			
			m.addAttribute("boardList",blist);
			m.addAttribute("totalCount",totalCount);
			m.addAttribute("paging",paging);
			m.addAttribute("email",cri.getEmail());
			
			log.info("cri.getEmail(): "+cri.getEmail());
			log.info("blist: "+blist);
			log.info("totalCount: "+totalCount);
			
			String userAgent = httpRequest.getHeader("User-Agent").toUpperCase();
			
			httpSession.setAttribute("User-Agent", userAgent);
			
			
			return "main";
		
		}
		
		return "main";
	}
	
	@GetMapping("/regist")
	public String registration() {
		return "registration";
	}
	
	@PostMapping("/regist")
	public String regist(@ModelAttribute BoardVO board, Model m, 
			MemberVO memberVo, CriteriaVO cri) {
		
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
		
		BoardVO boardVo = boardService.getBno(bno);
		m.addAttribute("board", boardVo);

		return "modify";
	}
	
	@PostMapping("/modify")
	public String modification(@RequestParam(defaultValue = "")String mode, 
							   @RequestParam(defaultValue = "")String mode2, 
							   BoardVO board, @RequestParam("bno") String bno, 
							   Model m) {
		try {	
			int n = 0;
			String msg = "";
			
			if(mode != null && !mode.isEmpty()) {
				if("mdf".equals(mode)) {
					n = this.boardService.modify(board);
					msg = "수정";
				}
			}
			
			if(mode2 != null && !mode2.isEmpty()) {	
				if("dlt".equals(mode2)) {
					n = this.boardService.delete(bno);
					msg = "삭제";
				}
			}
			
			msg += (n > 0) ? " 성공" :" 실패";
			String loc = (n > 0) ? "javascript:opener.parent.location.reload(); "
					+ "window.close();" : "javascript:history.back()";
					
			m.addAttribute("msg",msg);
			m.addAttribute("loc",loc);
		} catch(Exception e) {
			// TODO: handle exception
			log.info("modify error: " +e);
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
		
		String msg = (n > 0) ? "회원가입 성공" : "회원가입 실패";
		String loc = (n > 0) ? "/easyLedger" : "javascript:history.back()";
		
		m.addAttribute("msg", msg);
		m.addAttribute("loc", loc);
						
		return "message";
	}

	@ResponseBody
	@RequestMapping(value = "signup/emailcheck", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public void emailCheck(@RequestParam String email, HttpServletResponse response) {
		boolean useEmail = false;
		int n = boardService.emailCheck(email);
		if(n > 0) {
			useEmail = true;
		}
		
		try {
			PrintWriter out = response.getWriter();
			out.print(useEmail);
			out.flush();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/signin")
	public String signin() {
		return "Signin";
	}
	
	@PostMapping("/signin")
	public String signin(
			@RequestParam(name="email", defaultValue ="")String email,
			@RequestParam(name="pwd", defaultValue = "")String pwd,
			Model m, HttpServletResponse res)
			throws NotUserException{
		
		if(email.trim().isEmpty()||pwd.trim().isEmpty()) {
			return "redirect:/";
		}
		MemberVO loginUser=boardService.loginCheck(email, pwd);
		
		if(loginUser!=null && !loginUser.toString().isEmpty()) {
			Cookie cookie = new Cookie("email", email);
			res.addCookie(cookie);
			httpSession.setAttribute("email", cookie);
			
			cookie = new Cookie("pwd", pwd);
			res.addCookie(cookie);
			httpSession.setAttribute("pwd", cookie);
			
			httpSession.setAttribute("loginUser", loginUser);
			httpSession.setMaxInactiveInterval(-1);
			
		}
		
		return "redirect:main";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		if(httpSession.getAttribute("loginUser") != null) {
			httpSession.invalidate();
		}
		return "redirect:/";
	}	
	
	
	@ResponseBody
	@GetMapping("/capture_test")
	public void test(String url, HttpServletRequest request, HttpServletResponse res) throws NotUserException {
		
		System.out.println("url: "+ url);
		String email = null;
		String pwd = null;
		
//		Cookie [] cookies = request.getCookies();

		
		System.setProperty("webdriver.chrome.driver", "/Users/yeonguksong/Downloads/chromedriver_mac64/chromedriver");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--window-size=1920, 1280");
		
//		options.setCapability("email", "asd");
//		options.setCapability("pwd", "zcx");
		
		WebDriver driver = new ChromeDriver(options);
		
		MemberVO getloginUser = (MemberVO)httpSession.getAttribute("loginUser");
		
		System.out.println("getloginUser1111"+ getloginUser.getEmail() + getloginUser.getPwd());

//		driver.manage().addCookie(new org.openqa.selenium.Cookie("email", getloginUser.getEmail(), url, "/", null ) );
//		driver.manage().addCookie(new org.openqa.selenium.Cookie("pwd", getloginUser.getPwd(), url, "/", null ) );
		
		driver.get(url);
		
		driver.manage().addCookie(new org.openqa.selenium.Cookie("email", getloginUser.getEmail() ) );
		driver.manage().addCookie(new org.openqa.selenium.Cookie("pwd", getloginUser.getPwd() ) );
		
		
		
   		email = driver.manage().getCookieNamed("email").toString();
	    System.out.println("email!!!!" + email);
	    
	    pwd = driver.manage().getCookieNamed("pwd").toString();
	    System.out.println("pwd!!!" + pwd);
	    
	    Set<org.openqa.selenium.Cookie> cookies = driver.manage().getCookies();
	    
		for(org.openqa.selenium.Cookie cookie : cookies) {
			
			if("email".equals(cookie.getName())) {
				email = cookie.getValue();
			}
			
			if("pwd".equals(cookie.getName())) {
				pwd = cookie.getValue();
			}
		}
	    
	    
		Cookie cookie = new Cookie("email", email);
		res.addCookie(cookie);
		
		cookie = new Cookie("pwd", pwd);
		res.addCookie(cookie);
		
		driver.navigate().refresh();
		
		WebElement element = null;
		element = driver.findElement(By.xpath("//*[@id=\"Container\"]/div[1]/ul/li[2]/a"));
		element.click();
		element = driver.findElement(By.id("Container"));
		File elementScreenShot = element.getScreenshotAs(OutputType.FILE);
		
		
		try {
			FileUtils.copyFile(elementScreenShot, new File("/Users/yeonguksong/Downloads/chromedriver_mac64/el_ss.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		driver.quit();
	}
}