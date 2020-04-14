package com.easyLedger.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.easyLedger.domain.boardVO;
import com.easyLedger.mapper.easyLedgerMapper;
@Service(value="boardSI")
public class boardServiceImpl implements boardService {

	@Inject
	private easyLedgerMapper elMpper;
	
	@Override
	public List<boardVO> selectAll() {
		return elMpper.selectAll();
	}
	
}
