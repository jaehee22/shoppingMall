 $(document).ready(function(){        
        CartList();
        CartPaging();
    });

    function CartList(){
		
    	var cartNum = $("#cartNum").val();
    	var userID = $("#userID").val();

		if(cartNum !=0 && userID != null){
			$.ajax({    
	  
	            url        : "/cart/CartList",
	            data    : "num="+$("#cartNum").val()+"&"+$("#userID").serialize(),
	            dataType: "JSON",
	            cache   : false,
	            async   : true,
	            type    : "POST",    
	            success : function(obj) {
		                CartListCallback(obj);                
	            },           
	            error     : function(xhr, status, error) {}
	            
	         });
		}
    }
    
    function CartListCallback(obj){
		var cartNum = $("#cartNum").val();
		
        var list = obj;
        var listLen = obj.length;
        var imgPath = "http://localhost:8080/resources/bbsImg/";
        console.log(list);
        console.log(listLen);

        var str = "";
        
        if(listLen >  0){
            
            for(var a=0; a<listLen; a++){            	            	
            	
            	var cartID = list[a].cartID;
            	var bbsID = list[a].bbsID;
                var title = list[a].title;
                var price = list[a].price;
                var amount = list[a].amount;
            	
                var filePath = imgPath+bbsID+"/"+bbsID+".jpg";
                
                str += "<tr>";
                str += "<td><br><input type=\"checkbox\" name=\"cartCheck\"/></td>"
                str += "<td><a href=\"/bbs/bbsView?bbsID="+bbsID+"&comCategory=1&commentNum=1\"><img src=\""+filePath+"\" onerror=\"this.src='http://placehold.it/700x400'\" width=\"80\" height=\"80\" alt=\"\"/></a></td>";
                str += "<td><a href=\"/bbs/bbsView?bbsID="+bbsID+"&comCategory=1&commentNum=1\"><br>"+title+"</a></td>";
                str += "<td><br>"+price+"원</td>";
                str += "<td><br>"+amount+"</td>";
                str += "<td><br>"+(price*amount)+"원</td>"
                str += "<td><br><button type=\"button\" onclick=\"/bbs/bbsOrder\">주문</button>&emsp;";
                str += "<button type=\"button\" onclick=\"javascript:CartDelete("+cartID+");\">삭제</button></td>";
                str += "</tr>";
            } 
        } else {
            
            str += "<tr>";
            str += "<td colspan=6 style=\"text-align:center;\">장바구니가 비어있습니다.</td>";
            str += "<tr>";
        }
        	
    		$("#tbody").html(str);
    }
    
    function CartPaging(cartNum){
    	
 		var cartNum = $("#cartNum").val();
 		
 		$.ajax({    
 			  
            url        : "/cart/CartPaging",
            data    : "num="+$("#cartNum").val()+"&"+$("#userID").serialize(),
            dataType: "JSON",
            cache   : false,
            async   : true,
            type    : "POST",    
            success : function(obj) {
	                CartPagingCallback(obj);                
            },           
            error     : function(xhr, status, error) {}
            
         });
 	}
 	
 	function CartPagingCallback(obj){

 		var cartNum = $("#cartNum").val();

 		var list = obj;
        console.log(list);
        
        if(list != null){
        	str = "";
        	var pageNum = list.pageNum;
            var startPageNum = list.startPageNum; 
            var endPageNum = list.endPageNum; 
            var prev = list.prev;
            var next = list.next;

            str += "<ul class=\"page_nation\">";
            
            if(prev){
        		str += "<a class=\"arrow prev\" href=\"/bbs/bbsCart?cartNum="+(startPageNum-1)+"\"><span><<</span></a>";
        	}
        	for(i=startPageNum; i<=endPageNum; i++){
        		if(i == cartNum){
        			str += "<a class=\"active\" href=\"/bbs/bbsCart?cartNum="+i+"\">"+i+"</a>";
        		}
        		else{
        			str += "<a href=\"/bbs/bbsCart?cartNum="+i+"\">"+i+"</a>";
        		}
        	}
        	
        	if(next){
        		str += "<a class=\"arrow next\" href=\"/bbs/bbsCart?cartNum="+(endPageNum+1)+"\"><span>>></span></a>";
        	}
        	
        		str += "</ul>";
        	$("#tPaging").html(str);
        }
 		
 	}
 	
 	//장바구니 상품 삭제
 	 function CartDelete(cartID){

 		var yn = confirm("장바구니에 담긴 상품을 삭제하시겠습니까?");        
        
        if(yn){
 			$.ajax({    
 	  
 	            url        : "/cart/CartDelete",
 	            data    : "cartID="+cartID,
 	            dataType: "JSON",
 	            cache   : false,
 	            async   : true,
 	            type    : "POST",    
 	            success : function(obj) {
 		                CartDeleteCallback(obj);                
 	            },           
 	            error     : function(xhr, status, error) {}
 	            
 	         });
 		}
     }
 	 
 	//장바구니 삭제 함수
	function CartDeleteCallback(obj){	

		var cartNum = $("#cartNum").val();
		
	    if(obj != null){        
	        
	    	var result = obj.result;
	                
	        if(result == "SUCCESS"){                
	            alert("장바구니의 상품을 삭제하였습니다.");
	            location.href = "/cart/cartBbs?cartNum="+cartNum;
	        } else {                
	            alert("상품 삭제를 실패하였습니다.");    
	            return;
	        }
	    }
	}