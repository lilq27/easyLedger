<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
 
    <title>easyLedger</title>  
</head>
<style>
	.navbar-default {
		background-color:whitesmoke;
	}
	#footer {
		text-align:center;
		margin-top: 60px;
	}
</style>
<script>

</script>
<body>
    <nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
				  data-target="#myNavbar">
				    <span class="icon-bar"></span>
				    <span class="icon-bar"></span>
				    <span class="icon-bar"></span>
				</button>
				
				<a class="navbar-brand" href="${pageContext.request.contextPath}">Easy Ledger</a>
			</div>
		
			<div class="collapse navbar-collapse" id="myNavbar">
			    <ul class="nav navbar-nav navbar-right">
			   		<li><a href="${pageContext.request.contextPath}/signin"><span class="glyphicon glyphicon-log-in"></span>
			      	Sign in</a></li>
			    	<li><a href="${pageContext.request.contextPath}/signup"><span class="glyphicon glyphicon-user"></span>
			        Sign up</a></li>
			    </ul>
		    </div>
		</div>
 	</nav>