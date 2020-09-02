 $(document).ready(function(){        
        CartList();
        CartPaging();
    });

//장바구니 리스트
function CartList(){
	
	var userID = $("#userID").val();

	if(userID != null){
		$.ajax({    
  
            url     : "/cart/CartList",
            data    : $("#cartNum").serialize()+"&"+$("#userID").serialize(),
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
            str += "<td><br><input type=\"checkbox\" name=\"cartBox\" value=\""+(price*amount)+"\" onClick=\"javascript:itemSum(checkBoxForm);\"></td>"
            str += "<td><a href=\"/bbs/bbsView?bbsID="+bbsID+"&comCategory=1&commentNum=1\"><img src=\""+filePath+"\" onerror=\"this.src='http://placehold.it/80x80?text=No Image'\" width=\"80\" height=\"80\" alt=\"\"/></a></td>";
            str += "<td><a href=\"/bbs/bbsView?bbsID="+bbsID+"&comCategory=1&commentNum=1\"><br>"+title+"</a></td>";
            str += "<td><br>"+price+"원</td>";
            str += "<td><br><input type=\"text\" name=\"amount\" value=\""+amount+"\" class=\"tbox\" size=\"2\"></td>";
            str += "<td><br>"+(price*amount)+"원</td>"
            str += "<td><br><button type=\"button\" onclick=\"javascript:CartUpdate("+a+",checkBoxForm,"+bbsID+");\">변경</button>&emsp;";
            str += "<button type=\"button\" onclick=\"javascript:CartDelete("+cartID+");\">삭제</button></td>";
            str += "<td><input type=\"hidden\" name=\"bbsID\" value=\""+bbsID+"\"></td>"
            str += "</tr>";
        } 
    } else {
        
        str += "<tr>";
        str += "<td colspan=6 style=\"text-align:center;\">장바구니가 비어있습니다.</td>";
        str += "</tr>";
    }
   		$("#tbody").html(str);
}
//페이징
function CartPaging(){
	
	$.ajax({    
		  
        url        : "/cart/CartPaging",
        data    : $("#userID").serialize()+"&"+$("#cartNum").serialize(),
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
    
    if(list != null){
    	str = "";
        var startPageNum = list.startPageNum; 
        var endPageNum = list.endPageNum; 
        var prev = list.prev;
        var next = list.next;
        
        str += "<ul class=\"page_nation\">";
        
        if(prev){
    		str += "<a class=\"arrow prev\" href=\"/cart/cartBbs?cartNum="+(startPageNum-1)+"\"><span><<</span></a>";
    	}
    	for(i=startPageNum; i<=endPageNum; i++){
    		if(i == cartNum){
    			str += "<a class=\"active\" href=\"/cart/cartBbs?cartNum="+i+"\">"+i+"</a>";
    		}
    		else{
    			str += "<a href=\"/cart/cartBbs?cartNum="+i+"\">"+i+"</a>";
    		}
    	}
    	
    	if(next){
    		str += "<a class=\"arrow next\" href=\"/cart/cartBbs?cartNum="+(endPageNum+1)+"\"><span>>></span></a>";
    	}
    	
    		str += "</ul>";
    	$("#tPaging").html(str);
    }
	
}

//장바구니 체크 합계
function itemSum(frm){
   var sum = 0;
   var count = frm.cartBox.length;
   for(var i=0; i < count; i++ ){
       if(frm.cartBox[i].checked == true ){
	    sum += parseInt(frm.cartBox[i].value);
       }
   }
   frm.total_sum.value = sum;
}
    
//장바구니 분량 수정
function CartUpdate(a,frm,bbsID){
	
	var amount = frm.amount[a].value
	
	if(amount==0 || amount==undefined){
		alert("수량을 입력해주세요.")
	}
	
	var yn = confirm("장바구니 수량을 변경 하시겠습니까?");        
	
    if(yn){
		$.ajax({    
  
            url        : "/cart/CartUpdate",
            data    : "amount="+amount+"&"+$("#userID").serialize()+"&bbsID="+bbsID,
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
}
    
//장바구니 수량 변경 함수
function CartDeleteCallback(obj){	

	var cartNum = $("#cartNum").val();
	
    if(obj != null){        
        
    	var result = obj.result;
                
        if(result == "SUCCESS"){                
            location.href = "/cart/cartBbs?cartNum="+cartNum;
        } else {                
            alert("장바구니 수량변경을 실패하였습니다.");    
            return;
        }
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
	
//장바구니 선택주문
function CheckOrder(frm){
	var count = frm.cartBox.length;
	var a = 0;
	var yn = confirm("선택한 상품들을 구매하시겠습니까?");        
	
	if(yn){
		for(var i=0; i < count; i++ ){
			if(frm.cartBox[i].checked == true ){
				a += 1;
				var bbsID = frm.bbsID[i].value;
				$.ajax({    
				 	  
	 	            url     : "/cart/CartOrder",
	 	            data    : $("#userID").serialize()+"&bbsID="+bbsID,
	 	            dataType: "JSON",
	 	            cache   : false,
	 	            async   : true,
	 	            type    : "POST",    
	 	            success : function(obj) {
	 		               location.href = "/order/orderWrite?whereOrder=0";            
	 	            },           
	 	            error     : function(xhr, status, error) {}
	 	            
	 	         });
			}
		}
	} 
	if(yn && a==0){
		alert("선택된 제품이 없습니다.");
	}
}
	
//장바구니 전체주문
function AllOrder(frm){
	
	var count = frm.cartBox.length;
	
	var yn = confirm("전체 상품을 구매하시겠습니까?");        
	
	if(yn){
		for(var i=0; i < count; i++ ){
			
			var bbsID = frm.bbsID[i].value;
			$.ajax({    
			 	  
 	            url     : "/cart/CartOrder",
 	            data    : $("#userID").serialize()+"&bbsID="+bbsID,
 	            dataType: "JSON",
 	            cache   : false,
 	            async   : true,
 	            type    : "POST",    
 	            success : function(obj) {
 		               location.href = "/order/orderWrite?whereOrder=0";            
 	            },           
 	            error     : function(xhr, status, error) {}
 	            
 	         });
		}
		
	}
}