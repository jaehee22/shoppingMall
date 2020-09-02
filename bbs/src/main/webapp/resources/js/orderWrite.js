 $(document).ready(function(){      
	 OrderList();
 });
 
 //현재 페이지를 벗어날 때 이 함수가 실행된다.
 var checkUnload = true;
 $(window).on("beforeunload", function(){
	 if(checkUnload){
		 return OrderReset();
     }
 });

 //주문서를 작성하지 않고 페이지를 나갈 때
 function OrderReset(){
	 
	 var whereOrder = $("#whereOrder").val();
	 
	 if(whereOrder==0){
		 $.ajax({    
			  
	         url        : "/cart/CartOrderReset",
	         data    : $("#userID").serialize(),
	         dataType: "JSON",
	         cache   : false,
	         async   : true,
	         type    : "POST",    
	         success : function(obj) {
	        	 alert("주문이 취소되었습니다.")
	         },           
	         error     : function(xhr, status, error) {}
	         
		 });
	 }
	 
	 else{
		 $.ajax({    	  
			 url        : "/cart/BbsOrderReset",
	         data    : $("#userID").serialize(),
	         dataType: "JSON",
	         cache   : false,
	         async   : true,
	         type    : "POST",    
	         success : function(obj) {
	        	 alert("주문이 취소되었습니다.")
	         },           
	         error     : function(xhr, status, error) {}
	         
		 });
	 }	
 }

 //주문하려는 상품 목록
 function OrderList(){

	 $.ajax({    
  
            url        : "/order/OrderList",
            data    : $("#userID").serialize(),
            dataType: "JSON",
            cache   : false,
            async   : true,
            type    : "POST",    
            success : function(obj) {
	                OrderListCallback(obj);                
            },           
            error     : function(xhr, status, error) {}         
	 });
 }
    
 function OrderListCallback(obj){

	 var list = obj;
	 var listLen = obj.length;
	 var imgPath = "http://localhost:8080/resources/bbsImg/";
	 var totalPrice = 0;

	 var str = "";
	 
	 if(listLen >  0){
		 
		 for(var a=0; a<listLen; a++){            	            	
			 
			 var bbsID = list[a].bbsID;
			 var title = list[a].title;
			 var price = list[a].price;
			 var amount = list[a].amount;
            	
			 var filePath = imgPath+bbsID+"/"+bbsID+".jpg";
			 
			 str += "<tr>";
			 str += "<td><a href=\"/bbs/bbsView?bbsID="+bbsID+"&comCategory=1&commentNum=1\"><img src=\""+filePath+"\" onerror=\"this.src='http://placehold.it/80x80?text=No Image'\" width=\"80\" height=\"80\" alt=\"\"/></a></td>";
			 str += "<td><a href=\"/bbs/bbsView?bbsID="+bbsID+"&comCategory=1&commentNum=1\"><br>"+title+"</a></td>";
			 str += "<td><br>"+price+"원</td>";
			 str += "<td><br>"+amount+"</td>";
			 str += "<td><br>"+(price*amount)+"원</td>"
			 str += "</tr>";
			 totalPrice += (price*amount);
		 } 
	 } else {
		 
		 str += "<tr>";
		 str += "<td colspan=6 style=\"text-align:center;\">장바구니가 비어있습니다.</td>";
		 str += "</tr>";
	 }
	 str += "<tr>";
	 str += "<td colspan=7 style=\"text-align:right;\">총 상품 금액 :"+totalPrice+"</td>";
	 str += "</tr>";
	 $("#tbody").html(str);
}

 //주소api사용
 function goPopup(){
	 var pop = window.open("/order/jusoPopup","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
 }    
 function jusoCallBack(addr2,addr3,addr1){
	 // 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
	 document.orderForm.addr2.value = addr2;
	 document.orderForm.addr3.value = addr3;
	 document.orderForm.addr1.value = addr1;
 }
    
 //주문하려는 배송정보 
 function OrderWrite(){
    	
	 var userID = $("#userID").val();
	 var orderName = $("#orderName").val();
	 var addr1 = $("#addr1").val();
	 var addr2 = $("#addr2").val();
	 var addr3 = $("#addr3").val();
	 var phoneNumber = $("#phoneNumber").val();

	 if(userID==""||orderName==""||addr1==""||addr2==""||phoneNumber==""){
		 alert("모든 칸을 채워주세요");
		 return;
	 }
    		
	 var yn = confirm("주문을 하시겠습니까?");        
	    
	 if(yn){
		 $.ajax({    
			 
			 url        : "/order/OrderWrite",
			 data    : $("#orderForm").serialize(),
			 dataType: "JSON",
			 cache   : false,
			 async   : true,
			 type    : "POST",    
			 success : function(obj) {
				 SubOrderWrite(obj);                
			 },           
			 error     : function(xhr, status, error) {}
		 });
	 }
 }
 
 //주문한 상품을 mysql테이블에 추가해준다
 function SubOrderWrite(obj){
    	
	 if(obj!=null){
		 var orderID = obj.orderID;
		 
		 $.ajax({    
    		  
            url        : "/order/SubOrderWrite",
            data    : "orderID="+orderID+"&"+$("#userID").serialize(),
            dataType: "JSON",
            cache   : false,
            async   : true,
            type    : "POST",    
            success : function(obj) {
	                SubOrderSuccess(obj);                
            },           
            error     : function(xhr, status, error) {}
         });
	}
}

//주문이 완료되면 cart에 있는 데이터를 없애준다.
function SubOrderSuccess(obj){
	
	if(obj!=null){

		$.ajax({    
    		  
            url        : "/order/SubOrderSuccess",
            data    : $("#userID").serialize(),
            dataType: "JSON",
            cache   : false,
            async   : true,
            type    : "POST",    
            success : function(obj) {
	                OrderWriteCallback(obj);                
            },           
            error     : function(xhr, status, error) {}
         });
	}
}
//주문서 작성 함수
function OrderWriteCallback(obj){

    if(obj != null){        
        
        var result = obj.result;
        
        if(result == "SUCCESS"){                
            alert("주문을 성공하였습니다.");    
    	    checkUnload = false;
            location.href = "/bbs/home";                 
        } else {                
            alert("주문을 실패하였습니다.");    
            return;
        }
    }
}
