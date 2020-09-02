 $(document).ready(function(){        
        UserOrder();
        UserOrderPaging();
    });
 
 //주문목록
 function UserOrder(){
	 $.ajax({    
  
		 url     : "/order/UserOrder",
		 data    : "num="+$("#userOrderNum").val(),
		 dataType: "JSON",
		 cache   : false,
		 async   : true,
		 type    : "POST",    
		 success : function(obj) {
			 UserOrderCallback(obj);                
		 },           
		 error     : function(xhr, status, error) {}		 
	 });
}
   
function UserOrderCallback(obj){
	
	var userOrderNum = $("#userOrderNum").val();
	var tmp = 0;
    var list = obj;
    var listLen = obj.length;

    var str = "";
    
    if(listLen >  0){
        for(var a=0; a<listLen; a++){            	            	
        	
        	var orderID = list[a].orderID;
        	var userID = list[a].userID;
        	var title = list[a].title;
        	var items = list[a].items;
        	var delivery = list[a].delivery;
        	
        	if(orderID!=tmp){
        		
        	}
            str += "<tr>";
            str += "<td>"+orderID+"</td>";
            
            if(list[a].items == 1){
            	str += "<td><a href=\"/order/orderView?orderID="+orderID+"\">"+title+"</td>";
            }
            if(list[a].items > 1){
            	str += "<td><a href=\"/order/orderView?orderID="+orderID+"\">"+title+" 외"+(items-1)+"개의 제품</td>";
            }
            str += "<td>"+userID+"</td>";
            str += "<td>"+delivery+"</td>";
            str += "<td><button type=\"button\" class=\"btn btn-primary\" onclick=\"javascript:DeliveryState("+orderID+");\">수정</button>"
            
        } 
    } else {
        
        str += "<tr colspan='3'>";
        str += "<td>등록된 글이 존재하지 않습니다.</td>";
        str += "<tr>";
    }
    	
		$("#tbody").html(str);
}

//주문목록 페이징
function UserOrderPaging(){
	
	$.ajax({    
		  
        url     : "/order/UserOrderPaging",
        data    : "num="+$("#userOrderNum").val(),
        dataType: "JSON",
        cache   : false,
        async   : true,
        type    : "POST",    
        success : function(obj) {
                OrderPagingCallback(obj);                
        },           
        error     : function(xhr, status, error) {}
        
     });
}
 	
function OrderPagingCallback(obj){
	
	var userOrderNum = $("#UserOrderNum").val();
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
    		str += "<a class=\"arrow prev\" href=\"/order/userOrder?userOrderNum="+(startPageNum-1)+"\"><span><<</span></a>";
    	}
    	for(i=startPageNum; i<=endPageNum; i++){
    		if(i == userOrderNum){
    			str += "<a class=\"active\" href=\"/order/userOrder?userOrderNum="+i+"\">"+i+"</a>";
    		}
    		else{
    			str += "<a href=\"/order/userOrder?userOrderNum="+i+"\">"+i+"</a>";
    		}
    	}
    	
    	if(next){
    		str += "<a class=\"arrow next\" href=\"/order/userOrder?userOrderNum="+(endPageNum+1)+"\"><span>>></span></a>";
    	}
    	
    		str += "</ul>";
    	$("#tPaging").html(str);
    }
	
}

//주문상태 수정 자식창으로 이동
//댓글 수정 자식창으로 이동
function DeliveryState(orderID){
  window.name = "deliveryParant";										//이름이 없으니까 그냥 이름을 설정해줍니다.
  var url= "/order/deliveryState?orderID="+orderID
  window.open(url,"","width=500,height=200,left=300");					//자식창이 되는 주소를 오픈해줌 (크기도 설정해 줍니다.)
}
    