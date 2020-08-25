 $(document).ready(function(){        
        BbsList();
 });

//신상품순서로
function BbsList(){
    
    $.ajax({            
        type:"GET",
        url:"/bbs/BbsList",
        dataType:"JSON",
        success : function(obj) {
            BbsListCallback(obj);                
        },           
        error : function(xhr, status, error) {}
     });
}
    
function BbsListCallback(obj){
    
    var list = obj;
    var listLen = obj.length;
    var imgPath = "http://localhost:8080/resources/bbsImg/";
    
    var str = "";
    
    if(listLen >  0){
        
        for(var a=0; a<listLen; a++){
            
        	var bbsID = list[a].bbsID
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
