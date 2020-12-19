package com.easyLedger.etc;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.servlet.view.AbstractView;

public class ExcelDownloadView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Locale locale = (Locale)model.get("locale");
		String workBookName = (String)model.get("workBookName");

		Date date = new Date();
		SimpleDateFormat dayFormat = new SimpleDateFormat("yyyyMMdd", locale);
		SimpleDateFormat hourFormat = new SimpleDateFormat("hhmmss", locale);
		
		String day = dayFormat.format(date);
		String hour = hourFormat.format(date);
		String fileName = workBookName + "_" + day + "_" + hour + ".xlsx";
		
		String browser = request.getHeader("user-Agent");
		if (browser.contains("Edge")){
			fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
		    response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\".xls;");
		} else if (browser.contains("MSIE") || browser.contains("Trident")) { // IE 11버전부터 Trident로 변경되었기때문에 추가해준다.
			fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
		    response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls;");
		} else if (browser.contains("Chrome")) {
			fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\".xls");
		} else if (browser.contains("Opera")) {
			fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\".xls");
		} else if (browser.contains("Firefox")) {
			fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		    response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xls");
		}

		response.setContentType("application/download;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		OutputStream os = null;
		SXSSFWorkbook workBook = null;
		
		try {
			workBook = (SXSSFWorkbook)model.get("workBook");
			os = response.getOutputStream();
			workBook.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(os != null) {
			try {
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
