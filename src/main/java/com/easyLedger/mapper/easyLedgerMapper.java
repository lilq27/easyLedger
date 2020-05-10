package com.easyLedger.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.easyLedger.domain.CriteriaVO;
import com.easyLedger.domain.boardVO;
import com.easyLedger.domain.memberVO;
import com.easyLedger.service.NotUserException;

public interface easyLedgerMapper {
	
	public List<boardVO> selectAll();
	
	int registration(boardVO board);
	
	List<boardVO> selectPaging(
			@Param("cri") CriteriaVO cri,
			@Param("email") memberVO member		
			);

	int getTotalCount(CriteriaVO cri);

	boardVO getMemberEmail(String member_eamil);
	
	int delete(String member_eamil);
	
	int modify(boardVO board);
	
	int emailCheck(String email);
	
	int memberRegist(memberVO member);

	memberVO finduserByemail(String email);
	
	memberVO loginCheck(String email, String pwd) throws NotUserException;
}
