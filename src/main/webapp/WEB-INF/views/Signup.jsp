<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                <input type="text" class="form-control" name="name" id="name" required="required">
            </div>
            <div class="form-group">
                <label for="email">Email address:</label>  
                <input type="email" class="form-control" name="email" id="email" onchange="emailcheck()" required="required">
            	<span class="check" id="emailCheck"></span>
            </div> 
            <div class="form-group">
                <label for="pwd">Password:</label>
                <input type="password" class="form-control" name="pwd" id="pwd" required="required">
            </div>
            <div align="right">
                <button id="button" class="btn btn-success" type="submit">Create account</button>
        </form>
    </div>
    
    </div>
<c:import url="/foot"/>
<script>
var check=true;
/*  $("#email").ready(function(){ 안됨
    $(document).ready(function(){ 안됨*/
/*  $("#email").blur(function(){ 커서 바뀌면 됨 */
/*  var emailcheck=function(){ 바로바로 됨  onchange사용 필수  */
	$("#email").keyup(function(){/*바로바로 됨  */
		var email=$("#email").val();
		$.ajax({/*${pageContext.request.contextPath} 써도되고 안써도 됨   */
			url : "${pageContext.request.contextPath}/signup/emailcheck?email="+email,
			type : "get",
			/* dataType: "json", 안써도 됨*/
			
			success : function(data){
				console.log(data);
				if(data==1){
					$("#emailCheck").text("사용중");
					check=false;
				}else{
					$("#emailCheck").text("사용가능");
					check=true;
				}
			}
			
		})
	})
	var validate=function(){
		if(!check){
			alert('이메일을 다시 확인해주세요');
			return false;
			}
		return true;
		}
</script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Signup.css"/>