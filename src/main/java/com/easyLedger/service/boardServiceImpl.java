package com.easyLedger.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.easyLedger.domain.CriteriaVO;
import com.easyLedger.domain.PagingVO;
import com.easyLedger.domain.boardVO;
import com.easyLedger.domain.memberVO;
import com.easyLedger.domain.CriteriaVO;
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
	public List<boardVO> selectPaging(String email , CriteriaVO cri) {
		// TODO Auto-generated method stub
		return elMapper.selectPaging(email, cri);
	}


	@Override
	public int getTotalCount(CriteriaVO cri , String email) {
		// TODO Auto-generated method stub
		return elMapper.getTotalCount(cri , email);
	}

	@Override
	public boardVO getMemberEmail(String member_eamil) {
		// TODO Auto-generated method stub
		return elMapper.getMemberEmail(member_eamil);
	}

	@Override
	public int delete(String member_eamil) {
		// TODO Auto-generated method stub
		return elMapper.delete(member_eamil);
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

	@Override
	public memberVO finduserByemail(String email) {
		// TODO Auto-generated method stub
		return elMapper.finduserByemail(email);
	}

	@Override
	public memberVO loginCheck(String email, String pwd) throws NotUserException {
		// TODO Auto-generated method stub
		memberVO dbuser=finduserByemail(email);
		if(dbuser==null)throw new NotUserException("등록되지 않은 회원입니다.");
		if(!dbuser.getPwd().equals(pwd)) throw new NotUserException("비밀번호가 일치하지 않습니다.");
		return dbuser;
	}


	




	
}
