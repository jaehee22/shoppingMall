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
	int comCategory = Integer.parseInt(request.getParameter("comCategory"));     
	int commentNum = Integer.parseInt(request.getParameter("commentNum"));     

%>

	<c:set var="bbsID" value="<%=bbsID%>"/> <!-- 게시글 번호 -->
	<c:set var="comCategory" value="<%=comCategory%>"/> <!-- bbs카테고리 -->
	<c:set var="commentNum" value="<%=commentNum%>"/> <!-- comment페이징 -->
	
	<!-- Bootstrap core CSS -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">

	<!-- Custom styles for this template -->
	<link type="text/css" href="${pageContext.request.contextPath}/resources/css/shop-homepage.css" rel="stylesheet">
</head>

<body>
	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg bg-light navbar-light fixed-top">
  		<div class="container">
    		<a class="navbar-brand" href="/bbs/home">♥ACCESSORY♥</a>
    		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      			<span class="navbar-toggler-icon"></span>
    		</button>
	    	<div class="collapse navbar-collapse" id="navbarResponsive">
		      	<ul class="navbar-nav ml-auto">
		      		<c:if test="${userForm == null }">          
						<li class="nav-item">
		  					<a class="nav-link" href="/user/login">로그인
		    					<span class="sr-only">(current)</span>
		  					</a>
						</li>
						<li class="nav-item">
		  					<a class="nav-link" href="/user/join">회원가입</a>
						</li>          
					</c:if>
					<c:if test="${userForm != null && userForm.isManager==0 }">
		 				<li class="nav-item">
		   					<a class="nav-link" href="/user/userView?userID=${userForm.userID}">내정보</a>
		 				</li>
		 				<li class="nav-item">
		   					<a class="nav-link" href="/cart/cartBbs?cartNum=1">장바구니</a>
						</li>
						<li class="nav-item">
		    				<a class="nav-link" href="/order/orderBbs?orderNum=1">주문조회</a>
						</li>
						<li class="nav-item">
		    				<a class="nav-link" href="/user/Logout">로그아웃</a>
						</li>
					</c:if>          
					<c:if test="${userForm != null && userForm.isManager==1 }">
						<li class="nav-item">
					   		<a class="nav-link" href="/user/userList?userNum=1">회원 관리</a>
						</li>
						<li class="nav-item">
					   		<a class="nav-link" href="/order/userOrder?userOrderNum=1">주문 관리</a>
						</li>
						<li class="nav-item">
					   		<a class="nav-link" href="/bbs/bbsWrite">제품 등록</a>
						</li>
						<li class="nav-item">
					    	<a class="nav-link" href="/user/Logout">로그아웃</a>
						</li>
					</c:if>
		      	</ul>
	    	</div>
  		</div>
	</nav>

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<br>
				<h1 class="my-4"><img class="d-block img-fluid" width="300" height="100" src="${pageContext.request.contextPath}/resources/img/로고.JPG"></h1>
				<br>
     			<div class="menu">
      				<ul class="accordion">
      					<li><a href="#menu1">귀걸이</a>
      						<ul>
				      			<li><a href="/bbs/bbs?category=1&subCategory=1&num=1">귀걸이</a></li>
				      			<li><a href="/bbs/bbs?category=1&subCategory=2&num=1">피어싱</a></li>
				      			<li><a href="/bbs/bbs?category=1&subCategory=3&num=1">귀찌</a></li>
				      			<li><a href="/bbs/bbs?category=1&subCategory=4&num=1">이어커프</a></li>
				      		</ul>
				      	</li>
				      	<li><a href="#menu2">목걸이</a>
				      		<ul>
				      			<li><a href="/bbs/bbs?category=2&subCategory=1&num=1">목걸이</a></li>
				      			<li><a href="/bbs/bbs?category=2&subCategory=2&num=1">초커</a></li>
				      			<li><a href="/bbs/bbs?category=2&subCategory=3&num=1">패션 목걸이</a></li>
				      		</ul>
				      	</li>
				      	<li><a href="#menu3">반지</a>
				      		<ul>
				      			<li><a href="/bbs/bbs?category=3&subCategory=1&num=1">반지</a></li>
				      			<li><a href="/bbs/bbs?category=3&subCategory=2&num=1">은반지</a></li>
				      			<li><a href="/bbs/bbs?category=3&subCategory=3&num=1">금반지</a></li>
				      		</ul>
				      	</li>
				      	<li><a href="#menu4">팔찌</a>
				      		<ul>
				      			<li><a href="/bbs/bbs?category=4&subCategory=1&num=1">팔찌</a></li>
				      			<li><a href="/bbs/bbs?category=4&subCategory=2&num=1">가죽 팔찌</a></li>
				      			<li><a href="/bbs/bbs?category=4&subCategory=3&num=1">원석 팔찌</a></li>
				      			<li><a href="/bbs/bbs?category=4&subCategory=4&num=1">은 팔찌</a></li>
				      		</ul>
				      	</li>
					</ul>
				</div>				
			</div>
			<!-- /.col-lg-3 -->

			<div class="col-lg-9">
   			<br>
     			<div id="carouselExampleIndicators">
     			<br>				
					<form id="bbsForm" name="bbsForm">
						<table class="table borderless">	
							<colgroup>
								<col width="30%">
								<col width="25%">
								<col width="55%">              
							</colgroup>
							<tbody id="tbody">
							
							</tbody>
						</table>
						<c:if test="${userForm != null && userForm.isManager==1 }">
 							<div align=center>
								<button type="button" onclick="javascript:goBbsUpdate();">수정하기</button>
								<button type="button" onclick="javascript:BbsDelete();">삭제하기</button>
								<br><br>
							</div>
						</c:if>
					    <input type="hidden" id="bbsID" name="bbsID"    value="${bbsID}"/> <!-- 게시글 번호 -->
					    <input type="hidden" id="comCategory" name="comCategory"    value="${comCategory}"/> <!-- 후기orQ&A카테고리번호 -->
					    <input type="hidden" id="commentNum" name="commentNum"    value="${commentNum}"/> <!-- comment페이징 -->
						<input type="hidden" id="userID" name="userID" value="${userForm.userID}"/>					    													    
      				</form>
					<nav>
						<ul class="nav-container">
							<c:if test="${comCategory==1}">
								<li class="nav-item"><a class="nav-link active" href="/bbs/bbsView?bbsID=${bbsID}&comCategory=1&commentNum=1">후기</a></li>
								<li class="nav-item"><a class="nav-link" href="/bbs/bbsView?bbsID=${bbsID}&comCategory=2&commentNum=1">Q&A</a></li>
							</c:if>
							<c:if test="${comCategory==2}">
								<li class="nav-item"><a class="nav-link" href="/bbs/bbsView?bbsID=${bbsID}&comCategory=1&commentNum=1">후기</a></li>
								<li class="nav-item"><a class="nav-link active" href="/bbs/bbsView?bbsID=${bbsID}&comCategory=2&commentNum=1">Q&A</a></li>
							</c:if>
						</ul>
					</nav>
					<table class="table">	
						<tbody id="comCategory">
						
						</tbody>
					</table>					
					<!-- 댓글리스트 -->
					<div id=tcomment>
						<input type="hidden" id="userID" name="userID" value="${userForm.userID}"/>
					    <input type="hidden" id="isManager" name="isManager"    value="${userForm.isManager}"/> <!-- 관리자여부 -->			
					</div>				
					<!-- 페이징 -->
					<div class="page_wrap" id=tPaging>
					
					</div>									
					<!-- 댓글 작성칸 -->
					<div class="form-group">
						<form id="commentForm" name="commentForm" enctype="multipart/form-data" method="post" onsubmit="return false;">
							<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
								<colgroup>
									<col width="10%">
									<col width="70%">
									<col width="20%">              
								</colgroup>
								<tr>
								<c:if test="${comCategory==1}">
									<td colspan=3>소중한 후기를 남겨주세요^^</td>
								</c:if>
								<c:if test="${comCategory==2}">
									<td colspan=3>궁금하신게 있다면 언제든지 물어보세요^^</td>
								</c:if>
								</tr>
								<tr>
									<td style="border-bottom:none;" valign="middle"><br><br>${userForm.userID}</td>
									<td><textarea id="content" name="content" cols="70" rows="5" maxlength="2048" class="tbox"/></textarea></td>
									<td style="text-align:center;"><br><br>
									<c:if test="${comCategory==1}">
									<button type="button" class="btn btn-primary" onclick="javascript:UserReview();">작성</button>
									</c:if>
									<c:if test="${comCategory==2}">
									<button type="button" class="btn btn-primary" onclick="javascript:CommentWrite();">작성</button>
									</c:if>
									</td>
								</tr>
								<tr>
									<td colspan="3"><input type="file" id="fileName" name="fileName"></td>									
								</tr>
							</table>
						</form>
					</div>
 				</div>
			</div>
			<!-- /.col-lg-9 -->
		</div>
		<!-- /.row -->
	</div>
	<!-- /.container -->
	
	<br>
  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; 악세사리 쇼핑몰 2020</p>
    </div>
    <!-- /.container -->
  </footer>
  
  <!-- Bootstrap core JavaScript -->
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/bbsView.js"></script>
</body>

</html>
