<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>대여 확정</title>
    <link
      rel="stylesheet"
      href="<c:url value='/css/global.css' />"
      type="text/css"
    />
    <link
      rel="stylesheet"
      href="<c:url value='/css/borrowedProduct.css' />"
      type="text/css"
    />
  </head>
  <body>
    <h2>대여 확정 정보</h2>
	<p>물품 이름: ${product.title}</p>
	<p>대여 기간: ${rent.start_day}부터 ${rent.end_day}까지</p>
	<p>보증금: ${product.deposit}</p>
	<p>대여비: ${rent.rental_fee}</p>
	<p>대여 장소: ${product.address} ${product.detailAddress}</p>
  </body>
</html>
