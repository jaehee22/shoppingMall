 $(document).ready(function(){      
	 OrderList();
    });
 
 var checkUnload = true;
 $(window).on("beforeunload", function(){
     if(checkUnload){
    	 return OrderReset();
     }
 });

 $("#OrderWrite").on("click", function(){
	    checkUnload = false;
	});

 //페이지에서 나가면 다시 isOrder=0으로 셋팅해준다.
 function OrderReset(){
	 
	 $.ajax({    
		  
         url        : "/cart/OrderReset",
         data    : $("#userID").serialize(),
         dataType: "JSON",
         cache   : false,
         async   : true,
         type    : "POST",    
         success : function(obj) {
         },           
         error     : function(xhr, status, error) {}
         
      });
 }

 
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
        console.log(list);
        console.log(listLen);

        var str = "";
        
        if(listLen >  0){
            
            for(var a=0; a<listLen; a++){            	            	
            	
            	var bbsID = list[a].bbsID;
                var title = list[a].title;
                var price = list[a].price;
                var amount = list[a].amount;
            	
                var filePath = imgPath+bbsID+"/"+bbsID+".jpg";

                str += "<tr>";
                str += "<td><a href=\"/bbs/bbsView?bbsID="+bbsID+"&comCategory=1&commentNum=1\"><img src=\""+filePath+"\" onerror=\"this.src='http://placehold.it/700x400'\" width=\"80\" height=\"80\" alt=\"\"/></a></td>";
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
    
    function goPopup(){
    	var pop = window.open("/order/jusoPopup","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
    }
    
    function jusoCallBack(addr2,addr3,addr1){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		document.orderForm.addr2.value = addr2;
		document.orderForm.addr3.value = addr3;
		document.orderForm.addr1.value = addr1;
    }