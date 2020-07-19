<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/top"/>
<div class="container">
	<br>
	<h2 class="head">Easy Ledger</h2>
	<p><br>
    	<div class="head2">Sign in to Easy Ledger</div>
    <p>
	<form id="loginForm" action="signin" method="post">
    	<div class="panel panel-default">
	    	<div class="form-group" id="emailArea">
	      		<label for="usr">Email:</label>
	      		<input type="email" class="form-control" name="email" id="email" >
	    	</div>
	    	<div class="form-group" id="passwordArea">
	      		<label for="pwd">Password:</label>
	      		<input type="password" class="form-control" name="pwd" id="pwd">
	    	</div>
	    	<div id="buttonArea">
	      		<button id="button" class="btn btn-success">Sign in</button>
	    	</div>
    	</div>
    </form>
</div>
<c:import url="/foot"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Signin.css"/>
<script>
	$(document).ready(function() {
		var loginForm=$("#loginForm");
		$("#button").on("click",function(e) {
			if(!loginForm.find("input[name='email']").val()) {
				alert("이메일을 입력하세요");
				return false;
			} else if(!loginForm.find("input[name='pwd']").val()) {
				alert("비밀번호를 입력하세요");
				return false;
			}
			e.preventDefault();
			loginForm.submit();	
		})
	})
</script>