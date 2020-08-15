$(document).ready(function(){        
    BbsView();
    CommentList();
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
	            str += "<td rowspan=6 style=\"text-align:center;\"><img src=\""+filePath+"\" onerror=\"this.src='http://placehold.it/700x400'\" width=\"400\" height=\"400\" alt=\"\"/></td>";
	            str += "</tr>";
	            
	            str += "<tr height=\"70\" style=\"border-top:1px solid #444444;\">";
	            str += "<th colspan=2 style=\"border-right:1px solid #444444;\"><font size=\"5em\">"+title+"</font></th>";
	            str += "</tr>";
	            
	            str += "<tr>"
	            str += "<th>가격</th>";
	            str += "<td style=\"border-right:1px solid #444444;\">"+ price +"원</td>"
	            str += "</tr>";
	            
	            str += "<tr>";
	            str += "<th>판매량</th>";
	            str += "<td style=\"border-right:1px solid #444444;\">"+ sell +"</td>";
	            str += "</tr>";
	            
	            str += "<tr>";
	            str += "<td colspan=2 style=\"text-align:center; border-right:1px solid #444444;\"><br><button type=\"button\" style=\"padding:20px 70px;\">구매하기</button>";
	            str += "</tr>";

	            str += "<tr>";
	            str += "<td colspan=2 style=\"text-align:center; border-right:1px solid #444444;\"><button type=\"button\" style=\"padding:20px 70px;\">장바구니</button><td>";
	            str += "</tr>";
	            
	            str += "<tr height=\"100\" style=\"border:1px solid #444444\">";
	            str += "<td colspan=3 style=\"border:1px solid #444444\"><font size=\"3em\"><제품상세설명></font><br><br>"+ content +"</td>";
	            str += "</tr>";
	            
	        } else {
	            
	            alert("등록된 글이 존재하지 않습니다.");
	            return;
	        }        
	        
	        $("#tbody").html(str);
	    }
	   
		//댓글 목록
		function CommentList(bbsID, comCategory, commentNum){
			
			var commentNum = $("#commentNum").val();

			if(bbsID!=0 && comCategory!=0){
				$.ajax({    
		  
		            url        : "/comment/CommentList",
		            data    : $("#bbsID").serialize()+"&"+$("#comCategory").serialize()+"&num="+commentNum,
		            dataType: "JSON",
		            cache   : false,
		            async   : true,
		            type    : "POST",    
		            success : function(obj) {
		                CommentListCallback(obj);                
		            },           
		            error     : function(xhr, status, error) {}
		            
		         });
		    } else {
		        alert("오류가 발생했습니다.\n관리자에게 문의하세요.");
			}
		}
	   function CommentListCallback(obj){
		   	
		   var num = $("#commentNum").val();
		   	
	        var list = obj;
	        var listLen = obj.length;
	        console.log(list);
	        console.log(listLen);

	        var str = "";
	        
	        if(listLen >  0){
	            
	            for(var a=0; a<listLen; a++){  
	            	
		        	var commentID = list[a].commentID;
		        	var bbsID = list[a].bbsID;
		            var userID = list[a].userID;
		            var content = list[a].content;
		            
			        var filePath = "http://localhost:8080/resources/bbsImg/"+bbsID+"/comment/"+commentID+".jpg";
		           
			        str += "<div class=\"container\">";
		            str += "<div class=\"row\">";
		            str += "<table class=\"table table-striped\" style=\"text-align:center; border: 1px solid #dddddd\">";
		            str += "<tr>";
		            str += "<td align=\"left\">"+userID+"></td>"
		            str += "<td colspan=2></td>";
		            str += "<td align=\"right\">";
		            str += "<c:if test=\"${userForm.userID}=="+userID+"\">";
		            str += "<button type=\"button\" onclick=\"javascript:CommentUpdate();\">수정하기</button>";
		            str += "<button type=\"button\" onclick=\"javascript:CommentDelete();\">삭제하기</button>";
		            str += "</c:if>";
		            str += "</td>";
		            str += "</tr>";
		            str += "<tr>";
		            str += "<td colspan=5 align=\"left\">";
		            str += "<img src=\""+filePath+"\" onerror=\"this.style.display='none'\" width=\"200\" height=\"200\" alt=\"\" /><br><br>"+content+"<br><br></td>";
		            str += "</tr>";
		            str += "</table>";
		            str += "</div>";
		            str += "</div>";
		          
	            }
	        } else {
	            return;
	        }        
	        
	        $("#tcomment").html(str);
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
	               location.href="/bbs/home";
	           } else {                
	               alert("게시글 삭제를 실패하였습니다.");    
	               return;
	           }
	       }
	   }
	   
   //댓글 작성
   function CommentWrite(bbsID,	comCategory, userID){
	   
	   var bbsID = $("#bbsID").val();
       var comCategory = $("#comCategory").val();
       var userID = $("userID").val()
	   
	   var yn = confirm("댓글을 등록하시겠습니까?");        
       
       if(yn){
           
       	var form = $('#commentForm')[0];
       	var data = new FormData(form);
       	
       	data.append("bbsID",bbsID);
       	data.append("comCategory",comCategory);
       	data.append("userID",userID);
       	
       	$.ajax({    
               type    : "POST",    
               enctype: "multpart/form-data",
               url     : "/comment/CommentWrite",
               data    : data,
               cache   : false,
               processData: false,
               contentType: false,
               success : function(obj) {
                   CommentWriteCallback(obj);                
               },           
               error     : function(xhr, status, error) {}
               
           });
       }
       
   }

   //댓글 작성 함수
   function CommentWriteCallback(obj){
	   
	   var bbsID = $("#bbsID").val();
       var comCategory = $("#comCategory").val();
	   var commentNum = $("#commentNum").val();
       
       if(obj != null){        
           
           var result = obj.result;
           
           if(result == "SUCCESS"){                
               alert("댓글 등록을 성공하였습니다.");                
               location.href = "/bbs/bbsView?bbsID="+encodeURI(bbsID)+"&comCategory="+comCategory+"&commentNum="+encodeURI(commentNum);
           } else {                
               alert("댓글 등록을 실패하였습니다.");    
               return;
           }
       }
   }
