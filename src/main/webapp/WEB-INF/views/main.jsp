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
<!--   	<script src="/js/jquery.fileDownload.js"></script> -->
    <title>easyLedger</title>  
</head>
<style>
	.navbar-default{
		background-color:whitesmoke;
	}
</style>
<body>
	<nav class="navbar navbar-default">
    	<div class="container-fluid">
        	<div class="navbar-header">
            	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
	            	<span class="icon-bar"></span>
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
           		</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath}/main">Easy Ledger</a>
			</div> 
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li>
            		<a class="nav-link" href="javascript:void(window.open('${pageContext.request.contextPath}/regist','등록','width=500,height=670'))">등록</a>
          			</li>
          		</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${pageContext.request.contextPath }/excelForm">엑셀 업로드</a></li>
					<li><a href="${pageContext.request.contextPath }/excel_down">엑셀 다운로드</a></li> 
					<li><a href="${pageContext.request.contextPath }/logout">
						<span class="glyphicon glyphicon-log-out"></span>Log Out</a>
				   </li>
				</ul>
			</div>
    	</div>
	</nav>

	<div class="container">
		<h2>Easy Ledger</h2>            
		<table class="table table-hover" id="tableClick">
	  	<c:forEach var="select" items="${boardList}">	
			<thead>
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
					<td style="display:none">${select.bno}</td>  
					<td>${select.name}</td>      
					<td>${select.grade}</td>
					<td>${select.paid}</td>
					<td>${select.depositDate}</td>
					<td>${select.paymentOption}</td>
					<td>${select.memo}</td>  
				</tr>
			</tbody>
		</c:forEach>	         
		</table>
		<div class='pull-right'>
			<ul class="pagination">
			<c:if test="${paging.prev }">
				<li class="paginate_button previous">
					<a href="${paging.startPage -1 }">Previous</a>
				</li>
			</c:if>
			<c:forEach var="num" begin="${paging.startPage }" end="${paging.endPage }">
				<li class="paginate_button ${paging.cri.pageNum == num ? 'active' : '' }">
				<a href="${num}">${num}</a></li>
			</c:forEach>
			<c:if test="${paging.next }">
				<li class="paginate_button next">
				<a href="${paging.endPage +1 }">Next</a></li>
			</c:if>
			</ul>
		</div>
			<form id="actionForm" action="main" method="get">
			<input type="hidden" name="pageNum" value="${paging.cri.pageNum }">
			<input type="hidden" name="amount" value="${paging.cri.amount }">
			<input type="hidden" name="keyWord" value="${paging.cri.keyWord}">
			<input type="hidden" name="findType" value="${paging.cri.findType}">
		</form>
			
		<div align="center">
			<form class="form-inline" id="searchForm" action="main" method="get">
				<select name="findType" class="form-control">
					<option value="1">전체</option>
		         	<option value="2"
		         		<c:out value="${paging.cri.findType eq '2' ? 'selected' : '' }"/>>이름
		         	</option>
		   			<option value="3"
		   				<c:out value="${paging.cri.findType eq '3' ? 'selected' : '' }"/>>학년
		   			</option>
		      		<option value="4"
						<c:out value="${paging.cri.findType eq '4' ? 'selected' : '' }"/>>입금 날짜
					</option> 
		      		<option value="5"
						<c:out value="${paging.cri.findType eq '5' ? 'selected' : '' }"/>>결제 방법
					</option> 
				</select>
				<input type="hidden" name="pageNum" value="${paging.cri.pageNum }">
				<input type="hidden" name="amount" value="${paging.cri.amount }">
		     	<input class="form-control mr-sm-3" type="text" name="keyWord" placeholder="Search"
		     		value="${paging.cri.keyWord }">
		      	<button class="btn btn-success" id="search">Search</button>
			</form>
		</div>
	</div>      
</body>
<script>
$(document).ready(function(){
	var actionForm=$("#actionForm");
	$(".paginate_button a").on("click",function(e){
		e.preventDefault();
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		actionForm.submit();
	});
	
	var searchForm=$("#searchForm");
	$("#search").on("click",function(e){
		if(!searchForm.find("input[name='keyWord']").val()){
			alert("키워드를 입력하세요");
			return false;
		}
		searchForm.find("input[name='pageNum']").val("1");
		e.preventDefault();
		searchForm.submit();	
	});
	
	$("#tableClick tr").dblclick(function(){
		var tr=$(this);
		var td=tr.children();
		var no = td.eq(0).text();
		//alert(no);
		/*$('#member_email').get(0).click(); */
 		window.open('modify?bno='+no+'','등록','width=500,height=670');
 		
		$("#tableClick tr>th").dblclick(function(){
			return false;
		})
	});
});
</script>
</html>