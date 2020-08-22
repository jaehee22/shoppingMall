 $(document).ready(function(){        
        BbsbbsList();
    });

    function BbsbbsList(category,subCategory, num){
		
    	var category = $("#category").val();
		var subCategory = $("#subCategory").val();

		if(category!=0 && subCategory!=0){
			$.ajax({    
	  
	            url        : "/bbs/BbsbbsList",
	            data    : $("#category").serialize()+"&"+$("#subCategory").serialize()+"&"+$("#num").serialize(),
	            dataType: "JSON",
	            cache   : false,
	            async   : true,
	            type    : "POST",    
	            success : function(obj) {
		                BbsbbsListCallback(obj);                
	            },           
	            error     : function(xhr, status, error) {}
	            
	         });
		}
    }
    
    function BbsbbsListCallback(obj){
		var num = $("#num").val();
        var list = obj;
        var listLen = obj.length;
        var imgPath = "http://localhost:8080/resources/bbsImg/";
        console.log(list);
        console.log(listLen);

        var str = "";
        
        if(listLen >  0){
            
            for(var a=0; a<listLen; a++){            	            	
            	
            	var bbsID = list[a].bbsID;
                var category = list[a].category; 
                var subCategory = list[a].subCategory; 
                var title = list[a].title;
                var price = list[a].price;
                var sell = list[a].sell;
            	
                var filePath = imgPath+bbsID+"/"+bbsID+".jpg";

                str += "<div class=\"col-lg-4 col-md-6 mb-4\">";
                str += "<div class=\"card h-100\">";
                str += "<a href=\"/bbs/bbsView?bbsID="+bbsID+"&comCategory=1&commentNum=1\"><img class=\"card-img-top\" src=\""+filePath+"\" onerror=\"this.src='http://placehold.it/700x400'\" width=\"300\" height=\"171\" alt=\"\"/></a>";
                str += "<div class=\"card-body\">";
                str += "<h4 class=\"card-title\">";
                str += "<a href=\"/bbs/bbsView?bbsID="+bbsID+"&comCategory=1&commentNum=1\">"+title+"</a>";
                str += "</h4>";
                str += "<h5>"+price+"원</h5>";
                str += "</div>";
                str += "</div>";
                str += "</div>";
            } 
        } else {
            
            str += "<tr colspan='3'>";
            str += "<td>등록된 글이 존재하지 않습니다.</td>";
            str += "<tr>";
        }
        	
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