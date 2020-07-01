package com.easyLedger.service;

import java.util.List;

import com.easyLedger.domain.CriteriaVO;
import com.easyLedger.domain.MemberVO;
import com.easyLedger.domain.BoardVO;

public interface boardService {
	
	public List<BoardVO> selectAll();

	int registration(BoardVO board);
	
	List<BoardVO> selectPaging(CriteriaVO cri);
	
	int getTotalCount(CriteriaVO cri);
	
	BoardVO getBno(String bno);
	
	int delete(String bno);
	
	int modify(BoardVO board);
	
	int emailCheck(String email);
	
	int memberRegist(MemberVO member);
	
	MemberVO finduserByemail(String email);
	
	MemberVO loginCheck(String email, String pwd) throws NotUserException;

	
	
}
