$(document).ready(function(){       
});
    
//기존댓글 가져오기
function DeliveryUpdate(i){
	
	var state = "";
	
	if(i==1){
		state="상품준비중";
	}else if(i==2){
		state="배송중";
	}else if(i==3){
		state="배송완료";
	}else{
		alert("error");
		return;
	}
	
	if(state!="error"){
		$.ajax({    
  
            url     : "/order/DeliveryUpdate",
            data    : "delivery="+state+"&"+$("#orderID").serialize(),
            dataType: "JSON",
            cache   : false,
            async   : true,
            type    : "POST",    
            success : function(obj) {
                DeliveryUpdateCallback(obj);                
            },           
            error     : function(xhr, status, error) {} 
         });
    } else {
        alert("오류가 발생했습니다.\n관리자에게 문의하세요.");
	}
}
function DeliveryUpdateCallback(obj){	
	
    if(obj != null){        
        
    	var result = obj.result;
                
        if(result == "SUCCESS"){                
            alert("배송상태를 수정하였습니다.");
        	window.opener.location.href= "/order/userOrder?userOrderNum=1";
    		window.close();
    	} else {                
            alert("배송상태 수정을 실패하였습니다.");    
            return;
        }
    }
}
