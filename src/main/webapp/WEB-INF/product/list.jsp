<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<div class="input">
				<input class="input-text" placeholder="어떤 제품을 찾고 계신가요?" type="text" />
				<img class="search-icon"
					src="<c:url value='/images/product/searchicon.svg'/>" />
			</div>

			<div class="category-wrapper">
				<div class="category-text">주방가전</div>
				<div class="category-text">오락기기</div>
				<div class="category-text">촬영장비</div>
				<div class="category-text">뷰티/헬스</div>
			</div>
			
			<div class="goods-wrapper">
				<c:forEach var="product" items="${products}">
					<div class="goods">
						<img class="goods-img" src="<c:url value="/upload/${product.productPhoto}" />"/> 
						<div class="goods-name">${product.title}</div>
						<div class="goods-price">${product.rentalFee}원 </div>
						<div class="goods-place">${product.address}</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>
