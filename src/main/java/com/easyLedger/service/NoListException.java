package com.easyLedger.service;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoListException extends Exception {
	
	public NoListException() {
		// TODO Auto-generated constructor stub
	}
	@Inject
	private boardService boardservice;
	
	@RequestMapping("/main2")
	public String main() {
		return "easeyLedger";
	}

}
