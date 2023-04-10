<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/board/detail">
		<div>
			<c:choose>
				<c:when test="${sessionScope.logon }">
					<h4>${logonUser.id }님로그온</h4>
				</c:when>
				<c:otherwise>
					<a href="/user/login">로그인</a>
				</c:otherwise>
			</c:choose>
		</div>
		<div>
			 <h3><b>디지털컨비전스</b>-<small>공공데이터융합 자바 웹개발자 양성 과정</small></h3>
		</div>
	</form>
	<div>
		<div>
			<h4>${findWriting.title }</h4>
		</div>
		<div>
			<p>
			<h5>${findWriting.userId }
				|
				<fmt:formatDate value="${findWriting.dates }"
					pattern="yyy-MM-dd  HH:mm:ss" />
			</h5>
			<h5>조회 ${findWriting.views } | 추천 0</h5>
			<p>
		</div>
		<div></div>
		<div style="height: 40vh;">${findWriting.content }</div>

	</div>
	<div>
		<c:choose>
			<c:when test="${sesion.Scope.logon }">
				<div style="width: 100px;">
					<a href="/board/modify?id=${findWriting.id }"><button
							type="submit" id="btn">수정</button></a>
				</div>
				<div style="width: 100px;">
					<a href="/board/delete?id=${findWriting.id }"><button
							type="submit" id="btn">삭제</button></a>
				</div>
				<c:choose>
					<c:when test="${likeUser }">
						<div>
							<button type="submit">추천</button>
						</div>
					</c:when>
						<c:otherwise>
							<div>
							<a href="/board/like?id=${findWriting.id }"><button type="submit" style="background-color: gray;">추천</button></a>
							</div>
						</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<div style="width: 100px;">
				<a href="/board/modify?id=${findWriting.id }"><button
					type="submit" id="btn">수정</button></a>
				</div>
				<div style="width: 100px;">
				<a href="/board/delete?id=${findWriting.id }">
				<button type="submit" id="btn">삭제</button></a>
			</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>