<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 목록</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
    
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
                
                str += "<tr>";
                str += "<td>"+ title +"</td>";
                str += "<td>"+ price +"</td>";
                str += "<td>"+ sell +"</td>";                
                str += "</tr>";
                
            } 
            
        } else {
            
            str += "<tr colspan='3'>";
            str += "<td>등록된 글이 존재하지 않습니다.</td>";
            str += "<tr>";
        }
        
        $("#tbody").html(str);
    }
    
</script>
</head>
<body>
<table border=1 width=350>
    <thead>
        <tr>
            <td>제품명</td>
            <td>가격</td>
            <td>판매량</td>
        </tr>
    </thead>
    <tbody id="tbody">
    
    </tbody>
    
</table>
</body>
</html>