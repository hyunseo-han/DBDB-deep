<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>빌려중고? - 물품 상세</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/global.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/product/view.css'/>">
<script>
    var rentsArray = [
        <c:forEach var="rent" items="${rents}" varStatus="status">
            {
                startDay: new Date("${rent.start_day}"),
                endDay: new Date("${rent.end_day}"),
                status: ${rent.status} // status 속성 추가
            }<c:if test="${!status.last}">,</c:if>
        </c:forEach>
    ];
    console.log(rentsArray)

    function checkDate() {
        if (document.rentForm.start_day.value == "") {
            alert("대여 시작일을 선택하십시오.");
            document.rentForm.start_day.focus();
            return false;
        } 
        else if (document.rentForm.end_day.value == "") {
            alert("대여 종료일을 선택하십시오.");
            document.rentForm.end_day.focus();
            return false;
        } 
        return true;
    }

    function checkOverlap(selectedStartDate, selectedEndDate) {
        for (var i = 0; i < rentsArray.length; i++) {
            var rent = rentsArray[i];
            if (rent.status === 1 && selectedStartDate <= rent.endDay && selectedEndDate >= rent.startDay) {
                return false; // 중복되는 날짜 발견
            }
        }
        return true; // 중복되는 날짜 없음
    }

    function validateForm() {
        if (checkDate()) {
            var selectedStartDate = new Date(document.getElementById('start_day').value);
            var selectedEndDate = new Date(document.getElementById('end_day').value);
            if (checkOverlap(selectedStartDate, selectedEndDate) == false) {
                alert("이미 예약된 대여 날짜입니다. 다른 날짜를 선택해주세요.");
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
</script>

</head>

<body>
	<div class="product-detail-container">
		<div class="header">
			<a href="<c:url value='list'/>"> <img class="back-icon"
				src="<c:url value='/images/addProductForm/Vector.svg'/>" />
			</a>
			<div class="header-text">물품 상세 보기</div>
			<a
				href="<c:url value='/user/cartItem/add?id=${product.productId }&rentalFee=${product.rentalFee}'/>">
				<img class="cart-icon"
				src="<c:url value='/images/addProductForm/simple-line-icons_basket.svg'/>" />
			</a>
		</div>

		<div class="product-image">
			<img src="<c:url value='/upload/${product.productPhoto}' />"
				alt="${product.title}" />
		</div>

		<div class="product-info">

			<p class="product-text">물품 이름</p>
			<p class="product-detail">${product.title}</p>

			<p class="product-text">물품 설명</p>
			<p class="product-detail">${product.description}</p>

			<p class="product-text">물품의 정가</p>
			<p class="product-detail">
				<fmt:formatNumber value="${product.regularPrice}" pattern="#,##0원" />
			</p>

			<p class="product-text">대여비</p>
			<p class="product-detail">
				<fmt:formatNumber value="${product.rentalFee}" pattern="#,##0원" />
			</p>


			<p class="product-text">보증금</p>
			<p class="product-detail">
				<fmt:formatNumber value="${product.deposit}" pattern="#,##0원" />
			</p>

			<p class="product-text">물품 카테고리</p>
			<p class="category">${product.category}</p>


			<p class="product-text">대여 진행 장소</p>
			<p class="product-detail">${product.address}
				${product.detailAddress}</p>

			<p class="product-text">제공자 매너점수</p>
			<p class="product-detail">${customer.name}님
				${customer.manner_score}점</p>


		</div>
		<c:choose>
			<c:when test="${product.customerId == sessionScope.customerId}">
				<div class="update-buttons">
					<a
						href="<c:url value='/product/update?action=update&id=${product.productId}' />">
						<button type="button">수정하기</button>
					</a> <a
						href="<c:url value="/product/delete?action=delete&id=${product.productId}" />">
						<button type="button">삭제하기</button>
					</a>
				</div>
			</c:when>
			<c:otherwise>
				<form name="rentForm" method="POST" class="order-form"
					action="<c:url value='/product/order' />">
					<input type="hidden" name="productId" value="${product.productId}" />
					<input type="hidden" name="customerId"
						value="${sessionScope.customerId}" />
					<div class="order-title">대여 기간 설정하기</div>

					<div class="select-date">
						<label for="start_day">대여 시작 날짜 </label> <input type="date"
							id="start_day" name="start_day" required>
					</div>
					<div class="select-date">
						<label for="end_day">대여 종료 날짜:</label> <input type="date"
							id="end_day" name="end_day" required>
					</div>
					<!-- 대여 불가능 날짜 보여주기 -->
					<c:if test="${not empty rents}">
					    <div class="show-date-wrapper">
					        <div class="show-title">❌ 대여 불가능 날짜 ❌</div>
					        <div class="description">⚠️ 아래는 다른 사용자들이 이 물품의 대여를 약속한 날짜입니다. 해당 기간에는 대여가 불가능하니 참고하세요!</div>
					        <ul>
					            <c:forEach var="rent" items="${rents}">
					                <!-- status가 1인 rent만 표시 -->
					                <c:if test="${rent.status == 1}">
					                    <li>${rent.start_day}부터${rent.end_day}까지</li>
					                </c:if>
					            </c:forEach>
					        </ul>
					    </div>
					</c:if>
					<div class="product-buttons">
						<button type="submit" onClick="return validateForm()">대여하기</button>
					</div>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
