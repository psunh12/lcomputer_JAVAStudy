<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물</title>
</head>

<style>
	h1{
		text-align:center;
	}

	table{
		border-collapse:collapse;
		margin:40px auto;
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
	
	ul{
		width:400px;
		height:50px;
		margin:10px;
		}
	li{
		list-style:none;
		width:50px;
		line-height:50px;
		border:1px solid #ededed;
		float:left;
		text-align:center;
		margin:0 5px;
		border-radius:5px;
		}	
</style>
<body>
<h1>게시물 목록</h1>
<table>
<tr>
	<td colspan = "4" >전체 게시글 수 : ${boardcount }</td>
</tr>
<tr>
	<th>No</th>
	<th>제목</th>
	<th>작성자</th>
	<th>날짜</th>
</tr>

<c:forEach items= "${list1}" var="item" varStatus = "status" >

	<tr>
		<td><a href="board-detail.do?b_idx=${item.b_idx}">${item.b_idx}</a></td>
		<td>${item.b_title}</td>
		<td>${item.b_writer}</td>
		<td>${item.b_date}</td>	
	</tr>
</c:forEach>
</table>
</body>
</html>