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
<link rel=stylesheet href="<c:url value='/css/global.css' />"
	type="text/css">
<link rel=stylesheet href="<c:url value='/css/user/cartItem.css' />"
	type="text/css">
	
	<script>
    function redirectToProduct(productId) {
        // 원하는 동작 수행, 예: 페이지 이동
        window.location.href = '<c:url value="/product/view?id=" />' + productId;
    }
</script>

	
</head>
	<header class="greenRec">
        <img class="cartImg" src="../images/cartItem/cart.png">
        <span class="headerFont"><span class="font1">${sessionScope.name}</span>님의 장바구니</span> 
    </header>
	<body>
	<c:choose>
		<c:when test="${not empty cartItems}">	
		<c:forEach var="cartItem" items="${cartItems}">
			<div class="container">
				<a href="<c:url value='/user/cartItem/delete?productId=${cartItem.productId }'/>">  
					<img class="trash" src="../images/cartItem/trash.png">   
				</a>			
				<div class="whiteRec" onclick="redirectToProduct(${cartItem.productId})">
    				<div class="itemInfo">    	
        				<img class="itemImg" src="<c:url value='/upload/${cartItem.productPhoto}' />" alt="${product.title}" />
							<div class="cartInfo">
							<div class="itemFont" >${cartItem.title}</div><br>
        					<div class="priceFont">${cartItem.rentalFee }원</div><br>
        					<div class="addressFont">${cartItem.address}</div>
        					</div>
    				</div>				
				</div>
			</div>
	 </c:forEach>
	 </c:when>
	 <c:otherwise>
	 <div class="empty">   
	 	<div class="emptyfont">담긴 물건이 아무것도 없네요!</div>
	 	<a href="<c:url value='/product/list'/>">
	 		<img class="emptyimg" src="../images/cartItem/cartPlus.png">
	 	</a>
	 	<div class="emptyfont2">담으러가기</div>
	 </div>
	 </c:otherwise>
	 </c:choose>
	 </body>
</html>
