$(document).ready(function(){        
    BbsView();
});
    
//게시판 목록 이동
function goBbsList(){                
    location.href = "/bbs/bbs";
}

//subCategory설정
function SubCategory(category){
	
	var category = category.value;
	var sub = new Array();	
	sub[1] = new Array('귀걸이','피어싱','귀찌','이어커프');
	sub[2] = new Array('목걸이','초커','패션 목걸이');
	sub[3] = new Array('반지','레이어드 링','은반지','금반지');
	sub[4] = new Array('팔찌','가죽 팔찌','원석 팔찌','은 팔찌');
	sub[5] = new Array('발찌');
	var target = document.getElementById("subCategory");
	
	target.options.length = 0;
	
	for(var i=0; i<sub[category].length; i++){
		var option = document.createElement("option");
		option.value = i+1;
		option.innerHTML = sub[category][i];
		target.appendChild(option);
	}
}

//게시판 뷰
function BbsView(bbsID){
    
    var bbsID = $("#bbsID").val();

    if(bbsID != 0){
        
        $.ajax({    
            
            url        : "/bbs/BbsView",
            data    : $("#bbsForm").serialize(),
            dataType: "JSON",
            cache   : false,
            async   : true,
            type    : "POST",    
            success : function(obj) {
                BbsViewCallback(obj);                
            },           
            error     : function(xhr, status, error) {}
            
         });
        
    } else {
        alert("오류가 발생했습니다.\n관리자에게 문의하세요.");
    }    
}
function BbsViewCallback(obj){
	
    var str = "";
    if(obj != null){                                
        var title = obj.title;
        var price = obj.price;
        var content = obj.content;
        var sell = obj.sell
	    
	    $("#title").text(title);
	    $("#price").text(price);
	    $("#content").text(content);
	    System.out.println(title);
	    
	} else {
	    alert("등록된 글이 존재하지 않습니다.");
	    return;
	    }        
	}
   

//게시글 수정
function BbsUpdate(){

    var title = $("#title").val();
    var price = $("#price").val();
    var content = $("#content").val();
        
    if (title == ""){            
        alert("제목을 입력해주세요.");
        $("#title").focus();
        return;
    }
    
    if (price == 0){            
        alert("가격을 입력해 주세요");
        $("#price").focus();
        return;
    }
    
    if (content == ""){            
        alert("내용을 입력해주세요.");
        $("#content").focus();
        return;
    }
    
    var yn = confirm("게시글을 수정하시겠습니까?");        
    if(yn){
            
        $.ajax({    
            
            url        : "/bbs/BbsUpdate",
            data    : $("#bbsForm").serialize(),
            dataType: "JSON",
            cache   : false,
            async   : true,
            type    : "POST",    
            success : function(obj) {
                BbsUpdateCallback(obj);                
            },           
            error     : function(xhr, status, error) {}
            
        });
    }
}

//게시글 수정 함수
function BbsUpdateCallback(obj){	
	
    if(obj != null){        
        
    	var result = obj.result;
                
        if(result == "SUCCESS"){                
            alert("게시글 수정을 성공하였습니다.");                
            goBbsList();                 
        } else {                
            alert("게시글 수정을 실패하였습니다.");    
            return;
        }
    }
}
