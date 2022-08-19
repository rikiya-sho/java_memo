<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>編集画面 - メモアプリ</title>
</head>
<body>
	<h1>メモ編集</h1>
	<p><%= request.getAttribute("message") %></p>
	
	<form action="update" method="get" >
		<input type="hidden" name="id" value='<%= request.getAttribute("id") %>'>
				<label for="title">タイトル</label><br>
		<input type="text" name="title" value='<%= request.getAttribute("title") %>'>
		<br><br>
		<label for="content">本文</label><br>
		<textarea name="content" cols="40" rows="10">
			<%= request.getAttribute("content") %>
		</textarea>
		<br>
		<button type="submit">保存する</button>
	</form>
	<br>
	<p><a href='show?id=<%= request.getAttribute("id") %>'>キャンセル</a></p>
</body>
</html>