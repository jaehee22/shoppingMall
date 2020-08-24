$(document).ready(function(){       
	CommentView();
});
    
//기존댓글 가져오기
function CommentView(commentID){
	
	var commentID = $("#commentID").val();
	var subCommentID = $("#subCommentID").val();

	if(commentID!=0){
		$.ajax({    
  
            url     : "/comment/CommentView",
            data    : "commentID="+commentID+"&subCommentID="+subCommentID,
            dataType: "JSON",
            cache   : false,
            async   : true,
            type    : "POST",    
            success : function(obj) {
                CommentViewCallback(obj);                
            },           
            error     : function(xhr, status, error) {} 
         });
    } else {
        alert("오류가 발생했습니다.\n관리자에게 문의하세요.");
	}
}
function CommentViewCallback(obj){

    var str = "";
    
    if(obj != null){
    	var commentID = obj.commentID;
    	var bbsID = obj.bbsID;
    	var content = obj.content;
    	
        $("#commentID").val(commentID);
	    $("#bbsID").val(bbsID);
	    $("#content").val(content);
        	    
	} else {
	    alert("등록된 댓글이 존재하지 않습니다.");
	    return;
	}        
}
   

//댓글 수정
function CommentUpdate(){
	
    var content = $("#content").val();
    
    if (content == ""){            
        alert("내용을 입력해주세요.");
        $("#content").focus();
        return;
    }
    
    var yn = confirm("댓글을 수정하시겠습니까?");   
    
    var form = $('#commentForm')[0];
	var data = new FormData(form);

	if($('#newFile').val()==""){
   		data.delete('newFile');
   	}

	$.ajax({    
        type    : "POST",    
        enctype: "multpart/form-data",
        url     : "/comment/CommentUpdate",
        data    : data,
        cache   : false,
        processData: false,
        contentType: false,
        success : function(obj) {
            CommentUpdateCallback(obj);                
        },           
        error     : function(xhr, status, error) {}
        
    });
    
}

//댓글 수정 함수
function CommentUpdateCallback(obj){	

	var bbsID = $("#bbsID").val();
	
    if(obj != null){        
        
    	var result = obj.result;
                
        if(result == "SUCCESS"){                
            alert("댓글 수정을 성공하였습니다.");
        	window.opener.location.href= "/bbs/bbsView?bbsID="+bbsID+"&comCategory=1&commentNum=1";
    		window.close();
    	} else {                
            alert("댓글 수정을 실패하였습니다.");    
            return;
        }
    }
}
