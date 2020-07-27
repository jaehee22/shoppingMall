$(document).ready(function(){        
});

//게시판 목록 이동
function goBbsList(){                
    location.href = "/bbs/home";
}

//로그인
function Login(){
   
        
    $.ajax({    
        
        url        : "/user/Login",
        data    : $("#userForm").serialize(),
        dataType: "JSON",
        cache   : false,
        async   : true,
        type    : "POST",    
        success : function(obj) {
        	goBbsList();
        },           
        error     : function(xhr, status, error) {
        	alert("등록된 아이디가 없거나 비밀번호가 일치하지 않습니다.");
        	}
        
    });
   
}

