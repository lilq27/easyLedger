package com.easyLedger.mapper;

import java.util.List;

import com.easyLedger.domain.BoardVO;

public interface ExcelMapper {
	
	List<BoardVO> selectBoardList(String email);

}
