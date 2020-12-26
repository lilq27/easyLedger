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
			console.log(123)
		} else if(files.length > 1) {
			alert("업로드는 한개만");
		}
		
		$.ajax({
			url: "${pageContext.request.contextPath}/excel_upload",
			processData: false,//파일다운로드 관련 설정 
			contentType: false,//파일다운로드 관련 설정
			data: formData,
			type: "POST",
			success: function(data) {
				console.log(data)
				alert("Uploaded");
			}
		});
	})
	
	function asd() {
		console.log(3)
	}
});

</script>
</html>