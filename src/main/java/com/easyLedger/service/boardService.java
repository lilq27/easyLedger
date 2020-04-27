package com.easyLedger.service;

import java.util.List;

import com.easyLedger.domain.CriteriaVO;
import com.easyLedger.domain.PagingVO;
import com.easyLedger.domain.boardVO;
import com.easyLedger.domain.memberVO;

public interface boardService {
	
	public List<boardVO> selectAll();

	int registration(boardVO board);
	
	List<boardVO> selectPaging(CriteriaVO cri);
	
	int getTotalCount(CriteriaVO cri);
	
	boardVO getNameId(Integer name_id);
	
	int delete(Integer name_id);
	
	int modify(boardVO board);
	
	int emailCheck(String email);
	
	int memberRegist(memberVO member);
	
}
