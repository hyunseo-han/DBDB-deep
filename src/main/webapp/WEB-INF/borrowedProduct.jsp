<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>마이페이지 > 빌린물품조회</title>
<link rel="stylesheet" href="<c:url value='/css/global.css' />" type="text/css">
<link rel="stylesheet" href="<c:url value='/css/borrowedProduct.css' />" type="text/css">
</head>
<body>
	<div class="header">
		<div class="headText">${sessionScope.name}님이 빌린 물품</div>
	</div>
	
	<div class="container">
		<c:forEach var="item" items="${borrowedItems}"> 
			<div class="box">
				<div class="item-img">
					<!-- 이미지 경로 수정: productPhoto -> photo -->
					<img src="<c:url value='/upload/${item.photo}' />" alt="물품 이미지" />
				</div>
				<div class="item-info">
					<!-- RentInfo 객체의 필드에 맞게 EL 표현식 수정 -->
					<div class="item-name">${item.title}</div>
					<div class="item-dates">
						<fmt:formatDate value="${item.start_day}" pattern="yyyy-MM-dd"/> ~ 
						<fmt:formatDate value="${item.end_day}" pattern="yyyy-MM-dd"/>
					</div>
					<div class="item-fee">
						<fmt:formatNumber value="${item.rental_fee}" pattern="#,##0원" />
					</div>
					<div class="item-owner">물품 주인: ${item.ownerName}</div>
					<div class="item-address">주소: ${item.address}</div>
				</div>
				<div class="return-btn">반납</div>
			</div>
		</c:forEach>
	</div>
	
</body>
</html>
