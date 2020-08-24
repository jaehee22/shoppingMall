$(document).ready(function(){        
    UserView();
});

//회원 수정 이동
function goUserUpdate(){
		
	var userID = $("#userID").val();		
	location.href = "/user/userUpdate?userID="+userID;
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
        var userName = obj.userName;
        var Gender = obj.userGender;
        
        str += "<tr>";
        str += "<th>아이디</th>";
        str += "<td>"+ userID+"</td>";
        str += "</tr>";
        str += "<tr>";	            
        str += "<th>이름</th>";
        str += "<td>"+ userName +"</td>";
        str += "</tr>";
        str += "<tr>";	     
        str += "<th>성별</th>";
        str += "<td>"+ Gender +"</td>";
        str += "</tr>";        
        
    } else {  
    	return;
    }         
    $("#tprofile").html(str);
}
	   
//회원탈퇴
function UserDelete(){
	
	var yn = confirm("회원을 탈퇴하시겠습니까?");        
	if(yn){
       
		$.ajax({    
           
			url        : "/user/UserDelete",
			data    : $("#userForm").serialize(),
			dataType: "JSON",
			cache   : false,
			async   : true,
			type    : "POST",    
			success : function(obj) {
				UserDeleteCallback(obj);                
			},           
			error     : function(xhr, status, error) {}	
		});
	}        
}
	   
function UserDeleteCallback(obj){
   
	if(obj != null){        
           
		var result = obj.result;
           
		if(result == "SUCCESS"){                
			alert("회원탈퇴를 성공하였습니다.");                
			location.href = "/bbs/home";       
		} else {                
			alert("회원탈퇴를 실패하였습니다.");    
			return;
		}
	}
}