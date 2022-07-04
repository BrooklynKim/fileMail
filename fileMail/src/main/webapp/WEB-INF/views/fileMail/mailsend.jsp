<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>			
<!DOCTYPE html>
<html>
<head>
<meta content="UTF-8">
<title>Email System</title> 

<script src="http://code.jquery.com/jquery-latest.js"></script>
<link rel="stylesheet" href="/resources/css/board.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/earlyaccess/hanna.css" rel="stylesheet">

</head>
<body>
	

	<table class="cssTable">
		<tr>
			<td><a href="/" style="float: left;">
					<img alt="main" src="/resources/css/images/home.png" height="70" width="70">
				</a>
			</td>
			<td>
				<h1 style="text-align: center;">이메일 전송 시스템</h1>
			</td>
			<td>
				<button type="button" class="move-btn-box" onclick="location.href='/member/memberListPage'" style="float:right; background-color: Linen; height: 70px; border-radius: 2px;">사원리스트로 이동</button>
			</td>
		</tr>
	</table>

	<table class="content-from">
	
		<tbody class="tableBody">	 
			 <tr>
			 	<th scope="row">보내는 사람</th>
				<td>
					<input id="fromEmail" type="email" name="email" 
						required="required" value="mailsender114@gmail.com" disabled="disabled">
				</td>
			</tr>
			
			<tr>
				<th scope="row">제목</th>
				<td>
					<input id="subject" class="title-box" type="text" name="title" placeholder="제목을 입력해주세요." required="required">
				</td>
			</tr>
			
			<tr>
				<th scope="row" class="even">첨부 파일</th>	
				<td class="even">
					<input id="files" class="fileBtn" type="file" name="files" multiple="multiple" />
				</td>
			</tr>
			
			<tr>
				<th scope="row">내용</th>	
				<td>
					<textarea id="content" class="content-box" name="body"	placeholder="내용을 입력해주세요." cols="125" rows="15"
						required="required"></textarea>
				</td>
			</tr>
		</tbody>	
	</table>
	
	<div align="center">
		<input type="button" class="send-btn-box" value="전송" onclick="sendMail()">
	</div>
	
	<!--  
	<div id="loading" class="loading" style="display: none;">  
		<div class="spinner">
		  <div class="bounce1"></div>
		  <div class="bounce2"></div>
		  <div class="bounce3"></div>
		</div>
	</div>
	-->
	
	<div id="loading" class="loading" style="display: none;">
		<div class="spinner">
		  <div class="rect1"></div>
		  <div class="rect2"></div>
		  <div class="rect3"></div>
		  <div class="rect4"></div>
		  <div class="rect5"></div>
		</div>
	</div>
	
	<script type="text/javascript">
	
	// 파일 확장자 check_1
	$("#files").on("change",function(){
		var str = $(this).val();
		var fileName = str.split('\\').pop().toLowerCase();
		
		checkFileName(fileName);
	});
	
	//파일 확장자 check_2
	function checkFileName(){
		if( $("#files").val() != "" ){
			var ext = $('#files').val().split('.').pop().toLowerCase();
			      if($.inArray(ext, ['jpg','gif','bmp','png','xls','xlsx','ppt','pptx','doc','docx','txt','zip','7z','pdf','swf']) == -1) {
			      alert(ext+'  파일은 업로드 할 수 없습니다.');
				 return $("#files").val("");
			      }
		}
	}
	
	
	function sendMail(){
		var form = new FormData();
		var url = "/mail/sendMail";
		var files = $("#files")[0].files;
		
		form.append("fromEmail",$("#fromEmail").val());
		form.append("subject",$("#subject").val());
		form.append("content",$("#content").val());
		
		for(var i=0; i <files.length; i++){
			form.append('files',files[i]);
			files[i].name; // 파일 이름 test
		}
		$.ajax({
			url : url,
			type : "POST",
			enctype: 'multipart/form-data',
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			processData: false,   
            contentType: false,
            cache: false,
			data : form,
			beforeSend: function() {
			  $('#loading').show();
				 },
			success:function(data){
				console.log(data);
				var state = data.state
				if(state=="OK"){
					alert("email 전송에 성공했습니다.");
					location.href="/";
				}else{
					alert("email 전송에 실패했습니다.");
				}
			},
			 complete: function() {
				  $('#loading').hide();
				 }
		});
	}
	</script>
</body>
</html>