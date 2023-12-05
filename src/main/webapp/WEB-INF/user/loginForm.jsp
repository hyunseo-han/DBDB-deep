<!-- UserMan3코드에서 회원가입 버튼만 남겨놓음! -->
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>DBDB-deep 로그인</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/user.css' />"
	type="text/css">
<script>

function userCreate(targetUri) {
	form.action = targetUri;
	form.method="GET";		// register form 요청
	form.submit();
}
</script>
</head>
<body>
	<br>
	<!-- login form  -->
	<form name="form" method="POST" action="<c:url value='/user/login' />">
		<input type="button" value="회원가입"
			onClick="userCreate('<c:url value='/user/register'/>')">
	</form>
</body>
</html>