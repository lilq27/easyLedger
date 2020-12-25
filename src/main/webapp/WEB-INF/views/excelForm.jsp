<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <c:import url="/top"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<title>Insert title here</title>
</head>
<body>
	<form id="Excel-form" method="post" enctype="multipart/form-data" action="/excel_upload">
		<input type="file" accept=".xlsx, .xls"></input>
		<input id="ButtonId" type="submit" value="업로드"></input>
	</form>
	
	<button onclick="asd()">asd</button>

</body>
<script>

$(function(){
	$("#ButtonId").click(function(){		
		console.log(1)
		$.ajax({
			url: "/excel_upload",
			type: "POST",
			processData: false,//파일다운로드 관련 설정 
			contentType: false,//파일다운로드 관련 설정
			sucess: function(data) {
				console.log(data)
			}
		});
	})
	
	function asd() {
		console.log(3)
	}
});

</script>
</html>