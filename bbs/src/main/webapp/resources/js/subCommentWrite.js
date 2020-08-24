$(document).ready(function(){       
});
    

//답글 작성  
function SubCommentWrite(){
	   
	var yn = confirm("댓글을 등록하시겠습니까?");        
    
	if(yn){
     
		var content = $("#content").val();   
		var isManager = $("#isManager").val();
 
     if (isManager == 0){            
    	 alert("권한이 없습니다");
    	 $("#isManager").focus();
    	 return;
     }
     
     if (content == ""){            
    	 alert("내용을 입력해주세요.");
    	 $("#content").focus();
    	 return;
     }
    	
     $.ajax({    
      
    	 url        :"/comment/SubCommentWrite",
    	 data    : $("#commentForm").serialize(),
    	 dataType:"JSON",
    	 cache   : false,
    	 async   : true,
    	 type    :"POST",    
    	 success : function(obj) {
    		 SubCommentWriteCallback(obj);                
    	 },	           
    	 error     : function(xhr, status, error) {}
    	 
     });
	}    
}

//답글 작성 함수
function SubCommentWriteCallback(obj){
	   
	var bbsID = $("#bbsID").val();
	var comCategory = $("#comCategory").val();
	
	if(obj != null){        
        
		var result = obj.result;
		
		if(result == "SUCCESS"){                
			alert("답글 등록을 성공하였습니다.");                
			window.opener.location.href= "/bbs/bbsView?bbsID="+encodeURI(bbsID)+"&comCategory="+comCategory+"&commentNum=1";
			window.close();
		} else {                
			alert("답글 등록을 실패하였습니다.");    
			return;
		}
	}
}