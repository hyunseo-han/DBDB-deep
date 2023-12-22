<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%-- <%@ page import="model.RentInfo"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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

	<div class="container">
		<div class="header">
			<div class="name-wrapper">
				<div class="name-div">${sessionScope.name}</div>
				님이 빌려준 물품
			</div>
		</div>

		<c:choose>
			<c:when test="${not empty rentProducts}">
				<c:forEach var="rentInfo" items="${rentProducts}">
				 <c:set var="imageSrc" value="${rentInfo.status == 1 ? '/images/mypage/statusno.svg' : '/images/mypage/statusok.svg'}" />
					<div class="product-container">
						<div class="product-img">
							<img class="product-image"
								src="<c:url value="/upload/${rentInfo.photo}" />"
								alt="Product Image" />
						</div>

						<div class="wrapper">
							<div class="title">${rentInfo.title}</div>
							<div class="fee">
								<fmt:formatNumber value="${rentInfo.rental_fee}"
									pattern="#,##0원" />
							</div>
							<div class="info">${rentInfo.address}</div>

						</div>

						<div class="wrapper2">
							<div class="info">${rentInfo.start_day}~${rentInfo.end_day}

							</div>
							<div class="info">대여자 : ${rentInfo.ownerName}</div>
						</div>



						<div class="status">
							<img class="status-img" src="<c:url value='${imageSrc}'/>"
								alt="${rentInfo.status == 1 ? '반납 미완료' : '반납 완료'}" /> 
							${rentInfo.status == 1 ? '반납 미완료' : '반납 완료'}
						</div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<p>빌려준 상품이 없습니다.</p>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
