<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>회원가입</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/user/register.css' />"
	type="text/css" >
<script>
	function selectAll(selectAll)  {
	  const checkboxes 
	       = document.getElementsByName('check');
	  
	  checkboxes.forEach((checkbox) => {
	    checkbox.checked = selectAll.checked;
	  })
	}
	
	function userCreate(){
		form.submit();
	}
	
	function checkDuplicate(event, type) {
        event.preventDefault();  // 폼 제출 기본 동작 방지

        var form = document.forms['form']; // form의 name 속성이 'form'인 경우
        var value = form[type].value;

        if (!value) {
            alert((type === 'nickname' ? '닉네임' : '이메일') + '을 입력하세요.');
            return;
        }

        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/DBDB-deep/user/checkDuplicate', true); //경로 수정해서 에러 해결
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var response = JSON.parse(xhr.responseText);
                if(response.isDuplicate) {
                    alert('이미 사용 중인 ' + (type === 'nickname' ? '닉네임' : '이메일') + '입니다.');
                } else {
                    alert('사용 가능한 ' + (type === 'nickname' ? '닉네임' : '이메일') + '입니다.');
                }
            }
        };
        xhr.send('type=' + encodeURIComponent(type) + '&value=' + encodeURIComponent(value));
    }
</script>
</head>
<body>
	<br>
	<!-- registration form  -->
	<form name="form" method="POST"
		action="<c:url value='/user/register' />">
		<div class="container">
			<b><p class="title">본인 정보를 입력해주세요</p></b>
			<input type="text" style="width: 500" name="name" placeholder="이름">
			<div class="gap"></div>
			<input type="date" style="width: 500" name="birth_date"
				placeholder="생년월일">
			<div class="gap"></div>

			<input type="text" style="width: 390" name="nickname"
				placeholder="닉네임">
			<button class="checkBtn" onclick="checkDuplicate(event,'nickname')">중복
				확인</button>
			<div class="gap"></div>
			<input type="text" style="width: 390" name="email" placeholder="이메일">
			<button class="checkBtn" onclick="checkDuplicate(event, 'email')">중복
				확인</button>

			<div class="gap"></div>
			<input type="text" style="width: 500" name="passwd"
				placeholder="비밀번호">
			<div class="gap"></div>
			<input type="text" style="width: 500" name="phone"
				placeholder="휴대폰번호 ex)010-1234-1234">
			<div class="gap"></div>
			<input type="text" style="width: 500" name="address"
				placeholder="거주지 ex)서울특별시 성북구 하월곡동">
			<div class="gap"></div>
			<div class="left">
				<input type='checkbox' value='selectall' onclick='selectAll(this)' class="checkBoxInput"/>
				<b style="font-size: 30px;">전체 선택</b>
				<div class="gap2"></div>
				<input type='checkbox' name="check" class="checkBoxInput"/> <span>빌려중고야? 이용약관 (필수)</span>
				<div class="gap2"></div>
				<input type='checkbox' name="check" class="checkBoxInput"/> <span>개인정보 수집 이용 동의 (필수)</span>
				<div class="gap2"></div>
				<input type='checkbox' name="check" class="checkBoxInput"/> <span>휴면 개인정보 분리보관 동의 (필수)</span>
				<div class="gap2"></div>
				<input type='checkbox' name="check" class="checkBoxInput"/> <span>위치정보 이용약관 동의 (필수)</span>
				<div class="gap2"></div>
				<input type='checkbox' name="check" class="checkBoxInput"/> <span>개인정보 수집 이용 동의 (선택)</span>
				<div class="gap2"></div>
				<input type='checkbox' name="check" class="checkBoxInput"/> <span>마케팅 수신 동의 (선택)</span>
				<div class="gap2"></div>
				<input type='checkbox' name="check" class="checkBoxInput"/> <span>개인정보 광고활용 동의 (선택)</span>
			</div>
			<div class="gap2"></div>
			<div class="gap2"></div>
			<div class="gap2"></div>
			<button class="completeBtn" onClick="userCreate()">회원가입 완료</button>
		</div>
	</form>
</body>
</html>