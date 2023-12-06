<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
</head>

<body>
	<div class="product-detail-container">
		<div class="header">
			<a href="<c:url value='list'/>">
                <img class="back-icon" src="<c:url value='/images/addProductForm/Vector.svg'/>" />
            </a>
			<div class="header-text">물품 상세 보기</div>
			<img class="cart-icon"
				src="<c:url value='/images/addProductForm/simple-line-icons_basket.svg'/>" />
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
			<p class="product-detail">${product.regularPrice}원</p>

			<p class="product-text">대여비</p>
			<p class="product-detail">${product.rentalFee}원</p>

			<p class="product-text">보증금</p>
			<p class="product-detail">${product.deposit}원</p>

			<p class="product-text">물품 카테고리</p>
			<p class="category">${product.category}</p>


			<p class="product-text">대여 진행 장소</p>
			<p class="product-detail">${product.address}
				${product.detailAddress}</p>

			<p class="product-text">대여 방식 </p>
			<p class="category">ㄷㄷㄷㄷㄷㄷ</p>

		</div>
		<c:choose>
		  <c:when test="${product.customerId == sessionScope.customerId}">
				<div class="update-buttons">
					<a href="<c:url value='/product/update?action=update&id=${product.productId}' />">
    					<button type="button">수정하기</button>
					</a>
		            <a href="<c:url value="/product/delete?action=delete&id=${product.productId}" />">
		              <button type="button">삭제하기</button>
		            </a>		            
		        </div>
	        </c:when>
	        <c:otherwise>	
				<div class="product-buttons">
					<button type="button">대여 날짜 선택하기</button>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
