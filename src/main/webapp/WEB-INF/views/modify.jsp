<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <title>modify</title>
</head>
<script type="text/javascript">
	$(document).ready(function(){
		$("#modifybt").click(function(){
			
			$("#modify").submit();
		});
		
		$("#deletebt").click(function(){
			if(confirm("정말 삭제 하시겠습니까?")==true){
			$("#modify").submit();
			}else return false;
			window.close();
			alert("삭제 되었습니다.");
		});
			
	});
</script>

<body>
    <div class="container">
        <h2>Easy Ledger</h2>
        <br>
        <form id="modify" name="modify" action="modify" method="post" >
        	<div class="form-group">
                <input type="hidden" class="form-control" id="name_id" name="name_id" value="${board.name_id }" readonly="readonly">
            </div>
            <div class="form-group">
                <label for="usr">이름:</label>
                <input type="text" class="form-control" id="name" name="name" value="${board.name }" required>
            </div>
            <div class="form-group">
                <label for="grade">학년:</label>
                <input type="text" class="form-control" id="grade" name="grade" value="${board.grade }" required>
            </div>
            <div class="form-group">
                <label for="paid">금액:</label>
                <input type="text" class="form-control" id="paid" name="paid" value="${board.paid }">
            </div>
            <div class="form-group">
                <label for="depositDate">입금 날짜:</label>
                <input type="text" class="form-control" id="depositDate" name="depositDate" value="${board.depositDate }">
            </div>
            <div class="form-group">
                <label for="paymentOption">결제 방법(카드, 계좌, 현금):</label>
                <input type="text" class="form-control" id="paymentOption" name="paymentOption" value="${board.paymentOption }">
            </div>
            <div class="form-group">
                <label for="memo">메모:</label>
                <textarea class="form-control" rows="5" id="memo" name="memo"></textarea>
            </div>
            <div align="right">
                <button class="btn btn-success" id="modifybt" name="mode" value="mdf">수정</button>
                <button class="btn btn-success" id="deletebt" name="mode2" value="dlt">삭제</button>
        	</div> 
        	
	
	</form>
    </div>

</body>

</html>