<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
.navbar-default{
  background-color:whitesmoke;
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

           <a class="navbar-brand" href="">Easy Ledger</a>
          </div> 

          <div class="collapse navbar-collapse" id="myNavbar">
          <ul class="nav navbar-nav">
          <li>
            <a class="nav-link" href="${pageContext.request.contextPath}/registration">등록</a>
          </li>
          <!-- <li>
            <a class="nav-link" href="#">Link</a>
          </li>
          <li>
            <a class="nav-link" href="#">Link</a>
          </li> -->
          </ul>

            <ul class="nav navbar-nav navbar-right">
              <!-- <li><a href=""><span class="glyphicon glyphicon-user"></span>
                Sign Up</a></li> -->
              <li><a href=""><span class="glyphicon glyphicon-log-out"></span>
                Log Out</a></li>
              </ul>
          </div>
    </div>
  </nav>
    
      <br>
      <br>

      <div class="container">
        <h2>Easy Ledger</h2>            
        <table class="table table-hover">
          <thead>
         <c:forEach var="select" items="${selectAll}">
            <tr>
              <th>이름</th>
              <th>학년</th>
              <th>금액</th>
              <th>입금 날짜</th>
              <th>결제 방법(카드, 계좌, 현금)</th>
              <th>메모</th>
            </tr>
          </thead>
          <tbody>
            <tr>      
              <td>${select.name}</td>
              <td>${select.grade}</td>
              <td>${select.paid}</td>
              <td>${select.depositDate}</td>
              <td>${select.paymentOption}</td>
              <td>${select.memo}</td>
            </tr>
         </c:forEach> 
          </tbody>
        </table>

        <div align="center">
          <form class="form-inline" action="/action_page.php">
            <input class="form-control mr-sm-3" type="text" placeholder="Search">
            <button class="btn btn-success" type="submit">Search</button>
          </form>
          </div>

      </div>

      
</body>
</html>