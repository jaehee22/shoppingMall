<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">

	<title>악세사리 쇼핑몰</title>

<%    
    int bbsID = Integer.parseInt(request.getParameter("bbsID"));
	int commentID = Integer.parseInt(request.getParameter("commentID"));
	int subCommentID = Integer.parseInt(request.getParameter("subCommentID"));
	String userID = request.getParameter("userID");	//userID
%>
 
	<c:set var="bbsID" value="<%=bbsID%>"/> <!-- 게시글번호 -->
	<c:set var="commentID" value="<%=commentID%>"/> <!-- 댓글번호 -->
	<c:set var="subCommentID" value="<%=subCommentID %>"/><!-- 답글번호 -->
	<c:set var="userID" value="<%=userID%>"/> <!-- 유저 아이디 -->
	
</head>

<body>
	<c:if test="${userForm==null || userID != userForm.userID}">
		<script>
			var bbsID = <%=bbsID%>;
			alert(<%=userID%>);
			alert("권한이 없습니다.");
        	window.opener.location.href= "/bbs/bbsView?bbsID="+bbsID+"&comCategory=1&commentNum=1";
    		window.close();
		</script>
	</c:if>
	<div class="container" align="center">
		<div class="col-lg-10">
			<div class="jumbotron" style="padding-top: 1px;">				
				<h3><br>댓글수정창<br></h3>
					<form id="commentForm" name="commentForm" enctype="multipart/form-data" method="post" onsubmit="return false;">
						<textarea id="content" name="content" style="width:400px;height:50px;" maxlength=1024 class="tbox"></textarea>
						<input type="button" onclick="javascript:CommentUpdate()" value="수정">
						<br><br>
						<c:if test="${subCommentID == 0}">
						<input type="file" id="newFile" name="newFile"/>
						</c:if>	
						<input type="hidden" id="commentID" name="commentID" value="${commentID}"/>
						<input type="hidden" id="bbsID" name="bbsID" value="${bbsID}"/>
						<input type="hidden" id="subCommentID" name="subCommentID" value="${subCommentID}"/>						
				</form>
			</div>
		</div>
		<div class="col-lg-10"></div>
	</div>
	
	<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/commentUpdate.js"></script>
</body>

</html>
