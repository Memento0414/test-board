<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core"prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/board/modify-task" method="post" class="form-write">
		<fieldset>
			<legend>글등록</legend>
			<div>
				<c:choose>
					<c:when test="${sessionScope.logon eq true }">
						<input type="text" name="userId" value="${write.userId}"/>
						<input type="text" name="title" placeholder="제목을 입력해주세요"/>
						<textarea name="content"placeholder="내용을 입력해주세요"></textarea>

					</c:when>
					<c:otherwise>
						<label for="userInfo">
						<input type="text" name="userId" id="userInfo"placeholder="작성자"/>
						<input type="password" name="userPass" id="userInfo"value=""/>
						</label>
						<input type="text" name="title" placeholder="제목을 입력해주세요"/>
						<textarea name="content"placeholder="내용을 입력해 주세요."></textarea>
						
					</c:otherwise>

				</c:choose>
				<input type="submit" value="작성" class="input-write"> <input
					type="reset" value="취소" class="input-write">
			</div>
		</fieldset>
		</form>
</body>
</html>