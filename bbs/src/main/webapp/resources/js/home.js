 $(document).ready(function(){        
        BbsList();
    });
 
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
        
        console.log(list);
        console.log(listLen);
        
        var str = "";
        
        if(listLen >  0){
            
            for(var a=0; a<listLen; a++){
                
                var category = list[a].category; 
                var subCategory = list[a].subCategory; 
                var title = list[a].title;
                var price = list[a].price;
                var sell = list[a].sell;
                
                str += "<div class=\"col-lg-4 col-md-6 mb-4\">";
                str += "<div class=\"card h-100\">";
                str += "<a href=\"#\"><img class=\"card-img-top\" src=\"http://placehold.it/700x400\" alt=\"\"></a>";
                str += "<div class=\"card-body\">";
                str += "<h4 class=\"card-title\">";
                str += "<a href=\"#\">"+title+"</a>";
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
    