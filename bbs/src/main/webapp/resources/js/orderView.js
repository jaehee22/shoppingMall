$(document).ready(function(){        
    OrderView();
});
    
	//주문 목록 이동
	function goBbsList(){       
		location.href = "/order/orderBbs?orderNum=1";
	}
	
	//주문 수정 이동
	function goBbsUpdate(){
		var bbsID = $("#bbsID").val();
		location.href = "/bbs/bbsUpdate?bbsID="+bbsID;
	}
	
	//게시판 뷰
	function OrderView(){
		var orderID = $("#orderID").val();
		if(orderID!=0){
			$.ajax({    
	  
	            url     : "/order/OrderView",
	            data    : $("#orderForm").serialize(),
	            dataType: "JSON",
	            cache   : false,
	            async   : true,
	            type    : "POST",    
	            success : function(obj) {
	                OrderViewCallback(obj);                
	            },           
	            error     : function(xhr, status, error) {}
	            
	         });
	    } else {
	        alert("오류가 발생했습니다.\n관리자에게 문의하세요.");
		}
	}
	
	   function OrderViewCallback(obj){
		   
	        var list = obj;
	        var listLen = obj.length;
	        var totalPrice = 0;
	        var str1 = "";
	        var str2 = "";
	        
	        if(listLen >  0){
	        	
	            for(var a=0; a<listLen; a++){
	            	
	            	var bbsID = list[a].bbsID;
	            	var title = list[a].title;
	            	var price = list[a].price;
	            	var amount = list[a].amount;
	            		
			        var filePath = "http://localhost:8080/resources/bbsImg/"+bbsID+"/"+bbsID+".jpg";

	            	str1 += "<tr>";
	                str1 += "<td><a href=\"/bbs/bbsView?bbsID="+bbsID+"&comCategory=1&commentNum=1\"><img src=\""+filePath+"\" onerror=\"this.src='http://placehold.it/700x400'\" width=\"80\" height=\"80\" alt=\"\"/></a></td>";
	                str1 += "<td colspan=2><a href=\"/bbs/bbsView?bbsID="+bbsID+"&comCategory=1&commentNum=1\"><br>"+title+"</a></td>";
	                str1 += "<td><br>"+price+"원</td>";
	                str1 += "<td><br>"+amount+"</td>";
	                str1 += "</tr>";
	                
	                totalPrice += (price*amount);
	            }	  
	            
		            var orderName = list[0].orderName;
		        	var addr1 = list[0].addr1;
		        	var addr2 = list[0].addr2;
		        	var addr3 = list[0].addr3;
		        	var phoneNumber = list[0].phoneNumber;
		        	var orderMemo = list[0].orderMemo;
		        	var delivery = list[0].delivery;
		        	
		        	str2 += "<tr>";
		        	str2 += "<td>"+orderName+"</td>";
		        	str2 += "<td>"+phoneNumber+"</td>";
		        	str2 += "<td>"+addr1+"</td>";
		        	str2 += "<td>"+addr2+", "+addr3+"</td>";
		        	str2 += "<td>"+orderMemo+"</td>";
		        	str2 += "</tr>";
		        	str2 += "<tr>";
		        	str2 += "<td colspan=5><br>총 가격 : "+totalPrice+"</td>";
		        	str2 += "</tr>";
		        	str2 += "<tr><td colspan=5><br><h3>현재 상태 : "+delivery+"</td></tr>";
		        	
	        } else {
	            
	            alert("등록된 글이 존재하지 않습니다.");
	            return;
	        }        
	        
	        $("#tstr1").html(str1);
	        $("#tstr2").html(str2);
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
		   var NowUserID = $("#userID").val();
		   var isManager = $("#isManager").val();
		   var comCategory = $("#comCategory").val();

		   	var list = obj;
	        var listLen = obj.length;

	        var str = "";
	        
	        if(listLen >  0){
	            
	            for(var a=0; a<listLen; a++){  
	            	
		        	var commentID = list[a].commentID;
		        	var bbsID = list[a].bbsID;
		            var userID = list[a].userID;
		            var content = list[a].content;
		            var subCommentID = list[a].subCommentID;
		            var comCategory = list[a].comCategory;
		            
			        var filePath = "http://localhost:8080/resources/bbsImg/"+bbsID+"/comment/"+commentID+".jpg";
			       			        
			        //답글은 본인 or 관리자 이외에는 비밀글로 보일 예정
			        if(comCategory != 2 || NowUserID == userID || isManager == 1 || (a>0 && commentID == list[a-1].commentID && NowUserID == list[a-1].userID)){
			        	
				        //답글이 아닐 경우
				        if(subCommentID == 0){
					        str += "<div class=\"container\">";
				            str += "<div class=\"row\">";
				            str += "<table class=\"table table-striped\" style=\"text-align:center; border: 1px solid #dddddd\">";
				            str += "<tr>";
				            str += "<td align=\"left\">"+userID+"님의 ";
				            
				            if(comCategory == 1){
				            	str += "후기입니다♥"
				            }else{
				            	str += "질문입니다♥"
				            }
				           
				            str += "</td><td colspan=2></td>";
				            str += "<td colspan=2 align=\"right\">";
				            
				            if(NowUserID == userID){
				            str += "<button type=\"button\" onclick=\"nwindow("+bbsID+","+commentID+","+subCommentID+")\">수정</button>";
				            str += "&emsp;<button type=\"button\" onclick=\"javascript:CommentDelete("+commentID+","+subCommentID+");\">삭제</button>";
				            }
				            
				            if(isManager == 1){
					            str += "&emsp;<button type=\"button\" onclick=\"subWindow("+bbsID+","+commentID+","+comCategory+")\">답글</button>";
				            }
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
				        
			            //답글일 경우
				        else{
				        	str = str.slice(0,str.length-20);
				            str += "<tr>";
				            str += "<td bgcolor=\"white\"  colspan=4 align=\"left\">└─&emsp;"+content+"</td>"
			            	str += "<td bgcolor=\"white\" align=\"right\">";
				            
				            //관리자
				            if(isManager == 1){
					            str += "<button type=\"button\" onclick=\"nwindow("+bbsID+","+commentID+","+subCommentID+")\">수정</button>";
					            str += "&emsp;<button type=\"button\" onclick=\"javascript:CommentDelete("+commentID+","+subCommentID+");\">삭제</button>";
				            }        
				            
				            str += "</td>";
				            str += "</tr>";			      
				            str += "</table>";
				            str += "</div>";
				            str += "</div>";
				        }
				        
			        }else{
			        	//비밀Q&A
			            if(subCommentID == 0){
			            	str += "<div class=\"container\">";
				            str += "<div class=\"row\">";
				            str += "<table  class=\"table\" style=\"text-align:center; border: 1px solid #dddddd\">";
				            str += "<tr>";
				            str += "<td colspan=6 align=\"left\">비밀글 입니다♥</td>"
			           
			            }else{
				        	str = str.slice(0,str.length-20);
				        	str += "<tr><td colspan=6 align=\"left\">└─&emsp;비밀글 입니다♥</td>"
			            }
			        
			            str += "</tr>";			      
			            str += "</table>";
			            str += "</div>";
			            str += "</div>";
			        }
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
	   
	   var yn = confirm("댓글을 등록하시겠습니까?");        
       
       if(yn){
        
  		var content = $("#content").val();   
    	        
        if (userID == undefined){            
 	        alert("로그인을 해주세요.");
 	        $("#userID").focus();
 	        return;
 	    }
        
  		 if (content == ""){            
 	        alert("내용을 입력해주세요.");
 	        $("#content").focus();
 	        return;
 	    }
  		
       	var form = $('#commentForm')[0];
       	var data = new FormData(form);
       	
       	if($('#fileName').val()==""){
       		data.delete('fileName');
       	}

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

   //댓글 수정
   function BbsUpdate(){

	    var title = $("#title").val();
	    var price = $("#price").val();
	    var content = $("#content").val();
	        
	    if (title == ""){            
	        alert("제목을 입력해주세요.");
	        $("#title").focus();
	        return;
	    }
	    
	    if (price == 0){            
	        alert("가격을 입력해 주세요");
	        $("#price").focus();
	        return;
	    }
	    
	    if (content == ""){            
	        alert("내용을 입력해주세요.");
	        $("#content").focus();
	        return;
	    }
	    
	    var yn = confirm("게시글을 수정하시겠습니까?");        
	    if(yn){
	            
	        $.ajax({    
	            
	            url        : "/bbs/BbsUpdate",
	            data    : $("#bbsForm").serialize(),
	            dataType: "JSON",
	            cache   : false,
	            async   : true,
	            type    : "POST",    
	            success : function(obj) {
	                BbsUpdateCallback(obj);                
	            },           
	            error     : function(xhr, status, error) {}
	            
	        });
	    }
	}

	//댓글 수정 함수
	function BbsUpdateCallback(obj){	
		
	    if(obj != null){        
	        
	    	var result = obj.result;
	                
	        if(result == "SUCCESS"){                
	            alert("게시글 수정을 성공하였습니다.");
	            location.href = history.back(-3);
	        } else {                
	            alert("게시글 수정을 실패하였습니다.");    
	            return;
	        }
	    }
	}
	
	 
	   //댓글 삭제
	   function CommentDelete(commentID,subCommentID){
	
		   var bbsID = $("#bbsID").val();
		   
	       var yn = confirm("댓글을 삭제하시겠습니까?");        
	       
	       if(yn){
	           
	           $.ajax({    
	               
	               url        : "/comment/CommentDelete",
	               data    : "commentID="+commentID+"&bbsID="+bbsID+"&subCommentID="+subCommentID,
	               dataType: "JSON",
	               cache   : false,
	               async   : true,
	               type    : "POST",    
	               success : function(obj) {
	                   CommentDeleteCallback(obj);                
	               },           
	               error     : function(xhr, status, error) {}
	               
	            });
	       }        
	   }
	   
	   function CommentDeleteCallback(obj){
	   
		   var bbsID = $("#bbsID").val();
	       var comCategory = $("#comCategory").val();
		   var commentNum = $("#commentNum").val();
	       
	       if(obj != null){        
	           
	           var result = obj.result;
	           
	           if(result == "SUCCESS"){                
	               alert("댓글 삭제를 성공하였습니다.");                
	               location.href = "/bbs/bbsView?bbsID="+encodeURI(bbsID)+"&comCategory="+comCategory+"&commentNum="+encodeURI(commentNum);
	           } else {                
	               alert("댓글 삭제를 실패하였습니다.");    
	               return;
	           }
	       }
	   }
	   
	   //페이징
	   function CommentPaging(){

		   $.ajax({    
	 			  
	            url        : "/comment/CommentPaging",
	            data    : $("#bbsID").serialize()+"&"+$("#comCategory").serialize()+"&num="+$("#commentNum").val(),
	            dataType: "JSON",
	            cache   : false,
	            async   : true,
	            type    : "POST",    
	            success : function(obj) {
		                CommentPagingCallback(obj);                
	            },           
	            error     : function(xhr, status, error) {}
	            
	         });
	 	}
	 	
	 	function CommentPagingCallback(obj){
	 		
	 		var bbsID = $("#bbsID").val();
			var comCategory = $("#comCategory").val();
	 		var commentNum = $("#commentNum").val();
	 		
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
	        		str += "<a class=\"arrow prev\" href=\"/bbs/bbsView?bbsID="+bbsID+"&comCategory="+comCategory+"&commentNum="+(startPageNum-1)+"\"><span><<</span></a>";
	        	}

	        	for(i=startPageNum; i<=endPageNum; i++){
	        		if(i == commentNum){
	        			str += "<a class=\"active\" href=\"/bbs/bbsView?bbsID="+bbsID+"&comCategory="+comCategory+"&commentNum="+i+"\">"+i+"</a>";
	        		}
	        		else{
	        			str += "<a href=\"/bbs/bbsView?bbsID="+bbsID+"&comCategory="+comCategory+"&commentNum="+i+"\">"+i+"</a>";
	        		}
	        	}
	        	if(next){
	        		str += "<a class=\"arrow next\" href=\"/bbs/bbsView?bbsID="+bbsID+"&comCategory="+comCategory+"&commentNum="+(endPageNum+1)+"\"><span>>></span></a>";
	        	}
	        	
	        	str += "</ul>";

	        	$("#tPaging").html(str);
	        }
	 		
	 	}
	 	
	    //장바구니 추가or수정
	    function CartView(){
	 	   
	 	   var yn = confirm("장바구니에 담으시겠습니까?");        
	        
	        if(yn){
	         
	   		var amount = $("#amount").val(); 
	   		var bbsID = $("#bbsID").val();
	   		var userID = $("#userID").val();
	   			   		
	   		 if (amount == 0){            
	  	        alert("수량을 입력해주세요.");
	  	        $("#amount").focus();
	  	        return;
	  	    }
	   		 
	   		 if (userID == null){
	   			 alert("로그인을 해주세요");
	   			 $("#userID").focus();
	   			 return;
	   		 }
	   		 
	   		//장바구니에 이미 들어있는 품목인지 확인
	   		$.ajax({    
	   		  
	            url        : "/cart/CartView",
	            data    : $("#userID").serialize()+"&"+$("#bbsID").serialize(),
	            dataType: "JSON",
	            cache   : false,
	            async   : true,
	            type    : "POST",    
	            success : function(obj) {		            
		                CartUpdate(obj.amount);                
	            },           
	            error     : function(xhr, status, error) {
	            	CartWrite();
	            	}
	         });
	        }
	        
	    }
	    
	    function CartUpdate(Amount){
	    	
	   		var amount = Number($("#amount").val()) + Number(Amount); 

	   		$.ajax({    
	  	   		  
	            url        : "/cart/CartUpdate",
	            data    : "amount="+amount+"&"+$("#userID").serialize()+"&"+$("#bbsID").serialize(),
	            dataType: "JSON",
	            cache   : false,
	            async   : true,
	            type    : "POST",    
	            success : function(obj) {
		                CartUpdateCallback(obj);                
	            },           
	            error     : function(xhr, status, error) {}
	            
	         });
	   } 
		
	    //장바구니 수정 함수
		function CartUpdateCallback(obj){	
			
			var bbsID = $("#bbsID").val();
		    var comCategory = $("#comCategory").val();
			var commentNum = $("#commentNum").val();
			
		    if(obj != null){        
		        
		    	var result = obj.result;
		                
		        if(result == "SUCCESS"){                
		            alert("장바구니에 수량을 변경하였습니다.");
		               location.href = "/bbs/bbsView?bbsID="+encodeURI(bbsID)+"&comCategory="+comCategory+"&commentNum="+encodeURI(commentNum);
		        } else {                
		            alert("장바구니 수량 변경을 실패하였습니다.");    
		            return;
		        }
		    }
		}
		
		function CartWrite(){
	    	
	   		var amount = $("#amount").val(); 
	   		var bbsID = $("#bbsID").val();
	   		var userID = $("#userID").val();
	   		 
	   		$.ajax({    
	  	   		  
	            url        : "/cart/CartWrite",
	            data    : $("#userID").serialize()+"&"+$("#bbsID").serialize()+"&"+$("#amount").serialize(),
	            dataType: "JSON",
	            cache   : false,
	            async   : true,
	            type    : "POST",    
	            success : function(obj) {
		                CartWriteCallback(obj);                
	            },           
	            error     : function(xhr, status, error) {}
	            
	         });
	   }
		//장바구니 작성 함수
		function CartWriteCallback(obj){	
			
			var bbsID = $("#bbsID").val();
		    var comCategory = $("#comCategory").val();
			var commentNum = $("#commentNum").val();
			
		    if(obj != null){        
		        
		    	var result = obj.result;
		                
		        if(result == "SUCCESS"){                
		            alert("장바구니에 추가하였습니다.");
		            location.href = "/bbs/bbsView?bbsID="+encodeURI(bbsID)+"&comCategory="+comCategory+"&commentNum="+encodeURI(commentNum);
		        } else {                
		            alert("장바구니 추가를 실패하였습니다.");    
		            return;
		        }
		    }
		}
		
		//구매하기 함수
		function ThisOrder(){
			
			var amount = $("#amount").val()
			
			 if (amount == 0){            
		  	        alert("수량을 입력해주세요.");
		  	        $("#amount").focus();
		  	        return;
		  	 }
			
			var yn = confirm("해당상품을 구매하시겠습니까?");        
			
	 		if(yn){
				$.ajax({    
				 	  
	 	            url     : "/cart/ThisOrder",
	 	            data    : $("#userID").serialize()+"&"+$("#amount").serialize()+"&"+$("#bbsID").serialize(),
	 	            dataType: "JSON",
	 	            cache   : false,
	 	            async   : true,
	 	            type    : "POST",    
	 	            success : function(obj) {
	 		               location.href = "/order/orderWrite?whereOrder=1";            
	 	            },           
	 	            error     : function(xhr, status, error) {}
	 	            
	 	         });
			}
		}