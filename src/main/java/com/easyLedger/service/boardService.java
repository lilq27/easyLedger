package com.easyLedger.service;

import java.util.List;

import com.easyLedger.domain.CriteriaVO;
import com.easyLedger.domain.MemberVO;
import com.easyLedger.domain.boardVO;

public interface boardService {
	
	public List<boardVO> selectAll();

	int registration(boardVO board);
	
	List<boardVO> selectPaging(CriteriaVO cri);
	
	int getTotalCount(String email);
	
	boardVO getMemberEmail(String member_eamil);
	
	int delete(String member_eamil);
	
	int modify(boardVO board);
	
	int emailCheck(String email);
	
	int memberRegist(MemberVO member);
	
	MemberVO finduserByemail(String email);
	
	MemberVO loginCheck(String email, String pwd) throws NotUserException;

	
	
}
