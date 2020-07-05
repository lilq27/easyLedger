<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/top" />
    
      <br>
      <br>

<div class="container">
	<h2>Easy Ledger -sample-</h2>            
	<table class="table table-hover">
		<thead>
	    	<tr>
		        <th>이름</th>
		        <th>학년</th>
		        <th>금액</th>
		        <th>입금 날짜</th>
				<th>결제 방법(카드, 계좌, 현금 영수증)</th>
			</tr>
		</thead>
	    <tbody>
			<tr>
				<td>song@example.com</td>
		      	<td>song@example.com</td>
		      	<td>song@example.com</td>
		      	<td>song@example.com</td>
		      	<td>song@example.com</td>
		    </tr>
		</tbody>
	</table>
	<div align="center">
    	<form class="form-inline" action="/action_page.php">
			<input class="form-control mr-sm-3" type="text" placeholder="Search" readonly="readonly">
            <button class="btn btn-success" type="submit" disabled="disabled" >Search</button>
		</form>
	</div>
</div>
<c:import url="/foot"/>