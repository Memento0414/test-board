<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resource/css/style.css">
</head>

<body>
	<div style="display: flex; justify-content: flex-end;">
		<c:choose>
			<c:when test="${sessionScope.logon }">
				<div>
					${logonUser.id }님환영합니다.
				</div>
				<div>
				<a href="/user/logout">로그아웃</a>
				</div>
			</c:when>
			<c:otherwise>
				<a href="/user/login">로그인</a>
			</c:otherwise>
		</c:choose>
	</div>
	<div>
		<h2>디지털컨버전스 - 공공데이터융합 자바 웹개발자 양성 과정</h2>
		<h3>개발공부를 하면서 몰랐던 것이나 고민이 있다면 글을 남겨주세요</h3>
	</div>
		<%--글 정렬 기준 처리 --%>
		<div>
			<c:choose>
					<c:when test="">
						<li><a style="color: blue;">등록순</a></li>
					</c:when>
					<c:when test="">
						<li><a href="/index?listsort">등록순</a></li>
					</c:when>
				</c:choose>
				
				<c:choose>
					<c:when test="">
						<li><a style="color: blue;">조회순</a></li>
					</c:when>
					<c:when test="">
						<li><a href="">조회순</a></li>
					</c:when>
				</c:choose>
				
				<c:choose>
					<c:when test="">
						<li><a style="color: blue;">추천순</a></li>
					</c:when>
					<c:when test="">
						<li><a href="">추천순</a></li>
					</c:when>
				</c:choose>
		</div>

	<div>
		<a href="/board/write">글쓰기 </a>
	</div>
			
	
		<%--글목록 처리--%>	
	<div>
		<table>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회</th>
				<th>추천</th>
			</tr>
			<c:forEach items="${list}" var="li" varStatus="num">
				<tr>
					<td>${param.page eq 1 ? num.count : (writeNum-10) + num.count}</td>
					<td><a href="/board/detail?id=${li.id}">${li.title }</a></td>
					<td>${li.userId }</td>
					<td><fmt:formatDate value="${li.dates}" pattern="yy-MM-dd" /></td>
					<td>${li.views }</td>
					<td>1</td>
				</tr>
			</c:forEach>
		</table>
		<%-- prev 처리 --%>
		<div>
		<div>
			<c:set var="currentPage" value="${empty param.page ? 1: param.page }"/>
			
				<c:if test="${existPrev }">
					<a href="/index?page=${start - 1}"><b>이전</b></a>
				</c:if>
		</div>
		<div>
			<c:forEach begin="${start }" end="${last}" var="idx">
			<c:choose>
				<c:when test="${p eq currentPage }">
					<b style="color: green">${p }</b>
				</c:when>
				<c:otherwise>
					<a href="/index?page=${idx}">${idx }</a>
				</c:otherwise>
				
			</c:choose>
			</c:forEach>
		</div>

		<%-- next 처리 --%>
		<div>
			<c:choose>
				<c:when test="${existNext }">
					<a href="/index?page=${last + 1}">다음</a>
				

				</c:when>
				<c:otherwise>
					<a><i style="color: blue"></i></a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>

</body>

</html>