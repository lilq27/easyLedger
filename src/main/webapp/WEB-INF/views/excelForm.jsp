<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <title>excelUpload</title>
</head>
<style>
	#footer {
		text-align: center;
		margin-top: 35%;
	}
</style>
<body>
    <div class="container">
        <h2>Easy Ledger</h2>
        <br>
		<div class="uploadDiv">
			<input type="file" name="uploadExcel" multiple></input>
		</div>
		
		<button id="ButtonId">업로드</button>
		<div>${longFile}</div>
	</div>

</body>
<c:import url="/foot"/>
<script>

$(function() {
	$("#ButtonId").click(function() {
		var check = confirm("업로드 하시겠습니까?");
		if(check) {
			var formData = new FormData();
			var inputFile = $("input[name='uploadExcel']");
			var files = inputFile[0].files;
			
			if(files.length == 1) {
				formData.append("file", files[0]); //controller의 MultipartFile 변수명과 일치해야함
				console.log(files)
			} else if(files.length > 1) {
				alert("업로드는 한개씩만 가능합니다.");
			}
			
			$.ajax({
				url: "${pageContext.request.contextPath}/excel_upload",
				processData: false,//파일다운로드 관련 설정 
				contentType: false,//파일다운로드 관련 설정
				data: formData,
				type: "POST",
				success: function(data) {//data는 컨트롤러 return값 
					if(data == true) {
						console.log(data)
						alert("업로드 실패, 확장자명 또는 파일을 다시 확인하세요");
					} else {
						alert("업로드 완료");
					}
				}
			});
		} else return false;
	})
});
</script>