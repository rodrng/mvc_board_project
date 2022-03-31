<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글내용 보기</title>
</head>
<body>
	<h2>글 내용 보기</h2>
	<hr>
	<table width="500" cellpadding="0" cellspacing="0" border=1>
		<form action="modify.do" mehtod="post">
		<input type="hidden" name="bid" value="${content_view.bid}">
		<tr>
			<td>번호</td>
			<td>${content_view.bid}</td>
		</tr>
		<tr>
			<td>글쓴이</td>
			<td><input type="text" name="bname" value="${content_view.bname}"></td>
		</tr>
		<tr>
			<td>글제목</td>
			<td><input type="text" name="btitle" value="${content_view.btitle}"></td>
		</tr>
		<tr>
			<td>글내용</td>
			<td><textarea rows ="10" name="bcontent">${content_view.bcontent}</textarea></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="글수정"> &nbsp;&nbsp;
			<a href="list.do">목록보기</a> &nbsp;&nbsp;<a href="delete.do?bid=${content_view.bid}">글삭제</a>
			 &nbsp;&nbsp;<a href="reply_view.do?bid=${content_view.bid}">답변</a></td>
		</tr>		
		</form>
	</table>
</body>
</html>