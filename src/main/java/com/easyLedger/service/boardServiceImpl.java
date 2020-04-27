package com.easyLedger.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.easyLedger.domain.CriteriaVO;
import com.easyLedger.domain.PagingVO;
import com.easyLedger.domain.boardVO;
import com.easyLedger.domain.memberVO;
import com.easyLedger.mapper.easyLedgerMapper;
@Service(value="boardSI")
public class boardServiceImpl implements boardService {

	@Inject
	private easyLedgerMapper elMapper;
	
	@Override
	public List<boardVO> selectAll() {
		return this.elMapper.selectAll();
	}
	
	@Override
	public int registration(boardVO board) {
		return this.elMapper.registration(board);
	}

	@Override
	public List<boardVO> selectPaging(CriteriaVO cri) {
		// TODO Auto-generated method stub
		return elMapper.selectPaging(cri);
	}

	@Override
	public int getTotalCount(CriteriaVO cri) {
		// TODO Auto-generated method stub
		return elMapper.getTotalCount(cri);
	}

	@Override
	public boardVO getNameId(Integer name_id) {
		// TODO Auto-generated method stub
		return elMapper.getNameId(name_id);
	}

	@Override
	public int delete(Integer name_id) {
		// TODO Auto-generated method stub
		return elMapper.delete(name_id);
	}

	@Override
	public int modify(boardVO board) {
		// TODO Auto-generated method stub
		return elMapper.modify(board);
	}

	@Override
	public int emailCheck(String email) {
		// TODO Auto-generated method stub
		return elMapper.emailCheck(email);
	}

	@Override
	public int memberRegist(memberVO member) {
		// TODO Auto-generated method stub
		return elMapper.memberRegist(member);
	}

	




	
}
