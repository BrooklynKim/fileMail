<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Member List</title>
<link rel="stylesheet" type="text/css" href="/resources/js/jquery-ui.min.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/ui.jqgrid.css" />

<script src="http://code.jquery.com/jquery-latest.js"></script>
<!--  <script type="text/javascript" src="/resources/js/common/jquery/jquery-3.2.1.min.js"></script>-->
<script src="/resources/js/i18n/grid.locale-kr.js" type="text/javascript"></script>
<script src="/resources/js/minified/jquery.jqGrid.min.js" type="text/javascript"></script>
<style>

  .ui-jqgrid .ui-jqgrid-htable{
        	overflow: hidden; 
        	position:relative; 
        	height:50px;
        	font-weight: bold;
        	font-family:'NanumGothicB','나눔고딕', "돋움", dotum;
        	font-size:15px;
        }

  .ui-jqgrid-btable, .ui-pg-table  {
        	font-family:'NanumGothicB','나눔고딕', "돋움", dotum;
        	font-size:15px;
        }

</style>

</head>
<body>
<form>
	<div class="tableWrap">
		<table id="mainGrid">
			<tr>
					<td>
						<input id="num" type="hidden" name="num">
					</td>
				<th>이메일<th>
					<td>
						<input id="email" type="email" name="email" required="required" placeholder="이메일을 입력해주세요">
					</td>	
				<th>이름<th>
					<td>
						<input id="name" type="text" name="title" placeholder="이름을 입력해주세요" required="required" >
					</td>
				<th>사용여부<th>
					<td>		
						<input type="radio" name="rdoUseYn" id="rdoUseYnY" value="Y" checked="checked">
							<span>Y</span>
						<input type="radio" name="rdoUseYn" id="rdoUseYnY" value="N">
							<span>N</span>
						<input type="button" value="등록" onclick="addMember()">
					</td>	
			</tr>
		</table>
	</div>
</form>	
	
<div id="memberDiv" class="table_wrap">
	<table id="memberGrid"></table>
</div>


<script type="text/javascript">

	var colNames = ['사번','이메일','이름','재직여부'];
	var colModel = [
		{name:'A_NUM', index:'A_NUM', align:'center', width:30},
		{name:'A_EMAIL', index:'A_EMAIL', align:'center'},
		{name:'A_NAME',	index:'A_NAME',	align:'center', },
		{name:'USE_YN',	index:'USE_YN',	align:'center', width:30}
	];
	
	$(window).on('resize.jqGrid',function(){
		jQuery('#memberDiv').jqGrid('setGridWidth', $('#memberDiv').width());
	});
	
	$('#memberGrid').jqGrid({
		height: 400,
		autowidth : true,
		colNames : colNames,
		colModel : colModel,
		multiselect : true, // 셀렉트 박스
		//rownumbers : true, // 자동으로 생성된 rowNum
		rowNum : 10,
		caption : "사원리스트",
		sortname : 'A_NAME',
		sortorder : 'asc',
		pager : '#pager'
	});
	
	
	$(function(){
		callMember();
	});
	

	
	function callMember (){
		$.ajax({
		   url:"/member/memberList",		
		   mtype:"GET",
		   data : {},
		   dataType: "JSON",
		   async : false,
		   success:function(data){
			   $("#memberGrid").clearGridData();
			   $("#memberGrid").jqGrid('setGridParam',{data: data.list});
			   $("#memberGrid").trigger('reloadGrid');
			  
			   for(var i=0; i<=data.list.length;i++){
				   $("#memberGrid").jqGrid('addRowData',i+1,data.list[i]);
			   }
		   },
		   error : function(xhr, status, error) {
			   alert(xhr.status);
		   },
			complete : function(){
			}
		}); 

	}

	// form 전송할떄 한글 처리 해야함
	// 필요에 따라 auto increment -> maxNum으로 변경
	function addMember(){
		var form = new FormData();
		var url = "/member/addMember"
		var eCheck = $("#email").val();
		var eRule = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
		var rSelect = $('[name=rdoUseYn]:checked').val();
		
		
		form.append("aEmail",$("#email").val());
		//form.append("aName",$("#name").val());
		//form.append("aName",encodeURIComponent($("#name").val()));
		form.append("aName",decodeURI($("#name").val()));
		form.append("rSelect",$('[name=rdoUseYn]:checked').val());
		
		if($("#email").val() == ""){
			alert("이메일을 입력해주세요.");
			$('#email').focus();
			return;
		}
		
		if(!eRule.test(eCheck)){
		      alert("이메일 형식에 맞게 입력해주세요.");
		    return false;
		  }
		
		if($("#name").val() == ""){
			alert("이름을 입력해주세요.");
			$('#name').focus();
			return;
		}
		
		
		
		$.ajax({
				 type:"POST", 
				 enctype: 'form-data',
				 url:url,
	             processData: false,   
	             contentType: false,
	             //contentType: "application/json; charset:UTF-8", 
	             cache: false,
	             data: form,
				 success : function(data) {
					var state = data.state;
					
					if(state == "OK"){
						alert("사원 등록이 완료 되었습니다.");
						$("#email").val("");
						$("#name").val("");
						$('[name=rdoUseYn]:checked').val("");
					}else{
						alert("causes:" + state);
						$("#email").val("");
						$("#name").val("");
						$('[name=rdoUseYn]:checked').val("");
					}
				}
			});
			
	}

</script>

<!--  jqGrid로 바꾸기 전 Ver
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

-->
</body>
</html>