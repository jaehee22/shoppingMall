 $(document).ready(function(){        
        OrderBbs();
        OrderPaging();
    });
 
 //주문목록
 function OrderBbs(){
	 $.ajax({    
  
		 url     : "/order/OrderBbs",
		 data    : $("#userID").serialize()+"&num="+$("#orderNum").val(),
		 dataType: "JSON",
		 cache   : false,
		 async   : true,
		 type    : "POST",    
		 success : function(obj) {
			 OrderBbsCallback(obj);                
		 },           
		 error     : function(xhr, status, error) {}		 
	 });
}
   
function OrderBbsCallback(obj){
	
	var orderNum = $("#orderNum").val();
	var tmp = 0;
    var list = obj;
    var listLen = obj.length;

    var str = "";
    
    if(listLen >  0){
        for(var a=0; a<listLen; a++){            	            	
        	
        	var orderID = list[a].orderID;
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
            str += "<td>"+delivery+"</td>";
            
        } 
    } else {
        
        str += "<tr colspan='3'>";
        str += "<td>등록된 글이 존재하지 않습니다.</td>";
        str += "<tr>";
    }
    	
		$("#tbody").html(str);
}

//주문목록 페이징
function OrderPaging(){
	
	$.ajax({    
		  
        url     : "/order/OrderPaging",
        data    : "num="+$("#orderNum").val()+"&"+$("#userID").serialize(),
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
	
	var orderNum = $("#orderNum").val();
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
    		str += "<a class=\"arrow prev\" href=\"/order/orderBbs?orderNum="+(startPageNum-1)+"\"><span><<</span></a>";
    	}
    	for(i=startPageNum; i<=endPageNum; i++){
    		if(i == orderNum){
    			str += "<a class=\"active\" href=\"/order/orderBbs?orderNum="+i+"\">"+i+"</a>";
    		}
    		else{
    			str += "<a href=\"/order/orderBbs?orderNum="+i+"\">"+i+"</a>";
    		}
    	}
    	
    	if(next){
    		str += "<a class=\"arrow next\" href=\"/order/orderBbs?orderNum="+(endPageNum+1)+"\"><span>>></span></a>";
    	}
    	
    		str += "</ul>";
    	$("#tPaging").html(str);
    }
	
}
    