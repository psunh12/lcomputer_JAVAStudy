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
		width:600px;
		height:50px;
		margin:10px auto;
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
	<td colspan = "4" >전체 게시글 수 : ${pagination.totalCount }</td>
</tr>
<tr>
	<th>No</th>
	<th>제목</th>
	<th>작성자</th>
	<th>게시글 내용</th>
</tr>

<c:forEach items= "${list1}" var="item" varStatus = "status" >

	<tr>
		<td><a href="board-detail.do?b_idx=${item.b_idx}">${item.rownum}</a></td>
		<td>${item.b_title}</td>
		<td>${item.b_writer}</td>
		<td>${item.b_content}</td>	
	</tr>
</c:forEach>
</table>
	<div>
		<ul>
			<c:choose>
				<c:when test="${pagination.prevPage ge 5 }">
					<li>
						<a href="write-list.do?page=${pagination.prevPage }" >
						◀
						</a>
					</li>
				</c:when>
			</c:choose>
			
			<c:forEach var="i" begin="${pagination.startPage }"	end="${pagination.endPage }" step="1">
				
				<c:choose>
					<c:when test="${pagination.page eq i }">
					
						<li style="background-color:#ededed;">
							<a href="write-list.do">${i}</a>
						</li>
					</c:when>
					
					<c:when test="${ pagination.page ne i }">
						<li>
							<a href="write-list.do?page=${i}">${i}</a>
						</li>
					</c:when>
				</c:choose>
			</c:forEach>
			
			<c:choose>
				<c:when test="${ pagination.nextPage lt pagination.lastPage }">
					<li style="">	
						<a href="write-list.do?page=${pagination.nextPage }">▶</a>
					</li>		
				</c:when>					
			</c:choose>
			
			<%-- <li>
				<a href = "write-list.do?page=${pagination.nextPage}">▶</a> 
				</li> --%>	
		</ul>
	</div>
</body>
</html>