<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 답변쓰기</title>
</head>
<body>
	<h2>자유게시판 답변쓰기</h2>
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form action="reply.do" method="post">
		<input type="hidden" name="bid" value="${reply_view.bid}">
		<input type="hidden" name="bgroup" value="${reply_view.bgroup}">
		<input type="hidden" name="bstep" value="${reply_view.bstep}">
		<input type="hidden" name="bindent" value="${reply_view.bindent}">
		<tr>
			<td>번호</td>
			<td>${reply_view.bid}</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${reply_view.bhit}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="bname" size="50" value="${reply_view.bname}"></td>
		</tr>
		<tr>
			<td>글제목</td>
			<td><input type="text" name="btitle" size="50" value="${reply_view.btitle}"></td>
		</tr>
		<tr>
			<td>글내용</td>
			<td><textarea name="bcontent" rows="10">${reply_view.bcontent}</textarea></td>
		</tr>
		<tr>
		</tr>
			<td><input type="submit" value="답변쓰기"></td>
			<td><a href="list.do">목록보기</a></td>	
		</form>
	</table>
</body>
</html>