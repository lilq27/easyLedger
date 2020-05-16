package com.easyLedger.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.easyLedger.domain.CriteriaVO;
import com.easyLedger.domain.PagingVO;
import com.easyLedger.domain.boardVO;
import com.easyLedger.domain.memberVO;

public interface boardService {
	
	public List<boardVO> selectAll();

	int registration(boardVO board);
	
	List<boardVO> selectPaging(String email,CriteriaVO cri);
	
	int getTotalCount(CriteriaVO cri , String email);
	
	boardVO getMemberEmail(String member_eamil);
	
	int delete(String member_eamil);
	
	int modify(boardVO board);
	
	int emailCheck(String email);
	
	int memberRegist(memberVO member);
	
	memberVO finduserByemail(String email);
	
	memberVO loginCheck(String email, String pwd) throws NotUserException;
	
	
}
