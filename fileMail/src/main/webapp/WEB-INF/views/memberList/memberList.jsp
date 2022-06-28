<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<% request.setCharacterEncoding("utf-8");%>   
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
  
  .email_ok{
  			color:#008000;
  			display:none;
  		}
  
  .email_already{
  			color:#E00000;
  			display:none;
  		}
  
  table {
	       border: 1px solid #333333;
	       margin: auto;
	       width : 70%;
     	}
     
     
   .btn-group button {
		  background-color:blue; 
		  border: 1px solid gray; 
		  color: white; 
		  padding: 10px 24px;
		  cursor: pointer;
		  margin : auto;
		}
	
	
	.btn-group:after {
		  content: "";
		  clear: both;
		  display: table;
		}
	
	.btn-group button:not(:last-child) {
		  border-right: none;
		}
		
	.btn-group button:hover {
	  	  background-color: tomato;
		}
	
	.a button {
		  width:50%
	  	} 
     

</style>

</head>
<body>
<form>
<pre>

</pre>
	<div class="tableWrap">
		<table id="mainGrid">
			<tr>
					<td>
						<input id="num" type="hidden" name="num">
					</td>
				<th>이메일<th>
					<td>
						<input id="email" type="email" name="email" required="required" placeholder="이메일을 입력해주세요" oninput="checkEmail()">
							<!--  <span class="email_ok">사용 할 수 있는 이메일입니다.</span>
							<span class="email_already">이메일을 다시 입력해주세요.</span>-->
					</td>	
				<th>이름<th>
					<td>
						<input id="name" type="text" name="title" placeholder="이름을 입력해주세요" required="required" >
					</td>
				<th>재직여부<th>
					<td>		
						<input type="radio" name="rdoUseYn" id="rdoUseYnY" value="Y" checked="checked">
							<span>Y</span>
						<input type="radio" name="rdoUseYn" id="rdoUseYnN" value="N">
							<span>N</span>
						
						&emsp;
							
						<input id="addBtn" type="button" value="입사" onclick="addMember()">
						<input id="delBtn" type="button" value="퇴사" onclick="delMember()">
						<input id="updateBtn" type="button" value="수정" onclick="updateMemberInput()">
						<input id="updateSaveBtn" type="button" value="수정완료" onclick="updateMemberSave()">
					</td>	
			</tr>
		</table>
	</div>
	<div>
		<span class="email_ok">사용 할 수 있는 이메일입니다.</span>
		<span class="email_already">이메일을 다시 입력해주세요.</span>
	</div>
</form>	

<pre>

</pre>
	
<div id="memberDiv" class="table_wrap">
	<table id="memberGrid"></table>
	<div id=pager></div>
</div>

<div class="btn-group a">
	<button type="button" onclick="location.href='/'">이메일 발송 시스템</button>
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
		rowNum : 5,
		caption : "사원리스트",
		sortname : 'A_NAME',
		sortorder : 'asc',
		pager : '#pager',
		pgbuttons : true
	});
	
	
	
	$(function(){
		$("#updateSaveBtn").hide();
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
	
	
	function checkEmail(){
		var email = $('#email').val();
		$.ajax({
            url:'/member/emailCheck',
            type:'post', 
            data:{email:email},
            success:function(cnt){ 
            	console.log(cnt);
                if(cnt == 0){  
                    $('.email_ok').css("display","inline-block"); 
                    $('.email_already').css("display", "none");
                } else { 
                    $('.email_already').css("display","inline-block");
                    $('.email_ok').css("display", "none");
                    alert("이미 사용중인 이메일 입니다.");
                    $('#email').val('');
                }
            },
            error:function(){
                alert("에러입니다");
            }
        });
        };
	
	
	function updateMemberInput(){
		
		var	rowId = $("#memberGrid").jqGrid('getGridParam',"selrow");
		console.log(rowId);
		
		var	rowNum = $("#memberGrid").jqGrid('getRowData',rowId).A_NUM;	
		console.log(rowNum);
		
		var	rowEmail = $("#memberGrid").jqGrid('getRowData',rowId).A_EMAIL;	
		console.log(rowEmail);
		
		var	rowName = $("#memberGrid").jqGrid('getRowData',rowId).A_NAME;	
		console.log(rowName);
		
		var	rowUseYn = $("#memberGrid").jqGrid('getRowData',rowId).USE_YN;	
		console.log(rowUseYn);
		
		//var	rowUseYn = $('[name=rdoUseYn]:checked').jqGrid('getRowData',rowId).USE_YN ;
		
			if(rowId==undefined||rowId==""){
				alert("수정 할 행을 선택해주세요.");
				return;
			}else{
				$("#updateBtn").hide();
				$("#updateSaveBtn").show();
				
				$("#addBtn").hide();
				
				$("#num").val(rowNum);
				$("#email").val(rowEmail);
				
				$("#name").val(rowName);
				$("#name").attr("disabled",true);
				
				$("#rdoUseYnY").val(rowUseYn);
				$("#rdoUseYnN").val(rowUseYn);
				}
			
	}
		
	
	
	

	function updateMemberSave(){
		var form = new FormData();
	 	var url = "/member/updateMember"
	 	
	 	form.append("aNum",$("#num").val());
 		form.append("aEmail",$("#email").val());
 		form.append("useYn",$("#rdoUseYnY").val());
 		form.append("useYn",$("#rdoUseYnN").val());
		//form.append("useYn",$('[name=rdoUseYn]:checked').val());
		console.log(form);
		 $.ajax({
		    	url	: url,
		        type	: "POST",
		        enctype: 'form-data',
		        processData: false,   
	            contentType: false,
	            cache: false,
		        data	: form,
		        success : function(data) {
		        	var state = data.state;
		        	
					if(state == "OK") {
						alert("사원정보가 수정되었습니다.");
						$("#email").val("");
						$("#name").val("");
						$('[name=rdoUseYn]:checked').val("");
						
						$("#updateBtn").show();
						$("#updateSaveBtn").hide();
						
						callMember ();
					}else{
						alert("causes:" + state);
						$("#email").val("");
						$("#name").val("");
						$('[name=rdoUseYn]:checked').val("");
					}
						}
		});
	}
	
	function delMember(delMemberList) {
		
	 	var tempObj = new Object();
	 	tempObj.aNumList = delMemberList;
		
    	var url = "/member/delMember"
    	var postdata = {
    			aNumList	: JSON.stringify(tempObj)
    	}
    	
	    $.ajax({
	    	url		: url,
	        type	: "POST",
	        dataType: "JSON",
	        data	: postdata,
	        success : function(data) {
	        	var state = data.state;
	        	
				if(state == "OK") {
					alert("퇴사처리 완료 되었습니다.");
	            }
	            else {
	                alert("causes:" + state );
	                
		            }
		        }
			}); 
		}
	
</script>

</body>
</html>