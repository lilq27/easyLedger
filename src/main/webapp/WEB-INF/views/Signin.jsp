<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/top"/>
<div class="container">
<br>
<h2 class="head">Easy Ledger</h2>
<p><br>
    <div class="head2">Sign in to Easy Ledger</div></p>
    
  <form action="signin">
    <div class="panel panel-default">
    <div class="form-group" style="width: 350px; padding-left: 85px; padding-top: 30px;" >
      <label for="usr">Email:</label>
      <input type="email" class="form-control" id="email" >
    </div>
    <div class="form-group" style="width: 350px;  padding-left: 85px;">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd">
    </div>
    <div style="padding-bottom: 20px;">
      <button id="button" class="btn btn-success" type="submit">Sign in</button>
    </div>
    </form>
  </form>
</div>
<c:import url="/foot"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Signin.css"/>