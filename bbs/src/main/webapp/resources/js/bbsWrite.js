$(document).ready(function(){        
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
//게시글 작성
function BbsWrite(){

    
    var yn = confirm("게시글을 등록하시겠습니까?");        
    if(yn){
            
        $.ajax({    
            
            url        : "/bbs/BbsWrite",
            data    : $("#bbsForm").serialize(),
            dataType: "JSON",
            cache   : false,
            async   : true,
            type    : "POST",    
            success : function(obj) {
                BbsWriteCallback(obj);                
            },           
            error     : function(xhr, status, error) {}
            
        });
    }
}

//게시글 작성 함수
function BbsWriteCallback(obj){

    if(obj != null){        
        
        var result = obj.result;
        
        if(result == "SUCCESS"){                
            alert("게시글 등록을 성공하였습니다.");                
            goBbsList();                 
        } else {                
            alert("게시글 등록을 실패하였습니다.");    
            return;
        }
    }
}