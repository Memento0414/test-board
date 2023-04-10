<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<c:choose>
			<c:when test="${sessionScope.logon }">
				<h4>${logonUser.id }님 로그온</h4>
			</c:when>
			<c:otherwise>
				<a href="/user/login">로그인</a>
			</c:otherwise>
		</c:choose>
	</div>
	<div>
		<h3>
			<b>디지털컨비전스</b>-공공데이터융합 자바 웹개발자 양성 과정
		</h3>
	</div>
	<%-- 삭제 페이지 --%>
	<c:choose>
		<c:when test="${sessionScope.logon }">
			<h3>해당 글을 삭제 하시겠습니까?</h3>
			<form action="/board/delete-task" method="post">
					<input type="hidden" name="id" value="${param.id }">
					<button type="submit">삭제하기</button>
			</form>	
		</c:when>
	<%--비로그인일 때--%>
		<c:otherwise>
			<h3>해당 글을 삭제하시겠습니까?</h3>
			<form action="/board/delete-task" method="post">
					<input type="password" name="userPass" placeholder="비밀번호를 입력해주세요.">
					<c:if test="${param.error eq 1 }">
						<span style="color: red">비밀번호가 일치하지 않습니다. 다시 확인 해주세요</span>
					</c:if>
						<button type="submit">삭제하기</button>
			</form>	
		</c:otherwise>
	</c:choose>
</body>
</html>