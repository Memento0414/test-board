<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<div>
			<c:choose>
				<c:when test="${sessionScope.logon}">
					<h4>${logonUser.id}님 로그온</h4>
				</c:when>
				<c:otherwise>
					<a href="/user/login">로그인</a>
				</c:otherwise>
			</c:choose>
		</div>
		<div>
			 <h3><b>디지털컨비전스</b>-<small>공공데이터융합 자바 웹개발자 양성 과정</small></h3>
		</div>
		
		<c:choose>
			<c:when test="${sessionScope.logon}">
				<div>
					<div>
						<h3>해당 글을 수정하시겠습니까?</h3>
					</div>
					<form action="/board/modify-task" method="post">
						<input type="hidden" name="id" value="${param.id }">
						<div>
							<button type="submit">수정하기</button>
						</div>
					</form>
				</div>
			</c:when>
			<c:otherwise>
				<div>
					<div>
						<h3>해당 글을 수정하시겠습니까?</h3>
					</div>
					<form action="/board/modify-task" method="post">
						<input type="hidden" name="id" value="${param.id }">
						<div>
							<h3>글 작성시 입력하신 비밀번호를 입력 해주세요.</h3>
							<input type="password" name="userPass" placeholder="비밀번호를 입력해주세요.">
						</div>
						<c:if test="${param.error eq 1 }">
							<p style="color: red;">비밀번호가 일치하지 않습니다. 다시 확인해주세요.</p>
						</c:if>
						<div>
							<button type="submit">수정하기</button>
						</div>
					</form>
				</div>
			</c:otherwise>
		</c:choose>
	</div>

</body>
</html>