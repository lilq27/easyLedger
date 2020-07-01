package com.easyLedger.mapper;

import java.util.List;

import com.easyLedger.domain.CriteriaVO;
import com.easyLedger.domain.BoardVO;
import com.easyLedger.domain.MemberVO;
import com.easyLedger.service.NotUserException;

public interface easyLedgerMapper {
	
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
