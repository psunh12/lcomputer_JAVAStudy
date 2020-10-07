<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물</title>
</head>

<style>

	table{
		border-collapse:collapse;
	}
	table tr th {
		font-weight:700;
	}
	table tr td, table tr th {
		border:1px solid #818181;
		width:200px;
		text-align:center;
	}
	a{
		text-decoration:none;
		color:#000;
		font-weight:700;
	}
</style>
<body>
<h1>게시물 목록</h1>
<form action="write-list.do" name="user" method="post">
	<table>
	<tr>
		<th>No</th>
		<th>제목</th>
		<th>작성자</th>
		<th>날짜</th>
	</tr>
	<c:forEach items="${boardList}" var="item" varStatus="status">
		<tr>
			<td>${item.b_idx}</td>
			<td>${item.b_title}</td>
			<td>${item.b_writer}</td>
			<td>${item.b_date}</td>	
		</tr>
	</c:forEach>
	</table>
	</form>

</body>
</html>