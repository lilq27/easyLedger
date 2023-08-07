<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/top"/>
 <div class="container">
 	<br>
    <div class="head">Join in easy Ledger</div>
    <h2 class="head">Create yout account</h2>
    <br>
   	<form action="signup" method="post" onsubmit="return validate()">
    	<div class="form-group">
       		<label for="name">Username:</label><!--label for값과 input id값이 일치하면 label클릭시 입력창에 커서 이동  -->
            <input id="name" class="form-control" type="text" name="name" required="required">
		</div>
        <div class="form-group">
        	<label>Email address:</label>  
            <input id="InputEmail" class="form-control" type="email" name="email" required="required">
          	<span id="EmailCheck" class="check"></span>
		</div> 
        <div class="form-group">
        	<label for="pwd">Password:</label>
        	<input id="pwd" class="form-control" type="password" name="pwd" required="required">
		</div>
        <div align="right">
      		<button id="button" class="btn btn-success" type="submit">Create account</button>
		</div>
	</form>
</div>
<c:import url="/foot"/>
<script>
// $(function() {
	var check = true;
	/*  $("#email").ready(function(){} 안됨
	    $(document).ready(function(){} 안됨*/
	/*  $("#email").blur(function(){} 커서 바뀌면 됨 */
	/*  var emailcheck=function(){} 바로바로 됨   onchange="emailcheck()" 사용 필수  */
	$("#InputEmail").keyup(function(aaa) {/*바로바로 됨  */
		console.log("aaa", aaa)
		var email = $("#InputEmail").val();
		var data = {email : email};
		
		$.ajax ({/*${pageContext.request.contextPath} 써도되고 안써도 됨   */
			url : "${pageContext.request.contextPath}/signup/emailcheck",
			type : "get",
			data : data,
			dataType: "json",	
			success : function(data) {
				check = false;
				var emailcheck = '사용가능';
				if(data) {
					check = data;
					emailcheck = '사용중';
				} 
				
				$("#EmailCheck").text(emailcheck);
			}
		});
	});
	
	var validate = function() {
		console.log("check", check)
		if(check) {
			alert('이메일을 다시 확인해주세요');
			return false;
		}
		return false;
	};
// });
</script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Signup.css"/>