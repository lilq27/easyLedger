package com.easyLedger.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.easyLedger.domain.BoardVO;
import com.easyLedger.mapper.ExcelMapper;

@Service("ExcelService")
public class ExcelServiceImpl implements ExcelService{
	
	@Autowired
	private ExcelMapper excelMapper;
	private XSSFWorkbook workBook;
	
	@Override
	@Transactional
	public void selectExcelList(HttpServletResponse response) {
		
	}

	@Override
	public List<BoardVO> selectBoardList(String email) {
		// TODO Auto-generated method stub
		return excelMapper.selectBoardList(email);
	}

	@Override
	public SXSSFWorkbook excelWorkBook(List<BoardVO> boardList) {

		SXSSFWorkbook workBook = new SXSSFWorkbook();
		
		SXSSFSheet sheet = workBook.createSheet();
		
		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(0, 3000);

		Row headerRow = sheet.createRow(0);
		
		Cell headerCell = headerRow.createCell(0);
		headerCell.setCellValue("번호");
		
		headerCell = headerRow.createCell(1);
		headerCell.setCellValue("이름");
		
		headerCell = headerRow.createCell(2);
		headerCell.setCellValue("학년");
		
		headerCell = headerRow.createCell(3);
		headerCell.setCellValue("금액");
		
		headerCell = headerRow.createCell(4);
		headerCell.setCellValue("결제일");
		
		headerCell = headerRow.createCell(5);
		headerCell.setCellValue("결제방법");
		
		headerCell = headerRow.createCell(6);
		headerCell.setCellValue("메모");
		
		Row bodyRow = null;
		Cell bodyCell = null;
		int i = 1;
		for(BoardVO boardVo : boardList) {
			bodyRow = sheet.createRow(i);
			
			bodyCell = bodyRow.createCell(0);
			bodyCell.setCellValue(i);
			
			bodyCell = bodyRow.createCell(1);
			bodyCell.setCellValue(boardVo.getName());
			
			bodyCell = bodyRow.createCell(2);
			bodyCell.setCellValue(boardVo.getGrade());
			
			bodyCell = bodyRow.createCell(3);
			bodyCell.setCellValue(boardVo.getPaid());
			
			bodyCell = bodyRow.createCell(4);
			bodyCell.setCellValue(boardVo.getDepositDate());
			
			bodyCell = bodyRow.createCell(5);
			bodyCell.setCellValue(boardVo.getPaymentOption());
			
			bodyCell = bodyRow.createCell(6);
			bodyCell.setCellValue(boardVo.getMemo());
			i++;
		}
		
		return workBook;
	}

	@Override
	public List<BoardVO> uploadExcelFile(MultipartFile excelFile) {

		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		try {
			OPCPackage opcPackage = OPCPackage.open(excelFile.getInputStream());
			workBook = new  XSSFWorkbook(opcPackage);
		
			XSSFSheet sheet = workBook.getSheetAt(0);
			
			for(int i = 1; i < sheet.getLastRowNum() + 1; i++) {
				BoardVO boardVo = new BoardVO();
				XSSFRow row = sheet.getRow(i);
				
				if(row == null) {
					continue;
				}
				
				XSSFCell cell = row.getCell(2);
				if(cell != null) boardVo.setName(cell.getStringCellValue());
				
				cell = row.getCell(3);
				if(cell != null) boardVo.setGrade(cell.getStringCellValue());
				
				cell = row.getCell(4);
				if(cell != null) boardVo.setPaid(cell.getStringCellValue());
				
				cell = row.getCell(5);
				if(cell != null) boardVo.setDepositDate(cell.getStringCellValue());
				
				cell = row.getCell(6);
				if(cell != null) boardVo.setPaymentOption(cell.getStringCellValue());
				
				cell = row.getCell(7);
				if(cell != null) boardVo.setMemo(cell.getStringCellValue());
				
				boardList.add(boardVo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardList;
	}


}
