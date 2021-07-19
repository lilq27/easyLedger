<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <title></title>
</head>
<body>
</body>
<script>
$(function() {
	$.ajax({
		url: 'https://openapi.naver.com/v1/search/news.json?query=' + encodeURI(searchTxet) + '&display=' + encodeURI(display),
	    method: 'get',
	    dataType: 'json',
	    headers: {
	    	'Content-Type' : 'application/x-www-form-urlencoded',
	    	'X-Naver-Client-Id' : clientId,
	    	'X-Naver-Client-Secret' : clientSecret
	    },
	    success: function(returnData) {
	    	console.log(returnData);
	    },
	    error: function(err) {
	    	console.log(err);
	    }
	});
});
</script>