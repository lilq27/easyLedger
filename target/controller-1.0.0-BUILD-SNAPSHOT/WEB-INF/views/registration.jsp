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
    <title>registration</title>
</head>

<body>
<br>
    <div class="container">
        <h2>Easy Ledger</h2>
        <br>
        <form action="/action_page.php">

            <div class="form-group">
                <label for="usr">이름:</label>
                <input type="text" class="form-control" id="usr">
            </div>
            <div class="form-group">
                <label for="grade">학년:</label>
                <input type="text" class="form-control" id="grade">
            </div>
            <div class="form-group">
                <label for="paid">금액:</label>
                <input type="text" class="form-control" id="paid">
            </div>
            <div class="form-group">
                <label for="depositDate">입금 날짜:</label>
                <input type="text" class="form-control" id="depositDate">
            </div>
            <div class="form-group">
                <label for="paymentOption">결제 방법(카드, 계좌, 현금):</label>
                <input type="text" class="form-control" id="paymentOption">
            </div>
            <div class="form-group">
                <label for="memo">메모:</label>
                <textarea class="form-control" rows="5" id="memo"></textarea>
            </div>
            <div align="right">
                <button class="btn btn-success" type="submit">등록</button>
        </form>
    </div>

    </div>

</body>

</html>