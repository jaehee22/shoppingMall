$(document).ready(function(){        
    BbsView();
});
    
	//게시판 목록 이동
	function goBbsList(){       
		location.href = document.referrer;
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
	        	var bbsID = obj.bbsID;
	            var title = obj.title;
	            var price = obj.price;
	            var content = obj.content;
	            var sell = obj.sell
	         
		        var filePath = "http://localhost:8080/resources/bbsImg/"+bbsID+"/"+bbsID+".jpg";
	          
	          
	            str += "<tr style=\"border:1px solid #444444\">";
	            str += "<td rowspan=5 style=\"text-align:center;\"><img src=\""+filePath+"\" onerror=\"this.src='http://placehold.it/700x400'\" width=\"400\" height=\"400\" alt=\"\"/></td>";
	            str += "</tr><tr height=\"70\" style=\"border-top:1px solid #444444;\">";
	            str += "<th colspan=2 style=\"border-right:1px solid #444444;\"><font size=\"5em\">"+title+"</font></th>";
	            str += "</tr>";
	            str += "<tr><th>가격</th>";
	            str += "<td style=\"border-right:1px solid #444444;\">"+ price +"원</td></tr>";
	            str += "<tr><th>판매량</th>";
	            str += "<td style=\"border-right:1px solid #444444;\">"+ sell +"</td></tr>";
	            str += "<tr><td colspan=2 style=\"text-align:center; border-right:1px solid #444444;\"><br><br><button type=\"button\" style=\"padding:30px;\">구매하기</button>&emsp;&emsp;";
	            str += "<button type=\"button\" style=\"padding:30px;\">장바구니</button><td></tr>";
	            str += "</tr><tr height=\"100\" style=\"border:1px solid #444444\">";
	            str += "<td colspan=3 style=\"border:1px solid #444444\">"+ content +"</td></tr></tr>";
	            
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