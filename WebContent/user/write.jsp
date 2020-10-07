<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글작성</title>
</head>
<body>
<h1>글작성</h1>

<form action="user-write-process.do" name="user" method="post">
	<input type="hidden" name="u_idx" value="${sessionScope.user.u_idx }">
	<input type="hidden" name="writer" value="${sessionScope.user.u_name }">
	<p> 작성자 : ${sessionScope.user.u_name }</p>
	<P> 제목 : <input type="text" name="title" placeholder="제목을 입력하세요."></P>
	<textarea  name="content" rows="10" cols="100" placeholder="내용을 입력하세요"></textarea>
	<input type="submit" value="글작성">	
</form>
	

	
</body>
</html>