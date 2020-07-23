 $(document).ready(function(){        
        BbsbbsList();
        Category();
    });
 	
    function BbsbbsList(category,subCategory){
		var category = $("#category").val();
		var subCategory = $("#subCategory").val();
		
		if(category!=0 && subCategory!=0){
			$.ajax({    
	  
	            url        : "/bbs/BbsbbsList",
	            data    : $("#category").serialize()+"&"+$("#subCategory").serialize(),
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
    
    function Category(category,subCategory){
    	
    	var category = $("#category").val();
		var subCategory = $("#subCategory").val();
		str = "";
		
    	var sub = new Array('','귀걸이','목걸이','반지','팔찌','발찌');	
    	sub[1] = new Array('귀걸이','피어싱','귀찌','이어커프');
    	sub[2] = new Array('목걸이','초커','패션 목걸이');
    	sub[3] = new Array('반지','레이어드 링','은반지','금반지');
    	sub[4] = new Array('팔찌','가죽 팔찌','원석 팔찌','은 팔찌');
    	sub[5] = new Array('발찌');
    	
    	str += sub[category][0]+">"+sub[category][subCategory-1];
    	
    	$("#tcategory").html(str);
    }
    
    function BbsbbsListCallback(obj){
        
        var list = obj;
        var listLen = obj.length;
        
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
                
                str += "<div class=\"col-lg-4 col-md-6 mb-4\">";
                str += "<div class=\"card h-100\">";
                str += "<a href=\"/bbs/bbsView?bbsID="+bbsID+"\"><img class=\"card-img-top\" src=\"http://placehold.it/700x400\" alt=\"\"></a>";
                str += "<div class=\"card-body\">";
                str += "<h4 class=\"card-title\">";
                str += "<a href=\"/bbs/bbsView?bbsID="+bbsID+"\">"+title+"</a>";
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
    