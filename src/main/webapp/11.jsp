<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>빌려중고?</title>
<link rel=stylesheet href="<c:url value='css/global.css' />"
	type="text/css">
<link rel=stylesheet href="<c:url value='css/user/cartItem.css' />"
	type="text/css">
</head>
	<header class="greenRec">
        <img class="cartImg" src="images/cartItem/cart.png">
        <span class="headerFont">박멋사님의 장바구니</span> 
    </header>
	<body>
		<div class="container">  
			<img class="trash" src="images/cartItem/trash.png">   
			<div class="whiteRec">
    			<div class="itemInfo">
        			<img class="itemImg" src="images/cartItem/cart.png" alt="상품 이미지">
        			필립스 에어프라이어<br>
        			$100.00<br>
    				대구 수성 중동
    			</div>
			</div>
		</div>
	</body>
</html>
