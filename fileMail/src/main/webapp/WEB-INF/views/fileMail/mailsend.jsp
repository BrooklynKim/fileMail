<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메일발송</title>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<link rel="stylesheet" href="/resources/css/board.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/earlyaccess/hanna.css" rel="stylesheet">

</head>
<body>

<form method="post" enctype="multipart/form-data">
	<div class="titleDiv">이메일 자동 전송 시스템
		<button type="button" class="move-btn-box" onclick="location.href='/member/memberListPage'">사원리스트로 이동</button>
	</div>
	
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
				<textarea id="content" class="content-box" name="body"	placeholder="내용을 입력해주세요." cols="105" rows="20"
					required="required"></textarea>
			</td>
		</tr>
	</tbody>	
	</table>
	
	<div>
		<input type="button" class="send-btn-box" value="전송" onclick="sendMail()">
	</div>
</form>	

	<script type="text/javascript">
	
	
	function sendMail(){
		var form = new FormData();
		var url = "/mail/sendMail";
		var files = $("#files")[0].files;
		
		console.log(files);
		
		form.append("fromEmail",$("#fromEmail").val());
		form.append("subject",$("#subject").val());
		form.append("content",$("#content").val());
		
		for(var i=0; i <files.length; i++){
			form.append('files',files[i]);
		}
		
		$.ajax({
			url : url,
			type : "POST",
			enctype: 'multipart/form-data',
			processData: false,   
            contentType: false,
            cache: false,
			data : form,
			success:function(data){
				console.log(data);
				var state = data.state
				if(state=="OK"){
					alert("email 전송에 성공했습니다.");
					//location.replace("/mail/sendMail");
				}else{
					alert("email 전송에 실패했습니다.");
					//location.replace("/mail/sendMail");
				}
			}
		});
	}
	</script>
</body>
</html>