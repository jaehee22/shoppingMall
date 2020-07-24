$(document).ready(function(){        
});

//게시판 목록 이동
function goBbsList(){                
    location.href = "/bbs/home";
}

//아이디 중복 확인
function IDCheck(){
	if(!document.userForm.userID.value){
		alert("ID를 입력하세요");
		return false;
	}
	
	if(!document.userForm.userPassword.value){
		alert("비밀번호를 입력하세요");
		return false;
	}
	if(!document.userForm.userName.value){
		alert("이름을 입력하세요");
		return false;
	}
	$.ajax({    
        
        url        : "/user/ExistUser",
        data    : $("#userForm").serialize(),
        dataType: "JSON",
        cache   : false,
        async   : true,
        type    : "POST",    
        success : function(obj) {
        	if(obj == 1){
        		alert("중복된 아이디 입니다");
        		return false;
        	}
        	else{
        		Join();
        	}
        },           
        error     : function(xhr, status, error) {}
        
    });
}

//회원가입
function Join(){
    var yn = confirm("내용을 올바르게 작성하셨습니까?");        
    if(yn){
            
        $.ajax({    
            
            url        : "/user/Join",
            data    : $("#userForm").serialize(),
            dataType: "JSON",
            cache   : false,
            async   : true,
            type    : "POST",    
            success : function(obj) {
                JoinCallback(obj);                
            },           
            error     : function(xhr, status, error) {}
            
        });
    }
}

//게시글 작성 함수
function JoinCallback(obj){

    if(obj != null){        
        
        var result = obj.result;
        
        if(result == "SUCCESS"){                
            alert("회원가입을 성공하였습니다.");                
            goBbsList();                 
        } else {                
            alert("회원가입을 실패하였습니다.");    
            return;
        }
    }
}
