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
	<div class="uploadDiv">
		<input type="file" name="uploadExcel" multiple></input>
	</div>
	
	<button id="ButtonId">업로드</button>
	<div>${longFile}</div>

</body>
<script>

$(function(){
	$("#ButtonId").click(function(){
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
	})
	
	function asd() {
		console.log(3)
	}
});

</script>
</html>