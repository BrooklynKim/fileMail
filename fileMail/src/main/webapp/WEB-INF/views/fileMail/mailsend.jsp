<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메일발송</title>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<link rel="stylesheet" href="resources/css/board.css">
</head>
<body>

<form method="post" enctype="multipart/form-data">
	<table class="type06">
	<thead>
			<th scope="cols">메일전송</th>
	</thead>
	<tbody>	 
		 <tr>
		 	<th scope="row">보내는 사람</th>
			<td>
				<input id="fromEmail" type="email" name="email" 
					required="required" value="mailsender114@gmail.com">
			</td>
		</tr>
		
		<tr>
			<th scope="row">제목</th>
			<td>
				<input id="subject" type="text" name="title" placeholder="제목" required="required" style="border: none;" >
			</td>
		</tr>
		
		<tr>
			<th scope="row" class="even">첨부 파일</th>	
			<td class="even">
				<input id="files" type="file" name="files" multiple="multiple" placeholder="파일" />
			</td>
		</tr>
		
		<tr>
			<th scope="row">내용</th>	
			<td>
				<textarea id="content" name="body" class="content"	placeholder="내용"
					required="required" style="height:300px; width:500px; border: none;"></textarea>
			</td>
		</tr>
	</tbody>	
		<tr>
			<td>
				<input type="button" value="전송" onclick="sendMail()">
			</td>
		</tr>
	</table>
</form>	

<button type="button" onclick="location.href='/member/memberListPage'">사원리스트</button>
	<!--  </form>-->
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
					location.replace("/mail/sendMail");
				}else{
					alert("email 전송에 실패했습니다.");
					location.replace("/mail/sendMail");
				}
			}
		});
	}
	</script>
</body>
</html>