<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="/resource/css/style.css"/>
</head>
<body>
	<div class="login-form">
	   <form action="/user/login-task" method="post" class="form-login">
		 <fieldset>
			 <input type="text" name="id" placeholder="아이디" class="input-text" required> 	
			 <input type="password" name="pass" placeholder="비밀번호" class="input-pass" required> 
			 <input type="submit" value="로그인" class="input-submit">
		 </fieldset>
		  	<c:if test="${param.cause eq 'error' }">
		  		<span style="color: red">아이디 혹은 비밀번호가 일치가 않습니다</span>
		  	</c:if>
	   </form>
	</div>
</body>
</html>