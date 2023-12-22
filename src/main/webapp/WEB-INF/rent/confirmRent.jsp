<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>대여 확정</title>
    <link
      rel="stylesheet"
      href="<c:url value='/css/global.css' />"
      type="text/css"
    />
    <link
      rel="stylesheet"
      href="<c:url value='/css/rent/confirmRent.css' />"
      type="text/css"
    />
  </head>
  <body>
    <div class="confirm-container">
        <div class="header">
            <div class="header-text">대여 예약 완료</div>
        </div>
        <div class="rent-detail">      
            <div class="rent-detail-title">
                <img class="check-icon" src="<c:url value='/images/confirmRent/checkicon.png'/>" />
                <div class="product-name">${product.title}</div>           
            </div>           
            <div class="rent-detail-content">
	            <div class="detail-title">✅ 대여 기간</div>
	            <div class="detail-content">${rent.start_day} ~ ${rent.end_day}</div>
	            <div class="detail-title">✅ 보증금</div>
	            <div class="detail-content">${product.deposit}원</div>
	            <div class="detail-title">✅ 대여비</div>
	            <div class="detail-content">${rent.rental_fee}원</div>
	            <div class="detail-title">✅ 대여 장소</div>
	            <div class="detail-content">${product.address} ${product.detailAddress}</div>            
            </div>               
        </div>   
        <button class="return-list-btn" onclick="location.href='<c:url value='/product/list'/>'">홈으로 이동</button>
    </div>
    
  </body>
</html>
