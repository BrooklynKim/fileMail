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
			<th scope="cols"><h2>ȸ�����</h2></th>
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
			<th scope="col">�̸�</th>
			<td>
				<input id="name" type="text" name="title" placeholder="�̸�" required="required" >
			</td>
		</tr>

		<tr>
			<th scope="col">��뿩��</th>	
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

	<h2>��� ȸ������ �� �߰����</h2>	
	<a href="#" onClick="history.back()">��������</a>  
	<hr></hr>
	<input id="email" type="email" name="email" required="required" placeholder="�̸����� �Է����ּ���">
	<input id="name" type="text" name="title" placeholder="�̸��� �Է����ּ���" required="required" >
	<input type="radio" name="rdoUseYn" id="rdoUseYnY" value="Y">
		<span>Y</span>
	<input type="radio" name="rdoUseYn" id="rdoUseYnY" value="N">
		<span>N</span>
	<input type="button" value="���" onclick="addMember()">
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
		                      '<th>�̸���</th>'+
		                      '<th>�̸� </th>'+
		                      '<th>��뿩�� </th>'+
		               	    '</tr>'+
		                  '</thead>';
		      
		     	$.each(list , function(i){
		          tr += '<tr><td>' +  list[i].A_EMAIL + '</td><td>' + list[i].A_NAME + '</td><td>' + list[i].USE_YN + '</td></tr>';
		        });
		        
		        	// ���̺� �߰�
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