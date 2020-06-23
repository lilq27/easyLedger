package com.easyLedger.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.easyLedger.domain.CriteriaVO;
import com.easyLedger.domain.MemberVO;
import com.easyLedger.domain.boardVO;
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
	public int getTotalCount(String email) {
	
		return elMapper.getTotalCount(email);
	}

	@Override
	public boardVO getBno(String bno) {
		// TODO Auto-generated method stub
		return elMapper.getBno(bno);
	}

	@Override
	public int delete(String bno) {
		// TODO Auto-generated method stub
		return elMapper.delete(bno);
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
	public int memberRegist(MemberVO member) {
		// TODO Auto-generated method stub
		return elMapper.memberRegist(member);
	}

	@Override
	public MemberVO finduserByemail(String email) {
		// TODO Auto-generated method stub
		return elMapper.finduserByemail(email);
	}

	@Override
	public MemberVO loginCheck(String email, String pwd) throws NotUserException {
		// TODO Auto-generated method stub
		MemberVO dbuser=finduserByemail(email);
		if(dbuser==null)throw new NotUserException("등록되지 않은 회원입니다.");
		if(!dbuser.getPwd().equals(pwd)) throw new NotUserException("비밀번호가 일치하지 않습니다.");
		return dbuser;
	}



	




	
}
