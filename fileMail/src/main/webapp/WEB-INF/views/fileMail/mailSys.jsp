<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<title>Insert title here</title>

</head>
<body>
<!--  
	<form action="WebSendMail" method="post" enctype="multipart/form-data">
		<table border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td>Title</td>
				<td><input type="text" name="subject" size="40"></td>
			</tr>
			<tr>
				<td>Content</td>
				<td><textarea name="body" rows="10" cols="40"></textarea></td>
			</tr>
			<tr>
				<td>File</td>
				<td><input type="file" name="attachment"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit"></td>
			</tr>
		</table>
	</form>
-->

	<h4>���� ������</h4>
    <form action="mailSending.do" method="post" enctype="multipart/form-data">
      <div>
        <input type="text" name="tomail" size="120"
        style="width: 100%" placeholder="����� �̸���"
        class="form-control">
      </div>
      <div align="center">
        <!-- ���� -->
        <input type="text" name="title" size="120"
        style="width: 100%" placeholder="������ �Է����ּ���"
        class="form-control">
      </div>
        <p>
          <div align="center">
          <!-- ���� -->
            <textarea name="content" cols="120" rows="12"
            style="width: 100%; resize: none" placeholder="����#"
            class="form-control"></textarea>
          </div>
        <p>
      <div align="center">
        <input type="submit" value="���� ������" class="btn btn-warning">
      </div>
    </form>
	
</body>
</html>