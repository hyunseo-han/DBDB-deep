<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>빌려중고?</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/global.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/addProductForm.css'/>">
<script>
function productCreate() {
    if (myform.title.value == "") {
        alert("상품명을 입력하십시오.");
       myform.title.focus();
        return false;
    } 
    myform.submit();
}

</script>
</head>
<body>
	<div class="header">
		<img class="back-icon"
			src="<c:url value='/images/addProductForm/Vector.svg'/>" />
		<div class="header-text">물품 등록 하기</div>
		<img class="cart-icon"
			src="<c:url value='/images/addProductForm/simple-line-icons_basket.svg'/>" />
	</div>

	<!-- 물건 등록 form -->
	<form name="myform" method="POST" class="add-form"
		action="<c:url value='/product/create' />">
		<div class="input">
<!-- 			<div class="input-title">물품 사진</div>
			<input type="file" class="input-text" name="product_photo"><br>
			<br> -->
			<div class="input-title">물품 이름</div>
			<input class="input-text" placeholder="물품 이름을 입력해주세요." type="text"
				name="title" />
			<div class="input-title">물품 설명</div>
			<input class="input-text" placeholder="물품 설명을 입력해주세요." type="text"
				name="description" />

			<div class="input-title">물품의 정가</div>
			<input class="input-text" placeholder="물품의 정가를 입력해주세요." type="text"
				name="regular_price" />
			<div class="input-title">대여비</div>
			<input class="input-text" placeholder="물품의 대여비를 입력해주세요." type="text"
				name="rental_fee" />
			<div class="input-title">보증금</div>
			<input class="input-text" placeholder="물품의 보증금을 입력해주세요." type="text"
				name="deposit" />
			<div class="input-title">물품 카테고리</div>
			<input class="input-text" placeholder="물품 카테고리를 입력해주세요." type="text"
				name="category" />
			<div class="input-title">대여 진행 장소</div>
			<input class="input-text" placeholder="대여를 진행할 장소를 입력해주세요."
				type="text" name="address" />
			<div class="input-title">대여 상세 주소</div>
			<input class="input-text" placeholder="대여를 진행할 상세 주소를 입력해주세요."
				type="text" name="detail_address" />
		</div>
		<input type="hidden" name="action" value="add" />
		<!-- 로그인 구현 전까지 임시 customerId -->
		<input type="hidden" name="customerId" value="1" />
		<input type="hidden" name="product_photo" value="photo1234.jpg" />
		<!-- <input type="button" value="물건 등록" onClick="productCreate()" /> -->
		<button type="submit" class="select-date-btn" onClick="productCreate()">물건 등록</button>
	</form>


</body>
</html>