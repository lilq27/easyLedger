<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/top"/>
  <div class="container">
        <br>
        <div class="head">Join in easy Ledger</div>
        <h2 class="head">Create yout account</h2>
        <br>
        <form action="signup" method="post">
    
            <div class="form-group">
                <label for="name">Username:</label>
                <input type="text" class="form-control" name="name" id="name" required="required">
            </div>
            <div class="form-group">
                <label for="email">Email address:</label> 
                <input type="email" class="form-control" name="email" id="email" required="required">
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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Signup.css"/>