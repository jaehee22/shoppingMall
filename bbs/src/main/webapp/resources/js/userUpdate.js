$(document).ready(function(){        
	UserView();
});
    
//게시판 목록 이동
function goBbsList(){                
    location.href = "/bbs/home";
}

//회원정보
function UserView(){
	var userID = $("#userID").val();
	if(userID!=null){
		$.ajax({    
  
            url        : "/user/UserView",
            data    : $("#userForm").serialize(),
            dataType: "JSON",
            cache   : false,
            async   : true,
            type    : "POST",    
            success : function(obj) {
                UserViewCallback(obj);                
            },           
            error     : function(xhr, status, error) {
            	alert("등록된 유저가 존재하지 않습니다.");
            	location.href = "/bbs/home"
            } 
            
         });
    } else {
        alert("오류가 발생했습니다.\n관리자에게 문의하세요.");
	}
}
function UserViewCallback(obj){
	var str = "";
	if(obj != null){                                
		var userID = obj.userID;
		var userPassword = obj.userPassword;
		var userName = obj.userName;
		var userGender = obj.userGender;
        
        $("#userID").val(userID);
        $("#userPassword").val(userPassword);
        $("#userName").val(userName);
        $("#userGender").val(userGender);
        
        if( userGender == "여자" ){
        	$("input:radio[name='userGender']:radio[value='여자']").attr("checked", true);
        	} else {
        	$("input:radio[name='userGender']:radio[value='남자']").attr("checked", true);
        	}
        
    } else { 	
    	return;
    }        
    
}
	   
//회원 수정
function UserUpdate(){
	
	var userID = $("#userID").val();
    var userPassword = document.forms["userForm2"].elements["userPassword"].value;
    var userName = document.forms["userForm2"].elements["userName"].value;
    var userGender = document.forms["userForm2"].elements["userGender"].value;
    
    if (userPassword == 0){            
        alert("비밀번호를 입력해 주세요");
        $("#userPassword").focus();
        return;
    }
    
    if (userName == ""||userName==null){            
        alert("이름을 입력해주세요.");
        $("#userName").focus();
        return;
    }
    
    if (userGender == ""||userName==null){            
        alert("성별을 체크해주세요.");
        $("#userGender").focus();
        return;
    }
    
    var yn = confirm("회원정보를 수정하시겠습니까?");        
    if(yn){
            
        $.ajax({    
            
            url        : "/user/UserUpdate",
            data    : $("#userForm2").serialize(),
            dataType: "JSON",
            cache   : false,
            async   : true,
            type    : "POST",    
            success : function(obj) {
                UserUpdateCallback(obj);                
            },           
            error     : function(xhr, status, error) {}
            
        });
    }
}

//회원 수정 함수
function UserUpdateCallback(obj){	

	var userID = $("#userID").val();

    if(obj != null){        
        
    	var result = obj.result;
                
        if(result == "SUCCESS"){                
            alert("회원 수정을 성공하였습니다.");                
            location.href = "/user/userView/?userID="+userID;                
        } else {                
            alert("회원 수정을 실패하였습니다.");    
            return;
        }
    }
}

//회원탈퇴
function UserDelete(){
	
	var userID = $("#userID").val();
    var yn = confirm("회원탈퇴를 하시겠습니까?");        
    
	if(yn){
		$.ajax({    
  
			url        : "/user/UserDelete",
            data    : $("#userForm").serialize(),
            dataType: "JSON",
            cache   : false,
            async   : true,
            type    : "POST",    
            success : function(obj) {
            	alert("회원탈퇴가 완료되었습니다.");
        	    location.href = "/bbs/home";
            },           
            error     : function(xhr, status, error) {
            	alert("등록된 유저가 존재하지 않습니다.");
            	location.href = "/bbs/home"
            } 
		});
	}
}