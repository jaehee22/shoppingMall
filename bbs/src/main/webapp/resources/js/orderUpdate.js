$(document).ready(function(){        
    OrderView();
});

//배송지 수정 취소 돌아가기
function goView(){                
	location.href = "/order/orderView?orderID="+$("#orderID").val();
}
	
		
//게시판 뷰
function OrderView(){
	
	var orderID = $("#orderID").val();
	
	if(orderID!=0){
		$.ajax({    
  
            url     : "/order/OrderView",
            data    : $("#orderID").serialize(),
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
	
	if(listLen >  0){
		
		for(var a=0; a<listLen; a++){
			
			var bbsID = list[a].bbsID;
			var title = list[a].title;
        	var price = list[a].price;
        	var amount = list[a].amount;
        		
	        var filePath = "http://localhost:8080/resources/bbsImg/"+bbsID+"/"+bbsID+".jpg";

        	str1 += "<tr>";
            str1 += "<td><a href=\"/bbs/bbsView?bbsID="+bbsID+"&comCategory=1&commentNum=1\"><img src=\""+filePath+"\" onerror=\"this.src='http://placehold.it/80x80?text=No Image'\" width=\"80\" height=\"80\" alt=\"\"/></a></td>";
            str1 += "<td><a href=\"/bbs/bbsView?bbsID="+bbsID+"&comCategory=1&commentNum=1\"><br>"+title+"</a></td>";
            str1 += "<td><br>"+price+"원</td>";
            str1 += "<td><br>"+amount+"</td>";
            str1 += "</tr>";

        }	
		
	    	var orderName = list[0].orderName;
	    	var addr1 = list[0].addr1;
	    	var addr2 = list[0].addr2;
	    	var addr3 = list[0].addr3;
	    	var phoneNumber = list[0].phoneNumber;
	    	var orderMemo = list[0].orderMemo;
	    	
	        $("#orderName").val(orderName);
	        $("#addr1").val(addr1);
	        $("#addr2").val(addr2);
	        $("#addr3").val(addr3);
	        $("#phoneNumber").val(phoneNumber);
		    $("#orderMemo").val(orderMemo);
	    
	} else {
	    return;
	}       
			$("#str").html(str1);
}
   

//배송지 수정
function OrderUpdate(){
	
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
   		
	 var yn = confirm("배송지를 수정 하시겠습니까?");        
	    
	 if(yn){
		 $.ajax({    
			 
			 url        : "/order/OrderUpdate",
			 data    : $("#orderForm").serialize(),
			 dataType: "JSON",
			 cache   : false,
			 async   : true,
			 type    : "POST",    
			 success : function(obj) {
				 OrderUpdateCallback(obj);             
			 },           
			 error     : function(xhr, status, error) {}
		 });
	 }
}

//게시글 수정 함수
function OrderUpdateCallback(obj){	
	
	var orderID = $("#orderID").val();
	
    if(obj != null){        
        
    	var result = obj.result;
                
        if(result == "SUCCESS"){                
            alert("배송지 수정을 성공하였습니다.");
            location.href = "/order/orderView?orderID="+orderID;
        } else {                
            alert("배송지 수정을 실패하였습니다.");    
            return;
        }
    }
}
