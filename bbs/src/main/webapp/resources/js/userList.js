 $(document).ready(function(){  
	 UserList();
	 UserPaging();
 });

 //유저 정보 list
 function UserList(){
	 $.ajax({    
  
		 url        : "/user/UserList",
		 data    : $("#userNum").serialize(),
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
    
    var str = "";

    if(listLen >  0){
        for(var a=0; a<listLen; a++){
        	var userID = list[a].userID;
            var userName = list[a].userName; 
            var userGender = list[a].userGender;
            var isManager = list[a].isManager;

            str += "<tr>";
            str += "<td>"+userID+"</td>";           
            str += "<td>"+ userName +"</td>";
            str += "<td>"+ userGender +"</td>";
            if(isManager==1){
            	str += "<td>관리자</td>";
            }
            else{
            	str += "<td>일반회원</td>"
            }
            str += "<td><button type=\"button\" onclick=\"location.href='/user/userView?userID="+userID+"'\"</button>회원관리</td>"
            str += "</tr>";        
            	            
        } 
        
    } else {
        
        str += "<tr colspan='4'>";
        str += "<td>회원가입한 회원이 존재하지 않습니다.</td>";
        str += "<tr>";
    }
    
    $("#tbody").html(str);
}

//페이징
function UserPaging(){
	
	$.ajax({    
		  
        url     : "/user/UserPaging",
        data    : $("#userNum").serialize(),
        dataType: "JSON",
        cache   : false,
        async   : true,
        type    : "POST",    
        success : function(obj) {
                UserPagingCallback(obj);                
        },           
        error     : function(xhr, status, error) {}
        
     });
}
 	
function UserPagingCallback(obj){
	
	var userNum = $("#userNum").val();
	var list = obj;
    
    if(list != null){
    	str = "";
    	var pageNum = list.pageNum;
        var startPageNum = list.startPageNum; 
        var endPageNum = list.endPageNum; 
        var prev = list.prev;
        var next = list.next;

        str += "<ul class=\"page_nation\">";
        
        if(prev){
    		str += "<a class=\"arrow prev\" href=\"/user/userList?userNum="+(startPageNum-1)+"\"><span><<</span></a>";
    	}
    	for(i=startPageNum; i<=endPageNum; i++){
    		if(i == userNum){
    			str += "<a class=\"active\" href=\"/user/userList?userNum="+i+"\">"+i+"</a>";
    		}
    		else{
    			str += "<a href=\"/user/userList?userNum="+i+"\">"+i+"</a>";
    		}
    	}
    	
    	if(next){
    		str += "<a class=\"arrow next\" href=\"/user/userList?userNum="+(endPageNum+1)+"\"><span>>></span></a>";
    	}
    	
    		str += "</ul>";
    	$("#tPaging").html(str);
    }
	
}
    