 $(document).ready(function(){        
        BbsbbsList();
        Category();
        BbsPaging();
    });

//카테고리가 있는 게시물 리스트
function BbsbbsList(){
	
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

//카테고리 설정
function Category(){
	
	var category = $("#category").val();
	var subCategory = $("#subCategory").val();
	str = "";
	
	var sub = new Array('','귀걸이','목걸이','반지','팔찌','발찌');	
	sub[1] = new Array('귀걸이','피어싱','귀찌','이어커프');
	sub[2] = new Array('목걸이','초커','패션 목걸이');
	sub[3] = new Array('반지','은반지','금반지');
	sub[4] = new Array('팔찌','가죽 팔찌','원석 팔찌','은 팔찌');
	
	str += sub[category][0]+"▷"+sub[category][subCategory-1];
	
	$("#tcategory").html(str);
}
    
function BbsbbsListCallback(obj){
	var num = $("#num").val();
    var list = obj;
    var listLen = obj.length;
    var imgPath = "http://localhost:8080/resources/bbsImg/";

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
            str += "<a href=\"/bbs/bbsView?bbsID="+bbsID+"&comCategory=1&commentNum=1\"><img class=\"card-img-top\" src=\""+filePath+"\" onerror=\"this.src='http://placehold.it/300x200?text=No Image'\" width=\"300\" height=\"200\" alt=\"\"/></a>";
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
    
//페이징
function BbsPaging(){
	
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
    
    if(list != null){
    	str = "";
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
