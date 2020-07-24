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
        error     : function(xhr, status, error) {}
        
    });

}

