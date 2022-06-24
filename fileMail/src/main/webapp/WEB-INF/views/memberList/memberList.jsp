<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Member List</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body>
<!--  
	<table class="type06">
	<thead>
			<th scope="cols"><h2>회원등록</h2></th>
	</thead>
	<tbody>	 
		 <tr>
		 	<th scope="col">E-mail</th>
			<td>
				<input id="email" type="email" name="email" 
					required="required" value="">
			</td>
		</tr>
		
		<tr>
			<th scope="col">이름</th>
			<td>
				<input id="name" type="text" name="title" placeholder="이름" required="required" >
			</td>
		</tr>

		<tr>
			<th scope="col">사용여부</th>	
			<td>
				<input type="radio" name="rdoUseYn" id="rdoUseYnY" value="Y">
					<span>Y</span>
				<input type="radio" name="rdoUseYn" id="rdoUseYnY" value="N">
					<span>N</span>	
			</td>
		</tr>
	</tbody>
	</table>
-->		

	<h2>모든 회원보기 및 추가등록</h2>	
	<a href="#" onClick="history.back()">메일전송</a>  
	<hr></hr>
	<input id="email" type="email" name="email" required="required" placeholder="이메일을 입력해주세요">
	<input id="name" type="text" name="title" placeholder="이름을 입력해주세요" required="required" >
	<input type="radio" name="rdoUseYn" id="rdoUseYnY" value="Y">
		<span>Y</span>
	<input type="radio" name="rdoUseYn" id="rdoUseYnY" value="N">
		<span>N</span>
	<input type="button" value="등록" onclick="addMember()">
		<br></br>
			
			
	
	
	
			
			
	
	<div id="userList"></div>
	
	
	<script type="text/javascript">

	
	$(function() {
		memberList();
	});
	
	function memberList(){
	$.ajax({
		   url:"/member/memberList",		
		   type:"GET",
		   data : {},
		   dataType: "JSON",
		   success:function(data){
			   
			   var list = data.list;
			    var tr = '<table border="1"><thead>'+
		        	    '<tr>' +
		                      '<th>이메일</th>'+
		                      '<th>이름 </th>'+
		                      '<th>사용여부 </th>'+
		               	    '</tr>'+
		                  '</thead>';
		      
		     	$.each(list , function(i){
		          tr += '<tr><td>' +  list[i].A_EMAIL + '</td><td>' + list[i].A_NAME + '</td><td>' + list[i].USE_YN + '</td></tr>';
		        });
		        
		        	// 테이블에 추가
		        	tr += '</table>';
		        	
		        	$("#userList").append(tr);
		   },
		   error : function(xhr, status, error) {
			   alert(xhr.status);
		   },
			complete : function(){
			}
		});
	
	}
	</script>


</body>
</html>