$(document).ready(function(){        
    BbsView();
});
    
	//게시판 목록 이동
	function goBbsList(){                
	    location.href = "/bbs/bbs";
	}
	
	//게시글 수정 이동
	function goBbsUpdate(){
		var bbsID = $("#bbsID").val();
		location.href = "/bbs/bbsUpdate?bbsID="+bbsID;
	}
	
	//게시판 뷰
	function BbsView(bbsID){
		var bbsID = $("#bbsID").val();
		if(bbsID!=0){
			$.ajax({    
	  
	            url        : "/bbs/BbsView",
	            data    : $("#bbsForm").serialize(),
	            dataType: "JSON",
	            cache   : false,
	            async   : true,
	            type    : "POST",    
	            success : function(obj) {
	                BbsViewCallback(obj);                
	            },           
	            error     : function(xhr, status, error) {}
	            
	         });
	    } else {
	        alert("오류가 발생했습니다.\n관리자에게 문의하세요.");
		}
	}
	   function BbsViewCallback(obj){
	        var str = "";
	        if(obj != null){                                
	            var title = obj.title;
	            var price = obj.price;
	            var content = obj.content;
	            var sell = obj.sell
	            
	            str += "<tr>";
	            str += "<th>제품명</th>";
	            str += "<td>"+ title+"</td>";
	            str += "<th>가격</th>";
	            str += "<td>"+ price +"원</td>";
	            str += "<th>판매량</th>";
	            str += "<td>"+ sell +"</td>";
	            str += "</tr>";        
	            str += "<tr height=\"200\">";
	            str += "<td colspan=6><br><br>"+ content +"</td>";
	            str += "</tr>";
	            
	        } else {
	            
	            alert("등록된 글이 존재하지 않습니다.");
	            return;
	        }        
	        
	        $("#tbody").html(str);
	    }
	   
	   //게시글 삭제
	   function BbsDelete(){
	
	       var bbsID = $("#bbsID").val();
	       
	       var yn = confirm("게시글을 삭제하시겠습니까?");        
	       if(yn){
	           
	           $.ajax({    
	               
	               url        : "/bbs/BbsDelete",
	               data    : $("#bbsForm").serialize(),
	               dataType: "JSON",
	               cache   : false,
	               async   : true,
	               type    : "POST",    
	               success : function(obj) {
	                   BbsDeleteCallback(obj);                
	               },           
	               error     : function(xhr, status, error) {}
	               
	            });
	       }        
	   }
	   
	   function BbsDeleteCallback(obj){
	   
	       if(obj != null){        
	           
	           var result = obj.result;
	           
	           if(result == "SUCCESS"){                
	               alert("게시글 삭제를 성공하였습니다.");                
	               history.back(2);                
	           } else {                
	               alert("게시글 삭제를 실패하였습니다.");    
	               return;
	           }
	       }
	   }