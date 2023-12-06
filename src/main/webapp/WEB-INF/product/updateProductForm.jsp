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
<link rel="stylesheet" type="text/css"
    href="<c:url value='/css/product/addProductForm.css'/>">
</head>
<script>
function setCategory(category) {
    document.getElementById('categoryInput').value = category;
}
</script>
<body>
<form name="form" method="POST" class="add-form" enctype="multipart/form-data" 
	 action="<c:url value='/product/update?id=${product.productId}' />">
	<div class="product-detail-container">
		<div class="header">
			<a href="<c:url value='list'/>">
                <img class="back-icon" src="<c:url value='/images/addProductForm/Vector.svg'/>" />
            </a>
			<div class="header-text">물품 수정</div>
			<img class="cart-icon"
				src="<c:url value='/images/addProductForm/simple-line-icons_basket.svg'/>" />
		</div>
		
		<center> 
		<div class="product-image">
			<img src="<c:url value='/upload/${product.productPhoto}' />"
				alt="${product.productPhoto}" />
			<input type="file" class="input-text" name="product_photo"><br>
		</div>
		</center>
		<div class="product-info">

			<div class="input-title">물품 이름</div>
			<input class="input-text" type="text" name="title" value="${product.title}" />
			<div class="input-title">물품 설명</div>
			<input class="input-text" type="text" name="description" value="${product.description}" />

			<div class="input-title">물품의 정가</div>
			<input class="input-text" type="text" name="regularPrice" value="${Math.round(product.regularPrice)}" /> 

			<div class="input-title">대여비</div>
			<input class="input-text" type="text" name="rentalFee" value="${Math.round(product.rentalFee)}" /> 

			<div class="input-title">보증금</div>
			<input class="input-text" type="text" name="deposit" value="${Math.round(product.deposit)}" /> 

			<div class="input-title">물품 카테고리</div>
			 <div class="category-div">
			    <button class="category-buttons" type="button" onclick="setCategory('주방가전')">주방가전</button>
			    <button class="category-buttons" type="button" onclick="setCategory('촬영장비')">촬영장비</button>
			    <button class="category-buttons" type="button" onclick="setCategory('오락기기')">오락기기</button>
			    <button class="category-buttons" type="button" onclick="setCategory('기타')">기타</button>
			</div>
			
            <input type="hidden" name="category" id="categoryInput" value="${product.category }">

			<div class="input-title">대여 진행 장소</div>
			<input class="input-text" type="text" name="address" value="${product.address}" />
			
			<div class="input-title">대여 상세 주소</div>
			<input class="input-text" type="text" name="detailAddress" value="${product.detailAddress}" />

<!-- 			<div class="input-title">대여 방식 </div>
			<p class="category">ㄷㄷㄷㄷㄷㄷ</p> -->
		
    		<input type="hidden" name="customerId" value="${product.customerId}" />
			
		</div>
		<c:choose>
		  <c:when test="${product.customerId == sessionScope.customerId}">
				<div class="update-buttons">
		              <button type="submit"> 수정하기</button>			            
		        </div>
	        </c:when>
		</c:choose>
	</div>
	</form>
</body>
</html>
