<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>빌려준 물품 조회</title>
<link rel="stylesheet" href="<c:url value='/css/global.css' />"
	type="text/css">
<link rel="stylesheet" href="<c:url value='/css/user/lend.css' />"
	type="text/css">
</head>
<body>
	<div class="header">
		<div class="headText">
			<b>${sessionScope.name}</b>님이 빌려준 물품 
		</div>
	</div>
	
	<div class="container">
		<c:forEach var="item" items="${borrowedItems}">
				<div class="item-container">
					<div class="box">
						<div class="item-img">
							<img src="<c:url value='/upload/${item.photo}' />" alt="물품 이미지" />
						</div>
						<div class="item-info">
							<div class="item-name">${item.title}</div>
							<div class="item-fee">
								<fmt:formatNumber value="${item.rental_fee}" pattern="#,##0원" />
							</div>
							<div class="item-address">${item.address}</div>
						</div>
						<div class="item-dates">${item.start_day}~${item.end_day}</div>
					</div>
					<form action="/DBDB-deep/mypage/returnProduct" method="post">
						<input type="hidden" name="action" value="returnProduct" /><input
							type="hidden" name="productId" value="${item.prdt_id}" /> <input
							type="hidden" name="productName" value="${item.title}" /> <input
							type="submit" value="반납" class="return-btn" />
					</form>
				</div>
		</c:forEach>
	</div>
	
</body>
</html>
