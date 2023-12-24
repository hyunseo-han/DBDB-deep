<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>빌린물품조회 > 만족도조사</title>
<link rel="stylesheet" href="<c:url value='/css/global.css' />"
	type="text/css">
<link rel="stylesheet" href="<c:url value='/css/user/star.css' />"
	type="text/css">

<script>
	function validateForm() {
		var starRating = document.getElementsByName("starRating")[0].value;
		if (starRating === "") {
			alert("별점을 선택해주세요.");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<div class="header">
		<div class="headText">
			<b>${productName}</b> 만족도 평가
			<p>rentId: ${rentId}</p>
			<p>productId: ${productId}</p>
		</div>
	</div>

	<form action="/DBDB-deep/mypage/borrowedProduct?customerId=${customerId}" method="post"
		onsubmit="return validateForm()">
		<input type="hidden" name="action" value="returnStar" /> <input
			type="hidden" name="rentId" value="${rentId}" /> 
			<input
			type="hidden" name="productId" value="${productId}" /><select
			name="starRating">
			<option value="">선택하세요</option>
			<option value="0">0</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
		</select> <input type="submit" value="별점 제출" />
	</form>
</body>
</html>
