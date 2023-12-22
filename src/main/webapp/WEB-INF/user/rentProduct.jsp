<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%-- <%@ page import="model.RentInfo"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>빌려중고야? - 빌려준 상품 목록</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/global.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/user/rentProduct.css'/>">

</head>
<body>

	<h2>빌려준 물품</h2>

	<c:choose>
		<c:when test="${not empty rentProducts}">
			<c:forEach var="rentInfo" items="${rentProducts}">
				<div class="product-container">
					<div>
						<img class="product-image"
							src="<c:url value="/upload/${rentInfo.photo}" />"
							alt="Product Image" />
					</div>
					<div>상품명: ${rentInfo.title}</div>
					<div>대여 시작일: ${rentInfo.start_day}</div>
					<div>대여 종료일: ${rentInfo.end_day}</div>
					<div>대여비: ${rentInfo.rental_fee}</div>
					<div>상태: ${rentInfo.status == 1 ? '	반납 미완료 ' : '반납 완료 '}</div>
					<div>장소: ${rentInfo.address}</div>
					<div>소유자: ${rentInfo.ownerName}</div>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<p>빌려준 상품이 없습니다.</p>
		</c:otherwise>
	</c:choose>

</body>
</html>
