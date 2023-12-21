<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>빌려중고? - 메인 페이지</title>
<link rel=stylesheet href="<c:url value='/css/global.css' />"
	type="text/css">
<link rel=stylesheet href="<c:url value='/css/product/list.css' />"
	type="text/css">
</head>
<body>

	<div class="screen">

		<div class="div">
		   <a href="<c:url value='/product/list'/>">
                <div class="head">빌려중고야?</div>
            </a>

			<div class="top">
				<div class="wrapper">

					<div class="input">
						<form method="GET" class="search-form"
							action="<c:url value='/product/search' />">
							<input class="input-text" placeholder="어떤 제품을 찾고 계신가요?"
								type="text" name="keyword" value="${param.keyword}" /> <input
								type="image" class="search-icon"
								src="<c:url value='/images/product/searchicon.svg'/>" />
						</form>
					</div>

					<div class="category-wrapper">
						<div class="category-text">주방가전</div>
						<div class="category-text">오락기기</div>
						<div class="category-text">촬영장비</div>
						<div class="category-text">뷰티/헬스</div>
					</div>
				</div>

				<div class="btn">
					<div class="name-div">
						<div class="name">${sessionScope.name}</div>
						님
					</div>
					<a class="go-addForm"
						href="<c:url value='/product/addProductForm'/>"> 물품 등록</a>
					<div class="logout-btn">
						<a href="<c:url value='/user/logout'/>">로그아웃</a>
					</div>
				</div>
			</div>

			<div class="goods-wrapper">
				<c:choose>
					<c:when test="${not empty products}">
						<c:forEach var="product" items="${products}">
							<a href="<c:url value="/product/view?id=${product.productId}" />">
								<div class="goods">
									<img class="goods-img"
										src="<c:url value="/upload/${product.productPhoto}" />"
										alt="이미지 없슈 : ${product.title}" />
									<div class="goods-name">${product.title}</div>
									<div class="goods-price">
										<fmt:formatNumber value="${product.rentalFee}"
											pattern="#,##0원" />
									</div>
									<div class="goods-place">${product.address}</div>
								</div>
							</a>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="no-results">${error}</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
     <c:set var="customerId" value="${sessionScope.customerId}" />
		<a href="<c:url value="/user/cartItem?customerId=${customerId }"/>">장바구니로가라</a>
	</div>
	
</body>
</html>
