package com.easyLedger.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.easyLedger.domain.CriteriaVO;
import com.easyLedger.domain.PagingVO;
import com.easyLedger.domain.boardVO;
import com.easyLedger.service.NotUserException;

public interface easyLedgerMapper {
	
	public List<boardVO> selectAll();
	
	int registration(boardVO board);
	
	List<boardVO> selectPaging(CriteriaVO loginUser,CriteriaVO cri);

	int getTotalCount(CriteriaVO cri);

	boardVO getMemberEmail(String member_eamil);
	
	int delete(String member_eamil);
	
	int modify(boardVO board);
	
	int emailCheck(String email);
	
	int memberRegist(CriteriaVO member);

	CriteriaVO finduserByemail(String email);
	
	CriteriaVO loginCheck(String email, String pwd) throws NotUserException;
}
