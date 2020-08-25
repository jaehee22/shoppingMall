$(document).ready(function(){        
});

$("#bbsImage").change(function(){
	    var reader = new FileReader;
	    reader.onload = function(data) {
	     $(".select_img img").attr("src", data.target.result).width(300);        
	    }
	    reader.readAsDataURL(this.files[0]);
});

//게시판홈 이동
function goBbsList(){                
    location.href = "/bbs/home";
}

//subCategory설정
function SubCategory(category){
	
	var category = category.value;
	var sub = new Array();	
	sub[1] = new Array('귀걸이','피어싱','귀찌','이어커프');
	sub[2] = new Array('목걸이','초커','패션 목걸이');
	sub[3] = new Array('반지','은반지','금반지');
	sub[4] = new Array('팔찌','가죽 팔찌','원석 팔찌','은 팔찌');
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
		        	
    	var form = $('#bbsForm')[0];
    	var data = new FormData(form);

       	if($('#bbsImage').val()==""){
       		data.delete('bbsImage');
       	}
       	     	
    	$.ajax({    
            type    : "POST",    
            enctype: "multpart/form-data",
            url     : "/bbs/BbsWrite",
            data    : data,
            cache   : false,
            processData: false,
            contentType: false,
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
