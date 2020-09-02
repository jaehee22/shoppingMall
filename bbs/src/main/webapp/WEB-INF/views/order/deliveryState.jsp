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
    int orderID = Integer.parseInt(request.getParameter("orderID"));
%>
 
	<c:set var="orderID" value="<%=orderID%>"/> <!-- 게시글번호 -->
	
</head>

<body>
	<div class="container" align="center">
		<div class="col-lg-10">
			<div class="jumbotron" style="padding-top: 1px;">				
				<h3><br>배송상태 수정<br></h3><br>
					<form id="commentForm" name="commentForm">
						<input type="button" onclick="javascript:DeliveryUpdate(1)" value="상품준비중">
						<input type="button" onclick="javascript:DeliveryUpdate(2)" value="배송중">
						<input type="button" onclick="javascript:DeliveryUpdate(3)" value="배송완료">						
						<br><br>
						<input type="hidden" id="orderID" name="orderID" value="${orderID}"/>
				</form>
			</div>
		</div>
	</div>
	
	<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/deliveryState.js"></script>
</body>

</html>
