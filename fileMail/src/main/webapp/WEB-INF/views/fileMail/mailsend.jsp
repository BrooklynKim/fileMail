<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메일발송</title>
<!--  
<script src="https://cdn.ckeditor.com/4.8.0/full-all/ckeditor.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/34.1.0/classic/ckeditor.js"></script>
-->
<script src="http://code.jquery.com/jquery-latest.js"></script>

</head>
<body>
	 <form>
		<div>
			<input id="fromEmail" type="email" name="email" 
				required="required" value="mailsender114@gmail.com">
		</div>
		
		<div>
			<input id="toEmail" type="email" name="email" placeholder="받는 사람"
				required="required" autofocus="autofocus">
		</div>

		<div>
			<input id="subject" type="text" name="title" placeholder="제목" required="required">
		</div>
		
		<div>
			<input id="fileUpForm" type="file" name="fileUpForm" placeholder="파일" />
		</div>
		
		<div>
			<textarea id="content" name="body" placeholder="내용"
				required="required"></textarea>
		</div>

		<div>
			<input type="button" value="전송" onclick="sendMail()">
		</div>
	</form>	
	<!--  </form>-->
	
	<script type="text/javascript">
	/*
		CKEDITOR.replace('content', {
			skin : 'moono',
			enterMode : CKEDITOR.ENTER_BR,
			shiftEnterMode : CKEDITOR.ENTER_P,
			toolbar : [	{	name : 'basicstyles',	groups : [ 'basicstyles' ],	items : [ 'Bold', 'Italic', 'Underline', "-",	'TextColor', 'BGColor' ]	},
						{	name : 'styles',	items : [ 'Format', 'Font', 'FontSize' ]	},
						{	name : 'scripts',	items : [ 'Subscript', 'Superscript' ]	},
						{	name : 'justify',	groups : [ 'blocks', 'align' ],	items : [ 'JustifyLeft', 'JustifyCenter',	'JustifyRight', 'JustifyBlock' ]	},
						{	name : 'paragraph',	groups : [ 'list', 'indent' ],	items : [ 'NumberedList', 'BulletedList', '-',	'Outdent', 'Indent' ]	}, 
						{	name : 'links',		items : [ 'Link', 'Unlink' ]	}, 
						//{	name : 'insert',	items : [ 'Image' ]	}, 
						{	name : 'spell',		items : [ 'jQuerySpellChecker' ]	}, 
						{	name : 'table',		items : [ 'Table' ]	} 
					 ],
					});
	*/	
		
		function sendMail(){
			var form = new FormData();
			var url = "/mail/sendMail";
			
			form.append("fromEmail",$("#fromEmail").val());
			form.append("toEmail",$("#toEmail").val());
			form.append("subject",$("#subject").val());
			form.append("content",$("#content").val());
			form.append("fileUpForm",$("#fileUpForm")[0].files[0]);
			
			$.ajax({
				url : url,
				type : "POST",
				enctype: 'multipart/form-data',
				processData: false,   
	            contentType: false,
	            cache: false,
				data : form,
				success:function(data){
					var state = data.state
					if(state=="OK"){
						alert("email 전송에 성공했습니다.");	
					}else{
						alert("email 전송에 실패했습니다.");
					}
				}
			});
		}
	</script>
</body>
</html>