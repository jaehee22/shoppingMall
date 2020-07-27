 $(document).ready(function(){  
	 UserList();
    });
 	
    function UserList(){
		$.ajax({    
  
            url        : "/user/UserList",
            data    : $("#userForm").serialize(),
            dataType: "JSON",
            cache   : false,
            async   : true,
            type    : "POST",    
            success : function(obj) {
                UserListCallback(obj);                
            },           
            error     : function(xhr, status, error) {}
            
         });
    }
    
    function UserListCallback(obj){
        
        var list = obj;
        var listLen = obj.length;
        
        console.log(list);
        console.log(listLen);
        
        var str = "";

        if(listLen >  0){
            for(var a=0; a<listLen; a++){
            	var userID = list[a].userID;
                var userName = list[a].userName; 
                var userGender = list[a].userGender;
                var isManager = list[a].isManager;

                str += "<tr>";
	            str += "<td>"+ userID+"</td>";           
	            str += "<td>"+ userName +"</td>";
	            str += "<td>"+ userGender +"</td>";
                if(isManager==1){
                	str += "<td>관리자</td>";
                }
                else{
                	str += "<td>일반회원</td>"
                }
	            str += "</tr>";        
	            	            
            } 
            
        } else {
            
            str += "<tr colspan='4'>";
            str += "<td>등록된 글이 존재하지 않습니다.</td>";
            str += "<tr>";
        }
        
        $("#tbody").html(str);
    }
    