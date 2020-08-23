 $(document).ready(function(){        
        OrderBbs();
    });

    function OrderBbs(){
		$.ajax({    
  
            url        : "/order/OrderBbs",
            data    : $("#userID").serialize(),
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
            	var amount = list[a].amount;
            	var price = list[a].price;
            	var items = list[a].items;
            	if(orderID!=tmp){
            		
            	}
                str += "<tr>";
                str += "<td>"+orderID+"</td>";
                str += "<td>"+title+"</td>";
            } 
        } else {
            
            str += "<tr colspan='3'>";
            str += "<td>등록된 글이 존재하지 않습니다.</td>";
            str += "<tr>";
        }
        	
    		$("#tbody").html(str);
    }
    
    function BbsPaging(category,subCategory,num){
 		var category = $("#category").val();
		var subCategory = $("#subCategory").val();
 		
 		$.ajax({    
 			  
            url        : "/bbs/BbsPaging",
            data    : $("#category").serialize()+"&"+$("#subCategory").serialize()+"&"+$("#num").serialize(),
            dataType: "JSON",
            cache   : false,
            async   : true,
            type    : "POST",    
            success : function(obj) {
	                BbsPagingCallback(obj);                
            },           
            error     : function(xhr, status, error) {}
            
         });
 	}
 	
 	function BbsPagingCallback(obj){
 		var category = $("#category").val();
		var subCategory = $("#subCategory").val();
 		var num = $("#num").val();
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
        		str += "<a class=\"arrow prev\" href=\"/bbs/bbs?category="+category+"&subCategory="+subCategory+"&num="+(startPageNum-1)+"\"><span><<</span></a>";
        	}
        	for(i=startPageNum; i<=endPageNum; i++){
        		if(i == num){
        			str += "<a class=\"active\" href=\"/bbs/bbs?category="+category+"&subCategory="+subCategory+"&num="+i+"\">"+i+"</a>";
        		}
        		else{
        			str += "<a href=\"/bbs/bbs?category="+category+"&subCategory="+subCategory+"&num="+i+"\">"+i+"</a>";
        		}
        	}
        	
        	if(next){
        		str += "<a class=\"arrow next\" href=\"/bbs/bbs?category="+category+"&subCategory="+subCategory+"&num="+(endPageNum+1)+"\"><span>>></span></a>";
        	}
        	
        		str += "</ul>";
        	$("#tPaging").html(str);
        }
 		
 	}
    